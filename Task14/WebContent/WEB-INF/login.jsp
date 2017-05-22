<jsp:include page="top_template.jsp"/>
<jsp:include page="error_list.jsp"/>
<div class="row">
  <div class="col-sm-3"></div>
  <div class="col-sm-6">
	  <form method="post" action="login.do">
	    <div class="form-group">
	      <label for="username">User Name:</label>
	      <input type="text" class="form-control" name="username">
	    </div>
	    <div class="form-group">
	      <label for="pwd">Password:</label>
	      <input type="password" class="form-control" name="password">
	    </div>
	    <input type="submit" class="btn btn-default" name="loginType" value="Login as an employee">
	    <input type="submit" class="btn btn-primary" name="loginType" value="Login as a customer">
	  </form>
  </div>
  <div class="col-sm-3"></div>
</div>
<jsp:include page="bottom_template.jsp"/>