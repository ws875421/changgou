package com.changgou.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {

    /**
     * 查詢所有
     *
     * @return
     */
    List<Brand> findAll();

    Brand findById(Integer id);

    void addBrand(Brand brand);

    void updateBrand(Brand brand);

    void deleteBrand(Integer id);

    List<Brand> findList(Brand brand);

    PageInfo<Brand> findPage(Integer page, Integer size);

    PageInfo<Brand> findPage(Brand brand,Integer page, Integer size);
}
