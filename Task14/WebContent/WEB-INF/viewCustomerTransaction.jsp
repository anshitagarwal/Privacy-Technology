<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="top_template.jsp"/>


<div class = "row">
	<div class="col-sm-3">
		<jsp:include page="customer_menu.jsp"/>
	</div>
	
	<div class="col-sm-9" class="table-responisve">
		<h2>Transaction History for ${userName}</h2>
		<table class="table">
			<thead>
				<tr>
				<th>Execute Date</th>
				<th>Transaction Type</th>
				<th>Fund Name</th>
				<th style="text-align: right">Shares</th>
				<th style="text-align: right">Fund Price</th> 	
				<th style="text-align: right">Amount</th>
				</tr>
			</thead>
			<c:forEach var = "transaction" items = "${transactionList}">
				<tbody>
					<tr>
					<td>
					  <c:choose>
					    <c:when test="${(empty transaction.executeDate)}">pending</c:when>
					    <c:otherwise>${transaction.executeDate}</c:otherwise>
					  </c:choose>
					</td>
					<td>${transaction.transactionType}</td>
					<td>${transaction.fundName}</td>
					
					<c:set var = "buy" scope="request" value="BUY"/>
					<c:set var = "sell" scope="request" value="SELL"/>
					<c:choose>
						<c:when test="${transaction.transactionType eq buy}">
							<td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="3" minFractionDigits="3" value="${transaction.shares}"/></td>
							<td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="3" minFractionDigits="3" value="${transaction.price}"/></td>
						</c:when>
						<c:when test="${transaction.transactionType eq sell}">
							<td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="3" minFractionDigits="3" value="${transaction.shares}"/></td>
							<td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="3" minFractionDigits="3" value="${transaction.price}"/></td>
						</c:when>
						<c:otherwise>
							<td></td>
							<td></td>
						</c:otherwise>
					</c:choose> 
					<td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${transaction.amount}"/></td>
					
					
					
					<%-- <td>${transaction.executeDate}</td>
					<td>${transaction.transactionType}</td>
					<td>${transaction.fundName}</td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="3" minFractionDigits="3" value="${transaction.shares}"/></td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="3" minFractionDigits="3" value="${transaction.price}"/></td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${transaction.amount}"/></td> --%>
				</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
</div>
<jsp:include page="bottom_template.jsp"/>