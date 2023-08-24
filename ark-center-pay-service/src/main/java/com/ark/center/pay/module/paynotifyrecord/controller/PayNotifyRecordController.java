package com.ark.center.pay.module.paynotifyrecord.controller;

import com.ark.center.pay.module.paynotifyrecord.dto.request.PayNotifyRecordPageQueryReqDTO;
import com.ark.center.pay.module.paynotifyrecord.dto.request.PayNotifyRecordUpdateReqDTO;
import com.ark.center.pay.module.paynotifyrecord.dto.response.PayNotifyRecordRespDTO;
import com.ark.center.pay.module.paynotifyrecord.service.PayNotifyRecordService;
import com.ark.component.dto.PageResponse;
import com.ark.component.dto.SingleResponse;
import com.ark.component.validator.ValidateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;
import com.ark.component.web.base.BaseController;

/**
 * <p>
 * 支付结果通知记录 前端控制器
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Tag(name = "支付结果通知记录", description = "支付结果通知记录")
@Validated
@RestController
@RequestMapping("/v1/pay-notify-record")
public class PayNotifyRecordController extends BaseController {

    private final PayNotifyRecordService payNotifyRecordService;

    public PayNotifyRecordController(PayNotifyRecordService payNotifyRecordService) {
        this.payNotifyRecordService = payNotifyRecordService;
    }

    @Operation(summary = "创建支付结果通知记录")
    @PostMapping("/create")
    public SingleResponse<Long> create(@RequestBody @Validated PayNotifyRecordUpdateReqDTO reqDTO) {
        return SingleResponse.ok(payNotifyRecordService.createPayNotifyRecord(reqDTO));
    }

    @Operation(summary = "修改支付结果通知记录")
    @PostMapping("/update")
    public SingleResponse<Long> update(@RequestBody @Validated(ValidateGroup.Update.class) PayNotifyRecordUpdateReqDTO reqDTO) {
        return SingleResponse.ok(payNotifyRecordService.updatePayNotifyRecord(reqDTO));
    }

    @Operation(summary = "查询支付结果通知记录分页列表")
    @PostMapping("/page")
    public SingleResponse<PageResponse<PayNotifyRecordRespDTO>> pageList(@RequestBody @Validated PayNotifyRecordPageQueryReqDTO queryDTO) {
        return SingleResponse.ok(payNotifyRecordService.getPageList(queryDTO));
    }

    @Operation(summary = "查询支付结果通知记录详情")
    @ApiImplicitParam(name = "id", value = "id")
    @GetMapping("/info")
    public SingleResponse<PayNotifyRecordRespDTO> info(@RequestParam(required = false) @NotNull(message = "id不能为空") Long id) {
        return SingleResponse.ok(payNotifyRecordService.getPayNotifyRecordInfo(id));
    }


}
