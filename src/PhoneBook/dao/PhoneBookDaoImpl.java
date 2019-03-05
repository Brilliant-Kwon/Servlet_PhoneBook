package PhoneBook.dao;

import PhoneBook.vo.PhoneBookVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookDaoImpl extends BaseDao implements PhoneBookDao {

    public PhoneBookDaoImpl(String dbUser, String dbPass) {
        super(dbUser, dbPass);
    }

    @Override
    public List<PhoneBookVo> getList() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<PhoneBookVo> list = new ArrayList<>();

        try {
            conn = getConnection();
            String sql = "SELECT id, name, hp, tel FROM phone_book";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                String hp = rs.getString(3);
                String tel = rs.getString(4);

                PhoneBookVo vo = new PhoneBookVo(id, name, hp, tel);
                list.add(vo);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error!");
            System.err.println("ERROR : " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("SQL Error!");
                System.err.println("ERROR : " + e.getMessage());
            }
        }

        return list;
    }

    @Override
    public boolean insert(PhoneBookVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int insert_count = 0;

        try {
            conn = getConnection();
            String sql = "INSERT INTO phone_book VALUES (seq_phone_book.NEXTVAL, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getHp());
            pstmt.setString(3, vo.getTel());
            insert_count = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("SQL Error!");
                System.err.println("ERROR : " + e.getMessage());
            }
        }
        return insert_count == 1;
    }

    @Override
    public boolean delete(Long no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int delete_count = 0;

        try {
            conn = getConnection();
            String sql = "DELETE FROM phone_book where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);
            delete_count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("SQL Error!");
                System.err.println("ERROR : " + e.getMessage());
            }
        }
        return delete_count == 1;
    }

    @Override
    public List<PhoneBookVo> search(String str) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<PhoneBookVo> list = new ArrayList<>();

        try {
            conn = getConnection();
            String sql = "SELECT id, name, hp, tel FROM phone_book WHERE name LIKE ? OR hp LIKE ? OR  tel LIKE ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + str + "%");
            pstmt.setString(2, "%" + str + "%");
            pstmt.setString(3, "%" + str + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                String hp = rs.getString(3);
                String tel = rs.getString(4);

                PhoneBookVo vo = new PhoneBookVo(id, name, hp, tel);
                list.add(vo);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error!");
            System.err.println("ERROR : " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("SQL Error!");
                System.err.println("ERROR : " + e.getMessage());
            }
        }

        return list;
    }
}
