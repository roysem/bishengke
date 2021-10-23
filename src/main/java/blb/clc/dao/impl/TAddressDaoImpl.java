package blb.clc.dao.impl;


import blb.clc.bean.TAddressBean;
import blb.clc.dao.TAddressDao;
import blb.clc.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TAddressDaoImpl implements TAddressDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtil.getDataSource());
    @Override
    public List<TAddressBean> getByUId(Integer id) {
        String sql="select * from t_address where user_id=?";
        List<TAddressBean> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TAddressBean.class), id);
        return query;
    }

    @Override
    public int update(TAddressBean tAddress) {
        String sql="update t_address set address_province=?,address_city=?,address_district=?,address_describe=? where address_id=?";
        int update = jdbcTemplate.update(sql, tAddress.getAddressProvince(), tAddress.getAddressCity(), tAddress.getAddressDistrict(), tAddress.getAddressDescribe(), tAddress.getAddressId());
        return update;
    }

    @Override
    public int add(TAddressBean tAddress) {
        String sql="insert into t_address(address_province,address_city,address_district,address_describe,user_id,is_default) values (?,?,?,?,?,1)";
        int update = jdbcTemplate.update(sql, tAddress.getAddressProvince(), tAddress.getAddressCity(), tAddress.getAddressDistrict(), tAddress.getAddressDescribe(), tAddress.getUserId());
        return update;
    }

    @Override
    public int del(Integer addressId) {
        String sql="delete from t_address where address_id=?";
        int update = jdbcTemplate.update(sql, addressId);
        return update;
    }

    @Override
    public TAddressBean search(TAddressBean tAddress) {
        String sql="select * from t_address where address_province=? and address_city=? and address_district=? and address_describe=? and  user_id=?";
        List<TAddressBean> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TAddressBean.class), tAddress.getAddressProvince(), tAddress.getAddressCity(), tAddress.getAddressDistrict(), tAddress.getAddressDescribe(), tAddress.getUserId());
        return query.size()>0?query.get(0):null;
    }

    @Override
    public TAddressBean search(int user_id, int isDefault) {
        String sql="select * from t_address where user_id=? and is_default=?";
        List<TAddressBean> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TAddressBean.class), user_id, isDefault);
        return query.size()>0?query.get(0):null;
    }

    @Override
    public int updateDefault(TAddressBean tAddressBean) {
        String sql="update t_address set is_default=? where address_id=?";
        int update = jdbcTemplate.update(sql, tAddressBean.getIsDefault(), tAddressBean.getAddressId());
        return update;
    }
}
