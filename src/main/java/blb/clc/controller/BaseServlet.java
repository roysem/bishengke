package blb.clc.controller;

import blb.clc.bean.UserBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // 获取请求标识
            String methodName = request.getParameter("method");
            UserBean user = (UserBean) request.getSession().getAttribute("user");
            if (user==null || user.getUserRole().equals("U")){
                String requestURI = request.getRequestURI();
                if (requestURI.contains("UserServlet")){
                    if (methodName.equals("fuzzyQuery") || methodName.equals("updateStatus")){
                        response.sendRedirect("member.jsp");
                        return;
                    }
                }else if (requestURI.contains("CategoryServlet")){
                    response.sendRedirect("member.jsp");
                    return;
                }else if (requestURI.contains("ProductServlet")){
                    if (methodName.equals("updateStatus") || methodName.equals("del") || methodName.equals("upload") || methodName.equals("updata")){
                        response.sendRedirect("member.jsp");
                    }
                }

            }
            // 获取指定类的字节码对象
            Class<? extends BaseServlet> clazz = this.getClass();//这里的this指的是继承BaseServlet对象
            // 通过类的字节码对象获取方法的字节码对象
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 让方法执行
            method.invoke(this, request, response);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
