<%-- 
    Document   : AdminPage
    Created on : Oct 14, 2020, 2:10:54 PM
    Author     : Dell
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome.min.css" />"
          rel="stylesheet">
    <link href="<c:url value="/resources/css/prettyPhoto.css" />"
          rel="stylesheet">
    <link href="<c:url value="/resources/css/price-range.css" />"
          rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />"
          rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css" />"
          rel="stylesheet">
    <link href="<c:url value="/resources/css/responsive.css" />"
          rel="stylesheet">

    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.scrollUp.min.js" />"></script>
    <script src="<c:url value="/resources/js/price-range.js" />"></script>
    <script src="<c:url value="/resources/js/jjquery.prettyPhoto.js" />"></script>
    <script src="<c:url value="/resources/js/main.js" />"></script>

    <body>


        <form action="<c:url value='/j_spring_security_logout' />" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
        <c:if test="${pageContext.request.userPrincipal.name == null}" >
        <li><a href="login"><i class="fa fa-lock"></i> Login</a></li>
        </c:if>

    <c:if test="${pageContext.request.userPrincipal.name != null}">

        <li><a>Wellcome, ${pageContext.request.userPrincipal.name}   |</a> 
        </li>
        <li><a href="javascript:formSubmit()"> Logout</a></li>
        </c:if>

    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script> 



    <section>
        <!-- Left Sidebar -->
        <aside id="leftsidebar" class="sidebar">
            <!-- Menu -->
            <div class="menu">
                <ul class="list">
                    <li class="header">MENU</li>

                    <li>
                        <!--  <a href="javascript:void(0);" class="menu-toggle">
                              <span></span>
                          </a> -->
                        <ul class="ml-menu">
                            <li>
                                <a href="${pageContext.request.getContextPath()}/showOrder">View All Orders</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.getContextPath()}/showPageReport">Report</a>
                            </li>


                        </ul>
                    </li>

                </ul>
            </div>
            <!-- #Menu -->
            <!-- Footer -->
            <!-- #Footer -->
        </aside>

    </section>

    <div class="content-box-large">

        <div class="input-group form">
            <form action="${pageContext.request.getContextPath()}/dailyReport" method="get">


                <label >DAILY REPORT</label>
                <input type="text" size="100" class="input-group" name="date" placeholder="enter Date">

                <span class="input-group-btn">
                    <!-- <button class="btn btn-primary" type="button">Search</button> -->
                    <input class="btn btn-primary" type="submit" value="Submit">
                </span>
            </form>
        </div>
        <div class="panel-body">

            <div class="table-responsive">

                <table class="table">
                    <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th>RESULT ORDERS</th>
                        </tr>
                        <tr>
                            <th>Order Id</th>
                            <th>Order Date</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th>Customer Full Name</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="totalMoneyInDate" value="${0}" /> 
                        <c:forEach var="order" items="${orderLists}">
                            <c:set var="totalMoneyInDate" value="${totalMoneyInDate + order.amount}" />
                            <tr>
                                <td>${order.orderId}</td>
                                <td>${order.orderDate}</td>
                                <td>${order.customer.customerAddress}</td>
                                <td>${order.customer.phone}</td>
                                <td>${order.customer.customerFirstName} ${order.customer.customerLastName}</td>
                                <td>${order.formattedAmount}</td>
                                

                            </tr>

                        </c:forEach>
                        <tr>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th>Total Money In Date:    </th>
                            
                            <td><fmt:formatNumber value="${totalMoneyInDate}" type="currency"/></td>
                        </tr>
                    </tbody>

                </table>
                <div class="input-group form">
                    
                    
                    <a href="${pageContext.request.getContextPath()}/dailyReport/excel">Download Excel</a>
                    
                </div>



            </div>
        </div>
    </div>
</body>
</html>
