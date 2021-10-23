package blb.clc.service.impl;


import blb.clc.bean.TAddressBean;
import blb.clc.dao.TAddressDao;
import blb.clc.dao.impl.TAddressDaoImpl;
import blb.clc.service.TAddressService;

import java.util.List;

public class TAddressServiceImpl implements TAddressService {
    private TAddressDao tAddressDao=new TAddressDaoImpl();

    @Override
    public List<TAddressBean> getByUId(Integer id) {
        List<TAddressBean> byUId = tAddressDao.getByUId(id);
        return byUId;
    }

    @Override
    public boolean update(TAddressBean tAddress) {
        int update = tAddressDao.update(tAddress);
        return update==1?true:false;
    }

    @Override
    public boolean add(TAddressBean tAddress) {
        TAddressBean search = tAddressDao.search(Integer.parseInt(tAddress.getUserId()), 1);
        if (search !=null){
            search.setIsDefault(2);
            int i = tAddressDao.updateDefault(search);
        }
        int update = tAddressDao.add(tAddress);
        return update==1?true:false;
    }

    @Override
    public boolean del(Integer addressId) {
        int update = tAddressDao.del(addressId);
        return update==1?true:false;
    }

    @Override
    public TAddressBean search(TAddressBean tAddress) {
        TAddressBean search = tAddressDao.search(tAddress);
        return search;
    }
}
