package hao.hao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @outhor Chotck
 * @create 2022-0618上午 11:09
 */
public class JdbcUtils {

    /**
     * 關閉連接
     * @param conn 傳要關閉的Connection
     */
    public static void close(Connection conn) {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 關閉Connection和PreparedStatement
     * @param conn 要關閉的Connection
     * @param ps 要關閉的PreparedStatement
     */
    public static void close(Connection conn, PreparedStatement ps) {
        try {
            if(conn != null) {
                conn.close();
            }
            if(ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 關閉Connection和PreparedStatement和ResultSet
     * @param conn 要關閉的Connection
     * @param ps 要關閉的PreparedStatement
     * @param rs 要關閉的ResultSet
     */
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if(conn != null) {
                conn.close();
            }
            if(ps != null) {
                ps.close();
            }
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
