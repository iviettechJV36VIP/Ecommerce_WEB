<%-- 
    Document   : upload
    Created on : Oct 16, 2020, 9:09:43 AM
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
        <title>Up Load File</title>
        <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
        
        <link href="<c:url value="/resources/css/dropzone.css" />"
          rel="stylesheet">
    </head>
    <body>
        <div class="container" style="margin-top: 60px;">
            <div class="row">
                <div class="col-md-6">
                    
                    <h2>UP LOAD FILE....</h2>
                    
                    <form:form action="uploadFile" modelAttribute="image"  method="POST" enctype="multipart/form-data"  >
                        <div class="row clearfix">
                                <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                    <label for="Image Name">Image Name:</label>
                                </div>
                                <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                    <div class="form-group">
                                        <div class="form-line">
                                        <form:input path="imageName" maxlength="100" cssClass="form-control" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                                <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                    <label for="Product Id">Product Id:</label>
                                </div>
                                <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                    <div class="form-group">
                                        <div class="form-line">
                                        <form:input path="product.productId" maxlength="100" cssClass="form-control" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="input-group">
                            <input name="file" type="file" class="form-control">
                        </div>
                        <div>
                          <button type="submit" class="btn btn-success m-t-15 w-90 waves-effect">Save</button>
                        </div>
                    </form:form>
                    
                    
                    </div> 
            </div>
        </div>
        
        <script src="<c:url value="/resources/js/dropzone.js" />"></script>
        <script>
            Dropzone.options.myAwesomeDropzone ={
                paramName: "file",
                maxFilesize:3,
                acceptedFiles: "image/*",
                dictDefaultMessage:"Click hoac keo tha file muon upload",
                dictInvalidFileType:"Chi cho upload hinh anh",
                dictFileTooBig:"Dung luong file toi da 3MB"
             }
        </script>
    </body>
</html>
