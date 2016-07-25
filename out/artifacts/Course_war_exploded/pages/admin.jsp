<%--
  Created by IntelliJ IDEA.
  User: Java
  Date: 2016/6/15
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>AdminPanel</title>
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
                    <li><p class="navbar-text">编号：${adminNum}</p></li>
                    <li><p class="navbar-text">姓名：${adminName}</p></li>
                    <li>
                        <form action="roompanel.action"  class="nav-form" method="post">
                            <button type="submit" class="btn btn-primary navbar-btn">修改教室</button>
                        </form>
                    </li>
                    <li>
                        <form action="lessonpanel.action"  class="nav-form" method="post">
                            <button type="submit" class="btn btn-primary navbar-btn">修改课程</button>
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

<Script Language="JavaScript">
    function modify()
    {
        //action to modify
        document.form1.action="modifyuser.action";
        document.form1.submit();
    }

    function delet()
    {
        //action to modify
        document.form1.action="deleteuser.action";
        document.form1.submit();
    }
</Script>

<div class="container">
    <div class="jumbotron">
        <h1>修改用户信息</h1>
        <hr/>
        <form name="form1" action="" method="post">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">用户概要</div>
                <!-- Table -->
                <table class="table">
                    <thead>
                    <tr>
                        <th>学/工号</th><th>姓名</th><th>密码</th><th>身份</th><th>选中</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="ordinal" value="1"/>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <th scope="row">
                                <input class="form-control" name="number${ordinal}" type="text" value="${user.id}" style="width:120px;" readonly="readonly"/>
                            </th>
                            <td>
                                <input class="form-control" name="name${ordinal}" type="text" value="${user.username}" style="width:100px;"/>
                            </td>
                            <td>
                                <input class="form-control" name="password${ordinal}" type="text" value="${user.password}" style="width:100px;"/>
                            </td>
                            <td>
                                <input class="form-control" name="identity${ordinal}" type="text" value="${user.identity}" style="width:120px;"/>
                            </td>
                            <td><input name="chooseUser" type="radio" value="${user.id}"/></td>
                        </tr>
                        <c:set var="ordinal" value="${ordinal+1}"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <button type="button" class="btn btn-primary" onclick="modify()">修改用户信息</button>
            <button type="button" class="btn btn-primary" onclick="delet()">删除用户</button>
        </form>

        <hr/>
        <%--action to modify--%>
        <form action="adduser.action" class="form-inline" role="form" method="post">
            <div class="form-group">
                <h2>添加用户信息</h2>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon0">工/学号</span>
                    <input class="form-control" name="newNumber" type="text" placeholder="number"/>
                </div>

                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1">姓名</span>
                    <input class="form-control" name="newName" type="text" placeholder="name"/>
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon2">密码</span>
                    <input class="form-control" name="newPassword" type="text" placeholder="password"/>
                </div>
                <div class="form-group">
                    <label for="user-identity">身份</label>
                    <select class="form-control" id="user-identity" name="newIdentity">
                        <option value="student">student</option>
                        <option value="teacher">teacher</option>
                        <option value="admin">admin</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">确认添加</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
