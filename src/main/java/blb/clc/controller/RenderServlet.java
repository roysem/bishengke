package blb.clc.controller;

import blb.clc.bean.TAddressBean;
import blb.clc.bean.UserBean;
import blb.clc.service.TAddressService;
import blb.clc.service.UserService;
import blb.clc.service.impl.TAddressServiceImpl;
import blb.clc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RenderServlet")
public class RenderServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();

    /**
     * 个人信息
     * @param req
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void userInfo(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        UserBean userBean=new UserBean();
        userBean = (UserBean)req.getSession().getAttribute("user");
        UserBean byId = userService.getById(userBean.getUserId());
        if (byId==null){
            req.getSession().removeAttribute("user");
            response.sendRedirect("main.jsp");
        }else {
            req.setAttribute("list",byId);
            req.getRequestDispatcher("user_info.jsp").forward(req,response);
        }
    }

    /**
     * 地址管理
     * @param req
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void address(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        UserBean userBean=new UserBean();
        TAddressService tAddressService=new TAddressServiceImpl();
        userBean = (UserBean)req.getSession().getAttribute("user");
        List<TAddressBean> byUId = tAddressService.getByUId(userBean.getUserId());
        req.setAttribute("list",byUId);
        req.getRequestDispatcher("address.jsp").forward(req,response);
    }


//    /**
//     * 用户列表
//     * @param req
//     * @param response
//     * @throws IOException
//     * @throws ServletException
//     */
//    public void userList(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
//        String lineSize = req.getParameter("LineSize");
//
//        userBean = (UserBean)req.getSession().getAttribute("user");
//        UserBean byId = userService.getById(userBean.getUserId());
//
//        if (byId==null){
//            req.getSession().removeAttribute("user");
//            response.sendRedirect("main.jsp");
//        }else {
//            List<UserBean> select = userService.select(Integer.parseInt(lineSize));
//            int count = userService.getCount();
//            req.setAttribute("count",count);
//            req.setAttribute("list",select);
//            req.setAttribute("LineSize",Integer.parseInt(lineSize));
//            req.getRequestDispatcher("user_list.jsp").forward(req,response);
//        }
//    }



}
