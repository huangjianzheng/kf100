package com.medhead.kf100.common.util;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * 生成jwt的工具类
 */
public class JwtUtils {

    /**
     * 签发人
     */
    private static final String ISSUER = "alliance";

    private JwtUtils() {

    }


    /**
     *生成jwt
     * @param tokenId 生成token id
     * @param userId 用户id
     * @param perms 权限字符串
     * @param jwtSecret 密钥
     * @param period 过期时间
     * @param currentTimeMillis 当前时间
     * @return jwt字符串
     * */
    public static String issueJwt(Long tokenId, Long userId, String perms, String jwtSecret, Long period, Long currentTimeMillis) {
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(jwtSecret);// 秘钥
        JwtBuilder jwt = Jwts.builder();
        if(tokenId != null) jwt.setId(tokenId.toString());
        if(userId != null) jwt.setSubject(userId.toString());
        if(StringUtils.isNotBlank(ISSUER)) jwt.setIssuer(ISSUER);//签发者
        jwt.setIssuedAt(new Date(currentTimeMillis));//签发时间
        Date expiration = new Date(currentTimeMillis + period);
        jwt.setExpiration(expiration);//有效时间
        if(StringUtils.isNotBlank(perms)) jwt.claim("perms", perms);//权限
        jwt.compressWith(CompressionCodecs.GZIP);//压缩可选GZIP
        jwt.signWith(SignatureAlgorithm.HS256, secretKeyBytes);//加密设置
        return jwt.compact();
    }


}
