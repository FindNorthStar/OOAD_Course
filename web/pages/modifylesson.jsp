<%--
  Created by IntelliJ IDEA.
  User: Java
  Date: 2016/6/15
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>ModifyLesson</title>
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
                        <form action="adminpanel.action"  class="nav-form" method="post">
                            <button type="submit" class="btn btn-primary navbar-btn">修改用户</button>
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
        document.form1.action="modifylesson.action";
        document.form1.submit();
    }

    function delet()
    {
        //action to modify
        document.form1.action="deletelesson.action";
        document.form1.submit();
    }
</Script>

<div class="container">
    <div class="jumbotron">
        <h1>修改课程信息</h1>
        <hr/>
        <form name="form1" action="" method="post">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">课程概要</div>
                <!-- Table -->
                <table class="table">
                    <thead>
                    <tr>
                        <th>课程编号</th><th>课程名称</th><th>每周上课日</th><th>节次</th><th>教室</th><th>教师</th><th>选中</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="ordinal" value="1"/>
                    <c:forEach var="lesson" items="${lessonList}">
                        <tr>
                            <th scope="row">
                                <input class="form-control" name="number${ordinal}" type="text" value="${lesson.id}" style="width:100px;" readonly="readonly"/>
                            </th>
                            <td>
                                <input class="form-control" name="name${ordinal}" type="text" value="${lesson.name}" style="width:100px;"/>
                            </td>
                            <td>
                                <input class="form-control" name="occurday${ordinal}" type="text" value="${lesson.occurday}" style="width:100px;"/>
                            </td>
                            <td>
                                <input class="form-control" name="occurorder${ordinal}" type="text" value="${lesson.occurorder}" style="width:100px;"/>
                            </td>
                            <td>
                                <input class="form-control" name="room${ordinal}" type="text" value="${lesson.room}" style="width:100px;"/>
                            </td>
                            <td>
                                <input class="form-control" name="teachername${ordinal}" type="text" value="${lesson.teachername}" style="width:100px;"/>
                            </td>
                            <td><input name="chooseLesson" type="radio" value="${lesson.id}"/></td>
                        </tr>
                        <c:set var="ordinal" value="${ordinal+1}"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <button type="button" class="btn btn-primary" onclick="modify()">修改课程信息</button>
            <button type="button" class="btn btn-primary" onclick="delet()">删除课程</button>
        </form>

        <hr/>
        <%--action to modify--%>
        <form action="addlesson.action" class="form-inline" role="form" method="post">
            <div class="form-group">
                <h2>添加课程信息</h2>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon0">课程编号</span>
                    <input class="form-control" name="newNumber" type="text" placeholder="number"/>
                </div>

                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1">课程名称</span>
                    <input class="form-control" name="newName" type="text" placeholder="name"/>
                </div>
                <br/>
                <div class="form-group">
                    <label for="user-day">上课日</label>
                    <select class="form-control" id="user-day" name="newDay">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="user-order">节次</label>
                    <select class="form-control" id="user-order" name="newOrder">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon2">教室</span>
                    <input class="form-control" name="newClassroom" type="text" placeholder="classroom"/>
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon3">教师</span>
                    <input class="form-control" name="newTeacherName" type="text" placeholder="teacher"/>
                </div>

                <button type="submit" class="btn btn-primary">确认添加</button>
            </div>
        </form>
    </div>
</div>


</body>
</html>
