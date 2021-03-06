

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

        <div class="col-md-5">
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group form">

                        <form:form action="${pageContext.request.getContextPath()}/searchCustomer"  method="get" modelAttribute="searchCustomer">    
                            <input type="text" size="10000" class="form-control" name="searchCustomer" placeholder="Search for (Customer Name, Gender, Email )">
                            <span class="input-group-btn">
                                <!-- <button class="btn btn-primary" type="button">Search</button> -->
                                <input class="btn btn-primary" type="submit" value="SEARCH">
                            </span>
                        </form:form>
                    </div>
                    <div class="input-group form">
                        <li>
                            <a href="${pageContext.request.getContextPath()}/addNewCustomer">ADD NEW CUSTOMER....</a>
                        </li>
                    </div>
                    <c:if test="${messenger != null}">
            <div class="error"><i><h4 style="color:red;">${messenger}</h4></i></div>
                    </c:if>    
                </div>
            </div>
        </div>
        
        <div class="panel-body">

            <div class="table-responsive">

                <table class="table">
                    <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th>ALL Customer</th>
                        </tr>
                        <tr>
                            <th>Customer Id</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Birth Date</th>
                            <th>Gender</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Address</th>

                            <th>User Name</th>

                            <th>Enabled</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customer" items="${customerList.pageList}">
                            <tr>
                                <td>${customer.customerId}</td>
                                <td>${customer.customerFirstName}</td>
                                <td>${customer.customerLastName}</td>
                                <td>${customer.birthdate}</td>
                                <td>${customer.sex}</td>
                                <td>${customer.email}</td>
                                <td>${customer.phone}</td>
                                <td>${customer.customerAddress}</td>

                                <td>${customer.username}</td>

                                <td>${customer.enabled}</td>

                                <td> 


                                    <a href="${pageContext.request.contextPath}/deleteCustomer/${customer.customerId}"
                                       class="btn btn-danger btn-delete" onclick="if (!(confirm('Are you sure you want to delete this customer?')))
                                                   return false"><i class="material-icons">Delete</i></a>

                                </td>
                            </tr>

                        </c:forEach>
                    </tbody>

                </table>

                <c:choose>
                    <c:when test="${customerList.firstPage}">
                        <span>Prev</span>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/showCustomer/prev" var="url" />                  
                        <a href='<c:out value="${url}" />'>Prev</a>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${customerList.pageCount}" step="1"  varStatus="tagStatus">
                    <c:choose>

                        <c:when test="${(customerList.page + 1) == tagStatus.index}">
                            <span>${tagStatus.index}</span>
                        </c:when>
                        <c:otherwise>
                            <c:url value="/showCustomer/${tagStatus.index}" var="url" />                  
                            <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>

                    <c:when test="${customerList.lastPage}">
                        <span>Next</span>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/showCustomer/next" var="url" />                  
                        <a href='<c:out value="${url}" />'>Next</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>
