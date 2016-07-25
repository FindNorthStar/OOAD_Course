<%--
  Created by IntelliJ IDEA.
  User: Java
  Date: 2016/6/15
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>ChooseLesson</title>
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
                        <form action="studentpanel.action" class="nav-form" method="post">
                            <button type="submit" class="btn btn-primary navbar-btn">个人信息</button>
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
        <h1>可选课程信息</h1>
        <hr/>
        <form action="addstudylesson.action" method="post">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">可选课程</div>
                <!-- Table -->
                <table class="table">
                    <thead>
                    <tr>
                        <th>课程编号</th><th>课程名称</th><th>每周上课日</th><th>节次</th><th>教室</th><th>教师</th><th>选中</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="ordinal" value="1"/>
                    <c:forEach var="candidate" items="${candidateList}">
                        <tr>
                            <th scope="row">
                                <input class="form-control" name="number${ordinal}" type="text" value="${candidate.id}" style="width:100px;" readonly="readonly"/>
                            </th>
                            <td>
                                <input class="form-control" name="name${ordinal}" type="text" value="${candidate.name}" style="width:100px;" readonly="readonly"/>
                            </td>
                            <td>
                                <input class="form-control" name="occurday${ordinal}" type="text" value="${candidate.occurday}" style="width:100px;" readonly="readonly"/>
                            </td>
                            <td>
                                <input class="form-control" name="occurorder${ordinal}" type="text" value="${candidate.occurorder}" style="width:100px;" readonly="readonly"/>
                            </td>
                            <td>
                                <input class="form-control" name="room${ordinal}" type="text" value="${candidate.room}" style="width:100px;" readonly="readonly"/>
                            </td>
                            <td>
                                <input class="form-control" name="teachername${ordinal}" type="text" value="${candidate.teachername}" style="width:100px;" readonly="readonly"/>
                            </td>
                            <td><input name="chooseStudyLesson" type="radio" value="${candidate.id}"/></td>
                        </tr>
                        <c:set var="ordinal" value="${ordinal+1}"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <button type="submit" class="btn btn-primary">提交选课</button>
        </form>

        <hr/>
        <%--action to modify--%>
        <h1>已选课程删除</h1>
        <hr/>
        <form action="deletestudylesson.action" method="post">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">已选课程</div>
                <!-- Table -->
                <table class="table">
                    <thead>
                    <tr>
                        <th>课程编号</th><th>课程名称</th><th>每周上课日</th><th>节次</th><th>教室</th><th>教师</th><th>选中</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="ordin" value="1"/>
                    <c:forEach var="lesson" items="${lessonList}">
                        <tr>
                            <th scope="row">
                                <input class="form-control" name="number${ordin}" type="text" value="${lesson.id}" style="width:100px;" readonly="readonly"/>
                            </th>
                            <td>
                                <input class="form-control" name="name${ordin}" type="text" value="${lesson.name}" style="width:100px;" readonly="readonly"/>
                            </td>
                            <td>
                                <input class="form-control" name="occurday${ordin}" type="text" value="${lesson.occurday}" style="width:100px;" readonly="readonly"/>
                            </td>
                            <td>
                                <input class="form-control" name="occurorder${ordin}" type="text" value="${lesson.occurorder}" style="width:100px;" readonly="readonly"/>
                            </td>
                            <td>
                                <input class="form-control" name="room${ordin}" type="text" value="${lesson.room}" style="width:100px;" readonly="readonly"/>
                            </td>
                            <td>
                                <input class="form-control" name="teachername${ordin}" type="text" value="${lesson.teachername}" style="width:100px;" readonly="readonly"/>
                            </td>
                            <td><input name="chooseDeleteLesson" type="radio" value="${lesson.id}"/></td>
                        </tr>
                        <c:set var="ordin" value="${ordin+1}"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <button type="submit" class="btn btn-primary">删除选课</button>
        </form>
    </div>
</div>

</body>
</html>
