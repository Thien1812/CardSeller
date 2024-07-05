<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<div class="cart-content">
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="home"><i class="fa fa-home"></i> Trang chủ</a>
                        <span>Vỏ hàng của bạn</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <c:choose>
        <c:when test="${CARTS.size() == 0}">
            <div style="background-image: url('/img/empty_cart.svg'); background-size: contain; width: 800px; height: 80vh; background-repeat: no-repeat; background-position:center; margin: 0 auto;">
                <div class="pt-5">
                    <h6 class="mb-0">
                        <a href="home" class="text-body">
                            <i class="fas fa-long-arrow-alt-left me-2"></i>Nhấn vào đây để trở lại mua hàng
                        </a>
                    </h6>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <section class="shop-cart spad">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="shop__cart__table">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Tên sản phẩm</th>
                                            <th>Giá tiền</th>
                                            <th>Số lượng</th>
                                            <th>Tổng tiền</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${CARTS}" var="c">

                                            <tr>
                                                <td class="cart__product__item">
                                                    <img src="" width="90px" height="90px" alt="Anh san pham">
                                                    <div class="cart__product__item__title">
                                                        <h6>${c.providerName}</h6>
                                                    </div>
                                                </td>
                                                <td class="cart__price">${c.price}VNĐ</td>
                                                <td class="cart__quantity">
                                                    <div class="pro-qty">
                                                        <span class="dec qtybtn" onclick="descreaseQuantity('@item.ItemId')">-</span>
                                                        <input type="text"  value="${c.quantity}"/>
                                                        <span class="inc qtybtn" onclick="increaseQuantity('@item.ItemId')">+</span>
                                                    </div>
                                                </td>
                                                <td class="cart__total">${c.totalPrice}VNĐ</td>
                                                <td class="cart__close">
                                                        <a class="delete-item" onclick="removeItemFromCart('@item.ItemId')" data-itemid="@item.ItemId">
                                                            <span class="icon_close"></span>
                                                        </a>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="cart__btn">
                                <a href="homme">Tiếp tục mua sắm</a>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="cart__btn update__btn">
                                <a href="#"><span class="icon_loading"></span> cập nhật giả hàng</a>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="discount__content">
                                <h6>Áp dụng mã giảm giá</h6>
                                <form action="#">
                                    <button type="submit" class="site-btn">Áp dụng</button>
                                    <input type="text" placeholder="Nhập mã giảm giá của bạn">
                                </form>

                            </div>
                        </div>
                        <div class="col-lg-4 offset-lg-2">
                            <div class="cart__total__procced">
                                <h6>Chi tiết thanh toán</h6>
                                <ul>
                                    <li>Tổng tiền hàng <span>0VNĐ</span></li>
                                    <li>Chi phí vận chuyển <span>0đ</span></li>
                                    <li><strong>Tổng <span>0VNĐ</span></strong></li>
                                </ul>
                                <a href="checkout" class="primary-btn">Tiến hành đặt hàng</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </c:otherwise>
    </c:choose>

    <jsp:include page="footer.jsp"/>

</div>