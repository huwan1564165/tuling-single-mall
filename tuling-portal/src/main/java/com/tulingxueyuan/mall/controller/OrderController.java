package com.tulingxueyuan.mall.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.common.exception.ApiException;
import com.tulingxueyuan.mall.component.TradePayProp;
import com.tulingxueyuan.mall.dto.ConfirmOrderDTO;
import com.tulingxueyuan.mall.dto.OrderDetailDTO;
import com.tulingxueyuan.mall.dto.OrderListDTO;
import com.tulingxueyuan.mall.dto.OrderParamDTO;
import com.tulingxueyuan.mall.modules.oms.model.OmsOrder;
import com.tulingxueyuan.mall.modules.oms.service.OmsOrderService;
import com.tulingxueyuan.mall.modules.oms.service.TradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "OrderController" ,description = "订单服务接口")
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    OmsOrderService orderService;
    @Autowired
    TradeService tradeService;
    @Autowired
    TradePayProp tradePayProp;
    /**
     *初始化确认订单的商品和收货地址信息
     * .post(
     *           "/order/generateConfirmOrder",
     *           Qs.stringify({ itemIds: constStore.itemids },
     */
    @RequestMapping(value = "/generateConfirmOrder",method = RequestMethod.POST)
    public CommonResult generateConfirmOrder(
            @RequestParam("itemIds") List<Long> ids
    ){
        ConfirmOrderDTO confirmOrderDTO=orderService.generateConfirmOrder(ids);
        return CommonResult.success(confirmOrderDTO);
    }
    /**
     * 生成订单(下单)
     * this.axios.post("/order/generateOrder"
     */
    @RequestMapping(value = "/generateOrder",method = RequestMethod.POST)
    public CommonResult generateOrder(@RequestBody OrderParamDTO orderParamDTO){
        OmsOrder omsOrder = orderService.generateOrder(orderParamDTO);
        return CommonResult.success(omsOrder.getId());

    }
    /**
     * 读取下单成功后的订单详情
     * this.axios.get(`/order/orderDetail?orderId=${this.orderId}`)
     */
    @RequestMapping(value = "orderDetail")
    public CommonResult getOrderDetail(@RequestParam("orderId") Long id){
        OrderDetailDTO dto=orderService.getOrderDetail(id);
        return CommonResult.success(dto);
    }
    /**
     * 我的订单列表
     * this.axios.post('/order/list/userOrder',Qs.stringify({
     *             pageSize:10,
     *             pageNum:this.pageNum
     */
    @RequestMapping(value = "/list/userOrder'",method = RequestMethod.POST)
    public CommonResult getMyOrders(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        IPage<OrderListDTO> myOrders=orderService.getMyOrders(pageSize,pageNum);
        return CommonResult.success(myOrders);
    }

    /**
     * 生成当面付二维码
     * this.axios.post('/order/tradeQrCode',
     *orderId:this.orderId,
     *           payType:1
     */
    @ApiOperation("支付接口，只实现支付宝支付，微信支付暂未实现")
    @ApiImplicitParams({@ApiImplicitParam(name = "orderId",value = "订单id"),
            @ApiImplicitParam(name = "payType",value = "支付类型：1.支付宝2.微信",allowableValues = "1,2",dataType = "Integer")})
    @RequestMapping(value = "/tradeQrCode",method = RequestMethod.POST)
    private CommonResult tradeQrCode(@RequestParam("orderId") Long orderId,
                                     @RequestParam("payType") Integer payType){
        if(payType>2||payType<0){
            throw new ApiException("支付类型参数错误!");
        }
        return tradeService.tradeQrCode(orderId,payType);
    }



    @RequestMapping(value = "/paySuccess/{payType}",method = RequestMethod.POST)
    public CommonResult getMyOrders(
            @PathVariable("payType") Integer payType,
            HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {

        System.out.println("支付成功！");
//获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            if(!name.toLowerCase().equals("sign_type")) {
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
        }

        String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiPyYKVKr+nORo5PgigJwMU/y0SQX53JEmk008EtOSFBG9WjpAcTf1Nd4Ek4e08BRTAGax9Okj3TK/8tuG0L07aB/vf3nNY5WfBiI6HDWPMXBaTYQ7l939YrbS4ZeRBja9Xa4D9fCJEQ09B+lvKRbYc8T0aTW9s9tAY9iNPyNI5Q2elrMkqU6kQgwJ8tEGvK5G6BUZ5CyBHn14No05qijYx40BV8wi8GHQc2tFJNr9rfxW+hkUImOkiN0NME4mgksQ4POsBbz2GjUWGF8OM+SbwryOafoy7f/gu3nobobwHPy27b0FaJBbWh3GRKx0waU9DKSoqQ2ajnVsYc9ZRUyzwIDAQAB";

        // 验签  ：去除sign和sign_type 参数 进行验签， checkV1 会在方法中去除，CheckV2不会去除sign_type，所以要手动排除
        boolean signVerified = AlipaySignature.rsaCheckV2(params, alipay_public_key, "utf-8","RSA2"); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            // 订单id
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            if(StrUtil.isNotBlank(out_trade_no) && NumberUtil.isNumber(out_trade_no)){
                Long orderId=Long.parseLong(out_trade_no);
                orderService.paySuccess(orderId,payType);
                log.info("支付成功");
            }
        }else {
            System.out.println("验签失败");
        }
        return null;
    }

}
