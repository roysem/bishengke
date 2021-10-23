package blb.clc.controller;


import blb.clc.bean.*;
import blb.clc.service.impl.CartServiceImpl;
import blb.clc.service.impl.OrderDetailsServiceImpl;
import blb.clc.service.impl.OrderServiceImpl;
import blb.clc.service.impl.TAddressServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/OrderSubmitServlet")
public class OrderSubmitServlet extends BaseServlet {

    public void search(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");


        TAddressServiceImpl tAddressService = new TAddressServiceImpl();
        CartServiceImpl cartService = new CartServiceImpl();
        CartBean cartBean = new CartBean();
        HashMap<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();


        List<TAddressBean> byUId = tAddressService.getByUId(user.getUserId());
        cartBean.setUserId(user.getUserId());
        List<CartBean> show = cartService.show(cartBean);

        int sum=0;
        for (CartBean bean : show) {
            sum+=bean.getProductNum()*bean.getProductPrice();
        }
        req.setAttribute("address",byUId);
        req.setAttribute("sum",sum);
        req.getRequestDispatcher("order_submit.jsp").forward(req,resp);
    }


    public void add(HttpServletRequest req,HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        String sum = req.getParameter("sum");
        Map<String, String[]> parameterMap = req.getParameterMap();

        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        OrderBean orderBean = new OrderBean();
        OrderServiceImpl orderService = new OrderServiceImpl();
        CartServiceImpl cartService = new CartServiceImpl();
        CartBean cartBean = new CartBean();
        OrderDetailsServiceImpl orderDetailsService = new OrderDetailsServiceImpl();

        BeanUtils.populate(orderBean,parameterMap);

        //生成添加时间
        Date date = new Date();
        String format = formatData.format(date);
        orderBean.setAddTime(format);

        boolean add = orderService.add(orderBean);
        if (add){

            //获取订单编号
            orderBean.setEndTime(format);
            orderBean.setBeginTime(format);
            Map<String, Object> select = orderService.select(orderBean, 1);
            List<OrderBean> list = (List<OrderBean>) select.get("list");
            Integer orderId = list.get(0).getOrderId();

            //获取购物车内容
            cartBean.setUserId(user.getUserId());
            List<CartBean> show = cartService.show(cartBean);

            for (CartBean bean : show) {
                //创建订单详情
                OrderDetailsBean orderDetailsBean = new OrderDetailsBean();
                orderDetailsBean.setOrderId(orderId);
                orderDetailsBean.setProductName(bean.getProductName());
                orderDetailsBean.setProductNum(bean.getProductNum());
                orderDetailsBean.setProductMoney(bean.getProductNum()*bean.getProductPrice());

                //添加订单
                boolean add1 = orderDetailsService.add(orderDetailsBean);

                if (add1){
                    boolean del = cartService.del(bean.getCartId());
                }
            }

            resp.getWriter().print(1);
        }
    }

}
