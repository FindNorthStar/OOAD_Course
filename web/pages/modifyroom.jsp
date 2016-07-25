<%--
  Created by IntelliJ IDEA.
  User: Java
  Date: 2016/6/15
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>ModifyRoom</title>
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
                        <form action="adminpanel.action"  class="nav-form" method="post">
                            <button type="submit" class="btn btn-primary navbar-btn">修改用户</button>
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
        document.form1.action="modifyroom.action";
        document.form1.submit();
    }

    function delet()
    {
        //action to modify
        document.form1.action="deleteroom.action";
        document.form1.submit();
    }
</Script>

<div class="container">
    <div class="jumbotron">
        <h1>修改教室信息</h1>
        <hr/>
        <form name="form1" action="" method="post">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">教室概要</div>
                <!-- Table -->
                <table class="table">
                    <thead>
                    <tr>
                        <th>教室编号</th><th>教室名称</th><th>教室容量</th><th>选中</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="ordinal" value="1"/>
                    <c:forEach var="classroom" items="${roomList}">
                        <tr>
                            <th scope="row">
                                <input class="form-control" name="number${ordinal}" type="text" value="${classroom.id}" style="width:100px;" readonly="readonly"/>
                            </th>
                            <td>
                                <input class="form-control" name="name${ordinal}" type="text" value="${classroom.name}" style="width:100px;"/>
                            </td>
                            <td>
                                <input class="form-control" name="capacity${ordinal}" type="text" value="${classroom.capacity}" style="width:100px;"/>
                            </td>
                            <td><input name="chooseRoom" type="radio" value="${classroom.id}"/></td>
                        </tr>
                        <c:set var="ordinal" value="${ordinal+1}"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <button type="button" class="btn btn-primary" onclick="modify()">修改教室信息</button>
            <button type="button" class="btn btn-primary" onclick="delet()">删除教室</button>
        </form>

        <hr/>
        <%--action to modify--%>
        <form action="addroom.action" class="form-inline" role="form" method="post">
            <div class="form-group">
                <h2>添加教室信息</h2>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon0">教室编号</span>
                    <input class="form-control" name="newNumber" type="text" placeholder="number"/>
                </div>

                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1">教室名称</span>
                    <input class="form-control" name="newName" type="text" placeholder="name"/>
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon2">教室容量</span>
                    <input class="form-control" name="newCapacity" type="text" placeholder="capacity"/>
                </div>
                <%--<div class="form-group">
                    <label for="user-identity" class="sr-only">身份</label>
                    <select class="form-control" id="user-identity" name="newIdentity">
                        <option value="student">student</option>
                        <option value="teacher">teacher</option>
                        <option value="admin">admin</option>
                    </select>
                </div>--%>
                <button type="submit" class="btn btn-primary">确认添加</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
