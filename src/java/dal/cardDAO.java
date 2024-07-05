/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ProviderDetail;
import model.CardDetail;
import model.viewModel.CardHomepageVM;

/**
 *
 * @author BINH
 */
public class cardDAO extends DBContext {

    public int getAllProviderTotal() throws SQLException {
        String sql = "SELECT COUNT(*) FROM ProviderDetail";
        List<CardHomepageVM> listCard = new ArrayList<>();
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("disable account failed");
        } finally {
            st.close();
        }
        return 0;
    }

    public List<CardHomepageVM> getAllProduct(int index, String category) throws SQLException {
        String sql = "SELECT * FROM ProviderDetail WHERE category = ? ORDER BY ID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";

        List<CardHomepageVM> listCard = new ArrayList<>();
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            st.setString(1, category);
            st.setInt(2, (index - 1) * 12);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CardHomepageVM card = new CardHomepageVM();
                card.setId(rs.getInt("Id"));

                card.setCardPrice(getCardDetailByProviderID(card.getId()));

                card.setProviderName(rs.getString("providerName"));
                card.setImage(rs.getString("image"));
                card.setCreateAt(rs.getString("createdAt"));
                card.setUpdatedAt(rs.getString("updatedAt"));
                card.setCreatedBy(rs.getInt("createdBy"));
                card.setIsDeleted(rs.getBoolean("isDeleted"));
                card.setDeletedBy(rs.getInt("deletedBy"));
                card.setDeletedAt(rs.getString("deletedAt"));
                listCard.add(card);
            }
            return listCard;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("disable account failed");
        } finally {
            st.close();
        }
        return null; 
    }

    public List<CardDetail> getCardDetailByProviderID(int id) throws SQLException {
        String sql = "SELECT * FROM CardDetail WHERE ProviderID = ?";
        List<CardDetail> listCard = new ArrayList<>();
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CardDetail card = new CardDetail();
                card.setId(rs.getInt("Id"));
                card.setProviderId(rs.getInt("ProviderID"));
                card.setPrice(rs.getDouble("price"));
                card.setCreatedBy(rs.getInt("createdBy"));
                card.setIsDeleted(rs.getBoolean("isDeleted"));
                card.setDeletedBy(rs.getInt("deletedBy"));
                card.setDeletedAt(rs.getString("deletedAt"));
                card.setCreatedAt(rs.getString("createdAt"));
                card.setUpdatedAt(rs.getString("updatedAt"));
                listCard.add(card);
            }
            return listCard;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("disable account failed");
        } finally {
            st.close();
        }
        return null;
    }
    public int countProviderByCagegory(String category){
        int count=0;
        String sql = "select count(*) from ProviderDetail where category = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, category);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {

        }
        return count;
    }
    
    public int getTotalProvider() {
        String sql = "select count(*) from ProviderDetail";
        int count = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {

        }
        return count;

    }

    public List<ProviderDetail> getProviderByCategory(int idx, String category) {
        List<ProviderDetail> listByCategory = new ArrayList<>();
        String sql = "select * from ProviderDetail where category = ? "
                + "ORDER BY ID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, category);
            st.setInt(2, (idx - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProviderDetail card = new ProviderDetail(rs.getInt("ID"),
                        rs.getString("providerName"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        rs.getInt("createdBy"),
                        rs.getBoolean("isDeleted"),
                        rs.getInt("deletedBy"),
                        rs.getString("deletedAt"),
                        getCardDetailByProviderID(rs.getInt("ID")));

                listByCategory.add(card);

            }
        } catch (SQLException e) {

        }
        return listByCategory;
    }

    public List<ProviderDetail> getProviderPagingManage(int idx) {
        List<ProviderDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM ProviderDetail ORDER BY ID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (idx - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProviderDetail card = new ProviderDetail(rs.getInt("ID"),
                        rs.getString("providerName"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        rs.getInt("createdBy"),
                        rs.getBoolean("isDeleted"),
                        rs.getInt("deletedBy"),
                        rs.getString("deletedAt"),
                        getCardDetailByProviderID(rs.getInt("ID")));

                list.add(card);

            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<ProviderDetail> getProviderByName(int idx, String name) {
        List<ProviderDetail> listByName = new ArrayList<>();
        String sql = "select * from ProviderDetail where providerName like ? "
                + "ORDER BY ID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
        try {
            String names = "%" + name + "%";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, names);
            st.setInt(2, (idx - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProviderDetail card = new ProviderDetail(rs.getInt("ID"),
                        rs.getString("providerName"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        rs.getInt("createdBy"),
                        rs.getBoolean("isDeleted"),
                        rs.getInt("deletedBy"),
                        rs.getString("deletedAt"),
                        getCardDetailByProviderID(rs.getInt("ID")));

                listByName.add(card);

            }
        } catch (SQLException e) {

        }
        return listByName;
    }

    public void deleteProvider(String id) {
        String sql = "delete from ProviderDetail\n"
                + "where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deleteCardDetail(String id) {
        String sql = "delete from PriceDetail\n"
                + "where ProviderID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public ProviderDetail getProviderDetailById(int id) {
        ProviderDetail c = new ProviderDetail();
        String sql = "select * from ProviderDetail where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                c = new ProviderDetail(rs.getInt("ID"),
                        rs.getString("providerName"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        rs.getInt("createdBy"),
                        rs.getBoolean("isDeleted"),
                        rs.getInt("deletedBy"),
                        rs.getString("deletedAt"),
                        getCardDetailByProviderID(rs.getInt("ID")));
            }
        } catch (SQLException e) {

        }
        return c;
    }

    public void editProviderDetail(String id, String providerName, String image, String category) {
        String sql = "update ProviderDetail\n"
                + "set providerName = ?,\n"
                + "image = ?,\n"
                + "category = ?,\n"
                + "updatedAt = getdate()"
                + "where Id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, providerName);
            st.setString(2, image);
            st.setString(3, category);
            st.setString(4, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void createProvider(String providerName, String image, String category) {
        String sql = "insert into ProviderDetail (providerName,image,category,createdAt)\n"
                + "values (?,?,?,getdate())";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, providerName);
            st.setString(2, image);
            st.setString(3, category);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }
    public int countProvider(String provider){
        int count=0;
        String sql = "select count (id) from ProviderDetail where lower(providerName) = lower(?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, provider);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {

        }
        return count;
    }
    
    public static void main(String[] args) throws SQLException {
        cardDAO _cardDAO = new cardDAO();
        List<CardHomepageVM> list = _cardDAO.getAllProduct(1,"phonecard");
        for (CardHomepageVM phoneCardDetail : list) {
            System.out.println(phoneCardDetail.toString());
        }

        
    }
}
