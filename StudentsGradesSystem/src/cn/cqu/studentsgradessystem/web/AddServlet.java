package cn.cqu.studentsgradessystem.web;

import cn.cqu.studentsgradessystem.domain.Student;
import cn.cqu.studentsgradessystem.service.ListService;
import cn.cqu.studentsgradessystem.service.impl.ListServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(urlPatterns = "/addServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        Map<String, String[]> newStu = request.getParameterMap();
        //封装数据
        Student newStudent = new Student();
        try {
            BeanUtils.populate(newStudent,newStu);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用底层服务添加数据
        ListService listService = new ListServiceImpl();
        listService.add(newStudent);
        request.getRequestDispatcher("/findByPageServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
