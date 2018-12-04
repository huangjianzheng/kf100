package com.medhead.kf100.web.pc;

import com.baomidou.mybatisplus.entity.Column;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.medhead.kf100.common.dto.ResponseResult;
import com.medhead.kf100.common.util.JwtUtils;
import com.medhead.kf100.common.util.MD5Utils;
import com.medhead.kf100.common.util.VerifyCodeUtil;
import com.medhead.kf100.model.*;
import com.medhead.kf100.model.vo.HomePageVo;
import com.medhead.kf100.model.vo.VerifyCodeVo;
import com.medhead.kf100.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pc/user")
public class PcUserController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserService sysUserService;
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.period}")
    private Long jwtPeriod;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ArticleCategoryService articleCategoryService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CourseService courseService;

    @GetMapping("homePage")
    public ResponseResult<HomePageVo> homePage() {
        HomePageVo homePageVo = new HomePageVo();
        homePageVo.setBannerList(getBannerList());
        homePageVo.setExpertList(getExpertList());
        homePageVo.setCourseList(getCourseList());
        homePageVo.setNavList(getNavList());
        homePageVo.setNewsList(getNewsList());
        homePageVo.setNoticeList(getNoticeList());
        return ResponseResult.success(homePageVo);
    }

    private List<ArticleCategory> getNavList() {
        EntityWrapper<ArticleCategory> ew = new EntityWrapper<>();
        List<ArticleCategory> categoryList = articleCategoryService.selectList(ew);
        List<ArticleCategory> list = categoryList.stream().filter(c -> c.getLevel().equals(1)).collect(Collectors.toList());
        list.forEach(c -> {
            c.setChildrens(categoryList.stream().filter(category -> c.getId().equals(category.getParentId())).collect(Collectors.toList()));
        });
        return list;
    }

    private List<Banner> getBannerList() {
        EntityWrapper<Banner> ew = new EntityWrapper<>();
        ew.setSqlSelect(
                Column.create().column("id"),
                Column.create().column("logo"),
                Column.create().column("url")
        );
        ew.eq("type",1);
        ew.eq("status",1);
        return bannerService.selectList(ew);
    }

    private List<Article> getNewsList() {
        EntityWrapper<Article> ew = new EntityWrapper<>();
        ew.setSqlSelect(
                Column.create().column("id"),
                Column.create().column("create_time").as("timeStamp"),
                Column.create().column("title"),
                Column.create().column("introduction")
        );
        ew.eq("type",1);
        ew.eq("status",1);
        ew.last("limit 4");
        ew.orderBy("id" ,false);
        return articleService.selectList(ew);
    }

    private List<Article> getNoticeList() {
        EntityWrapper<Article> ew = new EntityWrapper<>();
        ew.setSqlSelect(
                Column.create().column("id"),
                Column.create().column("create_time").as("timeStamp"),
                Column.create().column("title"),
                Column.create().column("introduction")
        );
        ew.eq("type",2);
        ew.eq("status",1);
        ew.last("limit 4");
        ew.orderBy("id" ,false);
        return articleService.selectList(ew);
    }

    private List<Course> getCourseList() {
        EntityWrapper<Course> ew = new EntityWrapper<>();
        ew.setSqlSelect(
                Column.create().column("id"),
                Column.create().column("title"),
                Column.create().column("logo")
        );
        ew.eq("status",1);
        ew.last("limit 3");
        ew.orderBy("id" ,false);
        return courseService.selectList(ew);
    }

    private List<Banner> getExpertList() {
        EntityWrapper<Banner> ew = new EntityWrapper<>();
        ew.setSqlSelect(
                Column.create().column("id"),
                Column.create().column("logo"),
                Column.create().column("title"),
                Column.create().column("tag")
        );
        ew.eq("type",2);
        ew.eq("status",1);
        return bannerService.selectList(ew);
    }


    @PostMapping("login")
    public ResponseResult login(String username, String password) {
        if(StringUtils.isEmpty(username)) {
            return ResponseResult.badRequest("用户名为空");
        }
        if(StringUtils.isEmpty(password)) {
            return ResponseResult.badRequest("密码为空");
        }
        SysUser model = new SysUser();
        model.setAccount(username);
        model.setStatus(1);
        SysUser sysUser = sysUserService.selectOne(new EntityWrapper<>(model));
        if(sysUser == null) {
            return ResponseResult.badRequest("用户名不存在");
        }
        String pwd = MD5Utils.MD5EncodePwd(password, username);
        if(!pwd.equals(sysUser.getPassWord())) {
            return ResponseResult.badRequest("密码错误");
        }
        Token condition = new Token();
        condition.setUserId(sysUser.getId());
        List<Token> list = tokenService.selectList(new EntityWrapper<>(condition));
        // 移除缓存
        if(list != null) {
            Cache tokenCache = cacheManager.getCache("tokenCache");
            list.stream().map(Token::getId).forEach(tokenCache::evict);
            tokenService.delete(new EntityWrapper<>(condition));
        }
        Token token = new Token();
        token.setUserId(sysUser.getId());
        Date now = new Date();
        token.setCreateTime(now);
        Long expireMillis = now.getTime() + jwtPeriod;
        Date expireTime = new Date(expireMillis);
        token.setExpireTime(expireTime);
        tokenService.insert(token);
        String jwt = JwtUtils.issueJwt(token.getId(), token.getUserId(), null, jwtSecret, jwtPeriod, now.getTime());
        return ResponseResult.success(jwt);
    }

    @GetMapping("getVerifyCode")
    public ResponseResult getVerifyCode() {
        String code = VerifyCodeUtil.getRandomNumeric(4);
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(code, 120, 40, 5, true, Color.white, null, null);
        String base64 = VerifyCodeUtil.imageToBase64(bufferedImage);
        String vid = UUID.randomUUID().toString();
        vid = vid.replaceAll("-", "");
        VerifyCodeUtil.registerVerifyCodeCache.put("vid", base64);
        return ResponseResult.success(new VerifyCodeVo(vid, base64));
    }

    @GetMapping("checkVerifyCode")
    public ResponseResult checkVerifyCode(String vid, String verifyCode) {
        if(StringUtils.isEmpty(vid)) {
            return ResponseResult.badRequest("vid为空");
        }
        if(StringUtils.isEmpty(verifyCode)) {
            return ResponseResult.badRequest("验证码为空");
        }
        if(verifyCode.equals(VerifyCodeUtil.registerVerifyCodeCache.getIfPresent(vid))) {
            return ResponseResult.success();
        } else {
            return ResponseResult.badRequest("验证码错误");
        }
    }


    @PostMapping("register")
    public ResponseResult register(String username, String password, String email, String verifyCode, Integer type, String vid) {
        if(StringUtils.isEmpty(username)) {
            return ResponseResult.badRequest("用户名为空");
        }
        if(StringUtils.isEmpty(password)) {
            return ResponseResult.badRequest("密码为空");
        }
        if(StringUtils.isEmpty(email)) {
            return ResponseResult.badRequest("邮箱为空");
        }
        if(StringUtils.isEmpty(verifyCode)) {
            return ResponseResult.badRequest("验证码为空");
        }
        if(StringUtils.isEmpty(verifyCode)) {
            return ResponseResult.badRequest("验证码为空");
        }
        if(type == null) {
            return ResponseResult.badRequest("用户类型为空");
        }
        if(StringUtils.isEmpty(vid)) {
            return ResponseResult.badRequest("vid为空");
        }
        if(type != 1 && type != 2) {
            return ResponseResult.badRequest("未知用户类型");
        }
        if(!verifyCode.equals(VerifyCodeUtil.registerVerifyCodeCache.getIfPresent(vid))) {
            return ResponseResult.badRequest("验证码错误");
        }

        SysUser condition = new SysUser();
        condition.setAccount(username);
        if(sysUserService.selectCount(new EntityWrapper<>(condition)) > 0) {
            return ResponseResult.badRequest("用戶名已存在");

        }
        SysUser sysUser = new SysUser();
        sysUser.setAccount(username);
        sysUser.setPassWord(MD5Utils.MD5EncodePwd(password, username));
        sysUser.setUserType(type);
        sysUser.setAccountType(1);
        sysUser.setEmail(email);
        sysUserService.insert(sysUser);
        return ResponseResult.success();
    }

}
