package blb.clc.service;

import blb.clc.bean.OrderDetailsBean;

import java.util.List;

public interface OrderDetailsService {

    /**
     * 查询
     */

    List<OrderDetailsBean> select(Integer order_id);

    /**
     * 添加订单
     */

    boolean add(OrderDetailsBean orderDetailsBean);
}
