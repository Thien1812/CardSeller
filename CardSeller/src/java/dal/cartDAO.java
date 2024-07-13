/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.CartItem;
import model.viewModel.CartDetailVM;

/**
 *
 * @author BINH
 */
public class cartDAO extends DBContext {

    public boolean addToCart(CartItem cartItem) throws SQLException {
        String sql = "INSERT INTO CartItem (CardDetailID, quantity, createdAt, UserId) VALUES(?, ?, ?, ?)";
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            st.setInt(1, cartItem.getCartDetailId());
            st.setInt(2, cartItem.getQuantity());

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            st.setString(3, currentDate);
            st.setInt(4, cartItem.getUserId());
            int affectedRow = st.executeUpdate();
            return affectedRow > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add to cart failed");
        } finally {
            st.close();
        }
        return false;
    }

    public boolean updateCart(CartItem cartItem) throws SQLException {
        String sql = "UPDATE CartItem SET quantity = ? WHERE ID = ? AND UserId = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            st.setInt(1, cartItem.getQuantity());
            st.setInt(2, cartItem.getId());
            st.setInt(3, cartItem.getUserId());
            int affectedRow = st.executeUpdate();
            return affectedRow > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update cart failed");
        } finally {
            st.close();
        }
        return false;
    }

    public boolean removeFromCart(int cartItemId) throws SQLException {
        String sql = "DELETE FROM CartItem WHERE ID = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            st.setInt(1, cartItemId);
            int affectedRow = st.executeUpdate();
            return affectedRow > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Remove from cart failed");
        } finally {
            st.close();
        }
        return false;
    }

    public List<CartDetailVM> getCartByUserId(int userId) throws SQLException {
        List<CartDetailVM> cartDetailIem = new ArrayList<>();
        String sql = "SELECT ci.quantity, cd.price, p.providerName, ci.ID FROM CartItem ci JOIN CardDetail cd ON ci.CardDetailID = cd.ID JOIN ProviderDetail p ON cd.ProviderID = p.ID WHERE ci.UserId = ? ";
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CartDetailVM cartDetailVM = new CartDetailVM();
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                float totalPriice = quantity * price;
                cartDetailVM.setProviderName(rs.getString("providerName"));
                cartDetailVM.setId(rs.getInt("ID"));
                cartDetailVM.setQuantity(quantity);
                cartDetailVM.setPrice(price);
                cartDetailVM.setTotalPrice(totalPriice);
                cartDetailIem.add(cartDetailVM);
            }
            return cartDetailIem;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            st.close();
        }
        return null;
    }

    public CartItem getCartItemByUserIdAndCardDetailId(int userId, int cardDetailId) throws SQLException {
        String sql = "SELECT * FROM CartItem WHERE UserId = ? AND CardDetailID = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            st.setInt(1, userId);
            st.setInt(2, cardDetailId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setId(rs.getInt("ID"));
                cartItem.setCartDetailId(rs.getInt("CardDetailID"));
                cartItem.setUserId(rs.getInt("UserId"));
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItem.setCreatedAt(rs.getString("createdAt"));
                cartItem.setUpdatedAt(rs.getString("updatedAt"));
                cartItem.setCreatedBy(rs.getInt("createdBy"));
                cartItem.setIsDeleted(rs.getBoolean("isDeleted"));
                cartItem.setDeletedBy(rs.getInt("deletedBy"));
                cartItem.setDeletedAt(rs.getString("deletedAt"));
                return cartItem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            st.close();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        cartDAO cartDAO = new cartDAO();
        cartDAO.removeFromCart(1);
        List<CartDetailVM> list = cartDAO.getCartByUserId(1004);
        for (CartDetailVM cartDetailVM : list) {
            System.out.println(cartDetailVM.toString());
        }
    }

}



