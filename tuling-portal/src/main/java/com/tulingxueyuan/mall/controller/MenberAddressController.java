package com.tulingxueyuan.mall.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberReceiveAddress;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "MenberAddressController" ,description = "收货地址服务接口")
@RequestMapping("/member/address")
public class MenberAddressController {
    @Autowired
    UmsMemberReceiveAddressService addressService;
    /**
     *添加
     * (method = "post"), (url = "/member/address/add");
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public CommonResult add(@RequestBody UmsMemberReceiveAddress address){
        Boolean result=addressService.add(address);
        if(result){
            return CommonResult.success(result);
        }else {
            return CommonResult.failed();
        }

    }
    /**
     *
     * (method = "post"), (url = `/member/address/update/${checkedItem.id}`)
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public CommonResult edit(
            @PathVariable Long id,
            @RequestBody UmsMemberReceiveAddress address){
        address.setId(id);
        Boolean result=addressService.edit(address);
        if(result){
            return CommonResult.success(result);
        }else {
            return CommonResult.failed();
        }

    }
    /**
     *
     * (method = "post"), (url = `/member/address/delete/${checkedItem.id}`)
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id){
        Boolean result=addressService.delete(id);
        if(result){
            return CommonResult.success(result);
        }else {
            return CommonResult.failed();
        }

    }
    /**
     *
     * this.axios.get("/member/address/list").
     */
    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public CommonResult list(){
        List<UmsMemberReceiveAddress> list=addressService.listByMemberId();
        return CommonResult.success(list);

    }

}
