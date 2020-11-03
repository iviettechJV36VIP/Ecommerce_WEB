
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



        <section id="slider"><!--slider-->
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <div id="slider-carousel" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
                                <li data-target="#slider-carousel" data-slide-to="1"></li>
                                <li data-target="#slider-carousel" data-slide-to="2"></li>
                            </ol>

                            <div class="carousel-inner">
                                <div class="item active">
                                    <div class="col-sm-6">
                                        <h1><span>E</span>-SHOPPER</h1>
                                        <h2>Free E-Commerce Template</h2>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                                      
                                    </div>
                                    <div class="col-sm-6">
                                        <img src="${pageContext.request.getContextPath()}/resources/images/item/rsz_iphone-12-6131-1601627334.jpg" class="girl img-responsive" style="width: 484px; height: 441px;" alt="" />
                                        <!--
                                        <img src="${pageContext.request.getContextPath()}/resources/images/home/pricing.png" alt=""/>
                                        -->
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="col-sm-6">
                                        <h1><span>E</span>-SHOPPER</h1>
                                        <h2>100% Responsive Design</h2>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                                    </div>
                                    <div class="col-sm-6">
                                        <img src="${pageContext.request.getContextPath()}/resources/images/item/rsz_5f8800ad8f358-smart-tivi-samsung-4k-43-inch-43tu8000-hgqvbn.jpg" class="girl img-responsive"  style="width: 484px; height: 441px;" alt="" />
                                        <img src="images/home/pricing.png"  class="pricing" alt="" />
                                    </div>
                                </div>

                                <div class="item">
                                    <div class="col-sm-6">
                                        <h1><span>E</span>-SHOPPER</h1>
                                        <h2>Free Ecommerce Template</h2>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                                    </div>
                                    <div class="col-sm-6">
                                        <img src="${pageContext.request.getContextPath()}/resources/images/item/rsz_5f8800ad8f358-smart-tivi-samsung-4k-43-inch-43tu8000-hgqvbn.jpg" class="girl img-responsive"  style="width: 484px; height: 441px;" alt="" />
                                        <img src="images/home/pricing.png" class="pricing" alt="" />
                                    </div>
                                </div>

                            </div>

                            <a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
                                <i class="fa fa-angle-left"></i>
                            </a>
                            <a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </div>

                    </div>
                </div>
            </div>
        </section><!--/slider-->

        <section>
            <div class="container">
                <div class="row">
                    <jsp:include page="include/categoryandproducer.jsp"/>

                    <div class="col-sm-9 padding-right">
                        <div class="features_items"><!--features_items-->
                            <h2 class="title text-center">New Products</h2>
                            <c:forEach var="tempProductNew" items="${pagedListHolder.pageList}">
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <img src="${pageContext.request.getContextPath()}/resources/images/item/${tempProductNew.images[0].imageName}" alt="" />
                                                <h2>${tempProductNew.formattedPrice}</h2>
                                                <p>${tempProductNew.productName}</p>

                                            </div>
                                            <div class="product-overlay">
                                                <div class="overlay-content">
                                                    <h2>${tempProductNew.formattedPrice}</h2>
                                                    <p>${tempProductNew.productName}</p>
                                                    <a href="<c:url value="/productDetails/${tempProductNew.productId}" />" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Product Detail</a>
                                                    <a href="<c:url value="/buy/${tempProductNew.productId}" />" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>


                        </div>

                        

                    </div>
                </div>
            </div>
        </section>



        <jsp:include page="include/footer.jsp"/>


    </body>
</html>
