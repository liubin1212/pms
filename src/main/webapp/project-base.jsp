<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>项目信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
    <% String  basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/"; %>
    <script type="text/javascript" src="<%=basePath%>static/js/jquery-3.1.1.js"></script>
</head>
<body leftmargin="8" topmargin="8" background='<%=basePath%>skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="<%=basePath%>skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>基本信息管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='<%=basePath%>project-base-add.jsp';" value='添加新项目' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form id='form3' action='<%=basePath%>pro/search' method='get'>
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='<%=basePath%>skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150px;'>
          <option value='0'>选择类型...</option>
          	<option value='1'>项目名称</option>
          	<option value='2'>客户负责人</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='keyword' value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select name='orderby' style='width:120px'>
            <option value='id'>排序...</option>
            <option value='startdate'>立项时间</option>
            <option value='stopdate'>计划完成时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="<%=basePath%>skin/images/frame/search.gif" width="45" height="20" border="0" class="np" onclick="searchProject()" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="<%=basePath%>skin/images/tbg.gif">&nbsp;项目信息列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">项目名称</td>
	<td width="10%">客户公司名称</td>
	<td width="10%">客户方负责人</td>
	<td width="5%">项目经理</td>
	<td width="8%">开发人员数</td>
	<td width="6%">立项时间</td>
	<td width="8%">最近更新时间</td>
	<td width="8%">计划完成时间</td>
	<td width="8%">状态</td>
	<td width="10%">操作</td>
</tr>
<c:forEach items="${list}" var="project" varStatus="items">
    <tr class="choose" align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
        <td><input name="pid" type="checkbox" value="${project.pid}" class="np"></td>
        <td>${items.count}</td>
        <td align="left"><a href=''><u>${project.pname}</u></a></td>
        <td>${project.customer.comname}</td>
        <td>${project.comper}</td>
        <td>${project.employee.ename}</td>
        <td>${project.empcount}</td>
        <td>
            <fmt:formatDate value="${project.starttime}" pattern="yyyy-MM-dd"></fmt:formatDate>
        </td>
        <td>
            <fmt:formatDate value="${project.buildtime}" pattern="yyyy-MM-dd"></fmt:formatDate>
        </td>
        <td>
            <fmt:formatDate value="${project.endtime}" pattern="yyyy-MM-dd"></fmt:formatDate>
        </td>
        <td>
            <c:if test="${project.level == 1}">紧急</c:if>
            <c:if test="${project.level == 2}">一般</c:if>
            <c:if test="${project.level == 3}">暂缓</c:if>
        </td>
        <td><a href="<%=basePath%>pro/findProjectById/${project.pid}">编辑</a> | <a href="<%=basePath%>pro/findProjectById/${project.pid}?mark=1">查看详情</a></td>
    </tr>
</c:forEach>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:chooseAll()" class="coolbg">全选</a>
	<a href="javascript:chooseReverseAll()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:chooseDelete()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
</body>
<
<script type="text/javascript">
    //全选
    function chooseAll() {
        $(".choose input").prop("checked","checked");
    }
    //反选
    function chooseReverseAll() {
        $(".choose input").each(function () {
            var b = $(this).prop("checked");
            $(this).prop("checked",!b);
        })
    }
    //删除
    function chooseDelete() {
        var pids = [];
        $(".choose input:checked").each(function () {
            var pid = $(this).val();
            pids.push(pid);
        })
        alert(pids);
        if (confirm("您确定要删除吗？")){
            $.ajax({
                url:"<%=basePath%>pro/deleteProjects",
                type:"post",
                data:{"_method":"DELETE","pids":pids},
                dataType:"json",
                success:function (msg) {
                    if(msg.statusCode == 200){
                        window.location.href = "<%=basePath%>pro/findAllProject"
                    }
                },
                error:function () {
                    alert("删除失败");
                }
            });
        }
    }
    //提交搜索表单
    function searchProject() {
        $("#form3").submit();
    }
</script>
</html>