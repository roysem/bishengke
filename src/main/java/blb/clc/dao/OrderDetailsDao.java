package blb.clc.dao;

import blb.clc.bean.OrderDetailsBean;

import java.util.List;

public interface OrderDetailsDao {

    /**
     * 查询
     */

    List<OrderDetailsBean> select(Integer order_id);


    /**
     * 添加订单
     */

    int add(OrderDetailsBean orderDetailsBean);
}
