<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="top_template.jsp"/>

<div class = "row">
	<div class = "col-sm-3">
		<jsp:include page="employee_menu.jsp"/>
	</div>
	
	<div class = "col-sum-9">
		<jsp:include page="error_list.jsp"/>
		<form>
			<c:choose>
				<c:when test="${(empty successMessage) }">
					<div class="form-group">
						<label>User Name: </label>
						<div>
							<input type="text" name="userName" value="${form.userName}" />
						</div>
					</div>
					<div class="form-group">
						<label>Password: </label>
						<div>
							<input type="password" name="password" value="${form.password}" />
						</div>
					</div>
					<div class="form-group">
						<label>First Name: </label>
						<div>
							<input type="text" name="firstName" value="${form.firstName}" />
						</div>
					</div>
					<div class="form-group">
						<label>Last Name: </label>
						<div>
							<input type="text" name="lastName" value="${form.lastName}" />
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<p style="font-size:medium; color:blue">${ successMessage }</p>
					</div>
					<div class="form-group">
						<label>User Name: </label>
						<div>
							<input type="text" name="userName" value="" />
						</div>
					</div>
					<div class="form-group">
						<label>Password: </label>
						<div>
							<input type="password" name="password" value="" />
						</div>
					</div>
					<div class="form-group">
						<label>First Name: </label>
						<div>
							<input type="text" name="firstName" value="" />
						</div>
					</div>
					<div class="form-group">
						<label>Last Name: </label>
						<div>
							<input type="text" name="lastName" value="" />
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		
	
			
			<input type="submit" class="btn btn-primary" name=addEmployee value="Add Employee">
		</form>
		
		
		
		
		<!-- 
		<div class="table-responsive">
		
			<table class="table">

				<tr>
					<td>User Name:</td>
					<td><input type="text" name="userName"
						value="${form.userName}" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"
						value="${form.password}" /></td>
				</tr>
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="firstName"
						value="${form.firstName}" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" name="lastName"
						value="${form.lastName}" /></td>
				</tr>
				<tr>
					<td><input type="submit" name="addEmployee"
						value="Add Employee" /></td>
				</tr>

			</table>
		</div>
		 -->
		
	</div>
</div>


<jsp:include page="bottom_template.jsp"/>