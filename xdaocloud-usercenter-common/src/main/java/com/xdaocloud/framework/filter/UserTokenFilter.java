package com.xdaocloud.framework.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.utils.JsonFormatUtils;
import com.xdaocloud.base.utils.RedisUtils;
import com.xdaocloud.framework.Constant;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

@WebFilter(urlPatterns = "/api/auth/*", filterName = "userTokenFilter")
public class UserTokenFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(UserTokenFilter.class);

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException {
        log.info("init UserTokenFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException,
        ServletException {
        log.info("doFilter UserTokenFilter");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setHeader("content-type", "application/json;charset=UTF-8");

        String requestURI = request.getRequestURI();
        // 接口白名单
        String[] whiteList = new String[] {"login", "logout", "register", "sms", "password", "token"};
        log.info("requestURI = " + requestURI);
        for (String string : whiteList) {
            if (requestURI.contains(string)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        String token = request.getHeader("Authorization");
        log.info("Header token = " + token);
        if (StringUtils.isBlank(token)) {
            String jsonString = JSON.toJSONString(new ResultInfo<>(ResultInfo.UNAUTHORIZED, "请检查请求Header中是否包含Token"));
            log.info(JsonFormatUtils.formatJson(jsonString));
            PrintWriter out = response.getWriter();
            out.print(jsonString);
            return;
        }
        token = token.replace("Bearer ", "");
        String userId = null;
        try {
            userId = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
        } catch (MalformedJwtException e) {
            String jsonString = JSON.toJSONString(new ResultInfo<>(ResultInfo.UNAUTHORIZED, "Token不合法"));
            log.info(JsonFormatUtils.formatJson(jsonString));
            PrintWriter out = response.getWriter();
            out.print(jsonString);
            return;
        } catch (ExpiredJwtException e) {
            String jsonString = JSON.toJSONString(new ResultInfo<>(ResultInfo.UNAUTHORIZED, "Token已过期，请重新登录"));
            log.info(JsonFormatUtils.formatJson(jsonString));
            PrintWriter out = response.getWriter();
            out.print(jsonString);
            return;
        }

        // 检查Token是否存在Redis中
        log.info("Token　UserId = " + userId);
        Object object = redisUtils.get(Constant.USER_TOKEN + userId);
        if (object == null) {
            String jsonString = JSON.toJSONString(new ResultInfo<>(ResultInfo.UNAUTHORIZED, "Token已过期，请重新登录"));
            log.info(JsonFormatUtils.formatJson(jsonString));
            PrintWriter out = response.getWriter();
            out.print(jsonString);
            return;
        }

        if (!token.equalsIgnoreCase(String.valueOf(object))) {
            String jsonString = JSON.toJSONString(new ResultInfo<>(ResultInfo.UNAUTHORIZED, "账号已在其它地方登录，如不是本人操作请修改密码"));
            log.info(JsonFormatUtils.formatJson(jsonString));
            PrintWriter out = response.getWriter();
            out.print(jsonString);
            return;
        }

        String jsonString = JSON.toJSONString(new ResultInfo<>(ResultInfo.SUCCESS, "Token验证通过"));
        log.info(JsonFormatUtils.formatJson(jsonString));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("UserTokenFilter has destroyed.");
    }
}