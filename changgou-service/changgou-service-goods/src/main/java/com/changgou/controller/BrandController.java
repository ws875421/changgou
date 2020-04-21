package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public Result<List<Brand>> findAll() {

        List<Brand> brands = brandService.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK,"查詢成功",brands);
    }

    @GetMapping(value = "/{id}")
    public Result<Brand> findAll(@PathVariable(value = "id") Integer id){
        Brand brand=brandService.findById(id);
        return new Result<Brand>(true,StatusCode.OK,"查詢成功",brand);
    }

}
