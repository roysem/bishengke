package blb.clc.service;

import blb.clc.bean.OrderBean;

import java.util.Map;

public interface OrderService {
    public static final Integer LINE_SIZE=2;

    /**
     * 查询
     * @param orderBean
     * @return
     */
    Map<String,Object> select(OrderBean orderBean, int n);

    /**
     * 修改状态
     * @param orderBean
     * @return
     */
    boolean updataType(OrderBean orderBean);

    /**
     * 添加
     * @param orderBean
     * @return
     */
    boolean add(OrderBean orderBean);
}
