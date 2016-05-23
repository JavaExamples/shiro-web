<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>注册</title>
</head>
<body>

<h1><a href="<c:url value="/"/>">首页</a> - 注册</h1>

<shiro:guest>
    <form action="<c:url value="/register"/>" method="post">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>登录密码：</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>再次输入：</td>
                <td><input type="password"/></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="submit">注册</button>
                </td>
            </tr>
        </table>
    </form>
    <c:if test="${requestScope['exception'] == 'RegisterException'}">
        <p>注册失败：用户已存在！</p>
    </c:if>
</shiro:guest>

<shiro:user>
    <c:redirect url="/space"/>
</shiro:user>

</body>
</html>