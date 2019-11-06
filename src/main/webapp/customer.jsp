<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>客户信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
    <% String  basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/"; %>
    <script type="text/javascript" src="<%=basePath%>static/js/jquery-3.1.1.js"></script>
    <script type="text/javascript">
        function chooseAll() {
            $("#choose input").prop("checked","checked");
        }
        function reverseChooseAll() {
            $("#choose input").each(function (){
                var c = $(this).prop("checked");
                $(this).prop("checked",!c);
            });
        }
        function deleteChoose() {
            var ids = [];
            $("#choose input:checked").each(function (index,items){
                var id = $(this).val();
                ids.push(id);
            });

            if(confirm("您确定要删除吗？")){
                $.ajax({
                    type:"post",
                    url:"<%=basePath%>cust/deleteCustomers",
                    data:{"_method":"DELETE","ids":ids},
                    dataTyte:"json",
                    success:function (msg) {
                        if (msg.statusCode == 200){
                            window.location.href = "<%=basePath%>cust/findAllCustomer";
                        } else{
                            alert(msg.message);
                        }
                    },
                    error:function () {
                        alert("删除失败");
                    }

                });
            }

        }

        function commit() {
            $("#form3").submit();
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
    当前位置:客户信息管理>>客户信息
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='<%=basePath%>customer-add.jsp';" value='添加客户信息' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form id="form3" name='form3' action='<%=basePath%>cust/search' method='get'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150'>
          <option value='0'>选择类型...</option>
          	<option value='1'>客户所在公司名称</option>
          	<option value='2'>联系人姓名</option>
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
            <option value='1'>倒序</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input href="javascript:commit()" name="imageField" type="image" src="<%=basePath%>skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table id="choose" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;需求列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">联系人</td>
	<td width="10%">公司名称</td>
	<td width="8%">添加时间</td>
	<td width="8%">联系电话</td>
	<td width="10%">操作</td>
</tr>

    <c:forEach items="${list }" var="Customer" varStatus="index">
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
            <td><input name="id" type="checkbox" id="id" value="${Customer.id}" class="np"></td>
            <td>${index.count}</td>
            <td>${Customer.companyperson}</td>
            <td align="center">${Customer.comname}</td>
            <td>
                <fmt:formatDate value="${Customer.addtime}" pattern="yyyy-MM-dd"></fmt:formatDate>
            </td>
            <td>${Customer.comphone}</td>
            <td><a href="<%=basePath%>cust/findCustomerById/${Customer.id}">编辑</a> | <a href="<%=basePath%>cust/findCustomerById/${Customer.id}?mark=1">查看详情</a></td>
        </tr>
    </c:forEach>


<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:chooseAll();" class="coolbg">全选</a>
	<a href="javascript:reverseChooseAll();" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:deleteChoose();" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>