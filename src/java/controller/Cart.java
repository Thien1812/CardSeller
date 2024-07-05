/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.cartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.CartItem;
import model.User;
import model.viewModel.CartDetailVM;

/**
 *
 * @author BINH
 */
public class Cart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("acc") != null) {
            String action = request.getParameter("action") == null ? "" : request.getParameter("action");
            switch (action) {
                case "view": {
                    viewUserCart(request, response);
                    break;
                }

            }
        } else {
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("acc") != null) {
            String action = request.getParameter("action") == null ? "" : request.getParameter("action");
            switch (action) {
                case "add": {
                    addToCart(request, response);
                    break;
                }

            }
        } else {
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void viewUserCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("acc");
            cartDAO cartDAO = new cartDAO();
            List<CartDetailVM> listUserCart = cartDAO.getCartByUserId(user.getID());
            if (listUserCart != null) {
                request.setAttribute("CARTS", listUserCart);
            }
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("acc");
            String cardquantity = request.getParameter("card-quantity");
            String cardDetailId = request.getParameter("cardDetailId");
            cartDAO cartDAO = new cartDAO();
            CartItem cartItem = new CartItem();
            
            cartItem.setCartDetailId(Integer.parseInt(cardDetailId));
            cartItem.setQuantity(Integer.parseInt(cardquantity));
            cartItem.setUserId(user.getID());
            boolean result = cartDAO.addToCart(cartItem);
            if(result) {
                request.setAttribute("MESSAGE", "ADD TO CART SUCCESSFULLY");
            } else {
                System.out.println("Failed add to cart");
            }
            response.sendRedirect("home");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
