<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <c:forEach var="error" items="${ errors }">
         <p style="font-size:medium; color:red">${ error }</p>
    </c:forEach>