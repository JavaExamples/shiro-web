package demo.shiro.service;

import demo.shiro.exception.LoginException;
import demo.shiro.exception.RegisterException;
import java.util.Map;

public interface UserService {

    void login(String username, String password, boolean isRememberMe) throws LoginException;

    void register(Map<String, String> fieldMap) throws RegisterException;
}
