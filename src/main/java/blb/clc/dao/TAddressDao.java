package blb.clc.dao;


import blb.clc.bean.TAddressBean;

import java.util.List;

public interface TAddressDao {

    /**
     * 通过用户id获取
     * @param id
     * @return
     */
    List<TAddressBean> getByUId(Integer id);


    /**
     * 修改地址
     * @param tAddress
     * @return
     */
    int update(TAddressBean tAddress);


    /**
     * 添加
     * @param tAddress
     * @return
     */
    int add(TAddressBean tAddress);


    /**
     * 删除
     * @param addressId
     * @return
     */
    int del(Integer addressId);


    /**
     * 搜索
     * @param tAddress
     * @return
     */
    TAddressBean search(TAddressBean tAddress);

    /**
     * 搜索
     * @param user_id
     * @param isDefault
     * @return
     */
    TAddressBean search(int user_id, int isDefault);

    /**
     * 修改默认
     * @param tAddressBean
     * @return
     */
    int updateDefault(TAddressBean tAddressBean);
}
