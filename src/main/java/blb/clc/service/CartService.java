package blb.clc.service;

import blb.clc.bean.CartBean;

import java.util.List;

public interface CartService {

    /**
     * 展示所有
     * @param cartBean
     * @return
     */
    List<CartBean> show(CartBean cartBean);


    /**
     * 添加
     * @param cartBean
     * @return
     */
    boolean add(CartBean cartBean);

    /**
     * 减少
     * @param cartBean
     * @return
     */
    boolean down(CartBean cartBean);



    boolean del(int cartId);


    /**
     * 删除所有
     * @param userId
     * @return
     */
    boolean delAll(int userId);
}
