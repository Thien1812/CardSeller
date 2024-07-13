/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.cardDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.viewModel.CardHomepageVM;

/**
 *
 * @author BINH
 */
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String type = request.getParameter("type");
            cardDAO _cardDAO = new cardDAO();
            String indexS = request.getParameter("index");

            if (indexS == null) {
                indexS = "1";
            }
            if(type == null) {
                type = "phonecard";
            }
            int index = Integer.parseInt(indexS);
            List<CardHomepageVM> listCard = _cardDAO.getAllProduct(index,type);
            int total = _cardDAO.getAllProviderTotal();
            int lastPage = total / 9;
            if (total % 9 != 0) {
                lastPage++;
            }
            request.setAttribute("type", type);
            request.setAttribute("LIST_CARD", listCard);
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
