package hao.hao.dao.impl;

import hao.hao.bean.User;
import hao.hao.dao.BaseDao;
import hao.hao.dao.UserDao;

import java.util.List;

/**
 * @outhor Chotck
 * @create 2022-0620上午 10:00
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    /**
     * 新增用戶 (對象內的id不儲存 表內自動自增)
     * @param user 要新增的user
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    @Override
    public int insertUser(User user) {
        //獲取user資訊
        String username = user.getUsername();
        String password = user.getPassword();
        String exerTime = user.getExerTime();
        int exerTotalQuery = user.getExerTotalQuery();
        int exerBingoQuery = user.getExerBingoQuery();
        String createAccountTime = user.getCreateAccountTime();

        //寫sql並執行
        String sql = "INSERT INTO `user` VALUES(null, ?, ?, ?, ?, ?, ?)";
        int rows = update(sql, username, password, exerTime, exerTotalQuery, exerBingoQuery, createAccountTime);

        return rows;
    }

    /**
     * 刪除用戶 - 根據id
     * @param id 要刪除的用戶id
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    @Override
    public int deleteUser(int id) {
        //寫sql並執行
        String sql = "DELETE FROM `user` WHERE id = ?";
        int rows = update(sql, id);
        return rows;
    }

    /**
     * 刪除用戶 - 根據username
     * @param username 要刪除的用戶username
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    @Override
    public int deleteUser(String username) {
        //寫sql並執行
        String sql = "DELETE FROM `user` WHERE username = ?";
        int rows = update(sql, username);
        return rows;
    }

    /**
     * 修改用戶 - 根據id
     * @param user 修改過的user
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    @Override
    public int alterUser(User user) {
        //獲取user資訊
        int id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        String exerTime = user.getExerTime();
        int exerTotalQuery = user.getExerTotalQuery();
        int exerBingoQuery = user.getExerBingoQuery();
        String createAccountTime = user.getCreateAccountTime();

        //寫sql並執行
        String sql = "UPDATE `user` SET username = ?, password = ?, exer_time = ?, exer_total_query = ?, exer_bingo_query = ?, create_account_time = ? WHERE id = ?";
        int rows = update(sql, username, password, exerTime, exerTotalQuery, exerBingoQuery, createAccountTime, id);
        return rows;
    }

    /**
     * 修改用戶 - 根據username (需要username跟要修改的新username一致 不然新的username去找資料會找不到)
     * @param user 修改過的user
     * @param clazz 傳一個String的Class 代表區分重載方法 要用根據username修改的這個方法
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    @Override
    public int alterUser(User user, Class<String> clazz) {
        //獲取user資訊
        String username = user.getUsername();
        String password = user.getPassword();
        String exerTime = user.getExerTime();
        int exerTotalQuery = user.getExerTotalQuery();
        int exerBingoQuery = user.getExerBingoQuery();
        String createAccountTime = user.getCreateAccountTime();

        //寫sql並執行
        String sql = "UPDATE `user` SET password = ?, exer_time = ?, exer_total_query = ?, exer_bingo_query = ?, create_account_time = ? WHERE username = ?";
        int rows = update(sql, password, exerTime, exerTotalQuery, exerBingoQuery, createAccountTime, username);
        return rows;
    }

    /**
     * 查詢一個用戶 - 根據id
     * @param id 要查詢的用戶id
     * @return 失敗返回null
     */
    @Override
    public User queryUser(int id) {
        //寫sql並執行
        String sql = "SELECT id, username, password, exer_time exerTime, exer_total_query exerTotalQuery, exer_bingo_query exerBingoQuery, create_account_time createAccountTime FROM `user` WHERE id = ?";
        User user = query(User.class, sql, id);
        return user;
    }

    /**
     * 查詢一個用戶 - 根據username
     * @param username 要查詢的username
     * @return 失敗返回null
     */
    @Override
    public User queryUser(String username) {
        //寫sql並執行
        String sql = "SELECT id, username, password, exer_time exerTime, exer_total_query exerTotalQuery, exer_bingo_query exerBingoQuery,  create_account_time createAccountTime FROM `user` WHERE username = ?";
        User user = query(User.class, sql, username);
        return user;
    }

    /**
     * 查詢一個用戶 - 根據username, password
     * @param username 要查詢的username
     * @param password password
     * @return 失敗返回null
     */
    @Override
    public User queryUser(String username, String password) {
        //寫sql並執行
        String sql = "SELECT id, username, password, exer_time exerTime, exer_total_query exerTotalQuery, exer_bingo_query exerBingoQuery, create_account_time createAccountTime FROM `user` WHERE username = ? AND password = ?";
        User user = query(User.class, sql, username, password);
        return user;
    }

    /**
     * 查詢全部用戶
     * @return 全部用戶集合而成的List (失敗返回null)
     */
    @Override
    public List<User> queryUserAll() {
        //寫sql並執行
        String sql = "SELECT id, username, password, exer_time exerTime, exer_total_query exerTotalQuery, exer_bingo_query exerBingoQuery, create_account_time createAccountTime FROM `user`";
        List<User> users = queryMore(User.class, sql);
        return users;
    }

    /**
     * 查詢用戶表數據數量
     * @return long類型的count數 (-1代表失敗)
     */
    @Override
    public long queryCountUser() {
        //先給默認值 沒被賦值代表失敗返回-1
        long count = -1;

        //寫sql並執行
        String sql = "SELECT COUNT(*) FROM `user`";
        Object obj = querySpecial(sql);

        /*
            如果查詢成功 把Object轉成int賦值
            Object轉int不會失敗 因SQL語句查出來的值就是int 只是BaseDao無法確定SQL要查什麼 所以返回值寫Object
            所以只要查個數的SQL 有查詢成功一定等於也Object轉int會成功
         */
        if(obj != null) {
            count = (long) obj;
        }

        return count;
    }
}
