package cn.cqu.studentsgradessystem.web.servlet;

import cn.cqu.studentsgradessystem.dao.AdministratorDao;
import cn.cqu.studentsgradessystem.dao.impl.AdministratorDaoImpl;
import cn.cqu.studentsgradessystem.domain.Administator;
import cn.cqu.studentsgradessystem.service.AdministatorService;
import cn.cqu.studentsgradessystem.service.impl.AdministratorServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.提交表单前设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取验证吗进行校验
        String verifycode = request.getParameter("verifycode");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        //2.1保证一次校验
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        if ((checkcode_server==null||!checkcode_server.equalsIgnoreCase(verifycode))){
            request.setAttribute("loginFeedback","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        //3.验证码校验成功，封装admini对象
        Map<String, String[]> loginMap = request.getParameterMap();
        Administator loginAdministator = new Administator();
        try {
            BeanUtils.populate(loginAdministator,loginMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.校验用户名及密码
        AdministatorService administatorService = new AdministratorServiceImpl();
        Administator admi = administatorService.login(loginAdministator);
        if (admi != null) {
            //登录成功
            request.getSession().setAttribute("login_admin",admi);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else{
            //登陆失败
            request.setAttribute("loginFeedback","用户名或密码错误");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
