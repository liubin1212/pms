<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>附件管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<% String  basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/"; %>
	<script type="text/javascript" src="<%=basePath%>static/js/jquery-3.1.1.js"></script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:个人报销管理>>报销列表
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='<%=basePath%>mybaoxiao-add.jsp';" value='添加报销' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form id='form3' action='<%=basePath%>baoxiao/findAllBaoxiao' method='get'>
	<table width='98%'  border='1' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
		<tr bgcolor='#EEF4EA'>
			<td background='skin/images/wbg.gif' align='center'>
				<table border='0' cellpadding='0' cellspacing='0'>
					<tr>
						<td width='90' align='center'>搜索条件：</td>
						<td width='160'>
							<select name='search_status' style='width:150px'>
								<option value=''>选择类型...</option>
								<option value='0'>未审批</option>
								<option value='1'>驳回</option>
								<option value='2'>审批通过</option>
								<option value='3'>审批并付款</option>
							</select>
						</td>
						<td width='70'>
							关键字：
						</td>
						<td width='160'>
							<input type='text' name='search_like_keyword' value='' style='width:120px' />
						</td>
						<td>
							&nbsp;&nbsp;&nbsp;<input onclick="searchAttachment()" name="imageField" type="image" src="<%=basePath%>skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>

<!--  内容列表   -->
<form name="form2">

<table width="98%"  cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="20%">编号</td>
	<td width="6%">总金额</td>
	<td width="10%">使用时间</td>
	<td width="40%">备注信息</td>
	<td width="10%">审批状态</td>
	<td width="10%">操作</td>
</tr>

	<c:forEach items="${page.list}" var="biaoxiao" varStatus="index">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${biaoxiao.bxid}" class="np"></td>
	<td>${index.count}</td>
	<td>${biaoxiao.totalmoney}</td>
	<td align="center"><a href=''><u> <fmt:formatDate value="${biaoxiao.bxtime}" pattern="yyyy-MM-dd"></fmt:formatDate> </u></a></td>
	<td>${biaoxiao.bxremark}</td>

	<c:if test="${biaoxiao.bxstatus == 0}">
		<td>未审批</td>
		<td>
			<a href="<%=basePath%>baoxiao/findBaoxiaoById/${biaoxiao.bxid}">编辑</a>
		</td>
	</c:if>
	<c:if test="${biaoxiao.bxstatus == 1}">
		<td>驳回</td>
		<td>
			<a style="pointer-events: none;color: grey;" href="#">编辑</a>
		</td>
	</c:if>
	<c:if test="${biaoxiao.bxstatus == 2}">
		<td>审批通过</td>
		<td>
			<a href="<%=basePath%>baoxiao/findBaoxiaoById/${biaoxiao.bxid}">编辑</a>
		</td>
	</c:if>
	<c:if test="${biaoxiao.bxstatus == 3}">
		<td>审批并付款</td>
		<td>
			<a style="pointer-events: none;color: grey;" href="#">编辑</a>
		</td>
	</c:if>

</tr>
	</c:forEach>
	<jsp:include page="page.jsp"></jsp:include>
</table>

</form>
</body>

</html>