package com.pwd.service;

import com.pwd.pojo.Brand;
import com.pwd.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandService {
    /*查询所有*/
    List<Brand> selectAll();

    /*添加*/
    void add(Brand brand);

    /*批量删除*/
    void deleteByIds(@Param("ids") int[] ids);

    /**
     *分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage,int pageSize);
    /**
     *分页条件查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);

    /*修改数据*/
    void updateById(Brand brand);

    /*删除数据*/
    void deleteById(int id);


    Brand selectById(int id);

}
