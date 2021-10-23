package blb.clc.service.impl;

import blb.clc.bean.CategoryBean;
import blb.clc.dao.CategoryDao;
import blb.clc.dao.impl.CategoryDaoImpl;
import blb.clc.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao=new CategoryDaoImpl();

    @Override
    public List<CategoryBean> select() {
        List<CategoryBean> select = categoryDao.select();
        return select;
    }

    @Override
    public boolean update(CategoryBean categoryBean) {
        int update = categoryDao.update(categoryBean);
        return update==1?true:false;
    }

    @Override
    public boolean add(CategoryBean categoryBean) {
        int update = categoryDao.add(categoryBean);
        return update==1?true:false;
    }

    @Override
    public CategoryBean selectByName(String name) {
        CategoryBean categoryBean = categoryDao.selectByName(name);
        return categoryBean;
    }

    @Override
    public boolean del(Integer id) {
        int del = categoryDao.del(id);
        return del==1?true:false;
    }


}
