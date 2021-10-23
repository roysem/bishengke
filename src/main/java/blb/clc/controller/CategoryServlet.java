package blb.clc.controller;

import blb.clc.bean.CategoryBean;
import blb.clc.bean.UserBean;
import blb.clc.service.CategoryService;
import blb.clc.service.ProductService;
import blb.clc.service.UserService;
import blb.clc.service.impl.CategoryServiceImpl;
import blb.clc.service.impl.ProductServiceImpl;
import blb.clc.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService=new CategoryServiceImpl();
    private ProductService productService=new ProductServiceImpl();
    private UserService userService=new UserServiceImpl();

    /**
     * 修改
     * @param req
     * @param resp
     */
    public void update(HttpServletRequest req, HttpServletResponse resp){
        CategoryBean categoryBean=new CategoryBean();

        Map<String, String[]> parameterMap = req.getParameterMap();

        try {
            BeanUtils.populate(categoryBean,parameterMap);

            if (categoryBean.getCategoryId()==null || categoryBean.getCategoryName()==null){
                resp.getWriter().println(-2);
            }else {
                CategoryBean categoryBean1 = categoryService.selectByName(categoryBean.getCategoryName());
                if (categoryBean1==null){
                    boolean update = categoryService.update(categoryBean);
                    if (update){
                        resp.getWriter().println(1);
                    }else {
                        resp.getWriter().println(0);
                    }
                }else {
                    resp.getWriter().println(-1);
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
        CategoryBean categoryBean=new CategoryBean();

        Map<String, String[]> parameterMap = req.getParameterMap();

        try {
            BeanUtils.populate(categoryBean,parameterMap);

            if (categoryBean.getCategoryName()==null){
                resp.getWriter().println(-2);
            }else {
                CategoryBean categoryBean1 = categoryService.selectByName(categoryBean.getCategoryName());
                if (categoryBean1==null){
                    boolean update = categoryService.add(categoryBean);
                    if (update){
                        resp.getWriter().println(1);
                    }else {
                        resp.getWriter().println(0);
                    }
                }else {
                    resp.getWriter().println(-1);
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
     * @throws IOException
     */
    public void del(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String categoryId = req.getParameter("categoryId");

        Integer integer = productService.countByCateId(Integer.parseInt(categoryId));
        if (integer>0){
            resp.getWriter().println(-1);
            return;
        }

        boolean del = categoryService.del(Integer.parseInt(categoryId));
        if (del){
            resp.getWriter().println(1);
        }else {
            resp.getWriter().println(0);
        }
    }

    /**
     * 分类管理
     * @param req
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void category(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        UserBean userBean=new UserBean();
        CategoryService categoryService=new CategoryServiceImpl();
        String lineSize = req.getParameter("LineSize");

        userBean = (UserBean)req.getSession().getAttribute("user");
        UserBean byId = userService.getById(userBean.getUserId());

        if (byId==null){
            req.getSession().removeAttribute("user");
            response.sendRedirect("main.jsp");
        }else {
            List<CategoryBean> select = categoryService.select();

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(),select);

        }
    }
}
