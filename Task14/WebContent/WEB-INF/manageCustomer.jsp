<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="top_template.jsp"/>
<!-- list the customers iteratively -->

<!-- several Buttons:
	create customer
	reset customer password
	view customer account
	view customer transaction history
 -->
 
<div class = "row">
	<div class="col-sm-3">
		<jsp:include page="employee_menu.jsp"/>
	</div>
	<div class="table-responsive">
		<table class="table">
			<tr>
			 <td><a class="btn btn-info" href="createCustomer.do">Create New Customer</a></td>
			</tr>
			<c:forEach var = "customer" items = "${customerList}">
				<tr>
					<td>${customer.customerId}</td>	
					<td>${customer.userName}</td>
					<td>
					    <form method="POST" action="resetPwd.do">
		                    <input type="hidden" name="id" value="${customer.customerId}"/>
		                    <input type="submit" class="btn btn-info" value="Reset Password"/>
		                </form>
					</td>
					<td>
						<form method="POST" action="viewCustomer.do">
		                    <input type="hidden" name="id" value="${customer.customerId}"/>
		                    <input type="submit" class="btn btn-info" value="View Account"/>
		                </form>
					</td>
					<td>
						<form method="POST" action="viewTransaction.do">
							<input type ="hidden" name="userName" value = "${customer.userName }"/>
							<input type="hidden" name="id" value = "${customer.customerId }"/>
							<input type="submit" class="btn btn-info" value="View Transaction"/>
						</form>
					</td>
					<td>
						<form method="POST" action="depositcheck.do">
							<input type="hidden" name="id" value = "${customer.customerId }"/>
							<input type="submit" class="btn btn-info" value="Deposit Check"/>
						</form>
					</td>
				</tr>
				
			</c:forEach>
		</table>
	</div>
</div>
	


<jsp:include page="bottom_template.jsp"/>