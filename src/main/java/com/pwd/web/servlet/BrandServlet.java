package com.pwd.web.servlet;

import com.alibaba.fastjson.JSON;
import com.pwd.pojo.Brand;
import com.pwd.pojo.PageBean;
import com.pwd.service.BrandService;
import com.pwd.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

    private BrandService brandService = new BrandServiceImpl();

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service查询
        List<Brand> brands = brandService.selectAll();
        //转为JSON
        String jsonString = JSON.toJSONString(brands);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 添加
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转换为java对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //调用service添加
        brandService.add(brand);
        //响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //转换为int数组
        int[] ids = JSON.parseObject(params, int[].class);
        //调用service删除
        brandService.deleteByIds(ids);
        //响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收当前页码和每页展示条数 url?currentPage=1&pageSize=5
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        //调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收当前页码和每页展示条数 url?currentPage=1&pageSize=5
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        //获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转换为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(Integer.parseInt(currentPage), Integer.parseInt(pageSize), brand);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 更新数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转换为java对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //调用service添加
        brandService.updateById(brand);
        //响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 删除数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        String id = request.getParameter("id");
        //调用service添加
        brandService.deleteById(Integer.parseInt(id));
        //响应成功标识
        response.getWriter().write("success");
    }

    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        String id = request.getParameter("id");
        //调用service搜索
        Brand brand = brandService.selectById(Integer.parseInt(id));
        //转为JSON
        String jsonString = JSON.toJSONString(brand);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
