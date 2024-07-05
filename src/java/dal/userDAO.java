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
import model.User;
import ultis.EncryptString;

/**
 *
 * @author PC
 */
public class userDAO extends DBContext {

    public User login(String username, String password) throws SQLException {
        EncryptString en = new EncryptString();
        User acc = null;
        String sql = "select * from [User]\n"
                + "where username = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                acc = new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getDate("createdAt"),
                        rs.getDate("updatedAt"),
                        rs.getInt("createdBy"),
                        rs.getBoolean("isDeleted"),
                        rs.getInt("deletedBy"),
                        rs.getDate("deletedAt"),
                        rs.getString("role"));
            }
        } catch (SQLException e) {

        } finally {
            st.close();
        }
        try {
            if (acc.password.equals(en.hashPassword(password))) {
                return acc;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    public User GetUserInfo(String username) throws SQLException {
        User acc = null;
        String sql = "select * from [User]\n"
                + "where username = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                acc = new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getDate("createdAt"),
                        rs.getDate("updatedAt"),
                        rs.getInt("createdBy"),
                        rs.getBoolean("isDeleted"),
                        rs.getInt("deletedBy"),
                        rs.getDate("deletedAt"),
                        rs.getString("role"));
            }
        } catch (SQLException e) {

        } finally {
            st.close();
        }
        return acc;
    }

    public int CheckUser(String username, String email) throws SQLException {
        String sql = "select * from [User] where email=?";
        String sql2 = "select * from [User] where username=?";
        PreparedStatement st = connection.prepareStatement(sql);
        PreparedStatement st2 = connection.prepareStatement(sql2);
        try {
            //checck email dup
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return 1;
            }
            //check user duplicate
            st2.setString(1, username);
            ResultSet rs2 = st2.executeQuery();
            if (rs2.next()) {
                return 2;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            st.close();
        }
        return 0;
    }

    public void CreateAcc(String username, String password, String email) throws SQLException {
        EncryptString en = new EncryptString();
        String sql = "INSERT INTO [User] (username,password,email,createdAt,role)\n"
                + "VALUES (?,?,?,getdate(),'user')";
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            st.setString(1, username);
            st.setString(2, en.hashPassword(password));
            st.setString(3, email);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            st.close();
        }
    }

    public boolean ForgotPassWord(String password, String email) throws SQLException {
        String sql = "UPDATE [User] SET password = ? WHERE email = ?";
        User user = null;
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            EncryptString en = new EncryptString();
            st.setString(1, en.hashPassword(password));
            st.setString(2, email);
            int affectedRow = st.executeUpdate();
            if (affectedRow > 0) {
                return true;
            } else {
                System.out.println("ForgotPassWord Cannot found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ForgotPassWord Cannot found");
        } finally {
            st.close();
        }
        return false;
    }

    public void ChangeAccountStatus(String status, int delId, int aid) throws SQLException {
        String sql = "UPDATE [User] SET isDeleted = ?,";
        if ("false".equals(status)) {
            sql += "deletedBy=?,deletedAt=getdate() WHERE ID = ?";
        } else {
            sql += "deletedBy=null,deletedAt=null WHERE ID = ?";
        }
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            if ("false".equals(status)) {
                //change to true or deactivated in database              
                st.setBoolean(1, true);
                st.setInt(2, delId);
                st.setInt(3, aid);
            } else {
                //change to false or activated in database
                st.setBoolean(1, false);
                st.setInt(2, aid);
            }
            ResultSet rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("disable account failed");
        } finally {
            st.close();
        }
    }

    public void UpdateInfo(User acc) throws SQLException {
        String sql = "UPDATE [User] SET email=?,phoneNumber = ?,updatedAt=getdate() WHERE ID = ?";
        User user = null;
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            st.setString(1, acc.email);
            st.setString(2, acc.phoneNumber);
            st.setInt(3, acc.ID);
            ResultSet rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("disable account failed");
        } finally {
            st.close();
        }
    }

    public boolean BlockAccount(String email) throws SQLException {
        String sql = "UPDATE [User] SET isDeleted = ? WHERE email = ?";
        User user = null;
        PreparedStatement st = connection.prepareStatement(sql);
        try {
            st.setBoolean(1, true);
            st.setString(2, email);
            int affectedRow = st.executeUpdate();
            return affectedRow > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("disable account failed");
        } finally {
            st.close();
        }
        return false;
    }

    public int getTotalAccount() {
        String sql = "select count(*) from [User]";
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

    public List<User> getAccount(int idx) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM [User] ORDER BY ID OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (idx - 1) * 10);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User acc = new User(rs.getInt("ID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getDate("createdAt"),
                        rs.getDate("updatedAt"),
                        rs.getInt("createdBy"),
                        rs.getBoolean("isDeleted"),
                        rs.getInt("deletedBy"),
                        rs.getDate("deletedAt"),
                        rs.getString("role"));
                list.add(acc);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) throws SQLException {
        userDAO d = new userDAO();
        User acc = d.login("abc", "password");
        System.out.println(acc);
    }
}
