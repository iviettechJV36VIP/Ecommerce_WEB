

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



    
        <div class="panel-body">

            <div class="table-responsive">
                
                <table class="table">
                    <thead>
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/editAccount/${customer.customerId}">
                                       Edit Account</a></td>
                        </tr>
                        <tr>
                            <th></th>
                            <th></th>
                            <th>Profile Customer</th>
                        </tr>
                        <tr>
                            
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Gender</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Address</th>                                     
                        </tr>
                    </thead>
                    <tbody>
                        
                            <tr>
                                
                                <td>${customer.customerFirstName}</td>
                                <td>${customer.customerLastName}</td>

                                <td>${customer.sex}</td>
                                <td>${customer.email}</td>
                                <td>${customer.phone}</td>
                                <td>${customer.customerAddress}</td> 
                            </tr>

                        
                    </tbody>
                    
                </table>
                
                
                
            </div>
        </div>
    
</body>
</html>
