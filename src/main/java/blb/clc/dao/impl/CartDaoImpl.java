package blb.clc.dao.impl;

import blb.clc.bean.CartBean;
import blb.clc.dao.CartDao;
import blb.clc.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<CartBean> show(CartBean cartBean) {
        String sql="select * from t_cart left join t_product on t_cart.product_id=t_product.product_id where user_id=? ";
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(cartBean.getUserId());
        if (cartBean.getProductId() !=null && cartBean.getProductId()>0){
            sql+=" and t_cart.product_id=? ";
            objects.add(cartBean.getProductId());
        }
        List<CartBean> query = jdbcTemplate.query(sql,objects.toArray(),new BeanPropertyRowMapper<>(CartBean.class));
        return query;
    }

    @Override
    public int add(CartBean cartBean) {
        String sql="insert into t_cart values (null,?,1,?)";
        int update = jdbcTemplate.update(sql, cartBean.getProductId(), cartBean.getUserId());
        return update;
    }

    @Override
    public int del(int cartId) {
        String sql="delete from t_cart where cart_id =?";
        int update = jdbcTemplate.update(sql, cartId);
        return update;
    }

    @Override
    public int updateNum(CartBean cartBean) {
        String sql="update t_cart set product_num=? where cart_id=?";
        int update = jdbcTemplate.update(sql, cartBean.getProductNum(), cartBean.getCartId());
        return update;
    }


    @Override
    public int delAll(int userId) {
        String sql="delete from t_cart where user_id =?";
        int update = jdbcTemplate.update(sql, userId);
        return update;
    }
}
