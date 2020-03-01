package cn.cqu.web;

import cn.cqu.dao.UserDao;
import cn.cqu.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        UserDao userDao = new UserDao();
        User returnUser = userDao.login(loginUser);
        if (returnUser == null){

            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else {
            req.setAttribute("user",returnUser);
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }
    }
}
