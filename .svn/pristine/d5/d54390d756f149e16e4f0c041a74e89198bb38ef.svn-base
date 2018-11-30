package com.medhead.kf100.common.util;

import com.baomidou.mybatisplus.plugins.Page;
import com.medhead.kf100.common.dto.PageEntity;

/**
 * 常用工具类
 * */
public class CommonUtil {

    public static <T> PageEntity<T> pageToPageEntity(Page<T> page) {
        if(page == null) {
            throw new IllegalArgumentException("page is null");
        }
        PageEntity<T> pageEntity = new PageEntity<>();
        pageEntity.setCurrent(page.getCurrent());
        pageEntity.setPages(page.getPages());
        pageEntity.setRecords(page.getRecords());
        pageEntity.setSize(page.getSize());
        pageEntity.setTotal(page.getTotal());
        return pageEntity;
    }

}