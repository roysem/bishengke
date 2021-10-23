package blb.clc.controller;


import blb.clc.bean.OrderBean;
import blb.clc.bean.UserBean;
import blb.clc.service.OrderService;
import blb.clc.service.impl.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderServiceImpl();
    /**
     * 渲染
     */
    public void search(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        String lineSize = req.getParameter("LineSize");
        UserBean user = (UserBean)req.getSession().getAttribute("user");

        HashMap<String, Object> map1 = new HashMap<>();
        OrderBean orderBean = new OrderBean();
        ObjectMapper mapper = new ObjectMapper();

        BeanUtils.populate(orderBean,map);

       if (user.getUserRole().equals("U")){
           orderBean.setUserId(user.getUserId());
       }

        Map<String, Object> select = orderService.select(orderBean, Integer.parseInt(lineSize));



       select.put("req",orderBean);

       mapper.writeValue(resp.getWriter(),select);
    }

    public void updataType(HttpServletRequest req,HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        UserBean user = (UserBean) req.getSession().getAttribute("user");

        OrderBean orderBean = new OrderBean();

        BeanUtils.populate(orderBean,map);

        if (user.getUserRole()==null ||orderBean.getOrderId() ==null || 0==orderBean.getOrderId() || orderBean.getOrderType()==null || orderBean.getOrderType()==0){
            resp.getWriter().print(-2);
        }else {
            if (user.getUserRole().equals("U")){
                resp.getWriter().print(-1);
                return;
            }
            boolean b = orderService.updataType(orderBean);
            if (b){
                resp.getWriter().print(1);
            }else {
                resp.getWriter().print(0);
            }
        }
    }


    /**
     * 生成订单
     * @param request
     * @param response
     */
//    public void add(HttpServletRequest request,HttpServletResponse response){
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        UserBean user = (UserBean) request.getSession().getAttribute("user");
//
//        if (user !=null){
//
//        }
//    }
}
