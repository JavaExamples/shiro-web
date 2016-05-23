package demo.shiro.servlet;

import demo.shiro.exception.LoginException;
import demo.shiro.service.UserService;
import demo.shiro.service.impl.UserServiceImpl;
import demo.shiro.tool.Web;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 转发到登录页面
        Web.forward("login");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isRememberMe = request.getParameter("rememberMe") != null;

        // 调用登录服务
        try {
            userService.login(username, password, isRememberMe);
        } catch (LoginException e) {
            request.setAttribute("exception", e.getName());
            doGet(request, response);
            return;
        }

        // 重定向到空间页面
        Web.redirect("/space");
    }
}
