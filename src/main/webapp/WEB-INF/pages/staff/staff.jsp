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

        
        <div class="panel-body">

            <div class="table-responsive">
                
                <table class="table">
                    <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th>ALL ORDERS</th>
                        </tr>
                        <tr>
                            <th>Order Id</th>
                            <th>Order Date</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th>Full Name</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orderList.pageList}">
                            <tr>
                                <td>${order.orderId}</td>
                                <td>${order.orderDate}</td>
                                <td>${order.customer.customerAddress}</td>
                                <td>${order.customer.phone}</td>
                                <td>${order.customer.customerFirstName} ${order.customer.customerLastName}</td>
                                
                                <td><fmt:formatNumber value="${order.amount}" type="currency"/></td>
                                <td><a href="${pageContext.request.getContextPath()}/viewOrderDetails/${order.orderId}">View Order Details</a></td>
                                
                            </tr>

                        </c:forEach>
                    </tbody>
                    
                </table>
                
                <c:choose>
                    <c:when test="${orderList.firstPage}">
                        <span>Prev</span>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/showOrder/prev" var="url" />                  
                        <a href='<c:out value="${url}" />'>Prev</a>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${orderList.pageCount}" step="1"  varStatus="tagStatus">
                    <c:choose>

                        <c:when test="${(orderList.page + 1) == tagStatus.index}">
                            <span>${tagStatus.index}</span>
                        </c:when>
                        <c:otherwise>
                            <c:url value="/showOrder/${tagStatus.index}" var="url" />                  
                            <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>

                    <c:when test="${orderList.lastPage}">
                        <span>Next</span>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/orderList/next" var="url" />                  
                        <a href='<c:out value="${url}" />'>Next</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>
