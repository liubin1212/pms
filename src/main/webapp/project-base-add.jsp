<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加项目信息</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<% String  basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/"; %>
	<script type="text/javascript" src="<%=basePath%>static/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
    function commit(){
        var form = document.getElementById("forms");
        form.submit();
    }
    $(function () {
		//请求客户信息
		$.ajax({
			url:"<%=basePath%>cust/jsonList",
			type:"get",
			dataType:"json",
			success:function (msg) {
				$(msg).each(function (index,item) {
				    var o1 = $("<option value='"+item.id+"'"+"'>"+item.comname+"</option>");
					$("#cuscomer").append(o1);
                })
            }
		});

		//请求员工信息获取经理信息
		$.ajax({
            url:"<%=basePath%>emp/findEmployeeById",
            type:"get",
            dataType:"json",
            success:function (msg) {
                $(msg).each(function (index,item) {
                    var o = $("<option value='"+item.eid+"'"+"'>"+item.ename+"</option>");
                    $("#manageropt").append(o);
                })
            },
			error:function () {
				alert("请求失败");
            }
		});
    })

		function changeComname(item) {
            $("#companyperson").val("");
            $.ajax({
                url:"<%=basePath%>cust/findJsonById/"+item,
                type:"get",
                dataType:"json",
                success:function (msg) {
                    $("#companyperson").val(msg.companyperson);
                }
            });

        }
</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
	  <table width="58%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			  <td >
				当前位置:项目管理>>添加项目基本信息
			  </td>
		 </tr>
	</table>
</td>
</tr>
</table>

<form name="form2" id="forms" action="<%=basePath%>pro/addProject" method="POST">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;添加新项目信息&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22" >项目名称：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input type="text" name="pname"/></td>
	<td align="right" bgcolor="#FAFAF1" height="22">客户公司名称：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="comname" id="cuscomer" onchange="changeComname(this.value)">
			<option>请选择...</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">客户方负责人：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="comper" id="companyperson" readonly/>
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">项目经理：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="empFk" id="manageropt">
			<option>请选择...</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22" >开发人数：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input type="text" name="empcount"/>人</td>
	<td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="starttime" class="Wdate" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" type="text" class="dfinput" value="" />
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">立项时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="buildtime" class="Wdate" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" type="text" class="dfinput" value="" />
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">预估成本：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input type="text" name="cost"/>万</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">级别：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><select  name="level"><option value=1>紧急</option><option value=2>一般</option><option value=3>暂缓</option></select></td>
	<td align="right" bgcolor="#FAFAF1" height="22">计划完成时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="endtime" class="Wdate" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" type="text" class="dfinput" value="" />
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea type="text" rows=15 cols=130 name="remark"></textarea><span id="number"></span>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<A class="coolbg" onclick="commit()" >保存</A>
	<a href="javascript:history.go(-1);" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>