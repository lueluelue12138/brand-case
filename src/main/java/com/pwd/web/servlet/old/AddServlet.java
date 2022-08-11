package com.pwd.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.pwd.pojo.Brand;
import com.pwd.service.BrandService;
import com.pwd.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
