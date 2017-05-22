<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="top_template.jsp"/>
<jsp:include page="error_list.jsp" />
<div class="row">
  <div class="col-sm-3">
	<jsp:include page="employee_menu.jsp"/>
  </div>
<p style="font-size:medium">
	Enter your new password
</p>



<p>
	<form method="POST" action="changeemppwd.do">
		<c:choose>
				<c:when test="${(empty successMessage) }">
					<div class="form-group">
						<label>New Password: </label>
						<div>
							<input type="password" name="newPassword" value="${form.password}" />
						</div>
					</div>
					<div class="form-group">
						<label>Confirm New Password: </label>
						<div>
							<input type="password" name="confirmPassword" value="" />
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<label>New Password: </label>
						<div>
							<input type="password" name="newPassword" value="${form.password}" />
						</div>
					</div>
					<div class="form-group">
						<label>Confirm New Password: </label>
						<div>
							<input type="password" name="confirmPassword" value="" />
						</div>
					</div>
				</c:otherwise>
			</c:choose><input type="submit" class="btn btn-primary" name="button" value="Change Password"/>
		
		
	</form>
</p>

<jsp:include page="bottom_template.jsp" />