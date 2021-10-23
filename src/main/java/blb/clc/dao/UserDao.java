package blb.clc.dao;

import blb.clc.bean.UserBean;

import java.util.List;

public interface UserDao {

    /**
     * 通过手机号码获取
     * @param tel
     * @return
     */
    UserBean getByTel(String tel);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    UserBean getById(Integer id);

    /**
     * 添加用户
     * @param userBean
     * @return
     */
    int add(UserBean userBean);


    /**
     * 修改用户名和性别
     * @param userBean
     * @return
     */
    int updateName(UserBean userBean);

    /**
     * 修改密码
     * @param userBean
     * @return
     */
    int updatePwd(UserBean userBean);



    /**
     * 修改状态
     * @param userBean
     * @return
     */
    int updateStatus(UserBean userBean);



    /**
     * 获取数量
     * @param userBean
     * @return
     */
    int getCount(UserBean userBean);

    /**
     * 模糊查询
     * @param userBean
     * @return
     */
    List<UserBean> fuzzyQuery(UserBean userBean, Integer m, Integer n);
}
