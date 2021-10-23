package blb.clc.service;

import blb.clc.bean.CategoryBean;

import java.util.List;

public interface CategoryService {

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
    boolean update(CategoryBean categoryBean);

    /**
     * 添加
     * @param categoryBean
     * @return
     */
    boolean add(CategoryBean categoryBean);


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
    boolean del(Integer id);

}
