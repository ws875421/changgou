package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageInfo;
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
        return new Result<List<Brand>>(true, StatusCode.OK, "查詢成功", brands);
    }

    @GetMapping(value = "/{id}")
    public Result<Brand> findAll(@PathVariable(value = "id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK, "查詢成功", brand);
    }

    @PostMapping
    public Result addBrand(@RequestBody Brand brand) {
        brandService.addBrand(brand);
        return new Result(true, StatusCode.OK, "新增成功");
    }

    @PutMapping(value = "/{id}")
    public Result updateBrand(@PathVariable(value = "id") Integer id, @RequestBody Brand brand) {
        brand.setId(id);
        brandService.updateBrand(brand);
        return new Result(true, StatusCode.OK, "新增成功");
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteBrand(@PathVariable(value = "id") Integer id) {
        brandService.deleteBrand(id);
        return new Result(true, StatusCode.OK, "刪除成功");
    }

    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand) {
        List<Brand> brandList = brandService.findList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK, "條件查詢", brandList);
    }

    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "分頁查詢!", pageInfo);
    }

    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "條件分頁查詢", pageInfo);
    }
}
