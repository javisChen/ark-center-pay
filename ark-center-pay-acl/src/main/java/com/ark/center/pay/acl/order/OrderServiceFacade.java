package com.ark.center.pay.acl.order;

import com.ark.center.trade.client.order.dto.OrderDTO;
import com.ark.center.trade.client.order.query.OrderDetailsQry;
import com.ark.component.microservice.rpc.util.RpcUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderServiceFacade {

    private final OrderServiceApi orderServiceApi;

    public OrderDTO queryOrder(String tradeNo) {
        OrderDetailsQry qry = new OrderDetailsQry();
        qry.setTradeNo(tradeNo);
        return RpcUtils.checkAndGetData(orderServiceApi.queryDetails(qry));
    }

}
