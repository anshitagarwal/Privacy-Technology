<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="top_template.jsp"/>
<div class="row">
  <div class="col-sm-3">
	<jsp:include page="employee_menu.jsp"/>
  </div>
  <div class="col-sm-9">
  <h2>Last Execute Date: ${lastDate}</h2>
  <jsp:include page="error_list.jsp" />
  <form method="post" action="transactionDay.do">
    <div class="form-group">
      <div class="col-sm-5">
        <label>Please enter Execute Date:</label>
      </div>
      <div class="col-sm-5">
        <input type="text" class="form-control" name="executeDate" placeholder="yyyy-mm-dd">
      </div>
    </div>
    <br>
    <br>
    <c:forEach var = "fund" items = "${fundList}">
      <div class="form-group">
        <div class="col-sm-5">
          <label>Price for ${fund.name}:</label>
        </div>
        <div class="col-sm-5">
          <input type="text" class="form-control" name="fund${fund.fundId}" placeholder="$">
        </div>
      </div>
      <br>
      <br>
    </c:forEach>
    <input type="submit" class="btn btn-primary" name="start" value="Transition Day Start">
  </form>
  </div>
</div>
<jsp:include page="bottom_template.jsp"/>