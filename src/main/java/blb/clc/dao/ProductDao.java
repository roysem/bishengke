package blb.clc.dao;

import blb.clc.bean.ProductBean;

import java.util.List;

public interface ProductDao {

    /**
     * 查询所有
     */

    List<ProductBean> select(ProductBean productBean, Integer n, Integer m);

    /**
     * 数量
     * @param cateId
     * @return
     */
    Integer countByCateId(Integer cateId);


    /**
     * 修改状态
     * @param productBean
     * @return
     */
    int updateStatus(ProductBean productBean);


    /**
     * 删除
     * @param productId
     * @return
     */
    int del(Integer productId);


    /**
     * 添加
     * @param productBean
     * @return
     */
    int add(ProductBean productBean);


    /**
     * 修改
     * @param productBean
     * @return
     */
    int update(ProductBean productBean);


    /**
     * 通过名字选择
     * @param productName
     * @return
     */
    ProductBean selectByName(String productName);
}
