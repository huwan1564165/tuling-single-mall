package com.tulingxueyuan.mall.modules.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tulingxueyuan.mall.common.api.ResultCode;
import com.tulingxueyuan.mall.common.exception.Asserts;
import com.tulingxueyuan.mall.dto.AddCarDTO;
import com.tulingxueyuan.mall.dto.CartItemStockDTO;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.tulingxueyuan.mall.modules.oms.mapper.OmsCartItemMapper;
import com.tulingxueyuan.mall.modules.oms.service.OmsCartItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import com.tulingxueyuan.mall.modules.pms.service.PmsSkuStockService;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2025-05-22
 */
@Service
public class OmsCartItemServiceImpl extends ServiceImpl<OmsCartItemMapper, OmsCartItem> implements OmsCartItemService {
    @Autowired
    UmsMemberService memberService;
    @Autowired
    PmsSkuStockService skuStockService;
    @Autowired
    PmsProductService productService;
    @Autowired
    OmsCartItemMapper cartItemMapper;

    @Override
    public Boolean add(AddCarDTO addCarDTO) {
        OmsCartItem omsCartItem = new OmsCartItem();
        BeanUtils.copyProperties(addCarDTO, omsCartItem);

        UmsMember currentMember = memberService.getCurrentMember();
        if(currentMember==null){
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }

        omsCartItem.setMemberId(currentMember.getId());

        //判断一个商品、sku、用户 下是否添加了重复的购物车
        OmsCartItem cartItem = getCartItem(omsCartItem.getProductId(), omsCartItem.getProductSkuId(), omsCartItem.getMemberId());
        //新增
        if(cartItem == null){
        omsCartItem.setMemberNickname(currentMember.getNickname());


        //查询sku
        PmsSkuStock sku = skuStockService.getById(omsCartItem.getProductSkuId());
        if(sku == null) Asserts.fail(ResultCode.VALIDATE_FAILED);
        omsCartItem.setPrice(sku.getPrice());
        omsCartItem.setSp1(sku.getSp1());
        omsCartItem.setSp2(sku.getSp2());
        omsCartItem.setSp3(sku.getSp3());
        omsCartItem.setProductPic(sku.getPic());
        omsCartItem.setProductSkuCode(sku.getSkuCode());

        PmsProduct product = productService.getById(omsCartItem.getProductId());
        if(product == null) Asserts.fail(ResultCode.VALIDATE_FAILED);
        omsCartItem.setProductName(product.getName());
        omsCartItem.setProductBrand(product.getBrandName());
        omsCartItem.setProductSn(product.getProductSn());
        omsCartItem.setProductSubTitle(product.getSubTitle());
        omsCartItem.setProductCategoryId(product.getProductCategoryId());
        omsCartItem.setCreateDate(new Date());
        omsCartItem.setModifyDate(new Date());
//            System.out.println("productId类型: " + addCarDTO.getProductId().getClass().getName());
//            System.out.println("productSkuId类型: " + addCarDTO.getProductSkuId().getClass().getName());
//            System.out.println("quantity类型: " + addCarDTO.getQuantity().getClass().getName());
        return baseMapper.insert(omsCartItem)>0;
        }
        //修改
        else {
            cartItem.setQuantity(omsCartItem.getQuantity() + addCarDTO.getQuantity());
            cartItem.setModifyDate(new Date());
            UpdateWrapper<OmsCartItem> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda()
                    .set(OmsCartItem::getQuantity, cartItem.getQuantity())
                    .set(OmsCartItem::getModifyDate, cartItem.getModifyDate())
                    .eq(OmsCartItem::getId,cartItem.getId());
//            System.out.println("productId类型: " + addCarDTO.getProductId().getClass().getName());
//            System.out.println("productSkuId类型: " + addCarDTO.getProductSkuId().getClass().getName());
//            System.out.println("quantity类型: " + addCarDTO.getQuantity().getClass().getName());
            return baseMapper.update(cartItem, updateWrapper)>0;

        }

    }
    /**
     * 初始化状态栏的购物车商品数量
     * this.axios.get('/car/products/sum')
     */
    @Override
    public Integer getCarProductSum() {
        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("sum(quantity) as total")
                .lambda().eq(OmsCartItem::getMemberId, memberService.getCurrentMember().getId());
        List<Map<String, Object>> list = baseMapper.selectMaps(queryWrapper);
        if (list!=null&&list.size()==1){
            Map<String, Object> map = list.get(0);
            if(map.get("total")!=null){
                return Integer.parseInt(map.get("total").toString());
            }
        }
        return 0;

    }

    /**
     * 初始化购物车数据
     * @return
     */
    @Override
    public List<CartItemStockDTO> getList() {
        //1.当前用户
        UmsMember currentMember = memberService.getCurrentMember();
        List<CartItemStockDTO> list = cartItemMapper.getCartItemStork(currentMember.getId());

        return list;
    }
    /**
     * 更新商品的数量
     * this.axios.post('/car/update/quantity',Qs.stringify({
     * id:item.id,
     * quantity:item.quantity   当前数量
     * })
     */
    @Override
    public Boolean updateQuantity(Long id, Long quantity) {
        UpdateWrapper<OmsCartItem> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(OmsCartItem::getQuantity, quantity)
                .eq(OmsCartItem::getId, id);
        return this.update(updateWrapper);
    }

    @Override
    public Boolean delete(Long ids) {
        return this.removeById(ids);
    }

    /**
     * 判断一个商品、sku、用户 下是否添加了重复的购物车
     * @param productId
     * @param skuId
     * @param memberId
     * @return
     */
    public OmsCartItem getCartItem(Long productId,Long skuId,Long memberId) {
        QueryWrapper<OmsCartItem> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(OmsCartItem::getProductId, productId)
                .eq(OmsCartItem::getProductSkuId, skuId)
                .eq(OmsCartItem::getMemberId, memberId);

        return baseMapper.selectOne(wrapper);

    }
}
