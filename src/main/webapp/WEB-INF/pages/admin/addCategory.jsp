<%-- 
    Document   : addNewProduct
    Created on : Oct 15, 2020, 8:46:30 AM
    Author     : Dell
--%>

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
    <body>
        <script src="<c:url value="/resources/js/jquery.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.scrollUp.min.js" />"></script>
        <script src="<c:url value="/resources/js/price-range.js" />"></script>
        <script src="<c:url value="/resources/js/jjquery.prettyPhoto.js" />"></script>
        <script src="<c:url value="/resources/js/main.js" />"></script>

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
                            <li>
                                <a href="${pageContext.request.getContextPath()}/deleteCategory">Detele Category</a>
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

    <h2 style="text-align: center;">ADD NEW CATEGORY</h2>
    <div class="row ">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            
            <div class="card">
                <div class="body">
                    <form:form action="${pageContext.request.contextPath}/saveCategory" cssClass="form-horizontal" method="post" modelAttribute="category">

                        <form:hidden path="categoryId" />

                        <div class="form-group">
                            <label for="categoryName" class="col-md-3 control-label">Category Name:</label>
                            <div class="col-md-4">
                                <form:input path="categoryName" cssClass="form-control" />
                            
                        </div>


                    </div>
                </div>
                           
                <div class="row clearfix">
                            <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">

                                <button type="submit" class="btn btn-success m-t-15 w-90 waves-effect">Save</button>
                                

                            </div>
                        </div>  
            </form:form>

        </div>
    </div>
</div>
</div>
</body>
</html>
