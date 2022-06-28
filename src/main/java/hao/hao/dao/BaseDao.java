package hao.hao.dao;


import hao.hao.utils.JdbcUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @outhor Chotck
 * @create 2022-0618上午 10:50
 */
public abstract class BaseDao {

    /**
     * 增刪改
     * @param sql
     * @param args
     * @return 影響行數 (-1代表運行失敗)
     */
    public int update(String sql, Object... args) {
        //寫在外面 方便關閉的資源
        Connection conn = null;
        PreparedStatement ps = null;

        //影響行數預設-1 如果沒被賦值就返回 那就是返回-1 代表失敗
        int rows = -1;

        //欲連接數據庫的資訊
        String url = "jdbc:mysql://localhost:3306/english_exer";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "a84120678");

        try {
            //獲取連接數據庫需要的對象
            Driver driver = new com.mysql.cj.jdbc.Driver();
            conn = driver.connect(url, properties);
            ps = conn.prepareStatement(sql);

            //填充占位符給PrepareStatement
            for (int i = 0; i < args.length; i++) {
                //MySQL中 編號是從1開始 所以前面i+1
                ps.setObject(i + 1, args[i]);
            }

            //影響行數獲取
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //關閉資源
            JdbcUtils.close(conn, ps);
        }

        return rows;
    }

    /**
     * 查詢單條數據
     * @param clazz 要封裝的Bean的Class
     * @param sql 要執行的SQL語句
     * @param args 要填充的佔位符
     * @param <T> 欲返回值的類型 由傳遞的Class參數決定
     * @return 封裝好數據的bean (查詢或封裝失敗返回null)
     */
    public <T> T query(Class<T> clazz, String sql, Object... args) {
        //寫在外面 方便關閉的資源
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //欲連接數據庫的資訊
        String url = "jdbc:mysql://localhost:3306/english_exer";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "a84120678");


        try {
            //獲取連接數據庫需要的對象
            Driver driver = new com.mysql.cj.jdbc.Driver();
            conn = driver.connect(url, properties);
            ps = conn.prepareStatement(sql);

            //填充占位符給PrepareStatement
            for (int i = 0; i < args.length; i++) {
                //MySQL中 編號是從1開始 所以前面i+1
                ps.setObject(i + 1, args[i]);
            }

            //獲取結果集, 結果集元數據
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();

            //如果有數據
            if(rs.next()) {
                //結果集內有數據才創建對象 來封裝數據
                T t = clazz.getConstructor().newInstance();

                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    //獲取字段名
                    String columnName = metaData.getColumnLabel(i + 1);
                    //獲取字段值
                    Object value = rs.getObject(i + 1);

                    //反射獲取類的屬性並設置值
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, value);
                }
                //成功封裝數據 返回Bean
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //關閉資源
            JdbcUtils.close(conn, ps, rs);
        }

        //封裝數據或查詢失敗 返回null
        return null;
    }

    /**
     * 查詢多條數據
     * @param clazz 要封裝的Bean的Class
     * @param sql 要執行的SQL語句
     * @param args 要填充的佔位符
     * @param <T> 欲返回值的類型 由傳遞的Class參數決定
     * @return 封裝好數據的List<bean> (查詢或封裝失敗返回null)
     */
    public <T> List<T> queryMore(Class<T> clazz, String sql, Object... args) {
        //要封裝數據的列表
        List<T> list = new ArrayList<>();

        //寫在外面 方便關閉的資源
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //欲連接數據庫的資訊
        String url = "jdbc:mysql://localhost:3306/english_exer";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "a84120678");

        try {
            //獲取連接數據庫需要的對象
            Driver driver = new com.mysql.cj.jdbc.Driver();
            conn = driver.connect(url, properties);
            ps = conn.prepareStatement(sql);

            //填充占位符給PrepareStatement
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();

            //如果有數據
            while(rs.next()) {
                //結果集內有數據才創建對象 來封裝數據
                T t = clazz.getConstructor().newInstance();

                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    //獲取字段名
                    String columnName = metaData.getColumnLabel(i + 1);
                    //獲取字段值
                    Object value = rs.getObject(i + 1);

                    //反射獲取類的屬性並設置值
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, value);
                }

                list.add(t);
            }

            //成功封裝數據 返回List
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn, ps, rs);
        }

        //沒數據 或執行失敗 返回null
        return null;
    }

    /**
     * 查詢一條一列的特殊值 (無法確定返回值類型 所以返回Object)
     * @param sql
     * @return 返回Object的特殊值  (失敗返回null)
     */
    public Object querySpecial(String sql) {
        //寫在外面 方便關閉的資源
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //欲連接數據庫的資訊
        String url = "jdbc:mysql://localhost:3306/english_exer";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "a84120678");

        try {
            //獲取連接數據庫需要的對象
            Driver driver = new com.mysql.cj.jdbc.Driver();
            conn = driver.connect(url, properties);
            ps = conn.prepareStatement(sql);

            //獲取結果集
            rs = ps.executeQuery();

            //如果結果集內有數據 就返回
            if(rs.next()) {
                Object obj = rs.getObject(1);
                return obj;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn, ps, rs);
        }

        //沒數據 或執行失敗 返回null
        return null;
    }
}
