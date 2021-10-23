package blb.clc.dao.impl;

import blb.clc.bean.OrderBean;
import blb.clc.dao.OrderDao;
import blb.clc.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<OrderBean> select(OrderBean orderBean ,int n, int m) {
        StringBuilder stringBuilder = new StringBuilder("select user_name,order_id,t_order.add_time as add_time,update_time,t_order.user_id as user_id,address_details,order_type from t_order left join t_user on t_order.user_id=t_user.user_id where 1=1 ");
        ArrayList<Object> objects = new ArrayList<>();

         //开始时间
        if (orderBean.getBeginTime() !=null && ! orderBean.getBeginTime().equals("")){
            stringBuilder.append(" and t_order.add_time>=? ");
            objects.add(orderBean.getBeginTime());
        }

        //结束时间
        if (orderBean.getEndTime() !=null && ! orderBean.getEndTime().equals("")){
            stringBuilder.append(" and t_order.add_time<=? ");
            objects.add(orderBean.getEndTime());
        }

        //订单状态
        if (orderBean.getOrderType() !=null && orderBean.getOrderType() !=0){
            stringBuilder.append(" and order_Type =? ");
            objects.add(orderBean.getOrderType());
        }

        //订单编号
        if (orderBean.getOrderId() !=null && orderBean.getOrderId() !=0){
            stringBuilder.append(" and order_id=? ");
            objects.add(orderBean.getOrderId());
        }

        //用户编号
        if (orderBean.getUserId() !=null && orderBean.getUserId() !=0){
            stringBuilder.append(" and t_order.user_id =? ");
            objects.add(orderBean.getUserId());
        }

        //分页
        if(n>=0){
            stringBuilder.append(" limit ?,?");
            objects.add(n);
            objects.add(m);
        }

        List<OrderBean> query = jdbcTemplate.query(stringBuilder.toString(), objects.toArray(), new BeanPropertyRowMapper<>(OrderBean.class));
        return query;
    }

    @Override
    public int updataType(OrderBean orderBean) {
        String sql="update t_order set order_type=? where order_id=?";
        int update = jdbcTemplate.update(sql, orderBean.getOrderType(), orderBean.getOrderId());
        return update;
    }

    @Override
    public int add(OrderBean orderBean) {
        String sql="insert into t_order(add_time,user_id,address_details) values(?,?,?)";
        int update = jdbcTemplate.update(sql, orderBean.getAddTime(), orderBean.getUserId(), orderBean.getAddressDetails());
        return update;
    }

//    public static void main(String[] args) {
//        OrderDaoImpl orderDao = new OrderDaoImpl();
//        OrderBean orderBean = new OrderBean();
//        List<OrderBean> select = orderDao.select(orderBean, 2, 2);
//        System.out.println(select.toString());
//    }
}
