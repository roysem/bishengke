package blb.clc.service;

import blb.clc.bean.UserBean;

import java.util.List;

public interface UserService {
    public static final Integer LINE_SIZE=3;

    /**
     * 电话是否重复
     * @param tel
     * @return
     */
    UserBean isReTel(String tel);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    UserBean getById(Integer id);

    /**
     * 注册
     * @param userBean
     * @return
     */
    boolean register(UserBean userBean);


    /**
     * 修改用户名和性别
     * @param userBean
     * @return
     */
    boolean updateName(UserBean userBean);



    /**
     * 修改密码
     * @param userBean
     * @return
     */
    boolean updatePwd(UserBean userBean);



    /**
     * 修改密码
     * @param userBean
     * @return
     */
    boolean updateStatus(UserBean userBean);





    /**
     * 模糊查询
     * @param userBean
     * @return
     */
    List<UserBean> fuzzyQuery(UserBean userBean, Integer m);


    /**
     * 获取数量
     * @param userBean
     * @return
     */
    int getCount(UserBean userBean);
}

