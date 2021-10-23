package blb.clc.service;


import blb.clc.bean.TAddressBean;

import java.util.List;

public interface TAddressService {

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
    boolean update(TAddressBean tAddress);


    /**
     * 添加
     * @param tAddress
     * @return
     */
    boolean add(TAddressBean tAddress);


    /**
     * 删除
     * @param addressId
     * @return
     */
    boolean del(Integer addressId);

    /**
     * 搜索
     * @param tAddress
     * @return
     */
    TAddressBean search(TAddressBean tAddress);
}
