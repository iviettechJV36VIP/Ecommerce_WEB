

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
                    <li>
                        <a href="${pageContext.request.getContextPath()}/showProduct">View All Products</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.getContextPath()}/showCustomer">View All Customers</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.getContextPath()}/addCategory">Add Category</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.getContextPath()}/addProducer">Add Producer</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.getContextPath()}/showOrder">View All Orders</a>
                    </li>
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
                            <th>ORDER DETAILS</th>
                        </tr>
                        <tr>
                            <th>Order Details Id</th>
                            <th>Product Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th> 
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="orderDetailses" items="${orderDetailses}">
                        <tr>
                            <td>${orderDetailses.orderDetailsId}</td>
                            <td>${orderDetailses.product.productName}</td>
                            <td>${orderDetailses.product.formattedPrice}</td>
                            <td>${orderDetailses.quantity}</td>

                            <td><fmt:formatNumber value="${orderDetailses.product.price * orderDetailses.quantity}" type="currency"/></td>
                            
                            
                            
                        </tr>
                        </c:forEach>
                    </tbody>

                </table>

            </div>
        </div>
    </div>
</body>
</html>
