package com.ark.center.pay.api;

import com.ark.center.pay.api.dto.request.PayOrderCreateReqDTO;
import com.ark.center.pay.api.dto.response.PayOrderCreateRespDTO;
import com.ark.component.dto.SingleResponse;
import com.ark.component.microservice.rpc.exception.FeignCommonErrorDecoder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "${ark.center.pay.service.name:pay}",
        path = "/v1/pay",
        url = "${ark.center.pay.service.uri:}",
        dismiss404 = true,
        configuration = FeignCommonErrorDecoder.class
)
public interface PayApi {

    @ApiOperation(value = "创建支付单")
    @PostMapping("/order/create")
    SingleResponse<PayOrderCreateRespDTO> createPayOrder(@RequestBody @Validated PayOrderCreateReqDTO reqDTO);
}
