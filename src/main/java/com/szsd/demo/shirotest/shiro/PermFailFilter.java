package com.szsd.demo.shirotest.shiro;

import com.alibaba.fastjson.JSONObject;
import com.szsd.demo.shirotest.util.result.ResultGenerator;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * API接口权限验证返回信息
 */
public class PermFailFilter extends PermissionsAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        Subject subject = getSubject(request, response);
        // If the subject isn't identified, redirect to login URL
        if (subject.getPrincipal() == null) {
            //跳转至登录页
            saveRequestAndRedirectToLogin(request, response);
        } else {
            //给前端提示无接口访问权限的错误码
            saveRequestAndReturnApiAccessError(request, response);
        }
        return false;
    }

    private void saveRequestAndReturnApiAccessError(ServletRequest request, ServletResponse response) {
        saveRequest(request);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("code", "-108");
//        jsonObject.put("desc", "无权限请求对应api接口");
        try {
            flushMsgStrToClient(response, ResultGenerator.genUnPowerResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {


        try {
            flushMsgStrToClient(response, ResultGenerator.loginLoseFail());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void flushMsgStrToClient(ServletResponse response, Object object)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(object));
        response.getWriter().flush();
    }


}
