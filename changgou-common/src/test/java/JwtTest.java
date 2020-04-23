import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class JwtTest {

    /****
     * 創建Jwt令牌
     */
    @Test
    public void testCreateJwt() {
        JwtBuilder builder = Jwts.builder()
                .setId("測試")
                .setSubject("Rex")
                .setIssuedAt(new Date())
                //1.使用HS256算法 2.設定SecretKey
                .signWith(SignatureAlgorithm.HS256, "rex123");
        //測試
        System.out.println(builder.compact());
    }
    /***
     * 解析Jwt
     */
    @Test
    public void testParseJwt(){
        String compactJwt="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiLmuKzoqaYiLCJzdWIiOiJSZXgiLCJpYXQiOjE1ODc2MjI0NDR9.aFr6mL2x0rcgtb0kgF1IN0qC4hz6M6pNEWYRusXLyrc";
        Claims claims = Jwts.parser().
                setSigningKey("rex123").
                parseClaimsJws(compactJwt).
                getBody();
        System.out.println(claims);
    }
}
