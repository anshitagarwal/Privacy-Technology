<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="top_template.jsp"/>
<div class="row">
  <div class="col-sm-3">
	<jsp:include page="employee_menu.jsp"/>
  </div>
  <div class="col-sm-9">
    <h1>Customer: ${customer.userName}</h1>
    <h3>Account Detail</h3>
    <table class="table table-striped">
      <tr>
        <th>Customer ID</th>
        <td>${customer.customerId}</td>
      </tr>
      <tr>
        <th>User Name</th>
        <td>${customer.userName}</td>
      </tr>
      <tr>
        <th>Firstname</th>
        <td>${customer.firstName}</td>
      </tr>
      <tr>
        <th>Last Name</th>
        <td>${customer.lastName}</td>
      </tr>
      <tr>
        <th>Address Line 1</th>
        <td>${customer.addrLine1}</td>
      </tr>
      <tr>
        <th>Address Line 2</th>
        <td>${customer.addrLine2}</td>
      </tr>
      <tr>
        <th>City</th>
        <td>${customer.city}</td>
      </tr>
      <tr>
        <th>State</th>
        <td>${customer.state}</td>
      </tr>
      <tr>
        <th>Zip</th>
        <td>${customer.zip}</td>
      </tr>
      <tr>
        <th>Balance</th>
        <td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${customer.cash}"/></td>
      </tr>
      <tr>
        <th>Available</th>
        <td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${customer.available}"/></td>
      </tr>
      <tr>
        <th>Last Trading day</th>
        <td>${lastDate}</td>
      </tr>
    </table>
    <br>
    <h3>Shares Owned</h3>
    <table class="table table-striped">
	  <thead>
	    <tr>
	      <th>Fund Name</th>
	      <th>Symbol</th>
	      <th>Shares</th>
	      <th>Value</th>
	    </tr>
	  </thead>
	  <tbody>
	    <c:forEach var = "fund" items = "${fundList}">
	      <tr>
	        <td>${fund.fundName}</td>
	        <td>${fund.fundSymbol}</td>
	        <td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="3" minFractionDigits="3" value="${fund.shares}"/></td>
	        <td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${fund.value}"/></td>
	      </tr>
	    </c:forEach>
	  </tbody>
	</table>
  </div>
</div>
<jsp:include page="bottom_template.jsp"/>