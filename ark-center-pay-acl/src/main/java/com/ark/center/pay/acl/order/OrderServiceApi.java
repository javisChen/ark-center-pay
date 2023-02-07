package com.ark.center.pay.acl.order;

import com.ark.center.trade.client.order.OrderApi;
import com.ark.component.microservice.rpc.exception.FeignCommonErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "${ark.center.order.service.name:order}",
        path = "/v1/order",
        url = "${ark.center.order.service.uri:}",
        decode404 = true,
        configuration = {FeignCommonErrorDecoder.class}
)
public interface OrderServiceApi extends OrderApi {

}
