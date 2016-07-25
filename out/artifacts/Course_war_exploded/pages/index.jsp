<%--
  Created by IntelliJ IDEA.
  User: Java
  Date: 2016/5/28
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/Course_war_exploded/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1>学生选课系统</h1>
        </div>
    </div>
    <div class="container">
        <div class="jumbotron">
            <h1>请登录</h1>
            <div class="form-group">
                <form method="POST" action="login.action">
                    <div class="col-md-3">
                    <input name="userId" placeholder="工号/学号" class="form-control" />
                    </div>
                    <div class="col-md-3">
                    <input type="password" name="password" placeholder="密码" class="form-control"/>
                    </div>
                    <button type="submit" class="btn btn-primary">登录</button>
                </form>
                <form name="registerForm" method="POST" action="register.action">
                    <button type="submit" class="btn btn-primary">注册</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
