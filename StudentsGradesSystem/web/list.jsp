<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 83613
  Date: 2020/3/12
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>
        $(function () {
            //给删除选中按钮添加单击事件
            $("#delSelected").click(function () {
               if (confirm("您确定要删除吗？")){
                   var flag = false;
                   $("input[name='check']").each(function (i,element) {
                        if (element.checked){
                            flag = true;
                            return false;
                        }
                   });
                   if (flag){
                       $("#form").submit();
                   };
               };
           });
            //单机进行全选以及全不选


        });
        function selectAll(obj){
            $("input[type='checkbox']:gt(0)").prop("checked",obj.checked);
        };
    </script>

</head>
<body>
<div class="container">
    <h3 style="text-align: center">学生信息列表</h3>
    <div style="float: left">
        <form class="form-inline" method="post" action="${pageContext.request.contextPath}/findByPageServlet">
            <div class="form-group">
                <label for="name">姓名：</label>
                <input type="text" class="form-control" id="name" name="name">
            </div>
            <div class="form-group">
                <label for="gender">性别：</label>
                <input type="text" class="form-control" id="gender" name="gender">
            </div>
            <div class="form-group">
                <label for="address">籍贯：</label>
                <input type="text" class="form-control" id="address" name="address">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin:5px">
        <a class="btn btn-primary"
                   href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary"
                   href="javascript:void(0);" id="delSelected">删除选定联系人</a>
    </div>
    <form action="${pageContext.request.contextPath}/delSelectedServlet" id="form" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" name="checkFir" id="firstCb" onclick="selectAll(this)"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>籍贯</th>
                <th>语文</th>
                <th>数学</th>
                <th>英语</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${requestScope.pageBean.stuByPage}" var="stu" varStatus="stu_s">
                <tr>
                    <td>
                        <input type="checkbox" name="check" value="${stu.id}">
                    </td>
                    <td>${stu_s.count}</td>
                    <td>${stu.name}</td>
                    <td>
                        <c:if test="${stu.gender}">女</c:if>
                        <c:if test="${not stu.gender}">男</c:if>
                    </td>
                    <td>${stu.address}</td>
                    <td>${stu.chinese}</td>
                    <td>${stu.math}</td>
                    <td>${stu.english}</td>
                    <td>
                        <a class="btn btn-default btn-sm" href=
                                "${pageContext.request.contextPath}/findOneServlet?uid=${stu.id}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm" href=
                                "${pageContext.request.contextPath}/deleteServlet?uid=${stu.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${requestScope.pageBean.currentPage==1}">
                    <li class="disabled">
                </c:if>

                <c:if test="${requestScope.pageBean.currentPage!=1}">
                    <li>
                </c:if>

                    <a href="${pageContext.request.contextPath}/findByPageServlet?currentPage=${requestScope.pageBean.currentPage-1}&rows=5&name=${requestScope.condition.name[0]}&gender=${requestScope.condition.gender[0]}&address=${requestScope.condition.address[0]}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                </li>

                <c:forEach begin="1" end="${requestScope.pageBean.totalPage}" var="i">
                    <c:if test="${requestScope.pageBean.currentPage==i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findByPageServlet?currentPage=${i}&rows=5&name=${requestScope.condition.name[0]}&gender=${requestScope.condition.gender[0]}&address=${requestScope.condition.address[0]}">${i}<span class="sr-only">(current)</span></a></li>
                    </c:if>
                    <c:if test="${requestScope.pageBean.currentPage!=i}">
                        <li><a href="${pageContext.request.contextPath}/findByPageServlet?currentPage=${i}&rows=5&name=${requestScope.condition.name[0]}&gender=${requestScope.condition.gender[0]}&address=${requestScope.condition.address[0]}">${i}<span class="sr-only">(current)</span></a></li>
                    </c:if>
                </c:forEach>

                <c:if test="${requestScope.pageBean.currentPage==requestScope.pageBean.totalPage}">
                <li class="disabled">
                    </c:if>

                    <c:if test="${requestScope.pageBean.currentPage!=requestScope.pageBean.totalPage}">
                <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/findByPageServlet?currentPage=${requestScope.pageBean.currentPage+1}&rows=5&name=${requestScope.condition.name[0]}&gender=${requestScope.condition.gender[0]}&address=${requestScope.condition.address[0]}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                </li>
            </ul>
        </nav>
    </div>

    <span>
        共${requestScope.pageBean.totalCount}条记录，共${requestScope.pageBean.totalPage}页
    </span>
</div>



</body>
</html>
</body>
</html>
