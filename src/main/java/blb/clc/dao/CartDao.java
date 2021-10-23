package blb.clc.dao;

import blb.clc.bean.CartBean;

import java.util.List;

public interface CartDao {

    /**
     * 查询所有
     * @param cartBean
     * @return
     */
    List<CartBean> show(CartBean cartBean);


    /**
     * 增加
     * @param cartBean
     * @return
     */
    int add(CartBean cartBean);


    /**
     * 删除
     * @param cartId
     * @return
     */
    int del(int cartId);


    /**
     * 修改
     * @param cartBean
     * @return
     */
    int updateNum(CartBean cartBean);


    /**
     * 删除所有
     * @param userId
     * @return
     */
    int delAll(int userId);

}
