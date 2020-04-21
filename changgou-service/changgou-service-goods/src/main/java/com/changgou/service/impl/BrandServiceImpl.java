package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查詢所有
     *
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * ID查詢
     *
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增Brand
     *
     * @param brand
     */
    @Override
    public void addBrand(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    /**
     * 組裝SQL語句忽略空直
     *
     * @param brand
     */
    @Override
    public void updateBrand(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 刪除Brand
     *
     * @param id
     */
    @Override
    public void deleteBrand(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 模糊查詢
     *
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand) {
        Example example = new Example(Brand.class);
        //條件構造緝
        Example.Criteria criteria = example.createCriteria();
        if (null != brand) {
            //根據名子模糊查詢 where name like '%蘋果%'
            String name = brand.getName();
            if (!StringUtils.isBlank(name)) {
                /**
                 * 1:Brand 屬性名
                 * 2:搜尋的條件
                 */
                criteria.andLike("name", "%" + name + "%");
            }
            //根據字首搜尋 and letter='A'
            String letter = brand.getLetter();
            if (!StringUtils.isBlank(letter)) {
                criteria.andEqualTo("letter", letter);
            }
        }

        return brandMapper.selectByExample(example);
    }

    /**
     * 分頁查詢
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {

        /**
         * page:當期頁
         * size:數量
         */
        PageHelper.startPage(page, size);

        List<Brand> brands = brandMapper.selectAll();

        return new PageInfo<Brand>(brands);
    }

    /**
     * 模糊查詢+分頁
     *
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        PageHelper.startPage(page, size);

        Example example = new Example(Brand.class);
        //條件構造緝
        Example.Criteria criteria = example.createCriteria();
        if (null != brand) {
            //根據名子模糊查詢 where name like '%蘋果%'
            String name = brand.getName();
            if (!StringUtils.isBlank(name)) {
                /**
                 * 1:Brand 屬性名
                 * 2:搜尋的條件
                 */
                criteria.andLike("name", "%" + name + "%");
            }
            //根據字首搜尋 and letter='A'
            String letter = brand.getLetter();
            if (!StringUtils.isBlank(letter)) {
                criteria.andEqualTo("letter", letter);
            }
        }

        List<Brand> brands = brandMapper.selectByExample(example);

        return new PageInfo<Brand>(brands);
    }


}
