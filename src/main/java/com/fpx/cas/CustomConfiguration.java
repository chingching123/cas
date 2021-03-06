package com.fpx.cas;

import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * @author chengbin
 * 创建日期:2019/06/03
 *
 */
public class CustomConfiguration  implements AuthenticationEventExecutionPlanConfigurer {
    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    //注册验证器
    @Bean
    public AuthenticationHandler customAuthenticationHandler() {
        //优先验证
        return new CustomerHandler("customerHandler",
                servicesManager, new DefaultPrincipalFactory(), 1);
    }

    //注册自定义认证器
    @Override
    public void configureAuthenticationExecutionPlan(final AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(customAuthenticationHandler());
    }
}