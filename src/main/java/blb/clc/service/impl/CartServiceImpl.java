package blb.clc.service.impl;

import blb.clc.bean.CartBean;
import blb.clc.dao.CartDao;
import blb.clc.dao.impl.CartDaoImpl;
import blb.clc.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao cartDao=new CartDaoImpl();

    @Override
    public List<CartBean> show(CartBean cartBean) {
        List<CartBean> show = cartDao.show(cartBean);
        return show;
    }

    @Override
    public boolean add(CartBean cartBean) {
        List<CartBean> show = cartDao.show(cartBean);
        int add;
        if (show.size()==0){
            add = cartDao.add(cartBean);
        }else {
            int number=show.get(0).getProductNum();
            number++;
            cartBean.setCartId(show.get(0).getCartId());
            cartBean.setProductNum(number);
            add = cartDao.updateNum(cartBean);
        }
        return add==1?true:false;
    }

    @Override
    public boolean down(CartBean cartBean) {
        List<CartBean> show = cartDao.show(cartBean);
        int down;
        if (show.size()==0){
            return false;
        }
        if (show.get(0).getProductNum()>1){
            int number=show.get(0).getProductNum();
            number--;
            cartBean.setProductNum(number);
            cartBean.setCartId(show.get(0).getCartId());
            down = cartDao.updateNum(cartBean);
        }else {
            down = cartDao.del(show.get(0).getCartId());
        }
        return down==1?true:false;
    }

    @Override
    public boolean del(int cartId) {
        int del = cartDao.del(cartId);
        return del>0?true:false;
    }

    @Override
    public boolean delAll(int userId) {
        int i = cartDao.delAll(userId);
        return i>0?true:false;
    }
}
