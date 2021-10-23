package blb.clc.dao;

import blb.clc.bean.OrderBean;

import java.util.List;

public interface OrderDao {

    /**
     * 查询
     * @param orderBean
     * @return
     */
    List<OrderBean> select(OrderBean orderBean, int n, int m);


    /**
     * 修改状态
     * @param orderBean
     * @return
     */
    int updataType(OrderBean orderBean);


    /**
     * 添加
     * @param orderBean
     * @return
     */
    int add(OrderBean orderBean);
}
