
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/manage.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </head>
<%
        User acc = (User) request.getSession().getAttribute("acc");
        try{
        if(!acc.role.equals("admin")){
        response.sendRedirect("login.jsp");
        }
    }catch(Exception e){
        response.sendRedirect("login.jsp");
    }
        %>

    <header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand">Manage Card</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="home">Home</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link " aria-current="page" href="manageproduct">All Card</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " aria-current="page" href="manageproduct?category=phonecard">Phone Card</a>
                        </li>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " aria-current="page" href="manageproduct?category=gamecard">Game Card</a>
                        </li>

                    </ul>
                    <form action="searchmanageproduct" class="d-flex" role="search" style="margin-left: 20%">
                        <input name="search" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <c:if test="${sessionScope.acc!=null}">
                            <li class="nav-item">
                                <a class="nav-link" href="profile.jsp">${sessionScope.acc.username}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="logout">Logout</a>
                            </li>
                        </c:if>

                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <section style="background-color: #ffffff; margin-bottom: 100px">
        <div class="container py-5">
            <div class="d-flex flex-column mt-4 col-md-6 col-lg-3 col-xl-3 border-sm-start-none border-start" style="margin-bottom: 10px">                
                <a class="btn btn-outline-primary btn-sm mt-2" href="addproduct.jsp">Add new card</a>             
            </div>
            <div class="row justify-content-center mb-3">
                <div class="col-md-12 col-xl-10">
                    <div class="card shadow-0 border rounded-3">
                        <c:forEach items = "${lists}" var = "card">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-12 col-lg-3 col-xl-3 mb-4 mb-lg-0">
                                        <div class="bg-image hover-zoom ripple rounded ripple-surface">
                                            <img src="${card.image}"
                                                 class="w-100" />
                                            <a href="#!">
                                                <div class="hover-overlay">
                                                    <div class="mask" style="background-color: rgba(253, 253, 253, 0.15);"></div>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6 col-xl-6">
                                        <h5>${card.providerName}</h5>
                                        <div class="d-flex flex-row">
                                            <c:if test="${card.category=='gamecard'}">
                                                <h6>Game Card</h6>
                                            </c:if>
                                            <c:if test="${card.category!='gamecard'}">
                                                <h6>Phone Card</h6>
                                            </c:if>
                                        </div>
                                        <div class="mt-1 mb-0 text-muted small">
                                            <c:forEach items = "${card.pricelist}" var = "p">
                                                ${p.price}
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-3 col-xl-3 border-sm-start-none border-start">
                                        <div class="d-flex flex-column mt-4">
                                            <a class="btn btn-primary btn-sm" href="editproduct?cid=${card.id}"> Edit</a>
                                            <a class="btn btn-outline-primary btn-sm mt-2" onclick="checkDelete('${card.id}')"> Delete</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div>
                    <div class="col-12 col-md-6 col-lg-4" style="margin-left: 50%">
                        <c:forEach begin="1" end="${endP}" var="i">
                            <a href="manageproduct?idx=${i}&category=${category}"class="link-dark">${i}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
    </section>
</html>
<script type="text/javascript">
    function checkDelete(id) {
        if (confirm('Are you sure about deleting this card?')) {
            window.location = 'deleteproduct?id=' + id;
        }
    }
</script>