<%-- 
    Document   : AdminPage
    Created on : Oct 14, 2020, 2:10:54 PM
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
                                <a href="${pageContext.request.getContextPath()}/formUpload">Up load Images</a>
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

                        <form:form action="${pageContext.request.getContextPath()}/searchProduct"  method="get" modelAttribute="searchProduct">    
                            <input type="text" size="10000" class="form-control" name="searchProduct" placeholder="Search for (Product Name, Price, Date Added, Note)">
                            <span class="input-group-btn">
                                <!-- <button class="btn btn-primary" type="button">Search</button> -->
                                <input class="btn btn-primary" type="submit" value="SEARCH">
                            </span>
                        </form:form>
                    </div>
                    <div class="input-group form">
                        <li>
                            <a href="${pageContext.request.getContextPath()}/addNewProduct">ADD NEW PRODUCT....</a>
                        </li>
                    </div>
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
                            <th>ALL PRODUCTS IN STORE</th>
                        </tr>
                        <tr>
                            <th>Product Id</th>
                            <th>Product Name</th>
                            <th>Price</th>
                            <th>Note</th>
                            <th>Date Added</th>
                            <th>Category Name</th>
                            <th>Producer Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${productList.pageList}">
                            <tr>
                                <td>${product.productId}</td>
                                <td>${product.productName}</td>
                                <td>${product.price}</td>
                                <td>${product.note}</td>
                                <td>${product.dateAdded}</td>
                                <td>${product.category.categoryName}</td>
                                <td>${product.producer.producerName}</td>
                                <td>
                                    
                                    <a href="${pageContext.request.contextPath}/editProduct/${product.productId}"
                                       class="btn btn-info"><i class="material-icons font-16">Edit</i></a>
                                    
                                    <a href="${pageContext.request.contextPath}/deleteProduct/${product.productId}"
                                       class="btn btn-danger btn-delete" onclick="if (!(confirm('Are you sure you want to delete this product?')))
                                                   return false"><i class="material-icons">Delete</i></a>
                                             
                                </td>
                            </tr>

                        </c:forEach>
                    </tbody>
                    
                </table>
                
                <c:choose>
                    <c:when test="${productList.firstPage}">
                        <span>Prev</span>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/showProduct/prev" var="url" />                  
                        <a href='<c:out value="${url}" />'>Prev</a>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${productList.pageCount}" step="1"  varStatus="tagStatus">
                    <c:choose>

                        <c:when test="${(productList.page + 1) == tagStatus.index}">
                            <span>${tagStatus.index}</span>
                        </c:when>
                        <c:otherwise>
                            <c:url value="/showProduct/${tagStatus.index}" var="url" />                  
                            <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>

                    <c:when test="${productList.lastPage}">
                        <span>Next</span>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/showProduct/next" var="url" />                  
                        <a href='<c:out value="${url}" />'>Next</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>
