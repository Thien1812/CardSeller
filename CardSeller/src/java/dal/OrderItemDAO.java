package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.OrderItem;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.time.LocalDateTime;
import model.CardOrderHistory;

/**
 *
 * @author hacom
 */

public class OrderItemDAO extends DBContext {

    public List<CardOrderHistory> getOrderItemsByUserId(int userId, int page, int itemsPerPage) {
        List<CardOrderHistory> orderItems = new ArrayList<>();
        String sql = "SELECT ID, providerName, image, createdAt, quantity, price " +
                     "FROM PurchaseHistory " +
                     "WHERE UserID = ? " +
                     "ORDER BY createdAt DESC " +
                     "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, (page - 1) * itemsPerPage);
            ps.setInt(3, itemsPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CardOrderHistory card = new CardOrderHistory(
                         rs.getString("providerName"),
                         rs.getInt("price"),
                         rs.getInt("ID"),
                         rs.getInt("quantity"),
                         rs.getTimestamp("createdAt").toLocalDateTime(),
                         rs.getString("image")
                );
                orderItems.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    public int getTotalOrderItemsByUserId(int userId) {
        String sql = "SELECT COUNT(*) FROM PurchaseHistory WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}