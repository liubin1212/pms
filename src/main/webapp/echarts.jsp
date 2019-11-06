
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Echarts</title>
    <% String  basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/"; %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>

</head>
<body>

<%--设置一个容器--%>
<div id="main" style="height: 500px;width: 700px; background-color: #b4ffae"></div>
</body>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById("main"));
    var names=[];
    var values=[];
    $.ajax({
        url:"<%=basePath%>echarts/data",
        type:"get",
        success:function (msg) {
            for(key in msg){
                names.push(key);
                values.push(msg[key]);
            };
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'ECharts 入门示例'
                },
                tooltip: {},
                legend: {
                    data:['销量']
                },
                xAxis: {
                    data: names
                },
                yAxis: {},
                series: [{
                    name: '销量',
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
