<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>需求分析管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
    <% String  basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/"; %>
    <script type="text/javascript" src="<%=basePath%>static/js/jquery-3.1.1.js"></script>
</head>
<style>
    #uploadImg{
        font-size:16px;
        overflow:hidden;
        position:absolute
    }
    #file{
        position:absolute;
        z-index:100;
        margin-left:-180px;
        font-size:60px;
        opacity:0;
        filter:alpha(opacity=0); margin-top:-5px;
    }
</style>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>需求分析管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='<%=basePath%>project-need-add.jsp';" value='添加新项目需求' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form id='form3' action='<%=basePath%>ana/searchAnalysis' method='get'>
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='id' style='width:150px'>
          <option value='0'>选择类型...</option>
          	<option value='1'>项目名称</option>
          	<option value='1'>标题</option>
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
            <option value='startdate'>添加时间</option>
            <option value='updatedate'>修改时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input onclick="searchAnalysis()" name="imageField" type="image" src="<%=basePath%>skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
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
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;需求列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">标题</td>
	<td width="10%">项目名称</td>
	<td width="8%">添加时间</td>
	<td width="8%">修改时间</td>
	<td width="10%">操作</td>
</tr>
    <c:forEach items="${list}" var="analysis" varStatus="index">
        <tr class="choose" align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
            <td><input name="id" type="checkbox" id="id" value="${analysis.id}" class="np"></td>
            <td>${index.count}</td>
            <td>${analysis.title}</td>
            <td align="center"><a href=''><u>${analysis.proname}</u></a></td>
            <td>
                <fmt:formatDate value="${analysis.addtime}" pattern="yyyy-MM-dd"></fmt:formatDate>
            </td>
            <td>
                <fmt:formatDate value="${analysis.updatetime}" pattern="yyyy-MM-dd"></fmt:formatDate>
            </td>
            <td><a href="<%=basePath%>ana/findAnalysisById/${analysis.id}">编辑</a> | <a href="<%=basePath%>ana/findAnalysisById/${analysis.id}?mark=1">查看详情</a></td>
        </tr>
    </c:forEach>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="#" onclick="chooseAll()" class="coolbg">全选</a>
	<a href="#" onclick="chooseReverseAll()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:deleteChoose()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="javascript:exportExcel()" class="coolbg">&nbsp;导出Excel&nbsp;</a>&nbsp;&nbsp;
    <span id="uploadImg" class="coolbg" style="display:inline-block;margin-top: -2px;">
       <input type="file" id="file" size="1" onclick="importExcel()" >
       <a href="javascript:void(0)" >上传Excel</a>
    </span>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
</body>
<script type="text/javascript">
    //导入Excel
    function importExcel() {
        //创建上传的数据对象
        var formData = new FormData();
        //定时器判断是否获取到文件
        var result = setInterval(function () {
            //获取到上传的文件
            var file = $("#file")[0].files[0];
            //获取到文件对象之后取消定时器，并异步上传
            if(file != undefined){
                clearInterval(result);
                alert("山川了");
                formData.append("excel",file);
                //异步上传
                $.ajax({
                    url:"<%=basePath%>ana/importExcel",
                    data:formData,
                    type:"post",
                    cache:false,
                    processData:false,
                    contentType:false,
                    success:function (msg) {
                        alert(msg.message);
                    }
                });
            }
        },1000);
        return false;
    }
    //导出Excel
    function exportExcel() {
        $.ajax({
            url:"<%=basePath%>ana/exportExcel",
            type:"get",
            dataType:"json",
            success:function (msg) {
                if (msg.statusCode == 200){
                    alert(msg.message);
                } else{
                    alert("导出失败");
                }
            }
        })
    }
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
    //把选中的需求删除
    function deleteChoose() {
        var ids = [];
        $(".choose input:checked").each(function () {
            ids.push($(this).val());
        })

        if(confirm("您确定要删除吗？")){
            $.ajax({
                url:"<%=basePath%>ana/deleteAnalysis",
                type:"post",
                data:{"_method":"delete","ids":ids},
                dataType:"json",
                success:function (msg) {
                    window.location.href = "<%=basePath%>ana/findAllAnalysis";
                },
                error:function () {
                    alert("删除失败");
                }
             })
        }

    }
    //根据条件查询
    function searchAnalysis() {
        $("#form3").submit();
    }

</script>
</html>