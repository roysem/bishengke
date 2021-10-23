package blb.clc.controller;

import blb.clc.bean.UserBean;
import blb.clc.service.UserService;
import blb.clc.service.impl.UserServiceImpl;
import blb.clc.util.ImageVerify;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();

    /**
     * 登录
     * @param req
     * @param resp
     */
    public void login(HttpServletRequest req,HttpServletResponse resp){
        Map<String, String[]> parameterMap = req.getParameterMap();
        UserBean userBean=new UserBean();

        try {
            BeanUtils.populate(userBean,parameterMap);

            //判断信息是否完整
            if (userBean.getUserTel()==null || userBean.getUserPwd()==null){
                resp.getWriter().println(-2);
                return;
            }

            //用户是否存在
            UserBean reTel = userService.isReTel(userBean.getUserTel());
            if (reTel==null){
                resp.getWriter().println(-1);
                return;
            }
            //密码是否正确
            if (! reTel.getUserPwd().equals(userBean.getUserPwd())){
                resp.getWriter().println(0);
            }else {
                //是否被停封
                if (reTel.getUserStatus().equals("Y")){
                    //登录成功
                    HttpSession session = req.getSession();
                    session.setAttribute("user",reTel);

                    resp.getWriter().println(1);
                }else {
                    resp.getWriter().println(2);
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
     * 用户名是否重复
     * @param req
     * @param resp
     */
    public void isReTel(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String userTel = req.getParameter("userTel");
        UserBean reTel = userService.isReTel(userTel);
        System.out.println(reTel==null);
        if (reTel==null){
            resp.getWriter().println(0);
        }else {
            resp.getWriter().println(-1);
        }
    }

    /**
     * 注册
     */
    public void register(HttpServletRequest req,HttpServletResponse resp){
        UserBean userBean=new UserBean();
        Map<String, String[]> parameterMap = req.getParameterMap();

        try {
            BeanUtils.populate(userBean,parameterMap);

            if (userBean.getUserTel()==null || userBean.getUserPwd()==null || userBean.getUserName()==null || userBean.getUserSex()==null){
                resp.getWriter().println(-2);
                return;
            }

            if (userService.isReTel(userBean.getUserTel())!=null){
                resp.getWriter().println(-1);
                return;
            }

            userBean.setUserStatus("Y");
            userBean.setUserRole("U");

            boolean register = userService.register(userBean);
            if (register){
                HttpSession session = req.getSession();
                UserBean reTel = userService.isReTel(userBean.getUserTel());
                session.setAttribute("user",reTel);
                resp.getWriter().println(1);
            }else {
                resp.getWriter().println(0);
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
     * 退出
     * @param req
     * @param resp
     */
    public void exit(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        resp.sendRedirect("main.jsp");
    }


    /**
     * 修改用户名和性别
     * @param req
     * @param resp
     */
    public void updateName(HttpServletRequest req,HttpServletResponse resp){
        UserBean userBean=new UserBean();

        Map<String, String[]> map = req.getParameterMap();

        try {
            BeanUtils.populate(userBean,map);

            if (userBean.getUserName()==null || userBean.getUserSex()==null || userBean.getUserId()==null){
                resp.getWriter().println(-1);
            }else {
                boolean b = userService.updateName(userBean);
                if (b){
                    UserBean reTel = userService.getById(userBean.getUserId());
                    req.getSession().setAttribute("user",reTel);
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
     * 验证码
     * @param request
     * @param response
     */
    public void codeVerify(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();

        ImageVerify vCode = new ImageVerify(120,40,5,100);
        vCode.createCode();
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
        response.getOutputStream().flush();
    }

    /**
     * 修改密码
     * @param request
     * @param response
     */
    public void updatePwd(HttpServletRequest request,HttpServletResponse response) throws IOException {
        UserBean userBean=new UserBean();

        String userId = request.getParameter("userId");
        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");
        String code = request.getParameter("code");

        String code1 = (String)request.getSession().getAttribute("code");

        if (userId==null || oldPwd==null || newPwd==null || code==null){
            response.getWriter().println(-2);
        }else if (code.equals(code1)){
            UserBean byId = userService.getById(Integer.parseInt(userId));
            if (byId==null){
                response.getWriter().println(0);
            }else {
                if (! byId.getUserPwd().equals(oldPwd)){
                    System.out.println(1);
                    response.getWriter().println(0);
                }else {
                    userBean.setUserId(Integer.parseInt(userId));
                    userBean.setUserPwd(newPwd);
                    boolean b = userService.updatePwd(userBean);
                    if (b){
                        UserBean reTel = userService.getById(userBean.getUserId());
                        request.getSession().setAttribute("user",reTel);
                        response.getWriter().println(1);
                    }else {
                        response.getWriter().println(2);
                    }
                }
            }
        }else {
            response.getWriter().println(-1);
        }
    }


    /**
     * 修改状态
     * @param request
     * @param response
     * @throws IOException
     */
    public void updateStatus(HttpServletRequest request,HttpServletResponse response) throws IOException{
        UserBean userBean=new UserBean();

        Object user = request.getSession().getAttribute("user");
        if (user==null){
            response.sendRedirect("main,jsp");
            return;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();


        try {
            BeanUtils.populate(userBean,parameterMap);
            if (userBean.getUserRole()==null ||userBean.getUserId()==null || userBean.getUserStatus()==null){
                response.getWriter().println(-2);
            }else {
                if(userBean.getUserRole().equals("A")){
                    response.getWriter().print(-3);
                    return;
                }

                UserBean byId = userService.getById(userBean.getUserId());
                if (byId==null){
                    response.getWriter().println(-1);
                    return;
                }

                boolean b = userService.updateStatus(userBean);
                if (b){
                    response.getWriter().println(1);
                }else {
                    response.getWriter().println(0);
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 列表
     * @param request
     * @param response
     * @throws IOException
     */
    public void fuzzyQuery(HttpServletRequest request,HttpServletResponse response) throws IOException{
        Map<String, String[]> parameterMap = request.getParameterMap();
        String lineSize = request.getParameter("LineSize");
        UserBean userBean=new UserBean();
        try {
            BeanUtils.populate(userBean,parameterMap);

            List<UserBean> userBeans = userService.fuzzyQuery(userBean, Integer.parseInt(lineSize));
            int count = userService.getCount(userBean);
            request.setAttribute("req",userBean);
            request.setAttribute("count",count);
            request.setAttribute("list",userBeans);
            request.setAttribute("LineSize",Integer.parseInt(lineSize));
            request.getRequestDispatcher("user_list.jsp").forward(request,response);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
