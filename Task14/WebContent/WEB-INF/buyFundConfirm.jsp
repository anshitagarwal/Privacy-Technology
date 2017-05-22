<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="top_template.jsp"/>
<div class="row">
  <div class="col-sm-3">
	<jsp:include page="customer_menu.jsp"/>
  </div>
  <div class="col-sm-9">
	<h1>Confirm Buying Fund</h1>
	<br>
	<c:choose>
	  <c:when test="${(empty successMessage)}">
	    <h3>Balance: <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${customer.cash}"/></h3>
	    <h3>Available: <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${customer.available}"/></h3>
	    <br>
	    <jsp:include page="error_list.jsp" />
	    <form class="form" method="post" action="buyConfirm.do">
	      <div class="form-group">
	        <label for="amount">Please input the amount of dollars you want for buying ${fund.name}:</label>
	      </div>
	      <div class="form-group">
	        <div class="col-sm-3">
	          <input type="hidden" name="confirmid" value = "${fund.fundId}"/>
	          <input type="text" class="form-control" name="amount">
	        </div>
	        <div class="col-sm-3">
	          <label>$</label>
	        </div>
	        <input type="submit" class="btn btn-info" value="Confirm" name="confirm">
	      </div>
	    </form>
	  </c:when>
	  <c:otherwise>
	    <h2>Buy fund successfully!</h2>
	    <a href="buyFund.do" class="btn btn-info">Back to buying fund</a>
	  </c:otherwise>
	</c:choose>
  </div>
<jsp:include page="bottom_template.jsp" />