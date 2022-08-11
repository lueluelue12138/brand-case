package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;

import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //创建SqlSessionFactory工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        //获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandmapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        List<Brand> brands = mapper.selectAll();
        //释放资源
        sqlSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {

        //获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandmapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.add(brand);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandmapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.deleteByIds(ids);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandmapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;
        //查询当前页数据
        List<Brand> brands = mapper.selectByPage(begin, size);
        //查询总记录数
        int totalCount = mapper.selectTotalCount();
        //封装pageBean对象
        PageBean<Brand> pageBean = new PageBean<>(totalCount, brands);
        //释放资源
        sqlSession.close();
        //返回结果
        return pageBean;

    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandmapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //处理数据
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }
        //查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);
        //封装pageBean对象
        PageBean<Brand> pageBean = new PageBean<>(totalCount, rows);
        //释放资源
        sqlSession.close();
        //返回结果
        return pageBean;
    }

    @Override
    public void updateById(Brand brand) {
        //获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        mapper.updateById(brand);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();

    }

    @Override
    public void deleteById(int id) {
        //获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        mapper.deleteById(id);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Override
    public Brand selectById(int id) {
        //获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        Brand brand = mapper.selectById(id);
        //释放资源
        sqlSession.close();
        //返回
        return brand;
    }
}
