package demo.shiro.servlet;

import demo.shiro.exception.RegisterException;
import demo.shiro.service.UserService;
import demo.shiro.service.impl.UserServiceImpl;
import demo.shiro.tool.Web;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 转发到注册页面
        Web.forward("register");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 封装注册服务所需的字段
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("username", username);
        fieldMap.put("password", password);

        // 调用注册服务
        try {
            userService.register(fieldMap);
        } catch (RegisterException e) {
            request.setAttribute("exception", e.getName());
            doGet(request, response);
            return;
        }

        // 调用登录服务并重定向到空间页面
        userService.login(username, password, false);
        Web.redirect("/space");
    }
}
