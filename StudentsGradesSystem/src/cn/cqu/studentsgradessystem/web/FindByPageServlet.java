package cn.cqu.studentsgradessystem.web;

import cn.cqu.studentsgradessystem.domain.PageBean;
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
import java.util.Map;

@WebServlet(urlPatterns = "/findByPageServlet")
public class FindByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取参数
        String _currentPage = request.getParameter("currentPage");
        String _rows = request.getParameter("rows");
        Map<String, String[]> parameterMap = request.getParameterMap();

        if (_currentPage==null||"".equals(_currentPage)){
            _currentPage = "1";
        }
        if (_rows==null||"".equals(_rows)){
            _rows = "5";
        }
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //调用listService对象进行操作
        ListService listService = new ListServiceImpl();
        PageBean paraByPage = listService.findByPage(currentPage, rows, parameterMap);
        request.setAttribute("pageBean",paraByPage);
        request.setAttribute("condition",parameterMap);
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
