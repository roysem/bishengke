package blb.clc.dao.impl;

import blb.clc.bean.UserBean;
import blb.clc.dao.UserDao;
import blb.clc.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate= new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public UserBean getByTel(String tel) {
        String sql="select * from t_user where user_tel=?";
        List<UserBean> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserBean.class), tel);
        return query.size()>0?query.get(0):null;
    }

    @Override
    public UserBean getById(Integer id) {
        String sql="select * from t_user where user_id=?";
        List<UserBean> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserBean.class), id);
        return query.size()>0?query.get(0):null;
    }

    @Override
    public int add(UserBean userBean) {
        String sql="insert into t_user(user_tel,user_pwd,user_name,user_sex,user_status,user_role) values (?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, userBean.getUserTel(), userBean.getUserPwd(), userBean.getUserName(), userBean.getUserSex(), userBean.getUserStatus(), userBean.getUserRole());
        return update;
    }

    @Override
    public int updateName(UserBean userBean) {
        String sql="update t_user set user_name=?,user_sex=?where user_id=?";
        int update = jdbcTemplate.update(sql, userBean.getUserName(), userBean.getUserSex(), userBean.getUserId());
        return update;
    }

    @Override
    public int updatePwd(UserBean userBean) {
        String sql="update t_user set user_pwd=? where user_id=?";
        int update = jdbcTemplate.update(sql, userBean.getUserPwd(), userBean.getUserId());
        return update;
    }


    @Override
    public int updateStatus(UserBean userBean) {
        String sql="update t_user set user_status=? where user_id=?";
        int update = jdbcTemplate.update(sql, userBean.getUserStatus(), userBean.getUserId());
        return update;
    }

    @Override
    public int getCount(UserBean userBean) {
        StringBuffer stringBuffer = new StringBuffer();
        List<String> strings = new ArrayList<String>();
        stringBuffer.append("select count(*) from t_user where 1=1");
        if (userBean.getUserTel() !=null && !"".equals(userBean.getUserTel())){
            stringBuffer.append(" and user_tel like ?");
            strings.add(userBean.getUserTel()+"%");
        }
        if (userBean.getBeginTime() !=null && !"".equals(userBean.getBeginTime())){
            stringBuffer.append(" and add_time>=?");
            strings.add(userBean.getBeginTime());
        }

        if (userBean.getBeginTime() !=null && !"".equals(userBean.getEndTime())){
            stringBuffer.append(" and add_time<=?");
            strings.add(userBean.getEndTime());
        }

        Integer integer = jdbcTemplate.queryForObject(stringBuffer.toString(), int.class, strings.toArray());
        return integer;

    }

    @Override
    public List<UserBean> fuzzyQuery(UserBean userBean ,Integer m ,Integer n) {
        StringBuffer stringBuffer = new StringBuffer();
        List<Object> strings = new ArrayList<Object>();
        stringBuffer.append("select * from t_user where 1=1");
        if (userBean.getUserTel() !=null && !"".equals(userBean.getUserTel())){
            stringBuffer.append(" and user_tel like ?");
            strings.add(userBean.getUserTel()+"%");
        }

        if (userBean.getBeginTime() !=null && !"".equals(userBean.getBeginTime())){
            stringBuffer.append(" and add_time>=?");
            strings.add(userBean.getBeginTime());
        }

        if (userBean.getBeginTime() !=null && !"".equals(userBean.getEndTime())){
            stringBuffer.append(" and add_time<=?");
            strings.add(userBean.getEndTime());
        }

        if( n != 0){
            stringBuffer.append(" limit ?,?");
            strings.add(m);
            strings.add(n);
        }

        List<UserBean> query = jdbcTemplate.query(stringBuffer.toString(),new BeanPropertyRowMapper<>(UserBean.class),strings.toArray());
        return query;
    }


    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
//        UserBean byTel = userDao.getByTel("13100001000");
//        System.out.println(byTel);
//        UserBean userBean = new UserBean("13100100003", "123456", "赵六", "F", "Y", "U");
        UserBean userBean = new UserBean();
//        userBean.setUserTel("13");
//        userBean.setBeginTime("2021-10-10");
        List<UserBean> userBeans = userDao.fuzzyQuery(userBean,0,5);
//        int add = userDao.add(userBean);
        System.out.println(userBeans.toString());
    }
}
