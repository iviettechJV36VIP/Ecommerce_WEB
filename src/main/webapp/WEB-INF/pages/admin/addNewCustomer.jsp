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



    <div class="row ">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="card">
                <div class="body">
                    <form:form action="${pageContext.request.contextPath}/saveCustomer" cssClass="form-horizontal" method="post" modelAttribute="customer">

                        <form:hidden path="customerId" />
                        
                        <div class="form-group">
				<label for="customerFirstName" class="col-md-3 control-label">First
				    Name</label>
				<div class="col-md-9">
				    <form:input path="customerFirstName" cssClass="form-control" />
				</div>
			    </div>
			    <div class="form-group">
				<label for="customerLastName" class="col-md-3 control-label">Last
				    Name</label>
				<div class="col-md-9">
				    <form:input path="customerLastName" cssClass="form-control" />
				</div>
			    </div>

			    <div class="form-group">
				<label for="sex" class="col-md-3 control-label">Gender</label>
				<div class="col-md-9">
				    <form:select cssClass="form-control" path="sex">
                                        <form:option value="male" label="male"/>
                                        <form:option value="female" label="female"/>
                                    </form:select>
				</div>
			    </div>
                            <div class="form-group">
				<label for="email" class="col-md-3 control-label">Email</label>
				<div class="col-md-9">
				    <form:input path="email" cssClass="form-control" />
				</div>
			    </div>
                            <div class="form-group">
				<label for="phone" class="col-md-3 control-label">Phone</label>
				<div class="col-md-9">
				    <form:input path="phone" cssClass="form-control" />
				</div>
			    </div>
                            <div class="form-group">
				<label for="customerAddress" class="col-md-3 control-label">Address</label>
				<div class="col-md-9">
				    <form:input path="customerAddress" cssClass="form-control" />
				</div>
			    </div>
                            <div class="form-group">
				<label for="username" class="col-md-3 control-label">Username</label>
				<div class="col-md-9">
				    <form:input path="username" cssClass="form-control" />
				</div>
			    </div>
                            <div class="form-group">
				<label for="password" class="col-md-3 control-label">Password</label>
				<div class="col-md-9">
				    <form:input path="password" cssClass="form-control" />
				</div>
			    </div>
                            <div class="form-group">
				<label for="enabled" class="col-md-3 control-label">Enabled</label>
				<div class="col-md-9">
				    
                                    <form:select cssClass="form-control" path="enabled">
                                        <form:option value="true" label="true"/>
                                        <form:option value="false" label="false"/>
                                    </form:select>
				</div>
			    </div>
                            <div class="form-group">
				<label for="accountRoles.roleName" class="col-md-3 control-label">Role Name</label>
				<div class="col-md-9">
				    
                                    <form:select cssClass="form-control" path="accountRoles[0].roleName">
                                        <form:option value="ROLE_CUSTOMER" label="ROLE_CUSTOMER"/>
                                        <form:option value="ROLE_STAFF" label="ROLE_STAFF"/>
                                        <form:option value="ROLE_ADMIN" label="ROLE_ADMIN"/>
                                    </form:select>
                                    
				</div>
			    </div>
                            <button type="submit" class="btn btn-success m-t-15 w-90 waves-effect">Save</button>   
                    </form:form>

                </div>
            </div>
        </div>
    </div>
</body>
</html>
