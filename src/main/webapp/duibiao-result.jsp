<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>对标管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <% String  basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/"; %>
    <script type="text/javascript" src="<%=basePath%>static/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
</head>
<body leftmargin="8" topmargin="8">

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:对标管理>>对标分析
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<%--设置一个容器--%>
<div id="main" style="height: 600px;width: 800px; background-color: ghostwhite"></div>
</body>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById("main"));
    var names=[];
    var values=[];
    $.ajax({
        url:"<%=basePath%>bench/findAllBenchmarking",
        type:"get",
        success:function (msg) {
            $(msg).each(function (index,item) {
               names.push(item.companyName) ;
               values.push(item.salesAmount);
            });
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '2018同行营业额对比'
                },
                tooltip: {},
                legend: {
                    data:['营业额']
                },
                xAxis: {
                    data: names
                },
                yAxis: {
                    axisLabel:{
                        formatter:'{value} 亿'
                    }
                },
                series: [{
                    name: '营业额',
                    type: 'bar',
                    data: values
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });
</script>
</html>