<%@ page import="java.net.URLEncoder" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ashion Template">
        <meta name="keywords" content="Ashion, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Card seller</title>
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/logo.jpg">

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
              rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome.min.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    </head>
    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Offcanvas Menu Begin -->
        <div class="offcanvas-menu-overlay"></div>
        <div class="offcanvas-menu-wrapper">
            <div class="offcanvas__close">+</div>
            <ul class="offcanvas__widget">
                <li><span class="icon_search search-switch"></span></li>
                <li>
                    <span class="icon_heart_alt"></span>
                    <div class="tip">2</div>
                </li>
                <li>
                    <a href="#">
                        <span class="icon_bag_alt"></span>
                        <div class="tip">2</div>
                    </a>
                </li>
            </ul>
            <div class="offcanvas__logo">
                <a href="./index.html"><img src="${pageContext.request.contextPath}/img/logo-card.jpg" alt=""></a>
            </div>
            <div id="mobile-menu-wrap"></div>
            <div class="offcanvas__auth">
                <c:if test="${acc == null}">
                    <a href="login.jsp">Đăng nhập</a>
                    <a href="signup.jsp">Đăng ký</a>
                </c:if>

                <c:if test="${acc != null}">
                    <a href="vnpay_pay.jsp">${uwallet.amount}</a>
                    <a href="profile.jsp">${acc.username}/</a>
                    <a href="logout">Log out</a>
                </c:if>
                <a href="cart?action=view">Cart</a>
            </div>
        </div> 
        <!-- Offcanvas Menu End -->
        <!-- Header Section Begin -->
        <header class="header">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-2 col-lg-1">
                        <div class="header__logo">
                            <a href="#"><img src="${pageContext.request.contextPath}/img/logo-card.jpg" alt="" style="height: 50px"></a>
                        </div>
                    </div>
                    <div class="col-xl-1">

                    </div>
                    <div class="col-xl-6 col-lg-7">
                        <nav class="header__menu">
                            <ul>
                                <li class="#">
                                    <a href="#">Trang chủ</a>
                                </li>
                                <li class="#">
                                    <a href="#">Sản phẩm</a>
                                </li>
                                <c:if test="${acc.role == 'admin'}">
                                    <li><a href="manageproduct">Quản lí sản phẩm</a></li>
                                    <li class="">
                                        <a href="manageaccount">Quản lí account</a>
                                    </li>
                                </c:if>

                            </ul>
                        </nav>
                    </div>
                    <div class="col-lg-3">
                        <div class="header__right">
                            <div class="header__right__auth">
                                <c:if test="${acc == null}">
                                    <a href="login.jsp">Đăng nhập</a>
                                    <a href="signup.jsp">Đăng ký</a>
                                </c:if>

                                <c:if test="${acc != null}">
                                    <a href="vnpay_pay.jsp">Số dư:${uwallet.amount}đ</a>
                                    <a href="profile.jsp"><b>${acc.username}</b></a>
                                    <a href="logout">Log out</a>
                                </c:if>
                                <a href="cart?action=view">Cart</a>
                            </div>
                            <!--                            <ul class="header__right__widget">
                                                            <li><span class="icon_search search-switch"></span></li>
                                                            <li>
                                                                <a href="#">
                                                                    <span class="icon_heart_alt"></span>
                                                                    <div class="tip">2</div>
                                                                </a>
                                                            </li> 
                                                            <li>
                                                                <a href="#">
                                                                    <span class="icon_bag_alt"></span>
                                                                    <div class="tip">2</div>
                                                                </a>
                                                            </li>
                                                        </ul>-->
                        </div>
                    </div>
                </div>
                <div class="canvas__open">
                    <i class="fa fa-bars"></i>
                </div>
            </div>
        </header>