package cn.cqu.studentsgradessystem.web;

import cn.cqu.studentsgradessystem.domain.Student;
import cn.cqu.studentsgradessystem.service.ListService;
import cn.cqu.studentsgradessystem.service.impl.ListServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/listFindAllServlet")
public class ListFindAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ListService listService = new ListServiceImpl();
        List<Student> students = listService.finAll();
        request.setAttribute("allStudents",students);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
