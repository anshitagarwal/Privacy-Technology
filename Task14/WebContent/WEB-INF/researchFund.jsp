<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="top_template.jsp"/>
<div class="row">
  <div class="col-sm-3">
	<jsp:include page="customer_menu.jsp"/>
  </div>
  <div class="col-sm-9">
    <h1>Research Fund</h1>
    <div class="panel-group" id="accordion">
      <c:forEach var = "fund" items = "${historyList}">
        <div class="panel panel-default">
          <div class="panel-heading">
	        <h4 class="panel-title">
	          <a data-toggle="collapse" data-parent="#accordion" href="#collapse${fund.fundId}">${fund.fundName}</a>
	        </h4>
	      </div>
	      <div id="collapse${fund.fundId}" class="panel-collapse collapse">
	        <div class="panel-body">
	          <div id="container${fund.fundId}" style="width:60%; height:400px;"></div>
	          <table class="table">
	            <thead>
	              <tr>
	                <th>Execute Date</th>
	                <th style="text-align: right">Price</th>
	              </tr>
	            </thead>
	            <tbody>
	              <c:forEach var = "h" items = "${fund.history}">
	                <tr>
	                  <td>${h.priceDate}</td>
	                  <td style="text-align: right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${h.price}"/></td>
	                </tr>
	              </c:forEach>
	            </tbody>
	          </table>
	        </div>
	      </div>
        </div>
        <script>
			$(function () { 
			    var myChart = Highcharts.chart('container${fund.fundId}', {
			        chart: {
			            type: 'line'
			        },
			        title: {
			            text: 'Price History'
			        },
			        xAxis: {
			            categories: [
			            	<c:forEach var = "hd" items = "${fund.history}">
			            	  '${hd.priceDate}',
			            	</c:forEach> ]
			        },
			        yAxis: {
			            title: {
			                text: 'Price'
			            }
			        },
			        series: [{
			            name: '${fund.fundName}',
			            data: [
			            	<c:forEach var = "hp" items = "${fund.history}">
			            	  <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${hp.price}"/>,
			            	</c:forEach>]
			        }]
			    });
			});
	    </script>
	    <br>
      </c:forEach>
    </div>
  </div>
</div>
<jsp:include page="bottom_template.jsp"/>