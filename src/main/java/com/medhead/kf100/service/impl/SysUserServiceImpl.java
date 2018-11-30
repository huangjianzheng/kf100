package com.medhead.kf100.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.medhead.kf100.common.dto.PageEntity;
import com.medhead.kf100.common.util.CommonUtil;
import com.medhead.kf100.common.util.MD5Utils;
import com.medhead.kf100.common.util.contant.CommonConstants;
import com.medhead.kf100.exception.BusinessException;
import com.medhead.kf100.mapper.SysUserMapper;
import com.medhead.kf100.mapper.SysUserRoleMapper;
import com.medhead.kf100.mapper.TokenMapper;
import com.medhead.kf100.model.SysUser;
import com.medhead.kf100.model.SysUserRole;
import com.medhead.kf100.model.Token;
import com.medhead.kf100.model.dto.SysUserDto;
import com.medhead.kf100.service.SysUserService;
import com.medhead.kf100.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
@Service
@CacheConfig(cacheNames = "userCache", cacheManager = "cacheManager")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SysUserMapper userMapper;

    @Override
    @Cacheable(key = "#id")
    public SysUser selectById(Serializable id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Page<SysUser> selectManagerUserListByPage(Page<SysUser> page, String keywords) {
        page.setRecords(baseMapper.selectManagerUserListByPage(page, keywords));
        return page;
    }

    @Override
    public void newManagerUser(String userName, String account, String passWord, Long roleId) {
        if (StringUtils.isEmpty(userName)) {
            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "管理员姓名不能为空");
        }
        if (StringUtils.isEmpty(account)) {
            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "管理员账号不能为空");
        }
        if (StringUtils.isEmpty(passWord)) {
            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "管理员密码不能为空");
        }
        if (!roleId.equals(2L) && !roleId.equals(3L)) {
            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "管理员身份为空或数据异常");
        }
        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        ew.eq("account", account);
        int count = baseMapper.selectCount(ew);
        if (count > 0) {
            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "管理员账号已存在");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserType(3);
        sysUser.setStatus(1);
        sysUser.setAccountType(1);
        sysUser.setUserName(userName);
        sysUser.setAccount(account);
        sysUser.setPassWord(MD5Utils.MD5EncodePwd(passWord, account));
        baseMapper.insert(sysUser);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(roleId);
        sysUserRole.setUserId(sysUser.getId());
        sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    @CacheEvict(key = "#userId")
    public void updateManagerUser(Long userId, String userName, String passWord, Long roleId) {
        if (userId == null) {
            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "管理员id不能为空");
        }
        if (StringUtils.isEmpty(userName)) {
            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "管理员姓名不能为空");
        }
        if (!roleId.equals(2L) && !roleId.equals(3L)) {
            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "管理员身份为空或数据异常");
        }
        SysUser sysUser = this.selectById(userId);
        if (sysUser == null || sysUser.getStatus().equals(0)) {
            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "管理员不存在");
        }
        if (sysUser.getSysUserRoleList().stream().anyMatch(sysUserRole -> sysUserRole.getRoleId().equals(1L))) {
            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "超级管理员无法修改");
        }
        if (sysUser.getSysUserRoleList().stream().noneMatch(sysUserRole -> sysUserRole.getRoleId().equals(2L) || sysUserRole.getRoleId().equals(3L))) {
            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "修改的用户非管理员");
        }

        EntityWrapper<SysUser> ew = new EntityWrapper<>();

        SysUser model = new SysUser();
        if (StringUtils.isNotEmpty(passWord)) {
            model.setPassWord(MD5Utils.MD5EncodePwd(passWord, sysUser.getAccount()));
        }
        model.setUserName(userName);
        model.setId(userId);
        baseMapper.updateById(model);
        EntityWrapper<SysUserRole> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_id", userId);
        entityWrapper.in("role_id", new Long[]{2L, 3L});
        sysUserRoleMapper.delete(entityWrapper);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(roleId);
        sysUserRole.setUserId(sysUser.getId());
        sysUserRoleMapper.insert(sysUserRole);
        Token condition = new Token();
        condition.setUserId(sysUser.getId());
        List<Token> list = tokenMapper.selectList(new EntityWrapper<>(condition));
        // 移除缓存
        if (list != null) {
            Cache tokenCache = cacheManager.getCache("tokenCache");
            list.stream().map(Token::getId).forEach(tokenCache::evict);
            tokenMapper.delete(new EntityWrapper<>(condition));
        }
    }


    @Override
    @CacheEvict(key = "#entity.getId()")
    public boolean updateById(SysUser entity) {
        return super.updateById(entity);
    }


    @Override
    public PageEntity<SysUser> selectSysUserListByUser(SysUserDto sysUserDto) {
        //分页对象
        Page<SysUser> page1 = new Page<>(sysUserDto.getCurrent(), sysUserDto.getSize());
        // 创建对象
        SysUser sysUser1 = new SysUser();
        //赋值
        if (sysUserDto.getUserName() != null && sysUserDto.getUserName() != "") {
            sysUser1.setUserName(sysUserDto.getUserName());
        }
        if (sysUserDto.getAccount() != null && sysUserDto.getAccount() != "") {
            sysUser1.setAccount(sysUserDto.getAccount());
        }
        if (sysUserDto.getEmail() != null && sysUserDto.getEmail() != "") {
            sysUser1.setEmail(sysUserDto.getEmail());
        }
        List<SysUser> list = userMapper.selectAllUserListByPage(page1, sysUser1);
        return CommonUtil.pageToPageEntity(page1.setRecords(list));
    }

    /**
     * 获取用户企业信息
     *
     * @param userId
     * @return
     */
    @Override
    public SysUser getSysUserCompany(Integer userId) {
        return userMapper.getUserCompanyById(userId);
    }

    /**
     * 获取用户个人信息
     *
     * @param userId
     * @return
     */
    @Override
    public SysUser getSysUserInfo(Integer userId) {
        return userMapper.getUserInfo(userId);
    }
}
