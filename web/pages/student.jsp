
<%--
  Created by IntelliJ IDEA.
  User: Java
  Date: 2016/6/15
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>StudentPanel</title>
    <link rel="stylesheet" href="/Course_war_exploded/bootstrap/css/bootstrap.min.css">
    <style>
        body {
            padding-top : 50px;
        }
    </style>
</head>
<body>

<header>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#example-nav-collapse" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand">欢迎登陆</a>
            </div>
            <div class="collapse navbar-collapse" id="example-nav-collapse">
                <ul class="nav navbar-nav">
                    <li><p class="navbar-text">编号：${user.id}</p></li>
                    <li><p class="navbar-text">姓名：${user.username}</p></li>
                    <li><p class="navbar-text">班级：${student.classes}</p></li>
                    <li>
                        <form action="timetable.action" class="nav-form" method="post">
                            <button type="submit" class="btn btn-primary navbar-btn">查看课表</button>
                        </form>
                    </li>
                    <li>
                        <form action="chooselesson.action" class="nav-form" method="post">
                            <button type="submit" class="btn btn-primary navbar-btn">选择课程</button>
                        </form>
                    </li>
                </ul>
                <form action="logout.action" class="nav-form navbar-right" method="post" role="button">
                    <button type="submit" class="btn btn-default navbar-btn" >退出</button>
                </form>
            </div>
        </div>
    </nav>
</header>

<div class="container">
    <div class="jumbotron">
        <h1>修改学生信息</h1>
        <hr/>
        <form action="updatestudent.action" method="post">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">学生概要</div>
                <!-- Table -->
                <table class="table">
                    <thead>
                    <tr>
                        <th>学号</th><th>姓名</th><th>密码</th><th>身份</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">
                            <input class="form-control" name="number" type="text" value="${user.id}" style="width:120px;" readonly="readonly"/>
                        </th>
                        <td>
                            <input class="form-control" name="name" type="text" value="${user.username}" style="width:100px;"/>
                        </td>
                        <td>
                            <input class="form-control" name="password" type="text" value="${user.password}" style="width:100px;"/>
                        </td>
                        <td>
                            <input class="form-control" name="identity" type="text" value="${user.identity}" style="width:120px;"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <button type="submit" class="btn btn-primary">更新个人信息</button>
        </form>

        <hr/>
        <%--action to modify--%>
        <form action="updateclasses.action" class="form-inline" role="form" method="post">
            <div class="form-group">
                <h2>修改班级</h2>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon0">班级</span>
                    <input class="form-control" name="newClasses" type="text" placeholder="number" value="${student.classes}"/>
                </div>
                <button type="submit" class="btn btn-primary">更新</button>
            </div>
        </form>

    </div>
</div>

</body>
</html>
