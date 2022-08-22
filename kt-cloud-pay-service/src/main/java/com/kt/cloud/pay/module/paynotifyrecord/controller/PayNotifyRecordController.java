package com.kt.cloud.pay.module.paynotifyrecord.controller;

import com.kt.cloud.pay.module.paynotifyrecord.dto.request.PayNotifyRecordUpdateReqDTO;
import com.kt.cloud.pay.module.paynotifyrecord.dto.request.PayNotifyRecordPageQueryReqDTO;
import com.kt.cloud.pay.module.paynotifyrecord.dto.response.PayNotifyRecordRespDTO;
import com.kt.cloud.pay.module.paynotifyrecord.service.PayNotifyRecordService;
import com.kt.component.dto.PageResponse;
import com.kt.component.dto.SingleResponse;
import com.kt.component.validator.ValidateGroup;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;
import com.kt.component.web.base.BaseController;

/**
 * <p>
 * 支付结果通知记录 前端控制器
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Api(tags = "支付结果通知记录")
@Validated
@RestController
@RequestMapping("/v1/pay-notify-record")
public class PayNotifyRecordController extends BaseController {

    private final PayNotifyRecordService payNotifyRecordService;

    public PayNotifyRecordController(PayNotifyRecordService payNotifyRecordService) {
        this.payNotifyRecordService = payNotifyRecordService;
    }

    @ApiOperation(value = "创建支付结果通知记录")
    @PostMapping("/create")
    public SingleResponse<Long> create(@RequestBody @Validated PayNotifyRecordUpdateReqDTO reqDTO) {
        return SingleResponse.ok(payNotifyRecordService.createPayNotifyRecord(reqDTO));
    }

    @ApiOperation(value = "修改支付结果通知记录")
    @PostMapping("/update")
    public SingleResponse<Long> update(@RequestBody @Validated(ValidateGroup.Update.class) PayNotifyRecordUpdateReqDTO reqDTO) {
        return SingleResponse.ok(payNotifyRecordService.updatePayNotifyRecord(reqDTO));
    }

    @ApiOperation(value = "查询支付结果通知记录分页列表")
    @PostMapping("/page")
    public SingleResponse<PageResponse<PayNotifyRecordRespDTO>> pageList(@RequestBody @Validated PayNotifyRecordPageQueryReqDTO queryDTO) {
        return SingleResponse.ok(payNotifyRecordService.getPageList(queryDTO));
    }

    @ApiOperation(value = "查询支付结果通知记录详情")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    @GetMapping("/info")
    public SingleResponse<PayNotifyRecordRespDTO> info(@RequestParam(required = false) @NotNull(message = "id不能为空") Long id) {
        return SingleResponse.ok(payNotifyRecordService.getPayNotifyRecordInfo(id));
    }


}
