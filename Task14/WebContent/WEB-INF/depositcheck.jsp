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
						<label>Enter Deposit Amount: </label>
						<div>
							<input type="text" name="amount" value="" />
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<p style="font-size:medium; color:blue">${ successMessage }</p>
					</div>
					<div class="form-group">
						<label>Enter Deposit Amount: </label>
						<div>
							<input type="text" name="amount" value="" />
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		
	
			<input type="hidden" name="id" value = "${customer.customerId}"/>
			<input type="submit" class="btn btn-primary" name=deposit value="Deposit Check">
			
		</form>
		
	</div>
</div>


<jsp:include page="bottom_template.jsp"/>