<html>
<head>
	<title>Login</title>
 	<link href="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
</head>

<body>
	<div class="container ">
			<h2>mobiEnforce Template Management</h2> 
    </div>
	<div class="container ">
			<h3>Login failed, Username or password incorrect</h3> 
    </div>
	<div class="container">
		<FORM action="${pageContext.request.contextPath}/j_security_check" method="POST">
		 <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Login</h1>
		  <table class="table">
		  	<tr>
				<td>Username</td>
				<td><INPUT type="text" name="j_username" class="form-control"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><INPUT type="password" name="j_password" class="form-control"></td>
			</tr>
			<tr>
				<td></td>
				<td><INPUT type="submit" name="action" value="Login" class="btn btn-primary"></td>
			</tr>
		  </table>
		 </FORM>
	</div>
	
	
	
</body>
</html>