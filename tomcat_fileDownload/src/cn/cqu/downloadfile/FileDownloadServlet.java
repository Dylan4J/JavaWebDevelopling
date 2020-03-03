package cn.cqu.downloadfile;

import cn.cqu.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = "/fileDownloadServlet")
public class FileDownloadServlet extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取文件名
        String filename = req.getParameter("filename");
        //2.获取文件在服务器中的真实路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/img/"+filename);

        //3.将资源加载进内存中
        FileInputStream fis = new FileInputStream(realPath);
        //4.设置响应首部的格式
        //4.1设置mimetype类型
        String mimeType = servletContext.getMimeType(filename);
        resp.setContentType(mimeType);
        //4.1设置响应头中的content-attachment的内容
        String agent = req.getHeader("user-agent");
        filename = DownLoadUtils.getFileName(agent, filename);
        resp.setHeader("content-disposition","attachment;filename="+filename);
        ServletOutputStream ops = resp.getOutputStream();
        byte[] buff = new byte[1024*8];
        int len = 0;
        while ((len = fis.read(buff))!=-1){
            ops.write(buff);
        }
        ops.close();
        fis.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
