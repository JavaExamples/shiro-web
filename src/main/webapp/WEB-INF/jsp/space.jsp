<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>我的空间</title>
</head>
<body>

<h1><a href="<c:url value="/"/>">首页</a> - 空间</h1>

<p>用户名：<shiro:principal/></p>

<dl>
    <dt>角色：</dt>
    <shiro:hasRole name="admin">
        <dd>管理员</dd>
    </shiro:hasRole>
    <shiro:hasRole name="user">
        <dd>普通用户</dd>
    </shiro:hasRole>
</dl>

<dl>
    <dt>权限：</dt>
    <shiro:hasPermission name="product.view">
        <dd>查看产品</dd>
    </shiro:hasPermission>
    <shiro:hasPermission name="product.new">
        <dd>新增产品</dd>
    </shiro:hasPermission>
    <shiro:hasPermission name="product.edit">
        <dd>编辑产品</dd>
    </shiro:hasPermission>
    <shiro:hasPermission name="product.delete">
        <dd>删除产品</dd>
    </shiro:hasPermission>
</dl>

<a href="<c:url value="/logout"/>">退出</a>

</body>
</html>