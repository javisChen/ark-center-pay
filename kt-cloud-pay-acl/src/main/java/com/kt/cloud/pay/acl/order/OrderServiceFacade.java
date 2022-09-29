package com.kt.cloud.pay.acl.order;

import cn.hutool.core.collection.CollUtil;
import com.kt.cloud.order.api.response.OrderDetailRespDTO;
import com.kt.component.microservice.rpc.util.RpcUtils;
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
