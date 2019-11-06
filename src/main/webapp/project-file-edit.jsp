<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑附件</title>
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
    当前位置:项目管理>>编辑附件
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form id="form2" action="<%=basePath%>attach/updateAttachment" method="post">
	<input type="hidden" name="_method" value="put"/>
	<input type="hidden" id="id" name="id" value="${attachment.id}"/>
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;编辑附件&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">所属项目：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		${attachment.project.pname}
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="attname" value="${attachment.attname}"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件信息描述：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="attdis" value="${attachment.attdis}"/></td>
</tr>


<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件1：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<span id="path1">
			<c:if test="${attachment.path!=''}">${attachment.path.substring(32,attachment.path.length())}</c:if>
		</span>
		&nbsp;&nbsp;<input id="file" name="attachment" type="file"/>&nbsp;&nbsp;&nbsp;
			<a href="javascript:updatePath();">添加</a>&nbsp;&nbsp;&nbsp;
			<a href="javascript:deletePath()">删除</a>
</tr >
	
<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea name="remark" rows=10 cols=130>${attachment.remark}</textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit()" class="coolbg">保存</a>
	<a href="javascript:history.go(-1);" class="coolbg">返回</a>
</td>
</tr>
</table>
</form>
</body>
<
<script type="text/javascript">
	//提交表单
	function commit() {
		$("#form2").submit();
    }
    //异步更新上传的新文件
    function updatePath() {
        var id= $("#id").val();
        var file=$("#file")[0].files[0];
        var formdata = new FormData();
        formdata.append("id",id);
        formdata.append("attachment",file);
		if(file != undefined){
			$.ajax({
				type:"post",
				url:"<%=basePath%>attach/updatePath",
				data:formdata,
				cache:false,//不要在浏览器端缓存
				processData:false, //告诉浏览器，不要进行数据转换
				contentType:false, //告诉浏览器，不要设置编码
				success:function(msg){
					if(msg.statusCode == 200){
						$("#path1").text(msg.path);
					}else{
						if(confirm("您确定要离开该页面么？")){
							window.location.href="<%=basePath%>project-file.jsp";
						}
					}
				}

			});
        }else{
		    alert("请先选择一个文件再添加！");
		}
    }
    
    //异步删除文件
	function deletePath() {
        var id= $("#id").val();
        $.ajax({
            type:"post",
            url:"<%=basePath%>attach/deletePath",
			data:{"_method":"put","path":"","id":id},
            success:function(msg){
                if(msg.statusCode == 200){
                    $("#path1").text("");
                }else{
                    if(confirm("您确定要离开该页面么？")){
                        window.location.href="<%=basePath%>project-file.jsp";
                    }
                }
            }

        });
    }
</script>
</html>