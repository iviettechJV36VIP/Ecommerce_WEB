
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">

    <jsp:include page="include/res.jsp"/>

    <body>
        <!--/header-->
        <jsp:include page="include/header.jsp"/>



        

        <section>
            <div class="container">
                <div class="row">
                    <jsp:include page="include/categoryandproducer.jsp"/>

                    <div class="col-sm-9 padding-right">
                        <div class="product-details"><!--product-details-->
                            <div class="col-sm-5">
                                <div class="view-product">
                                    <a href=""><img src="${pageContext.request.getContextPath()}/resources/images/item/${product.images[0].imageName}" alt="" "></a>

                                </div>
                                    <!--
                                <div id="similar-product" class="carousel slide" data-ride="carousel">

                                    
                                    <div class="carousel-inner">
                                        <div class="item active">
                                            <a href=""><img src="${pageContext.request.getContextPath()}/resources/images/laptop/1-org.jpg" alt="" style="width: 200px; height: 84px;"></a>
                                        </div>
                                        <div class="item"><a href=""><img src="${pageContext.request.getContextPath()}/resources/images/laptop/1-org.jpg" alt="" style="width: 85px; height: 84px;"></a>
                                        </div>
                                        <div class="item">
                                            <a href=""><img src="${pageContext.request.getContextPath()}/resources/images/laptop/1-org.jpg" alt="" style="width: 85px; height: 84px;"></a>
                                        </div>

                                    </div>

                                    
                                    <a class="left item-control" href="#similar-product" data-slide="prev">
                                        <i class="fa fa-angle-left"></i>
                                    </a>
                                    <a class="right item-control" href="#similar-product" data-slide="next">
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </div>
                                        -->

                            </div>
                            <div class="col-sm-7">
                                <div class="product-information"><!--/product-information-->
                                    <h2>${product.productName}</h2>
                                    <p>Product ID: ${product.productId}</p>
                                    <p>Date Add: ${product.dateAdded}</p>
                                    <p>Category: ${product.category.getCategoryName()}</p>
                                    <p>Producer: ${product.producer.getProducerName()}</p>
                                    <span>
                                        <span>${product.formattedPrice}</span>
                                        <a href="<c:url value="/buy/${product.productId}" />" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                    </span></div><!--/product-information-->
                            </div>
                        </div><!--/product-details-->
                    </div>



                    

                </div>
            </div>
        </div>
    </section>



    <jsp:include page="include/footer.jsp"/>


</body>
</html>
