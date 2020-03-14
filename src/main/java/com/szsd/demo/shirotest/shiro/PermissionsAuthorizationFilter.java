package com.szsd.demo.shirotest.shiro;

import com.alibaba.fastjson.JSONObject;
import com.szsd.demo.shirotest.util.result.ErrorEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author: hxy
 * @description: 对没有登录的请求进行拦截, 全部返回json信息. 覆盖掉shiro原本的跳转login.jsp的拦截方式
 * @date: 2017/10/24 10:11
 */
public class PermissionsAuthorizationFilter extends FormAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(PermissionsAuthorizationFilter.class);

    public PermissionsAuthorizationFilter() {
        super();
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Always return true if the request's method is OPTIONSif (request instanceof HttpServletRequest) {
        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    };

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ErrorEnum.E_502.getErrorCode());
        jsonObject.put("msg", ErrorEnum.E_502.getErrorMsg());
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = response.getWriter();
            out.println(jsonObject);
        } catch (Exception e) {
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }

    @Bean
    public FilterRegistrationBean registration(PermissionsAuthorizationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
