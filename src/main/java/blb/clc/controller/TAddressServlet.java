package blb.clc.controller;


import blb.clc.bean.TAddressBean;
import blb.clc.service.TAddressService;
import blb.clc.service.impl.TAddressServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/TAddressServlet")
public class TAddressServlet extends BaseServlet {
    private TAddressService tAddressService=new TAddressServiceImpl();
    /**
     * 修改
     * @param req
     * @param resp
     */
    public void update(HttpServletRequest req, HttpServletResponse resp){
        TAddressBean tAddress=new TAddressBean();

        Map<String, String[]> parameterMap = req.getParameterMap();

        try {
            BeanUtils.populate(tAddress,parameterMap);

            if (tAddress.getAddressId()==null || tAddress.getAddressProvince()==null || tAddress.getAddressCity()==null || tAddress.getAddressDistrict()==null || tAddress.getAddressDescribe()==null){
                resp.getWriter().println(-1);
            }else {
                if (tAddressService.search(tAddress)!=null){
                    resp.getWriter().println(-2);
                    return;
                }

                boolean update = tAddressService.update(tAddress);
                if (update){
                    resp.getWriter().println(1);
                }else {
                    resp.getWriter().println(0);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加
     * @param req
     * @param resp
     */
    public void add(HttpServletRequest req, HttpServletResponse resp){
        TAddressBean tAddress=new TAddressBean();

        Map<String, String[]> parameterMap = req.getParameterMap();

        try {
            BeanUtils.populate(tAddress,parameterMap);

            if ( tAddress.getAddressProvince()==null || tAddress.getAddressCity()==null || tAddress.getAddressDistrict()==null || tAddress.getAddressDescribe()==null || tAddress.getUserId()==null){
                resp.getWriter().println(-1);
            }else {
                if (tAddressService.search(tAddress)!=null){
                    resp.getWriter().println(-2);
                    return;
                }
                boolean update = tAddressService.add(tAddress);
                if (update){
                    resp.getWriter().println(1);
                }else {
                    resp.getWriter().println(0);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除
     * @param req
     * @param resp
     */
    public void del(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String addressId = req.getParameter("addressId");
        if (addressId==null){
            resp.getWriter().println(-1);
        }else{
            boolean del = tAddressService.del(Integer.parseInt(addressId));
            if (del){
                resp.getWriter().println(1);
            }else {
                resp.getWriter().println(0);
            }
        }

    }

}
