<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
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
    当前位置:项目管理>>附件管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='<%=basePath%>project-file-add.jsp';" value='添加新附件' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form id='form3' action='<%=basePath%>attach/searchAttachment' method='get'>
<table width='98%'  border='1' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150px'>
          <option value='0'>选择类型...</option>
          	<option value='1'>附件名称</option>
          	<option value='2'>项目名称</option>
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
            <option value='0'>排序...</option>
            <option value='1'>添加时间</option>
            <option value='2'>修改时间</option>
      	</select>
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

<table width="98%" border="1" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">附件名称</td>
	<td width="10%">所属项目</td>
	<td width="10%">附件信息描述</td>
	<td width="8%">上传时间</td>
	<td width="8%">修改时间</td>
	<td width="13%">操作</td>
</tr>
<c:forEach items="${list}" varStatus="index" var="attachment">
    <tr class="choose" align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
        <td><input name="id" type="checkbox" id="id" value="${attachment.id}" class="np"></td>
        <td>${index.count}</td>
        <td>${attachment.attname}</td>
        <td align="center"><a href=''><u>${attachment.project.pname}</u></a></td>
        <td>${attachment.attdis}</td>
        <td>
            <fmt:formatDate value="${attachment.project.starttime}" pattern="yyyy-MM-dd"></fmt:formatDate>
        </td>
        <td>
            <fmt:formatDate value="${attachment.project.buildtime}" pattern="yyyy-MM-dd"></fmt:formatDate>
        </td>
        <td><a href="<%=basePath%>attach/download?name=${attachment.path}">下载</a> |
            <a href="<%=basePath%>attach/deleteAttachmentById/${attachment.id}">删除</a>
            |<a href="<%=basePath%>attach/findAttachmentById/${attachment.id}">编辑</a> |
            <a href="<%=basePath%>attach/findAttachmentById/${attachment.id}?mark=1">查看详情</a></td>
    </tr>
</c:forEach>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:chooseAll()" class="coolbg">全选</a>
	<a href="javascript:reverseChooseAll()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:deleteChoose()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="javascript:exportExcel()" class="coolbg">&nbsp;导出Excel&nbsp;</a>
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
    //提交搜索选项
    function searchAttachment() {
        $("#form3").submit();
    }
    //全选
    function chooseAll() {
        $(".choose input").prop("checked","checked");
    }
    //反选
    function reverseChooseAll() {
        $(".choose input").each(function () {
            var b = $(this).prop("checked");
            $(this).prop("checked",!b);
        })
    }
    //删除
    function deleteChoose() {
        var ids = [];
        $(".choose input:checked").each(function () {
            ids.push($(this).val());
        });
        alert(ids);
        if (confirm("你确定要删除吗？")) {
            $.ajax({
                url:"<%=basePath%>attach/deleteAttachment",
                type:"post",
                data:{"_method":"delete","ids":ids},
                dataType:"json",
                success:function (msg) {
                    if (msg.statusCode == 200){
                        console(msg.message);
                    }
                }
            })
        }
    }
    //导出Excel表单
    function exportExcel() {
        $.ajax({
           url:"<%=basePath%>attach/exportExcel",
           type:"post",
            dataType:"json",
            success:function (msg) {
                if (msg.satatusCode == 200){
                    alert(msg.message);
                }
            }
        });
    }
</script>
</html>