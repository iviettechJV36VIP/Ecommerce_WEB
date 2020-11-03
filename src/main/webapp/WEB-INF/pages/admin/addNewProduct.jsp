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
    <link href="<c:url value="/resources/css/dropzone.css" />"
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


    <h2 style="text-align: center;">ADD NEW PRODUCT</h2>
    <div class="row clearfix">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="card">
                <div class="body">
                    <form:form action="${pageContext.request.contextPath}/saveProduct" cssClass="form-horizontal" method="post" modelAttribute="product" enctype="multipart/form-data">

                        <form:hidden path="productId" />

                        <div class="form-group">

                            <label for="Product Name" class="col-md-3 control-label">Product Name:</label>        
                            <div class="col-md-4">
                                <form:textarea path="productName" rows="4" cssClass="form-control no-resize" ></form:textarea>
                                </div>

                        </div>
                        <div class="form-group">

                            <label for="Price" class="col-md-3 control-label">Price:</label>        
                            <div class="col-md-4">
                                <form:input  path="price" maxlength="100" cssClass="form-control" />
                            </div>

                        </div>
                        <div class="form-group">

                            <label for="Note" class="col-md-3 control-label">Note:</label>        
                            <div class="col-md-4">
                                <form:input path="note" maxlength="100" cssClass="form-control" />
                                </div>

                        </div>
                        <div class="form-group">

                            <label for="Date Added" class="col-md-3 control-label">Date Added:</label>        
                            <div class="col-md-4">
                                <form:input path="dateAdded"  maxlength="100" cssClass="form-control" />
                                </div>

                        </div>
                        <div class="form-group">

                            <label for="Category Name" class="col-md-3 control-label">Category Name:</label>        
                            <div class="col-md-2">
                                <form:select path="category.categoryId" >

                                        <c:forEach var="category" items="${categoryList}">
                                            <form:option   value="${category.categoryId}" label="${category.categoryName}"/>
                                        </c:forEach> 

                                    </form:select>
                                </div>

                        </div>
                        <div class="form-group">

                            <label for="Producer Name" class="col-md-3 control-label">Producer Name:</label>        
                            <div class="col-md-2">
                                <form:select path="producer.producerId">                                          
                                        <c:forEach var="producer" items="${producerList}">
                                            <form:option   value="${producer.producerId}" label="${producer.producerName}"/>
                                        </c:forEach> 
                                    </form:select>
                                </div>

                        </div>
                        <div class="form-group">

                            <label for="images[0].imageName" class="col-md-3 control-label">Image Name:</label>        
                            <div class="col-md-4">
                                <form:input path="images[0].imageName" maxlength="100" cssClass="form-control" />
                                </div>

                        </div>
                        <div class="form-group">

                            <label for="file" class="col-md-3 control-label">Choose file:</label>      
                            <div class="col-md-4">
                                <input name="file" type="file" class="form-control">
                                </div>

                        </div>

                    <br>
                    <br>
                    <div class="row clearfix">
                        <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">

                            <button type="submit" class="btn btn-success m-t-15 w-90 waves-effect">Save</button>
                            <button type="reset" class="btn btn-warning m-t-15 w-90 waves-effect " 
                                    onclick="if (!(confirm('Are you sure reset and input new Question?')))
                                                return false"> Reset</button>

                        </div>
                    </div> 
                </div>
            </div>

        </form:form>

    </div>
</div>
<script src="<c:url value="/resources/js/dropzone.js" />"></script>
<script>
                                        Dropzone.options.myAwesomeDropzone = {
                                            paramName: "file",
                                            maxFilesize: 3,
                                            acceptedFiles: "image/*",
                                            dictDefaultMessage: "Click hoac keo tha file muon upload",
                                            dictInvalidFileType: "Chi cho upload hinh anh",
                                            dictFileTooBig: "Dung luong file toi da 3MB"
                                        }
</script>
</body>
</html>
