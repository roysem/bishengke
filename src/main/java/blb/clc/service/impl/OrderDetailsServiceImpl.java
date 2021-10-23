package blb.clc.service.impl;

import blb.clc.bean.OrderDetailsBean;
import blb.clc.dao.OrderDetailsDao;
import blb.clc.dao.impl.OrderDetailsDaoImpl;
import blb.clc.service.OrderDetailsService;

import java.util.List;

public class OrderDetailsServiceImpl implements OrderDetailsService {
    private OrderDetailsDao orderDetailsDao=new OrderDetailsDaoImpl();

    @Override
    public List<OrderDetailsBean> select(Integer order_id) {
        List<OrderDetailsBean> select = orderDetailsDao.select(order_id);
        return select;
    }

    @Override
    public boolean add(OrderDetailsBean orderDetailsBean) {
        int add = orderDetailsDao.add(orderDetailsBean);
        return add>0?true:false;
    }
}
