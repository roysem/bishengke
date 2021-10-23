package blb.clc.service.impl;

import blb.clc.bean.UserBean;
import blb.clc.dao.impl.UserDaoImpl;
import blb.clc.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoImpl userDaoImpl=new UserDaoImpl();

    @Override
    public UserBean isReTel(String tel) {
        UserBean byTel = userDaoImpl.getByTel(tel);
        return byTel;
    }

    @Override
    public UserBean getById(Integer id) {
        UserBean byTel = userDaoImpl.getById(id);
        return byTel;
    }

    @Override
    public boolean register(UserBean userBean) {
        int add = userDaoImpl.add(userBean);
        return add==1?true:false;
    }

    @Override
    public boolean updateName(UserBean userBean) {
        int i = userDaoImpl.updateName(userBean);
        return i==1?true:false;
    }

    @Override
    public boolean updatePwd(UserBean userBean) {
        int i = userDaoImpl.updatePwd(userBean);
        return i==1?true:false;
    }


    @Override
    public boolean updateStatus(UserBean userBean) {
        int i = userDaoImpl.updateStatus(userBean);
        return i==1?true:false;
    }

    @Override
    public List<UserBean> fuzzyQuery(UserBean userBean, Integer m) {
       int n=m*LINE_SIZE;
       m=(m-1)*LINE_SIZE;

        List<UserBean> userBeans = userDaoImpl.fuzzyQuery(userBean, m, n);
        return userBeans;
    }

    @Override
    public int getCount(UserBean userBean) {
        int count = userDaoImpl.getCount(userBean);
        if (count%LINE_SIZE==0){
           count= count/LINE_SIZE;
        }else {
            count=count/LINE_SIZE+1;
        }
        return count;
    }


}
