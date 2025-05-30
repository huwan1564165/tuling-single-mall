package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.AddCarDTO;
import com.tulingxueyuan.mall.dto.CartItemStockDTO;
import com.tulingxueyuan.mall.modules.oms.model.OmsCartItem;
import com.tulingxueyuan.mall.modules.oms.service.OmsCartItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "CarController" ,description = "购物车服务接口")
@RequestMapping("/car")
public class CarController {
    @Autowired
    OmsCartItemService cartItemService;
    /**
     * post("/car/add", {
     *           productId: this.id,
     *           productSkuId: this.skuId,
     *           quantity: 1,
     *         })
     *
     * @return
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public CommonResult add(@RequestBody AddCarDTO addCarDTO){
        Boolean result = cartItemService.add(addCarDTO);
        if(result){
            return CommonResult.success(result);
        }else {
            return CommonResult.failed();
        }
    }
    /**
     * 初始化状态栏的购物车商品数量
     * this.axios.get('/car/products/sum')
     */
    @RequestMapping(value = "/products/sum" ,method = RequestMethod.GET)
    public CommonResult getCarProductSum(){
        Integer count=cartItemService.getCarProductSum();
        return CommonResult.success(count);
    }
    /**
     * 获取购物车初始化
     * this.axios.get('/car/list')
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult getList(){
        List<CartItemStockDTO>  list= cartItemService.getList();
        return CommonResult.success(list);
    }
    /**
     * 更新商品的数量
     * this.axios.post('/car/update/quantity',Qs.stringify({
     *             id:item.id,
     *             quantity:item.quantity   当前数量
     *           })
     */
    @RequestMapping(value = "/update/quantity",method = RequestMethod.POST)
    public CommonResult updateQuantity(
            @RequestParam Long id,
            @RequestParam Long quantity){
        Boolean result=cartItemService.updateQuantity(id,quantity);
        if(result){
            return CommonResult.success(result);
        }else {
            return CommonResult.failed();
        }
    }
    /**
     * 删除
     * this.axios.post('/car/delete',Qs.stringify({
     *             ids:item.id
     *           })
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public CommonResult delete(
            @RequestParam Long ids){
        Boolean result=cartItemService.delete(ids);
        if(result){
            return CommonResult.success(result);
        }else {
            return CommonResult.failed();
        }
    }
}
