package com.kt.cloud.pay.module.pay.controller;

import com.alibaba.fastjson.JSONObject;
import com.kt.cloud.pay.api.PayApi;
import com.kt.cloud.pay.api.dto.request.PayOrderCreateReqDTO;
import com.kt.cloud.pay.api.dto.request.PayOrderPageQueryReqDTO;
import com.kt.cloud.pay.api.dto.response.PayOrderCreateRespDTO;
import com.kt.cloud.pay.module.pay.service.PayOrderService;
import com.kt.component.dto.PageResponse;
import com.kt.component.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;
import com.kt.component.web.base.BaseController;

import java.util.Map;

/**
 * <p>
 * 支付订单表 前端控制器
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Api(tags = "支付订单表")
@Validated
@RestController
@RequestMapping("/v1/pay")
@RequiredArgsConstructor
public class PayController extends BaseController implements PayApi {

    private final PayOrderService payOrderService;

    @ApiOperation(value = "创建支付单")
    @PostMapping("/order/create")
    public SingleResponse<PayOrderCreateRespDTO> createPayOrder(@RequestBody @Validated PayOrderCreateReqDTO reqDTO) {
        return SingleResponse.ok(payOrderService.createPayOrder(reqDTO));
    }

    @ApiOperation(value = "获取支付单状态")
    @GetMapping("/order/status")
    public SingleResponse<Integer> getPayOrderStatus(@RequestParam Long id) {
        return SingleResponse.ok(payOrderService.getPayOrderStatus(id));
    }

    @ApiOperation(value = "订单通知")
    @PostMapping("/notify")
    public SingleResponse<Map<String, Object>> notify(@RequestBody JSONObject reqDTO) {
        Map<String, Object> notify = payOrderService.notify(reqDTO);
        return SingleResponse.ok(notify);
    }

    @ApiOperation(value = "查询支付订单表分页列表")
    @PostMapping("/page")
    public SingleResponse<PageResponse<PayOrderCreateRespDTO>> pageList(@RequestBody @Validated PayOrderPageQueryReqDTO queryDTO) {
        return SingleResponse.ok(payOrderService.getPageList(queryDTO));
    }

    @ApiOperation(value = "查询支付订单表详情")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    @GetMapping("/info")
    public SingleResponse<PayOrderCreateRespDTO> info(@RequestParam(required = false) @NotNull(message = "id不能为空") Long id) {
        return SingleResponse.ok(payOrderService.getPayOrderInfo(id));
    }


}
