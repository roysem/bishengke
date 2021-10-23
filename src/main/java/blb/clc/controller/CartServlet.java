package blb.clc.controller;

import blb.clc.bean.CartBean;
import blb.clc.bean.UserBean;
import blb.clc.service.CartService;
import blb.clc.service.impl.CartServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
    private CartService cartService=new CartServiceImpl();

    public void show(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        UserBean user = (UserBean)req.getSession().getAttribute("user");

        CartBean cartBean = new CartBean();
        HashMap<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        if (user==null){
            map.put("status",0);
        }else {

            cartBean.setUserId(user.getUserId());

            List<CartBean> show = cartService.show(cartBean);
            map.put("status",1);
            map.put("list",show);
        }

        mapper.writeValue(resp.getWriter(),map);

    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException {
        UserBean user = (UserBean)req.getSession().getAttribute("user");
        String type = req.getParameter("type");

        Map<String, String[]> map = req.getParameterMap();

        CartBean cartBean = new CartBean();
        HashMap<String, Object> map1 = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        BeanUtils.populate(cartBean,map);
        cartBean.setUserId(user.getUserId());
        boolean T=false;

        if (type.equals("1")){
            T = cartService.add(cartBean);
        }else if (type.equals("2")){
            T=cartService.down(cartBean);
        }

        mapper.writeValue(resp.getWriter(),T);
    }

    public void clear(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> map = new HashMap<>();
        if (user==null){
            map.put("status",0);
        }else {
            boolean b = cartService.delAll(user.getUserId());
            if (b){
                map.put("status",1);
            }else {
                map.put("status",0);
            }
        }
        mapper.writeValue(resp.getWriter(),map);
    }
}
