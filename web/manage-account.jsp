<%-- 
    Document   : manage-account
    Created on : Jun 17, 2024, 10:45:00 PM
    Author     : badao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Account</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <style>
            * {
                margin:0;
                padding:0;
                box-sizing: border-box;
                font-family: sans-serif;

            }

            body {
                background-color:#DEDEE8;
                background-size: 100%;
                min-height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: 62.5%;
            }

            main.res_table {
                width: 82vw;
                height: 90vh;
                background-color: #fff5;
                backdrop-filter: blur(7px);
                box-shadow: 0.4rem .8rem #0005;
                border-radius: 10px;
                border-color: #0005;
            }

            table {
                width: 100%;

            }

            .t_header {
                width: 100%;
                height: 10%;
                background-color: #fff4;
                padding: .8rem 1rem;
                display: flex;
                justify-content: space-between;
                align-items: center;

            }
            .t_header .search-group{
                width:35%;
                height:100%;
                background-color:#fff5;
                padding:0.8rem;
                border-radius: 2rem;
                display: flex;
                justify-content: center;
                align-items: center;

            }
            .t_header .search-group input{
                width:95%;
                max-height:calc(89%-.1.6rem);
                background-color:transparent;
                border: none;
                outline:none;
            }
            .t_header .search-group img{
                width:1.2rem;
                height:1.2rem;
            }
            .t_header .search-group:hover{
                width:45%;
                height:100%;
                background-color:#fff8;
                box-shadow: 0.1rem .4rem #0005;
            }
            .t_body {
                width: 95%;
                height: calc(89%-.1.6rem);
                background-color: #fffb;
                margin: .8rem auto;
                border-radius: .4rem;
                overflow: auto;
            }

            .t_body ::-webkit-scrollbar {
                width: 0.5rem;
                height: 0.5rem;
            }

            .t_body ::-webkit-scrollbar-thumb {
                border-radius: .5rem;
                background-color: #0004;
                visibility: hidden;
            }

            .t_body :hover::-webkit-scrollbar-thumb {
                visibility: visible;
            }

            table, th, td {
                border-collapse: collapse;
                padding: 1rem;
                text-align:left;
            }

            thead th {
                position: sticky;
                top: 0;
                left: 0;
                background-color: #d5d1defe;
            }
            tbody tr:nth-child(even){
                background-color: #0000000b;
            }
            tbody tr{
                --delay:.1s;
                transition: .5s ease-in-out var( --delay),background-color 0s;
            }
            tbody tr.hide{
                opacity: 0;
                transform: translateX(100%);
            }
            tbody tr:hover{
                background-color:#fff6;
            }
            tbody tr td,
            tbody tr td p{
                transition:.2s ease-in-out;
            }
            tbody tr.hide td,
            tbody tr.hide td p{
                padding:0;
                font:0/0 sans-serif;
                transition:.2s ease-in-out;
            }
            .S_button {
                padding:.4rem 0;
                border-radius: 0.5rem;
                text-align: center;
                background:#f6e05e;
                border-color:#f6e05e;
                border-width: 1px;
                border-style: solid;

            }
            @media(max-width:1000px){
                td:not(:first-of-type){
                    min-width: 12.1rem;
                }
            }
            .pagination{
                justify-content: center;
                align-items: center;
                text-align: center;
            }
            .pagination a{
                color: black;
                text-decoration: none;
                padding: 8px 15px;
                display: inline-block;
            }
            .pagination a.active{
                background-color: #007bff;
                font-weight: bold;
                border-radius: 5px;
            }
            .pagination a:hover:not(.active){
                background-color: hsl(0, 0%, 77%);
                border-radius: 5px;
            </style>

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
        <body>
            <main class="res_table">              
                <h3><a class="nav-link active" aria-current="page" href="home">Home</a></h3>
                <section class="t_header">
                    <h1>Account List</h1>
                    <div class="search-group">
                        <input type="search" placeholder="Search data...">
                        <img src="image/search.png" alt=""/>
                    </div>
                </section>
                <section class="t_body">
                    <table>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Username</th>
                                <th>Email</th>
                                <th>PhoneNumber</th>
                                <th>CreatedAt</th>
                                <th>UpdateAt</th>
                                <th>Status</th>
                                <th>Role</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.list1}" var="c">
                                <c:set var="id" value="${c.ID}"/>
                                <c:if test="${c.role!='admin'}">
                                    <tr>
                                        <td>${id}</td>
                                        <td>${c.username}</td>
                                        <td>${c.email}</td>
                                        <td>${c.phoneNumber}</td>
                                        <td>${c.createdAt}</td>
                                        <td>${c.updatedAt}</td>
                                        <td>
                                            <c:if test="${c.isDeleted==true}">Deactivated <br>at ${c.deletedAt}</c:if>
                                            <c:if test="${c.isDeleted==false}"> Activated</c:if>                                              
                                            </td>
                                            <td>${c.role}</td>
                                        <td><a href="manageaccount?aid=${id}&st=${c.isDeleted}" class="S_button">Status change</a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>
                <div class="pagination">
                    <c:forEach begin="1" end="${endP}" var="i">
                        <a href="manageaccount?idx=${i}" class="page-item">${i}</a>
                    </c:forEach>
                </div>
            </main>
        </body>
    </html>
    <script>
        const search = document.querySelector('.search-group input');
        tables_rows = document.querySelectorAll('tbody tr ');

        search.addEventListener("input", searchTable);
        function searchTable() {
            tables_rows.forEach((row, i) => {
                let table_data = row.textContent,
                        search_data = search.value;

                row.classList.toggle('hide', table_data.indexOf(search_data) < 0);
                row.style.setProperty('--delay', i / 25 + 's');
            })
            document.querySelectorAll('tbody tr:not(.hide)').forEach((visible_row, i) => {
                visible_row.style.backgroundColor = (i % 2 == 0) ? 'transparent' : '#0000000b';
            });
        }
    </script>