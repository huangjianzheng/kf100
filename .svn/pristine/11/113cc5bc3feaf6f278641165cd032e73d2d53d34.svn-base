package com.medhead.kf100.web.admin;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.medhead.kf100.common.dto.PageEntity;
import com.medhead.kf100.common.dto.ResponseResult;
import com.medhead.kf100.common.util.CommonUtil;
import com.medhead.kf100.common.util.contant.CommonConstants;
import com.medhead.kf100.exception.BusinessException;
import com.medhead.kf100.model.Article;
import com.medhead.kf100.model.ArticleCategory;
import com.medhead.kf100.model.dto.AdminArticleSearchDto;
import com.medhead.kf100.service.ArticleCategoryService;
import com.medhead.kf100.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ming
 * @since 2018-11-26
 */
@RestController
@RequestMapping("/api/admin/article")
@ApiIgnore
public class AdminArticleController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private ArticleService articleService;

    @PostMapping("category/list")
    public ResponseResult<List<ArticleCategory>> getArticleCategoryList(Integer level, Long parentId) {
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setLevel(level);
        articleCategory.setParentId(parentId);
        EntityWrapper<ArticleCategory> ew = new EntityWrapper<>(articleCategory);
        return ResponseResult.success(articleCategoryService.selectList(ew));
    }
    @PostMapping("list")
    public ResponseResult<PageEntity<Article>> getArticleList(AdminArticleSearchDto dto) {
        if(dto == null){
            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE,"参数错误");

        }
        Page<Article> page = new Page<>();
        page.setCurrent(dto.getCurrent());
        page.setSize(dto.getPageSize());
        EntityWrapper<Article> ew = new EntityWrapper<>();
        // todo:查询待完成
        return ResponseResult.success(CommonUtil.pageToPageEntity(articleService.selectPage(page,ew)));
    }

}

