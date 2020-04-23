package com.changgou.filter;


import com.changgou.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    //令牌名稱
    private static final String AUTHORIZE_TOKEN = "Authorization";
    /***
     * 全局過濾器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //取得 Request、Response
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //取得 URI
        String path = request.getURI().getPath();
        //如果登入
        if (path.startsWith("/user/login") || path.startsWith("/brand/search/")) {
            //放行
            Mono<Void> filter = chain.filter(exchange);
            return filter;
        }
        //取得Header信息
        String tokent = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        //如果Header中没有，從參數中拿
        if (StringUtils.isEmpty(tokent)) {
            tokent = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
        }
        //如果空，輸出錯誤代碼
        if (StringUtils.isEmpty(tokent)) {
            //不允許訪問
            response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
            return response.setComplete();
        }
        //解析令牌
        try {
            Claims claims = JwtUtil.parseJWT(tokent);
        } catch (Exception e) {
            e.printStackTrace();
            //解析失败，回應401錯誤
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //放行
        return chain.filter(exchange);
    }


    /***
     * 過濾器放行順序
     * 越低越先執行
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
