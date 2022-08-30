package com.kt.cloud.pay.api;

import com.kt.cloud.pay.api.dto.request.PayOrderCreateReqDTO;
import com.kt.cloud.pay.api.dto.response.PayOrderCreateRespDTO;
import com.kt.component.dto.SingleResponse;
import com.kt.component.microservice.rpc.exception.FeignCommonErrorDecoder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "${kt.cloud.pay.service.name:pay}",
        path = "/v1/pay",
        url = "${kt.cloud.pay.service.uri:}",
        decode404 = true,
        configuration = FeignCommonErrorDecoder.class
)
public interface PayApi {

    @ApiOperation(value = "创建支付单")
    @PostMapping("/order/create")
    SingleResponse<PayOrderCreateRespDTO> createPayOrder(@RequestBody @Validated PayOrderCreateReqDTO reqDTO);
}
