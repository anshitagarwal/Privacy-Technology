<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="top_template.jsp"/>

<div class = "row">
	<div class = "col-sm-3">
		<jsp:include page="customer_menu.jsp"/>
	</div>
	
	<div class = "col-sum-9">
		<jsp:include page="error_list.jsp"/>
		<h3>Balance: <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${customer.cash}"/></h3>
	    <h3>Available: <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${customer.available}"/></h3>
	    <br>
		<form>
			<c:choose>
				<c:when test="${(empty successMessage) }">
					<div class="form-group">
						<label>Enter Amount: </label>
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
						<label>Enter Amount: </label>
						<div>
							<input type="text" name="amount" value="" />
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			<input type="submit" class="btn btn-primary" name=deposit value="Request Check">
			
		</form>
		
	</div>
</div>


<jsp:include page="bottom_template.jsp"/>