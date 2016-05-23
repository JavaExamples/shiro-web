package demo.shiro.service.impl;

import demo.shiro.exception.LoginException;
import demo.shiro.exception.RegisterException;
import demo.shiro.service.UserService;
import demo.shiro.tool.Database;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private PasswordService passwordService = new DefaultPasswordService();

    @Override
    public void login(String username, String password, boolean isRememberMe) throws LoginException {
        // 创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(isRememberMe);

        // 获取当前用户，并进行登录操作
        Subject user = SecurityUtils.getSubject();
        try {
            user.login(token);
        } catch (AuthenticationException e) {
            logger.error("", e);
            throw new LoginException(e);
        }
    }

    @Override
    public void register(Map<String, String> fieldMap) throws RegisterException {
        String username = fieldMap.get("username");
        String password = fieldMap.get("password");

        // 在 user 表中根据 username 查询用户是否已存在
        String selectSQL = "select count(*) from user where username = ?";
        Long userCount = Database.queryColumn(selectSQL, username);
        if (userCount > 0) {
            throw new RegisterException();
        }

        // 加密密码
        password = passwordService.encryptPassword(password);
        System.out.println("password:" + password);
        // 插入 user 表
        String insertSQL = "insert into user (username, password) values (?, ?)";
        Database.update(insertSQL, username, password);
    }
}
