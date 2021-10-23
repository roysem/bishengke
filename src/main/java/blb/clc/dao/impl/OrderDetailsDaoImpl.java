package blb.clc.dao.impl;

import blb.clc.bean.OrderDetailsBean;
import blb.clc.dao.OrderDetailsDao;
import blb.clc.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<OrderDetailsBean> select(Integer order_id) {
        String sql="select * from t_order_details where order_id=?";
        List<OrderDetailsBean> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderDetailsBean.class), order_id);
        return query;
    }

    @Override
    public int add(OrderDetailsBean orderDetailsBean) {
        String sql="insert into t_order_details(product_name,product_num,product_money,order_id) values(?,?,?,?)";
        int update = jdbcTemplate.update(sql, orderDetailsBean.getProductName(), orderDetailsBean.getProductNum(), orderDetailsBean.getProductMoney(), orderDetailsBean.getOrderId());
        return update;
    }

}
