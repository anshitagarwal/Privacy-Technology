<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="top_template.jsp"/>

<div class = "row">
	<div class = "col-sm-3">
		<jsp:include page="employee_menu.jsp"/>
	</div>
	
	<div class = "col-sm-9">
		<jsp:include page="error_list.jsp"/>
		<form method="post" action="createCustomer.do">
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
					<div class="form-group">
						<label>Address Line 1: </label>
						<div>
							<input type="text" name="addrLine1" value="${form.addrLine1}" />
						</div>
					</div>
					<div class="form-group">
						<label>Address Line 2: </label>
						<div>
							<input type="text" name="addrLine2" value="${form.addrLine2}" />
						</div>
					</div>
					<div class="form-group">
						<label>City: </label>
						<div>
							<input type="text" name="city" value="${form.city}" />
						</div>
					</div>
					<div class="form-group">
						<label>State: </label>
						<div>
							<input type="text" name="state" value="${form.state}" />
						</div>
					</div>
					<div class="form-group">
						<label>ZIP: </label>
						<div>
							<input type="text" name="zip" value="${form.zip}" />
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
					<div class="form-group">
						<label>Address Line 1: </label>
						<div>
							<input type="text" name="addrLine1" value="${form.addrLine1}" />
						</div>
					</div>
					<div class="form-group">
						<label>Address Line 2: </label>
						<div>
							<input type="text" name="addrLine2" value="${form.addrLine2}" />
						</div>
					</div>
					<div class="form-group">
						<label>City: </label>
						<div>
							<input type="text" name="city" value="${form.city}" />
						</div>
					</div>
					<div class="form-group">
						<label>State: </label>
						<div>
							<input type="text" name="state" value="${form.state}" />
						</div>
					</div>
					<div class="form-group">
						<label>ZIP: </label>
						<div>
							<input type="text" name="zip" value="${form.zip}" />
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			<input type="submit" class="btn btn-primary" name=addCustomer value="Add_Customer">
		</form>
	</div>
</div>


<jsp:include page="bottom_template.jsp"/>