package cn.cqu.studentsgradessystem.web.filter;

import cn.cqu.studentsgradessystem.domain.Administator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *完成登录校验，未登录的情况下需要跳转到login页面
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.强制类型转换
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //2.获取uri，判断是否包含登陆相关资源
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/login.jsp") ||
                requestURI.contains("/checkCodeServlet") ||
                requestURI.contains("/loginServlet") ||
                requestURI.contains("/css/")||
                requestURI.contains("/js/")||
                requestURI.contains("/fonts/")||
                requestURI.contains("/loginUserNameServlet")){
            //资源放行
            chain.doFilter(request, response);
        }else{
            Administator login_admin = (Administator) request.getSession().getAttribute("login_admin");
            //未放行，进行跳转
            if (login_admin != null) {
                //登陆成功,资源放行
                chain.doFilter(request,response);
            } else {
                //登陆失败
                request.setAttribute("loginFeedback","请先登录！");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
