package blb.clc.service.impl;

import blb.clc.bean.ProductBean;
import blb.clc.dao.ProductDao;
import blb.clc.dao.impl.ProductDaoImpl;
import blb.clc.service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao=new ProductDaoImpl();

    @Override
    public Map<String, Object> select(ProductBean productBean, Integer n) {
        Map<String, Object> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<Integer>();

        n=(n-1)*LINE_SIZE;

        int size = productDao.select(productBean,-1,0).size();
        size=size%LINE_SIZE==0?size/LINE_SIZE:size/LINE_SIZE+1;
        for (int i = 0; i < size; i++) {
            list.add(i+1);
        }

        List<ProductBean> select = productDao.select(productBean, n, LINE_SIZE);
        map.put("pageList",list);
        map.put("pageNum",n);
        map.put("list",select);

        return map;
    }

    @Override
    public Integer countByCateId(Integer cateId) {
        Integer integer = productDao.countByCateId(cateId);
        return integer;
    }

    @Override
    public boolean updateStatus(ProductBean productBean) {
        int i = productDao.updateStatus(productBean);
        return i==1?true:false;
    }

    @Override
    public boolean del(Integer productId) {
        int del = productDao.del(productId);
        return del==1?true:false;
    }

    @Override
    public boolean add(ProductBean productBean) {
        int add = productDao.add(productBean);
        return add==1?true:false;
    }

    @Override
    public boolean update(ProductBean productBean) {
        int update = productDao.update(productBean);
        return update==1?true:false;
    }

    @Override
    public ProductBean selectByName(String productName) {
        ProductBean productBean = productDao.selectByName(productName);
        return productBean;
    }


}
