<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a{
		text-decoration: none;
	
	}
</style>
<script src="js/jquery/2.0.0/jquery.min.js"></script>
<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>


<script type="text/javascript">

	

</script>
<!-- <script type="text/javascript" src="js/jquery.min.js"></script> -->
<script type="text/javascript">
	$(function() {
		
		var pageNo=1;
		var pageCount=0;
		var pageSize=0;
		var total=0;
		
		
		
		list();
		function list(){
			
			$("#tBody").html("");
			
			$.post(
					"student/list.do",
					{"pageNo":pageNo},
					function(data) {
						//alert(data.sList);
						
						pageNo=data.pu.pageNo;
						pageCount=data.pu.pageCount;
						pageSize=data.pu.pageSize;
						total=data.pu.total;
						$("#pageNo").html(pageNo);
						$("#pageCount").html(pageCount);
						$("#pageSize").html(pageSize);
						$("#total").html(total);
						var i=1;
						$(data.sList).each(function(){
							//alert(data);
							$("#tBody").append("<tr><td>"+((i++)+(pageNo-1)*pageCount)
									+"</td><td>"+this.id
									+"</td><td>"+this.name
									+"</td><td>"+this.age
									+"</td><td><a href='jsp/student/edit.jsp?id="+this.id+
											"'>修改</a>||<a href='student/delete.do?id="+this.id+"'>删除</a></td></tr>");
						})
						
						
					},
					"json"
			)
		}
		
$("#addBtn").click(function(){
			
			window.location.href="jsp/student/add.jsp";
		})
		
		$("#firstPage").click(function(){
			
			if (pageNo==1) {
				return false;
			}else {
				pageNo=1;
				list();
			}


			
		})
		
		$("#proPage").click(function(){
			if (pageNo==1) {
				return false;
			}else {
				pageNo--;
				list();
			}


			
		})
		$("#nextPage").click(function(){
			if (pageNo==pageSize) {
				return false;
			}else {
				pageNo++;
				list();
			}


			
		})
		$("#lastPage").click(function(){
			if (pageNo==pageSize) {
				return false;
			}else {
				pageNo=pageSize;
				list();
			}
		})

	})
	
	
</script>
</head>
<body>
	欢迎,${username}<br />
	<hr/>
	<h3 align="center">学生信息管理系统</h3>
	<table class="table table-hover" border="0" align="center"  style="width:70%">
		
		<tr >
			<td style="text-align: left" >
				<button class="btn btn-success" id="addBtn" >添加学生</button>

			</td>	
		</tr>
	</table>
	<table class="table table-hover" border="1"  align="center" cellpadding="6" style="width:70%">
		<thead>
			<tr>	
			<td>序号</td>
			<td>编号</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>操作</td>
		</tr>
		</thead>
		<tbody id="tBody">
			
		</tbody>

	</table>
	
	<table class="table table-hover" border="0" align="center"  style="width:70%">
		
		<tr >
			<td align="center">
				共<span id="total"> </span>条记录&nbsp;&nbsp;
				共<span id="pageSize"> </span>页&nbsp;&nbsp;
				<span id="pageCount"> </span>条/页&nbsp;&nbsp;
				当前第<span id="pageNo"> </span>页&nbsp;&nbsp;
			
			</td>
		</tr>
		
		<tr >
			<td align="center">
				<a href="javascript:void(0)" id="firstPage">首页</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" id="proPage">上一页</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" id="nextPage">下一页</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" id="lastPage">尾页</a>&nbsp;&nbsp;
			
			</td>
		</tr>
	</table>
</body>
</html>