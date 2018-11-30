package com.medhead.kf100.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.medhead.kf100.mapper.TokenMapper;
import com.medhead.kf100.model.Token;
import com.medhead.kf100.service.TokenService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
@Service
@CacheConfig(cacheNames = "tokenCache", cacheManager = "cacheManager")
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {
    @Override
    @Cacheable(key = "#id")
    public Token selectById(Serializable id) {
        return baseMapper.selectById(id);
    }

    @Override
    @CacheEvict(key = "#id")
    public boolean deleteById(Serializable id) {
        return baseMapper.deleteById(id) > 0;
    }
}
