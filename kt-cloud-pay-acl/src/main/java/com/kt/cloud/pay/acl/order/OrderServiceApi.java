package com.kt.cloud.pay.acl.order;

import com.kt.cloud.order.api.OrderApi;
import com.kt.component.microservice.rpc.exception.FeignCommonErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "${kt.cloud.order.service.name:order}",
        path = "/v1/order",
        url = "${kt.cloud.order.service.uri:}",
        decode404 = true,
        configuration = {FeignCommonErrorDecoder.class}
)
public interface OrderServiceApi extends OrderApi {

}
