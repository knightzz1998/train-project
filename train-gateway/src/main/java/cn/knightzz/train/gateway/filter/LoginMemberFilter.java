package cn.knightzz.train.gateway.filter;

import cn.knightzz.train.gateway.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 王天赐
 * @title: LoginMemberFilter
 * @projectName train-project
 * @description: 登录拦截器
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-07-13 20:17
 */
@Component
public class LoginMemberFilter implements Ordered, GlobalFilter {

    private static final Logger LOG = LoggerFactory.getLogger(LoginMemberFilter.class);

    private static final String EXCLUDE_PATH_ADMIN = "/admin";
    private static final String EXCLUDE_PATH_LOGIN = "/train-member/member/login";
    private static final String EXCLUDE_PATH_SEND_CODE = "/train-member/member/send-code";
    private static final String JWT_TOKEN_NAME = "token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 1. 获取请求路径
        String path = exchange.getRequest().getURI().getPath();
        // 2. 过滤不需要验证的路径
        // 排除不需要拦截的请求
        if (path.contains(EXCLUDE_PATH_ADMIN)
                || path.contains(EXCLUDE_PATH_LOGIN)
                || path.contains(EXCLUDE_PATH_SEND_CODE)) {
            LOG.info("不需要登录验证：{}", path);
            return chain.filter(exchange);
        } else {
            LOG.info("需要登录验证：{}", path);
        }
        // 3. 从header中获取token
        String token = exchange.getRequest().getHeaders().getFirst(JWT_TOKEN_NAME);
        // 4. 校验token, 符合要求就通过
        LOG.info("会员登录验证开始，token：{}", token);
        if (token == null || token.isEmpty()) {
            LOG.info("token为空，请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 校验token是否有效，包括token是否被改过，是否过期
        boolean validate = JwtUtil.validate(token);
        if (validate) {
            LOG.info("token有效，放行该请求");
            return chain.filter(exchange);
        } else {
            LOG.warn("token无效，请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    /**
     * 优先级设置  值越小  优先级越高
     *
     * @return 优先级的值
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
