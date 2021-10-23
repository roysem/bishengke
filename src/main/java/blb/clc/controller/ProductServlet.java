package blb.clc.controller;

import blb.clc.bean.CategoryBean;
import blb.clc.bean.ProductBean;
import blb.clc.service.CategoryService;
import blb.clc.service.ProductService;
import blb.clc.service.impl.CategoryServiceImpl;
import blb.clc.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
    private ProductService productService=new ProductServiceImpl();
    private CategoryService categoryService=new CategoryServiceImpl();

    public void Search(HttpServletRequest request, HttpServletResponse response){
        ProductBean productBean=new  ProductBean();
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<ProductBean> productBeans = new ArrayList<>();

        Map<String, String[]> parameterMap = request.getParameterMap();
        String lineSize = request.getParameter("LineSize");

        int m=1;
        try {
            BeanUtils.populate(productBean,parameterMap);
            if (lineSize==null || lineSize==""){
              m=1;
            }else {
                m=Integer.parseInt(lineSize);
            }
            Map<String, Object> select = productService.select(productBean, m);
            List<CategoryBean> select1 = categoryService.select();
            productBeans.add(productBean);
            map.put("reqData",productBeans);
            map.put("product",select);
            map.put("category",select1);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(),map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改状态
     * @param request
     * @param response
     */
    public void updateStatus(HttpServletRequest request, HttpServletResponse response){
        ProductBean productBean = new ProductBean();

        Map<String, String[]> parameterMap = request.getParameterMap();

        try {
            BeanUtils.populate(productBean,parameterMap);

            if (productBean.getProductId()==null || productBean.getProductId()==0 || productBean.getProductStatus()==null || productBean.getProductStatus()==""){
                response.getWriter().println(-2);
            }else {
                boolean b = productService.updateStatus(productBean);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除
     * @param request
     * @param response
     * @throws IOException
     */
    public void del(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String productId = request.getParameter("productId");

        if (productId==null || productId.equals("")){
            response.getWriter().println(-2);
        }else {
            boolean del = productService.del(Integer.parseInt(productId));
            if (del){
                response.getWriter().println(1);
            }else {
                response.getWriter().println(0);
            }
        }
    }


    /**
     * 图片上传
     * @param request
     * @param response
     */
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        sfu.setHeaderEncoding("utf-8");

        try {
            List<FileItem> fileItems = sfu.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()){
                    String fileName = uploadFile(fileItem);
                    response.getWriter().println(fileName);
                }
            }
        } catch (Exception e) {
            response.getWriter().print("");
        }


    }

    public String uploadFile(FileItem fileItem){
        String realPath = this.getServletContext().getRealPath("/upload");
        File dir = new File(realPath);
        if (!dir.exists()){
            dir.mkdirs();
        }

        String fileName = fileItem.getName();
        if (fileName !=null){
            String extend = fileName.substring(fileName.lastIndexOf("."));
            fileName=System.currentTimeMillis()+extend;
        }

        //上传文件，自动删除临时文件
        try {
            fileItem.write(new File(realPath,"/"+fileName));
        } catch (Exception e) {
            fileName="";
        }

        return fileName;
    }

    /**
     *
     * @param request
     * @param response
     */
    public void updata(HttpServletRequest request, HttpServletResponse response){
        String type = request.getParameter("type");
        Map<String, String[]> map = request.getParameterMap();

        ProductBean productBean = new ProductBean();

        try {
            BeanUtils.populate(productBean,map);

            ProductBean productBean1 = productService.selectByName(productBean.getProductName());
            boolean update;
            if (type.equals("1")){
                if ( productBean1 !=null &&  ! productBean1.getProductId().equals(productBean.getProductId())){
                    response.getWriter().print(-1);
                    return;
                }
                update = productService.update(productBean);
            }else {
                if (productBean1 !=null){
                    response.getWriter().print(-1);
                    return;
                }
                update = productService.add(productBean);
            }

            if (update){
                response.getWriter().print(1);
            }else {
                response.getWriter().print(0);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
