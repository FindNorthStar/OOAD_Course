<%--
  Created by IntelliJ IDEA.
  User: Java
  Date: 2016/5/29
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="/Course_war_exploded/bootstrap/css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1>请注册</h1>
        <div class="form-group">
            <form method="POST" action="registerUser.action">
                <div class="col-md-2">
                    <input name="userId" placeholder="工号/学号" class="form-control" />
                </div>
                <div class="col-md-2">
                    <input name="username" placeholder="姓名" class="form-control" />
                </div>
                <div class="col-md-2">
                    <input type="password" name="password" placeholder="密码" class="form-control"/>
                </div>
                <div class="col-md-2">
                    <label for="identities" class="sr-only">身份</label>
                    <select class="form-control" id="identities" name="identity">
                        <option value="student">student</option>
                        <option value="teacher">teacher</option>
                        <option value="admin">admin</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">注册</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
