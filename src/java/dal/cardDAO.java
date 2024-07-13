/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
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
        String sql = "SELECT COUNT(*) FROM ProviderDetail where isDeleted !=1 or isDeleted is null";
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
        String sql = "SELECT * FROM ProviderDetail WHERE category = ? "
                + "and (isDeleted !=1 or isDeleted is null)"
                + "ORDER BY ID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";

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
        String sql = "SELECT * FROM CardDetail WHERE ProviderID = ? and (isDeleted !=1 or isDeleted is null)";
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
                card.setQuantity(rs.getInt("quantity"));
                listCard.add(card);
            }
            sortPrice(listCard);
            return listCard;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("disable account failed");
        } finally {
            st.close();
        }
        return null;
    }

    public void sortPrice(List<CardDetail> priceList) {
        int n = priceList.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (priceList.get(j).getPrice() > priceList.get(j + 1).getPrice()) {
                    CardDetail temp = priceList.get(j);
                    priceList.set(j, priceList.get(j + 1));
                    priceList.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public int countProviderByCagegory(String category) {
        int count = 0;
        String sql = "select count(*) from ProviderDetail where category = ? and (isDeleted !=1 or isDeleted is null)";
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
        String sql = "select count(*) from ProviderDetail where isDeleted != 1 or isDeleted is null";
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
        String sql = "select * from ProviderDetail where category = ?"
                + " and (isDeleted !=1 or isDeleted is null)"
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
        String sql = "SELECT * FROM ProviderDetail where isDeleted !=1 or isDeleted is null"
                + " ORDER BY ID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
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
                + "and (isDeleted !=1 or isDeleted is null) "
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
        String sql = "update ProviderDetail\n"
                + "set isDeleted = 1, deletedAt = getDate()  where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public ProviderDetail getProviderDetailById(int id) {
        ProviderDetail c = new ProviderDetail();
        String sql = "select * from ProviderDetail where id = ? and (isDeleted !=1 or isDeleted is null)";
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
            e.printStackTrace();
        }
    }

    public int countProvider(String provider) {
        int count = 0;
        String sql = "select count (id) from ProviderDetail where lower(providerName) = lower(?) and (isDeleted !=1 or isDeleted is null)";
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

    public int countSearchName(List<ProviderDetail> getProviderByName) {
        int count = getProviderByName.size();
        return count;
    }

    public boolean checkExistedPrice(String id, double price) {
        String sql = "  select count(id) from CardDetail\n"
                + "  where ProviderID = ? and price = ?";
        int count = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setDouble(2, price);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {

        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public CardDetail getPrice (String id){
        CardDetail c = new CardDetail();
        String sql = "select * from CardDetail where ID = ? and (isDeleted !=1 or isDeleted is null)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                c = new CardDetail(rs.getInt("ID"),
                        rs.getInt("providerID"),
                        rs.getDouble("price"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"), 
                        rs.getInt("createdBy"), 
                        rs.getBoolean("isDeleted"), 
                        rs.getInt("deletedBy"), 
                        rs.getString("deletedAt"), 
                        rs.getInt("quantity"));
            }
        } catch (SQLException e) {

        }
        return c;
        
    }

    public void createPrice(String id, double price) {
        String sql = " insert into CardDetail(ProviderID, price, createdAt, quantity)\n"
                + "  values (?,?,getDate(),0)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setDouble(2, price);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void addCard(String id, int seriNumber, int pinNumber) {
        String sql = "insert into Card (CardDetailID,seriNumber,pinNumber,createdAt)\n"
                + "  values (?,?,?,getdate())";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setInt(2, seriNumber);
            st.setInt(3, pinNumber);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public int countSeri(int seri) {
        String sql = "select count(id) from Card where seriNumber = ? and (isDeleted != 1 or isDeleted is null)";
        int count = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, seri);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {

        }
        return count;
    }

    public int countPin(int pin) {
        String sql = "select count(id) from Card where seriNumber = ? and (isDeleted != 1 or isDeleted is null)";
        int count = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pin);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {

        }
        return count;
    }

    public void updateQuantityAfterAdd(String id) {
        String sql = "  update CardDetail\n"
                + "  set quantity = ?\n"
                + "  where id = ?";
        CardDetail c = getPrice(id);
        int quantity = c.getQuantity() + 1 ;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setString(2, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public static void main(String[] args) throws SQLException {
        cardDAO _cardDAO = new cardDAO();
        List<CardHomepageVM> list = _cardDAO.getAllProduct(1, "phonecard");
        for (CardHomepageVM phoneCardDetail : list) {
            System.out.println(phoneCardDetail.toString());
        }

    }
}
