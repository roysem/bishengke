package blb.clc.service;

import blb.clc.bean.ProductBean;

import java.util.Map;

public interface ProductService {

    public static final int LINE_SIZE=2;

    Map<String,Object> select(ProductBean productBean, Integer n);

    Integer countByCateId(Integer cateId);


    /**
     * 修改状态
     * @param productBean
     * @return
     */
    boolean updateStatus(ProductBean productBean);


    /**
     * 删除
     * @param productId
     * @return
     */
    boolean del(Integer productId);

    /**
     * 添加
     * @param productBean
     * @return
     */
    boolean add(ProductBean productBean);


    /**
     * 修改
     * @param productBean
     * @return
     */
    boolean update(ProductBean productBean);


    /**
     * 通过名字选择
     * @param productName
     * @return
     */
    ProductBean selectByName(String productName);
}
