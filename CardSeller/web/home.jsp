<%-- 
    Document   : home
    Created on : Jun 13, 2024, 2:52:42 AM
    Author     : BINH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <style>
        body,
        html {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background: #f4f4f4;
        }

        .container {
            max-width: 800px;
            margin: 30px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.15);
        }

        h1 {
            color: #333;
            text-align: center;
            font-size: 24px;
        }

        .card-selection {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            margin-top: 20px;
        }

        .card-selection button {
            background: linear-gradient(to bottom, #ffffff 0%, #e9e9e9 100%);
            border: 1px solid #d1d1d1;
            border-radius: 4px;
            padding: 10px 15px;
            flex-basis: calc(25% - 10px);
            margin-bottom: 10px;
            text-align: center;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }

        .card-selection button:hover {
            background-color: #cccccc;
            border-color: #bbbbbb;
        }

        .card-values {
            margin-top: 20px;
            padding: 10px;
            background: #f9f9f9;
            border: 1px solid #dcdcdc;
            border-radius: 4px;
            flex-wrap: wrap;
        }

        .card-values div {
            width: 250px;
            font-size: 18px;
            color: #555;
            padding: 8px 0;
            border-bottom: 1px solid #eee;
            text-align: center;
        }

        .card-values div:last-child {
            border-bottom: none;
        }

        .card-value {
            display: inline-block;
            margin: 5px;
            padding: 8px 12px;
            background-color: #f0f0f0;
            border: 1px solid #ccc;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .card-value.selected {
            background-color: #fff;
            color: red;
            border-color: red;
        }

        .card-value.selected::after {
            content: "";
            position: absolute;
            top: 0;
            right: 0;
            border-top: 24px solid #cb1c22;
            border-left: 24px solid transparent;
        }
        .container {
            max-width: 800px;
            margin: 30px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.15);
        }

        h1 {
            color: #333;
            text-align: center;
            font-size: 24px;
        }

        .card-selection {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            margin-top: 20px;
        }

        .card-selection div {
            /*background: linear-gradient(to bottom, #ffffff 0%, #e9e9e9 100%);*/
            border: 1px solid #d1d1d1;
            border-radius: 4px;
            padding: 10px 15px;
            flex-basis: calc(25% - 10px);
            margin-bottom: 10px;
            text-align: center;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s, border-color 0.3s;
        }

        .card-selection div.selected {
            /*background-color: #007BFF;*/
            color: red;
            border-color: #red;
        }

        .card-values {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
            padding: 10px;
            background: #f9f9f9;
            border: 1px solid #dcdcdc;
            border-radius: 4px;
        }

        .card-values div {
            font-size: 18px;
            color: #555;
            padding: 8px 20px;
            border-bottom: 1px solid #eee;
            text-align: center;
            border-radius: 10px;
        }

        .card-values div::before {

        }
        .input-card {
            display: block;
            height: 52px;
            width: 50%;
            border: 1px solid #e1e1e1;
            border-radius: 10px;
            padding-left: 30px;
            font-size: 14px;
            color: #666666;
            margin-bottom: 15px;
        }
    </style>

</style>
<body>
    <jsp:include page="header.jsp"/>
    <section class="categories">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6 p-0">
                    <div class="categories__item categories__large__item set-bg"
                         data-setbg="${pageContext.request.contextPath}/img/card/viettel.jpg">
                        <div class="categories__text">
                            <h1>Viettel</h1>
                            <p>
                                Hãy nói theo cách của bạn
                            </p>
                            
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                            <div class="categories__item set-bg" data-setbg="${pageContext.request.contextPath}/img/card/mobilephone_1.jpg">
<!--                                <div class="categories__text">
                                    <h4>Men’s fashion</h4>
                                    <p>358 items</p>
                                    <a href="#">Shop now</a>
                                </div>-->
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                            <div class="categories__item set-bg" data-setbg="${pageContext.request.contextPath}/img/card/fpt.jpg">
<!--                                <div class="categories__text">
                                    <h4>Kid’s fashion</h4>
                                    <p>273 items</p>
                                    <a href="#">Shop now</a>
                                </div>-->
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                            <div class="categories__item set-bg" data-setbg="${pageContext.request.contextPath}/img/card/moblie.jpg">
<!--                                <div class="categories__text">
                                    <h4>Cosmetics</h4>
                                    <p>159 items</p>
                                    <a href="#">Shop now</a>
                                </div>-->
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                            <div class="categories__item set-bg" data-setbg="${pageContext.request.contextPath}/img/card/viettel.jpg">
<!--                                <div class="categories__text">
                                    <h4>Accessories</h4>
                                    <p>792 items</p>
                                    <a href="#">Shop now</a>
                                </div>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>



    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-4">
                    <div class="section-title">
                        <h4>Card List</h4>
                    </div>
                </div>
                <div class="col-lg-8 col-md-8">
                    <ul class="filter__controls">
                        <!--<li class="active" data-filter="*">All</li>-->
                        <li class="${type == 'phonecard' ? 'active' : ''}" >
                            <a data-filter=".quan" href="home?type=phonecard">Phone Card</a>
                        </li>
                        <li class="${type == 'gamecard' ? 'active' : ''}">
                            <a data-filter=".ao"  href="home?type=gamecard">Game Card</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row property__gallery">
                <!--  <c:forEach items="${LIST_CARD}" var="card">
                      <div class="col-lg-3 col-md-4 col-sm-6 mix quan">
                          <div class="product__item">
                              <div class="product__item__pic set-bg" data-setbg="${pageContext.request.contextPath}/img/vinaphone-logo.jpg">
                                  <ul class="product__hover">
                                      <li><a href="${pageContext.request.contextPath}/img/vinaphone-logo.jpg" class="image-popup"><span class="arrow_expand"></span></a></li>
                                      <li><span class="icon_search"></span></li>
                                      <li>
                                      </li>
                                  </ul>
                              </div>
                              <div class="product__item__text" style="text-align: left">
                                  <h6>Provider <b>${card.providerName}</b></h6>
                                  <div class="product__price">
                                      Price: 
                                      <select name="price-type" class="form-select" aria-label="Default select example">
                    <c:forEach items="${card.cardPrice}" var="p">
                        <option value="${p.id}">${p.price}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</div>
                </c:forEach>
                -->
                <form class="container" action="cart" method="POST">
                    <input type="hidden" name="action" value="add"/>
                    <h1>Card Top-Up</h1>
                    <div>
                        Bước 1: Lựa chọn loại thẻ mà bạn mong muốn
                    </div>
                    <div class="card-selection">
                        <!-- Card types will be dynamically generated here -->
                    </div>
                    <div>
                        Bước 2: Lựa chọn giá trị của loại thẻ
                    </div>
                    <div class="card-values">
                        <!-- Card values will appear here based on selection -->
                    </div>
                    <div>
                        Bước 3: Chọn số lượng thẻ
                        <input min="1" max="10" class="input-card" type="number" name="card-quantity" placeholder="Số lượng thẻ"/>
                    </div>
                    <div>
                        Bước 4: Nhập số điện thoại của bạn
                        <input class="input-card" type="text" name="phone" placeholder="Nhập số điện thoại của bạn để nhận mã thẻ"/>
                    </div>
                    <input type="hidden" name="cardDetailId" id="cardDetailId"/>
                    <button  class="site-btn" type="submit">Add to cart</button>
                </form>
                <div style="color: green">${MESSAGE}</div>
            </div>
        </div>
    </section>

   

    <jsp:include page="footer.jsp"/>
</body>

<script>
    document.addEventListener("DOMContentLoaded", function () {
    const cardTypes = {
    <c:forEach items="${LIST_CARD}" var="card">
    '${card.providerName}': [<c:forEach items="${card.cardPrice}" var="p">{
        price: ${p.price},
        id : ${p.id}
                    },</c:forEach>],
    </c:forEach>
    };
            const cardSelectionDiv = document.querySelector('.card-selection');
            const cardValuesDiv = document.querySelector('.card-values');
            Object.keys(cardTypes).forEach(type => {
    const typeDiv = document.createElement('div');
            typeDiv.textContent = type;
            typeDiv.addEventListener('click', () => {
            selectCardType(typeDiv);
                    displayCardValues(cardTypes[type]);
            });
            cardSelectionDiv.appendChild(typeDiv);
    });
            function displayCardValues(values) {
            cardValuesDiv.innerHTML = ''; // Clear previous values
                    values.forEach(value => {
                    const valueDiv = document.createElement('div');
                            valueDiv.className = 'card-value';
                            valueDiv.textContent = value.price.toLocaleString();
                            valueDiv.addEventListener('click', function () {
                                const cardDetailId = document.querySelector('#cardDetailId');
                                cardDetailId.value = value.id;
                            selectValue(valueDiv);
                            });
                            cardValuesDiv.appendChild(valueDiv);
                    });
            }

    function selectCardType(selectedDiv) {
    const allTypes = cardSelectionDiv.querySelectorAll('div');
            allTypes.forEach(div => {
            div.classList.remove('selected');
            });
            selectedDiv.classList.add('selected');
    }

    function selectValue(selectedDiv) {
    const allValues = cardValuesDiv.querySelectorAll('.card-value');
            allValues.forEach(div => {
            div.classList.remove('selected');
            });
            selectedDiv.classList.add('selected');
    }
    });
</script>
</html>


