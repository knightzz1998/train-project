package cn.knightzz.train.member.config;

import cn.knightzz.train.common.interceptor.LogInterceptor;
import cn.knightzz.train.member.interceptor.MemberInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 王天赐
 * @title: SpringMvcConfig
 * @projectName train-project
 * @description:
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-07-14 11:32
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LogInterceptor logInterceptor;

    @Resource
    MemberInterceptor memberInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);

        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**")
                // 对下列请求URL不进行拦截
                .excludePathPatterns(
                        "/train-member/hello",
                        "/train-member/member/send-code",
                        "/train-member/member/login"
                );
    }
}