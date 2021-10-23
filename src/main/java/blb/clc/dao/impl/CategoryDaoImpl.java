package blb.clc.dao.impl;

import blb.clc.bean.CategoryBean;
import blb.clc.dao.CategoryDao;
import blb.clc.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<CategoryBean> select() {
        String sql="select * from t_category";
        List<CategoryBean> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CategoryBean.class));
        return query;
    }

    @Override
    public int update(CategoryBean categoryBean) {
        String sql="update t_category set category_name=? where category_id=?";
        int update = jdbcTemplate.update(sql, categoryBean.getCategoryName(), categoryBean.getCategoryId());
        return update;
    }

    @Override
    public int add(CategoryBean categoryBean) {
        String sql="insert into t_category(category_name) values(?)";
        int update = jdbcTemplate.update(sql, categoryBean.getCategoryName());
        return update;
    }

    @Override
    public CategoryBean selectByName(String name) {
        String sql="select * from t_category where category_name=?";
        List<CategoryBean> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CategoryBean.class), name);
        return query.size()>0?query.get(0):null;
    }

    @Override
    public int del(Integer id) {
        String sql="delete from t_category where category_id=?";
        int update = jdbcTemplate.update(sql, id);
        return update;
    }
}
