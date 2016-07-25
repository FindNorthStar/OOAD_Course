<%--
  Created by IntelliJ IDEA.
  User: Java
  Date: 2016/6/15
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>TimeTable</title>
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
                        <form action="studentpanel.action" class="nav-form" method="post">
                            <button type="submit" class="btn btn-primary navbar-btn">个人信息</button>
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
        <h1>学生课表</h1>
        <hr/>
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">本学期课表</div>
                <!-- Table -->
                <table class="table">
                    <thead>
                    <tr>
                        <th>上课时间</th><th>星期一</th><th>星期二</th><th>星期三</th><th>星期四</th><th>星期五</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="tableItem" items="${tableList}">
                        <tr>
                            <th scope="row">
                                <input class="form-control" type="text" value="${tableItem.time}" style="width:120px;" readonly="readonly"/>
                            </th>
                            <td>
                                <input class="form-control" type="text" value="${tableItem.day1}" style="width:140px;" readonly="readonly"/>
                            </td>
                            <td>
                                <input class="form-control" type="text" value="${tableItem.day2}" style="width:140px;" readonly="readonly"/>
                            </td>
                            <td>
                                <input class="form-control" type="text" value="${tableItem.day3}" style="width:140px;" readonly="readonly"/>
                            </td>
                            <td>
                                <input class="form-control" type="text" value="${tableItem.day4}" style="width:140px;" readonly="readonly"/>
                            </td>
                            <td>
                                <input class="form-control" type="text" value="${tableItem.day5}" style="width:140px;" readonly="readonly"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>



    </div>
</div>

</body>
</html>
