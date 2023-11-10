package com.ark.center.pay.acl.order;

import com.ark.center.trade.client.order.OrderApi;
import com.ark.component.microservice.rpc.exception.FeignCommonErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "${ark.center.trade.service.name:trade}",
        path = "/v1/order",
        url = "${ark.center.trade.service.uri:}",
        dismiss404 = true,
        configuration = {FeignCommonErrorDecoder.class}
)
public interface OrderServiceApi extends OrderApi {

}
