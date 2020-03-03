package cn.cqu.web;

import cn.cqu.dao.UserDao;
import cn.cqu.domain.User;
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
public class LoginServlet extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、设置编码流
        req.setCharacterEncoding("utf-8");

        //2、获取参数
        Map<String, String[]> userMap = req.getParameterMap();
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser,userMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.通过数据库校验
        UserDao userDao = new UserDao();
        User returnUser = userDao.login(loginUser);
        if (returnUser == null){
            //登录失败
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else {
            //登陆成功
            req.setAttribute("user",returnUser);
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }
    }
}
