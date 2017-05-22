<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="top_template.jsp"/>
<jsp:include page="error_list.jsp" />
<div class="row">
  <div class="col-sm-3">
	<jsp:include page="customer_menu.jsp"/>
  </div>
  <div id="wrapper">
         <div class="chart">
            <h3>Population of endangered species from 2012 to 2016</h3>
            <table id="data-table" border="1" cellpadding="10" cellspacing="0"
            summary="The effects of the zombie outbreak on the populations
            of endangered species from 2012 to 2016">
               <caption>Population in thousands</caption>
               <thead>
                  <tr>
                     <td>&nbsp;</td>
                     <th scope="col">2012</th>
                     <th scope="col">2013</th>
                     <th scope="col">2014</th>
                     <th scope="col">2015</th>
                     <th scope="col">2016</th>
                  </tr>
               </thead>
               <tbody>
                  <tr>
                     <th scope="row">Carbon Tiger</th>
                     <td>4080</td>
                     <td>6080</td>
                     <td>6240</td>
                     <td>3520</td>
                     <td>2240</td>
                  </tr>
                  <tr>
                     <th scope="row">Blue Monkey</th>
                     <td>5680</td>
                     <td>6880</td>
                     <td>6240</td>
                     <td>5120</td>
                     <td>2640</td>
                  </tr>
                  <tr>
                     <th scope="row">Tanned Zombie</th>
                     <td>1040</td>
                     <td>1760</td>
                     <td>2880</td>
                     <td>4720</td>
                     <td>7520</td>
                  </tr>
               </tbody>
            </table>
         </div>
      </div>
  