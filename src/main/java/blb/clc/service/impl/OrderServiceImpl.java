package blb.clc.service.impl;

import blb.clc.bean.OrderBean;
import blb.clc.bean.OrderDetailsBean;
import blb.clc.dao.OrderDao;
import blb.clc.dao.impl.OrderDaoImpl;
import blb.clc.service.OrderDetailsService;
import blb.clc.service.OrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao =new OrderDaoImpl();
    private OrderDetailsService orderDetailsService=new OrderDetailsServiceImpl();

    @Override
    public Map<String,Object> select(OrderBean orderBean, int n) {
        n=(n-1)*LINE_SIZE;

        Map<String, Object> map = new HashMap<>();
        List<Integer> ints = new ArrayList<Integer>();

        List<OrderBean> select = orderDao.select(orderBean, n, LINE_SIZE);

        int size = orderDao.select(orderBean, -1,LINE_SIZE ).size();

        size=size%LINE_SIZE==0?size/LINE_SIZE:size/LINE_SIZE+1;
        for (int i = 0; i < size; i++) {
            ints.add(i+1);
        }

        if (select.size()>0){
            for (OrderBean bean : select) {
                List<OrderDetailsBean> select1 = orderDetailsService.select(bean.getOrderId());
                bean.setOrderDetailsBeans(select1);

                double count=0;
                if (select1.size()>0){
                    for (OrderDetailsBean orderDetailsBean : select1) {
                        count+=orderDetailsBean.getProductMoney();
                    }
                }
                bean.setCount(count);
            }
        }

        map.put("pageList",ints);
        map.put("list",select);
        map.put("nowPage",n+1);

        return map;
    }

    @Override
    public boolean updataType(OrderBean orderBean) {
        int i = orderDao.updataType(orderBean);
        return i==1?true:false;
    }

    @Override
    public boolean add(OrderBean orderBean) {
        int add = orderDao.add(orderBean);
        return add==1?true:false;
    }

    public static void main(String[] args) {
        OrderServiceImpl orderService = new OrderServiceImpl();
        OrderBean orderBean = new OrderBean();
        orderService.select(orderBean,1);
    }
}
