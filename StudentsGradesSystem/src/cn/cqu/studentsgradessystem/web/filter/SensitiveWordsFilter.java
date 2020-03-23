package cn.cqu.studentsgradessystem.web.filter;

import org.springframework.core.style.ValueStyler;

import javax.lang.model.element.NestingKind;
import javax.management.ValueExp;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebFilter(urlPatterns = "/addServlet")
public class SensitiveWordsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //创建代理对象
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //判断方法是否为getParameter方法
                if (method.getName().equals("getParameter")) {
                    //获取原方法的返回值
                    String value = (String) method.invoke(req,args);
                    if (value != null) {
                        for (String s : senWords) {
                            if (value.contains(s)) {
                                //替换敏感字符
                                value = value.replaceAll(s, "***");
                            }
                        }
                    }
                    return value;
                }
                if (method.getName().equals("getParameterMap")) {
                    //获取原方法的返回值
                    Map<String,String[]> value = (Map<String, String[]>) method.invoke(req,args);
                    if (value != null) {
                        for (Map.Entry<String,String[]> s : value.entrySet()) {
                            for (String sWords : senWords) {
                                if (s.getValue()[0].contains(sWords)) {
                                    //替换敏感字符
                                    s.getValue()[0] = s.getValue()[0].replaceAll(sWords,"***");
                                }
                            }
                        }
                    }
                    return value;
                }
                if (method.getName().equals("getParameterValues")) {
                    //获取原方法的返回值
                    String[] value = (String[]) method.invoke(req,args);
                    if (value != null) {
                        for (String s : value) {
                            for (String sWords : senWords) {
                                if (s.equals(sWords)) {
                                    //替换敏感字符
                                    s = s.replaceAll(sWords,"***");
                                }
                            }
                        }
                    }
                    return value;
                }
                //如果调用的是其他方法，则返回原方法返回值
                return method.invoke(req,args);
            }
        });
        chain.doFilter(proxy_req, resp);
    }
    private List<String> senWords = new ArrayList<>();
    public void init(FilterConfig config) throws ServletException {
        //涉及到文件操作，需要获取文件的真实路径
        String realPath = config.getServletContext().getRealPath("/WEB-INF/classes/sensitiveWords.txt");
        try {
            //新建bufferedReader进行读写操作
            BufferedReader bufferedReader = new BufferedReader(new FileReader(realPath));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                senWords.add(line);
            }
            System.out.println(senWords);
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
