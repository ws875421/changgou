package com.changgou.service;

import com.changgou.goods.pojo.Brand;

import java.util.List;

public interface BrandService {

    /**
     * 查詢所有
     * @return
     */
    List<Brand> findAll();

    Brand findById(Integer id);

    void addBrand(Brand brand);

    void updateBrand(Brand brand);
}
