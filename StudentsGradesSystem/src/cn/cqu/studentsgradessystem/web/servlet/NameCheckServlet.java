package cn.cqu.studentsgradessystem.web.servlet;

import cn.cqu.studentsgradessystem.domain.Administator;
import cn.cqu.studentsgradessystem.service.ListService;
import cn.cqu.studentsgradessystem.service.impl.ListServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/nameCheckServlet")
public class NameCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ggg");
        response.setContentType("application/json;charset=utf-8");
        //获取前端的参数
        String username = request.getParameter("name");
        //调用service层查询数据库
        ListService listService  = new ListServiceImpl();
        Administator AdByName = listService.findByName(username);
        //判断是否获取到了对应username的对象
        Map<String,Object> map = new HashMap<>();
        if (AdByName!=null){
            //查询成功
            map.put("exist",true);
            map.put("msg","该用户名已存在，请更换用户名！");
        }else{
            map.put("exist",false);
            map.put("msg","该用户名可用！");
        }
        ObjectMapper om = new ObjectMapper();
        om.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
