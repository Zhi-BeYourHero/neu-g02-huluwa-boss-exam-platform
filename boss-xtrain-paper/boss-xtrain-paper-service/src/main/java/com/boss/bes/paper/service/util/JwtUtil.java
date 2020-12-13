package com.boss.bes.paper.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangziyi
 * @program: com.boss.bes.paper.service.util
 * @Description:
 * @Date: 2020/7/9 9:22
 * @since: 1.0
 */
@Slf4j
public class JwtUtil {

    public static final String KEY = "022bdc63c3c5a45879ee6581508b9d03adfec4a4658c0ab3d722e50c91a351c42c231cf43bb8f86998202bd301ec52239a74fc0c9a9aeccce604743367c9646b";

    private JwtUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey(){
        byte[] encodedKey = Base64.decodeBase64(KEY);

        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 创建jwt
     * @param id
     * @param issuer
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public static String createJwt(String id, String issuer, String subject, long ttlMillis) {

        // 指定签名的时候使用的签名算法，也就是header那部分，jwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<>(3);
        claims.put("uid", "123456");
        claims.put("user_name", "admin");
        claims.put("nick_name", "X-rapido");

        // 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
        // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey key = generalKey();

        // 下面就是在为payload添加各种标准声明和私有声明了
        //1. 这里其实就是new一个JwtBuilder，设置jwt的body
        //2. 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
        //3. 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
        //4. iat: jwt的签发时间
        //5. issuer：jwt签发人
        //6. sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
        //7. 设置签名使用的签名算法和签名使用的秘钥
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);

        // 设置过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 检查jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims checkJwt(String jwt) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        //1.得到DefaultJwtParser
        //2.设置签名的秘钥
        //3.设置需要解析的jwt
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
    }

    /**
     * 获取用户id
     * @param jwt
     * @return
     * @throws JsonProcessingException
     */
    public static Long getUserId(String jwt) throws JsonProcessingException {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        //1.得到DefaultJwtParser
        //2.设置签名的秘钥
        //3.设置需要解析的jwt
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JwtModel jwtModel = objectMapper.readValue(claims.getSubject(),JwtModel.class);
        return jwtModel.getUserId();
    }

    /**
     * 获取Jwt模型
     * @param jwt
     * @return
     * @throws JsonProcessingException
     */
    public static JwtModel getJwtModel(String jwt) throws JsonProcessingException {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        //1.得到DefaultJwtParser
        //2.设置签名的秘钥
        //3.设置需要解析的jwt
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(claims.getSubject(),JwtModel.class);
    }
}