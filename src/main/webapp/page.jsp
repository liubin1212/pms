<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
    <% String  basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/" ; %>
    <script type="text/javascript" src="<%=basePath%>static/js/jquery-3.1.1.js"></script>
</head>
<body>
    <tr align="right" bgcolor="#EEF4EA">
        <td height="36" colspan="12" align="center">
            <div >
                <a style="font-size: 15px;" href="${requestURI}?pageNum=1${queryStr}" >首页</a>
                <a style="font-size: 15px;" href="${requestURI}?pageNum=${page.pageNum-1}${queryStr}" >上一页</a>
                <c:forEach items="${page.navigatepageNums}" var="pageN">
                    <c:if test="${pageN == page.pageNum}">
                        <a style="pointer-events: none;" ><span style="font-size: 15px;color: black;font-weight: bold">${pageN}</span></a>
                    </c:if>
                    <c:if test="${pageN != page.pageNum}">
                        <a style="font-size: 15px;" href="${requestURI}?pageNum=${pageN}${queryStr}" >${pageN}</a>
                    </c:if>
                </c:forEach>
                <a style="font-size: 15px;" href="${requestURI}?pageNum=${page.pageNum+1}${queryStr}" >下一页</a>
                <a style="font-size: 15px;" href="${requestURI}?pageNum=${page.pages}${queryStr}" >尾页</a>
                <span style="font-size: 15px;">&nbsp;跳转到 <input size="2px" id="changePage1" onblur="changePage()"> 页</span>
            </div>
        </td>
    </tr>
</body>
<script type="text/javascript">
    function changePage() {
        var pageNum = $("#changePage1").val();
        var reg = /^[1-9]\d*$/;
        if(reg.test(pageNum)){
            window.location.href="${requestURI}?pageNum="+pageNum +"${queryStr}";
        }else{
            alert("请输入正整数！");
            return ;
        }

    }
</script>
</html>
