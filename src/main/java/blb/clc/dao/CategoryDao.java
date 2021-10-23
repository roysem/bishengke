package blb.clc.dao;

import blb.clc.bean.CategoryBean;

import java.util.List;

public interface CategoryDao {

    /**
     * 查询所有
     * @return
     */
    List<CategoryBean> select();


    /**
     * 修改
     * @param categoryBean
     * @return
     */
    int update(CategoryBean categoryBean);

    /**
     * 添加
     * @param categoryBean
     * @return
     */
    int add(CategoryBean categoryBean);


    /**
     * 根据name查询
     * @param name
     * @return
     */
    CategoryBean selectByName(String name);


    /**
     * 根据id删除
     * @param id
     * @return
     */
    int del(Integer id);
}
