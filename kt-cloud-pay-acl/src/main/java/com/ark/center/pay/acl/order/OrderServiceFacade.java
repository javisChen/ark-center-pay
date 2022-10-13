package com.ark.center.pay.acl.order;

import com.ark.center.order.api.response.OrderDetailRespDTO;
import com.ark.component.microservice.rpc.util.RpcUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderServiceFacade {

    private final OrderServiceApi orderServiceApi;

    public OrderDetailRespDTO getOrder(Long orderId) {
        return RpcUtils.checkAndGetData(orderServiceApi.getOrderById(orderId));
    }

}
