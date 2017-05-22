<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="top_template.jsp"/>
<jsp:include page="error_list.jsp" />
<div class="row">
  <div class="col-sm-3">
	<jsp:include page="customer_menu.jsp"/>
  </div>
  <div class="col-sm-9">
	<h1>Buy Fund</h1>
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th>Fund ID</th>
	      <th>Fund Name</th>
	      <th>Symbol</th>
	      <th></th>
	    </tr>
	  </thead>
	  <tbody>
	    <c:forEach var = "fund" items = "${fundList}">
	      <tr>
	        <td>${fund.fundId}</td>
	        <td>${fund.name}</td>
	        <td>${fund.symbol}</td>
	        <td>
	          <form method="POST">
				<input type="hidden" name="id" value = "${fund.fundId}"/>
			    <input type="submit" class="btn btn-info" name="buy" value="Buy This Fund"/>
			  </form>
	        </td>
	      </tr>
	    </c:forEach>
	  </tbody>
	</table>
  </div>
<jsp:include page="bottom_template.jsp" />