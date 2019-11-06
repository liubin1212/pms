<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发件箱</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<% String  basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/"; %>
	<script type="text/javascript" src="<%=basePath%>static/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/moment.min.js"></script>
<script type="text/javascript">
	//开始进入页面进行查询数据
	$(function(){
		$.ajax({
			type:'GET',
			url:'<%=basePath%>notice/findAllJsonNotice',
			dataType:'json',
			success:function(msg){
			    //遍历数据
				$(msg.map.page.list).each(function (index,item) {
					var tr = $("<tr class='cleandata' align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                        "\t<td><input name=\"id\" type=\"checkbox\" id=\"id\" value=\""+item.nid+"\" class=\"np\"></td>\n" +
                        "\t<td>"+((msg.map.page.pageNum-1)*msg.map.page.pageSize+(index+1))+"</td>\n" +
                        "\t<td>"+item.ntitle+"</td>\n" +
                        "\t<td align=\"center\"><span >"+item.remark+"</span></td>\n" +
                        "\t<td>"+moment(item.ndate).format("YYYY-MM-DD")+"</td>\n" +
                        "\t<td><a >删除</a>|<a>编辑</a></td>\n" +
                        "</tr>");
					$("#addList").before(tr);
                });
				//先添加查询的要的td
				var td = $("<td height=\"36\" colspan=\"12\" align=\"center\" id=\"fenye\"></td>");
				$("#fenyeTd").append(td);
				//展示分页内容
				var firstPage =$("<a onclick='findList(this.name)' href='#' style='font-size: 15px;' name='"+msg.map.requestURI+"?pageNum=1"+msg.map.queryStr+"' >首页&nbsp;</a>");
				var prePage = $("<a onclick='findList(this.name)' href='#' style='font-size: 15px;' name='"+msg.map.requestURI+"?pageNum="+(msg.map.page.pageNum-1)+msg.map.queryStr+"' >&nbsp;上一页&nbsp;</a>");
				var pagePage = "";
				//展示有几页 即1,2,3,4,5
				$(msg.map.page.navigatepageNums).each(function (index,item) {
					if(item == msg.map.page.pageNum){
					    pagePage = pagePage + "<a style='pointer-events: none;' ><span style='font-size: 15px;color: black;font-weight: bold'>&nbsp;"+item+"&nbsp;</span></a>";
                    }
					if(item != msg.map.page.pageNum){
					    pagePage = pagePage + "<a onclick='findList(this.name)' href='#' style='font-size: 15px;' name='"+msg.map.requestURI+"?pageNum="+item+msg.map.queryStr+"' >&nbsp;"+item+"&nbsp;</a>";
					}
                });
				var nextPage = $("<a onclick='findList(this.name)' href='#' style='font-size: 15px;' name='"+msg.map.requestURI+"?pageNum="+(msg.map.page.pageNum+1)+msg.map.queryStr+"' >&nbsp;下一页&nbsp;</a>");
				var endPage = $("<a onclick='findList(this.name)' href='#' style='font-size: 15px;' name='"+msg.map.requestURI+"?pageNum="+msg.map.page.pages+msg.map.queryStr+"' >&nbsp;尾页&nbsp;</a>");
				var tzk = $("<span style='font-size: 15px;'>&nbsp;跳转到 <input size='2px' id='changePage1' onblur='changePage()'> 页</span>");

				$("#fenye").append(firstPage).append(prePage).append(pagePage).append(nextPage).append(endPage).append(tzk);
			},
			error:function(){
				alert("-----");
			}
		});
	});

	//分页异步查询的方法即列如 点击下一页，然后回填数据
	function findList(name) {
		$.ajax({
			url:name,
			type:"get",
			success:function (msg) {
				$(".cleandata").remove();
				$("#fenye").remove();
				if (msg.map.statusCode == 200){
                    //遍历数据
                    $(msg.map.page.list).each(function (index,item) {
                        var tr = $("<tr class='cleandata' align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                            "\t<td><input name=\"id\" type=\"checkbox\" id=\"id\" value=\""+item.nid+"\" class=\"np\"></td>\n" +
                            "\t<td>"+((msg.map.page.pageNum-1)*msg.map.page.pageSize+(index+1))+"</td>\n" +
                            "\t<td>"+item.ntitle+"</td>\n" +
                            "\t<td align=\"center\"><span >"+item.remark+"</span></td>\n" +
                            "\t<td>"+moment(item.ndate).format("YYYY-MM-DD")+"</td>\n" +
                            "\t<td><a >删除</a>|<a>编辑</a></td>\n" +
                            "</tr>");
                        $("#addList").before(tr);
                    });
				};
                //先添加查询的要的td
                var td = $("<td height=\"36\" colspan=\"12\" align=\"center\" id=\"fenye\"></td>");
                $("#fenyeTd").append(td);
                //展示分页内容
                var firstPage =$("<a onclick='findList(this.name)' href='#' style='font-size: 15px;' name='"+msg.map.requestURI+"?pageNum=1"+msg.map.queryStr+"' >首页&nbsp;</a>");
                var prePage = $("<a onclick='findList(this.name)' href='#' style='font-size: 15px;' name='"+msg.map.requestURI+"?pageNum="+(msg.map.page.pageNum-1)+msg.map.queryStr+"' >&nbsp;上一页&nbsp;</a>");
                var pagePage = "";
                //展示有几页 即1,2,3,4,5
                $(msg.map.page.navigatepageNums).each(function (index,item) {
                    if(item == msg.map.page.pageNum){
                        pagePage = pagePage + "<a style='pointer-events: none;' ><span style='font-size: 15px;color: black;font-weight: bold'>&nbsp;"+item+"&nbsp;</span></a>";
                    }
                    if(item != msg.map.page.pageNum){
                        pagePage = pagePage + "<a onclick='findList(this.name)' href='#' style='font-size: 15px;' name='"+msg.map.requestURI+"?pageNum="+item+msg.map.queryStr+"' >&nbsp;"+item+"&nbsp;</a>";
                    }
                });
                var nextPage = $("<a onclick='findList(this.name)' href='#' style='font-size: 15px;' name='"+msg.map.requestURI+"?pageNum="+(msg.map.page.pageNum+1)+msg.map.queryStr+"' >&nbsp;下一页&nbsp;</a>");
                var endPage = $("<a onclick='findList(this.name)' href='#' style='font-size: 15px;' name='"+msg.map.requestURI+"?pageNum="+msg.map.page.pages+msg.map.queryStr+"' >&nbsp;尾页&nbsp;</a>");
                var tzk = $("<span style='font-size: 15px;'>&nbsp;跳转到 <input size='2px' id='changePage1' onblur='changePage()'> 页</span>");

                $("#fenye").append(firstPage).append(prePage).append(pagePage).append(nextPage).append(endPage).append(tzk);

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
    当前位置:信息箱>>通知公告
 </td>
	  <td>
		  <input type='button' class="coolbg np" onClick="location='notice-send.jsp';" value='发布新通告' />
	  </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->

<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;发件箱&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" >
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">标题</td>
	<td width="10%">内容</td>
	<td width="8%">发送时间</td>
	<td width="8%">操作</td>
</tr>
<tr bgcolor="#FAFAF1" id="addList">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA" id="fenyeTd">
</tr>
</table>

</form>
</body>
</html>