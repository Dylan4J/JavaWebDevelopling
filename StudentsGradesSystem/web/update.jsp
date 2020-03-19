<%--
  Created by IntelliJ IDEA.
  User: 83613
  Date: 2020/3/12
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>信息修改</title>
</head>
<body>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/updateServlet?id=${requestScope.stu.id}" method="post">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" value="${requestScope.stu.name}" readonly="readonly" placeholder="请输入姓名" />
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:if test="${requestScope.stu.gender == false}">
                <input type="radio" name="gender" value=false checked />男
                <input type="radio" name="gender" value=true  />女
            </c:if>

            <c:if test="${requestScope.stu.gender == true}">
                <input type="radio" name="gender" value=false  />男
                <input type="radio" name="gender" value=true checked  />女
            </c:if>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" id="address" class="form-control" >
                <c:if test="${requestScope.stu.address == '陕西'}">
                    <option value="陕西" selected>陕西</option>
                    <option value="北京">北京</option>
                    <option value="上海">上海</option>
                </c:if>

                <c:if test="${requestScope.stu.address == '北京'}">
                    <option value="陕西" >陕西</option>
                    <option value="北京" selected>北京</option>
                    <option value="上海">上海</option>
                </c:if>

                <c:if test="${requestScope.stu.address == '上海'}">
                    <option value="陕西" >陕西</option>
                    <option value="北京">北京</option>
                    <option value="上海" selected>上海</option>
                </c:if>
            </select>
        </div>

        <div class="form-group">
            <label for="chinese">语文成绩：</label>
            <input type="text" id="chinese" class="form-control" name="chinese"  value="${requestScope.stu.chinese}" placeholder="请输入语文成绩"/>
        </div>

        <div class="form-group">
            <label for="math">数学成绩：</label>
            <input type="text" id="math" class="form-control" name="math" value="${requestScope.stu.math}" placeholder="请输入数学成绩"/>
        </div>

        <div class="form-group">
            <label for="english">英语成绩：</label>
            <input type="text" id="english" class="form-control" name="english" value="${requestScope.stu.english}" placeholder="请输入英语成绩"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>
</body>
</html>
