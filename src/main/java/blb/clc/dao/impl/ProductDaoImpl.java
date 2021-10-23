package blb.clc.dao.impl;

import blb.clc.bean.ProductBean;
import blb.clc.dao.ProductDao;
import blb.clc.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<ProductBean> select(ProductBean productBean, Integer n, Integer m) {
        StringBuffer stringBuffer = new StringBuffer("select * from t_product ,t_category where t_product.category_id=t_category.category_id ");
        ArrayList<Object> objects = new ArrayList<>();

        if(productBean.getProductName() !=null && !"".equals(productBean.getProductName())){
            stringBuffer.append(" and product_name like ?");
            objects.add("%"+productBean.getProductName()+"%");
        }

        if (productBean.getCategoryId() !=null && productBean.getCategoryId()>0){
            stringBuffer.append(" and t_product.category_id = ?");
            objects.add(productBean.getCategoryId());
        }

        if (productBean.getProductStatus() !=null  &&  productBean.getProductStatus().equals("Y")){
            stringBuffer.append(" and product_status=? ");
            objects.add(productBean.getProductStatus());
        }

        if (n >= 0){
                stringBuffer.append(" limit ?,?");
            objects.add(n);
            objects.add(m);
        }

        List<ProductBean> query = jdbcTemplate.query(stringBuffer.toString(),  objects.toArray(),new BeanPropertyRowMapper<>(ProductBean.class));
        return query;
    }

    @Override
    public Integer countByCateId(Integer cateId) {
        String sql="select count(*) from t_product where category_id";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, cateId);
        return integer;
    }

    @Override
    public int updateStatus(ProductBean productBean) {
        String sql="update t_product set product_status=? where product_id=?";
        int update = jdbcTemplate.update(sql, productBean.getProductStatus(), productBean.getProductId());
        return update;
    }

    @Override
    public int del(Integer productId) {
        String sql="delete from t_product where product_id=?";
        int update = jdbcTemplate.update(sql, productId);
        return update;
    }

    @Override
    public int add(ProductBean productBean) {
        String sql="insert into t_product values(NULL,?,?,?,?,?,'Y')";
        int update = jdbcTemplate.update(sql, productBean.getProductName(), productBean.getProductPic(), productBean.getProductPrice(), productBean.getProductDescribe(), productBean.getCategoryId());
        return update;
    }

    @Override
    public int update(ProductBean productBean) {
        String sql="update t_product set product_name=?,product_pic=?,product_price=?,product_describe=?,category_id=? where product_id=?";
        int update = jdbcTemplate.update(sql, productBean.getProductName(), productBean.getProductPic(), productBean.getProductPrice(), productBean.getProductDescribe(), productBean.getCategoryId(), productBean.getProductId());
        return update;
    }

    @Override
    public ProductBean selectByName(String productName) {
        String sql="select * from t_product where product_name=?";
        List<ProductBean> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProductBean.class), productName);
        return query.size()>0?query.get(0):null;
    }

//    public static void main(String[] args) {
//        ProductBean productBean = new ProductBean();
//        ProductDaoImpl productDao = new ProductDaoImpl();
//        List<ProductBean> select = productDao.select(productBean, 2, 4);
//        System.out.println(select.toString());
//    }
}
