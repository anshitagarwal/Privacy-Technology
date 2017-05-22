<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="top_template.jsp"/>

<div class = "row">
	<div class = "col-sm-3">
		<jsp:include page="employee_menu.jsp"/>
	</div>
	
	<div>
		<jsp:include page="error_list.jsp"/>
		<form method="post" action="createFund.do">
			<c:choose>
				<c:when test="${(empty successMessage) }">
					<div class="form-group">
						<label>Fund Name: </label>
						<div>
							<input type="text" name="name" value="${form.name}" />
						</div>
					</div>
					<div class="form-group">
						<label>Symbol: </label>
						<div>
							<input type="text" name="symbol" value="${form.symbol}" />
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<p style="font-size:medium; color:blue">${ successMessage }</p>
					</div>
					<div class="form-group">
						<label>Fund Name: </label>
						<div>
							<input type="text" name="name" value="${form.name}" />
						</div>
					</div>
					<div class="form-group">
						<label>Symbol: </label>
						<div>
							<input type="text" name="symbol" value="${form.symbol}" />
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		
	
			
			<input type="submit" class="btn btn-primary" name=addFund value="Add Fund">
		</form>
		
	</div>
</div>


<jsp:include page="bottom_template.jsp"/>