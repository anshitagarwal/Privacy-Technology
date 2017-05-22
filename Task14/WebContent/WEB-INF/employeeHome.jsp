<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="top_template.jsp"/>
<div class="row">
  <div class="col-sm-3">
	<jsp:include page="employee_menu.jsp"/>
  </div>
  <div class="col-sm-9"><h1>Welcome, ${ employee.firstName } ${ employee.lastName }!</h1></div>
</div>
<jsp:include page="bottom_template.jsp"/>