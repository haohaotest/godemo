package hao.hao.service.impl;

import hao.hao.bean.User;
import hao.hao.dao.UserDao;
import hao.hao.dao.impl.UserDaoImpl;
import hao.hao.service.UserService;

import java.util.List;

/**
 * @outhor Chotck
 * @create 2022-0620上午 11:54
 */
public class UserServiceImpl implements UserService {

    //要調用的Dao層
    UserDao userDao = new UserDaoImpl();

    /**
     * 新增用戶 (對象內的id不儲存 表內自動自增)
     * @param user 要新增的user
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    @Override
    public int insertUser(User user) {
        int rows = userDao.insertUser(user);
        return rows;
    }

    /**
     * 刪除用戶 - 根據id
     * @param id 要刪除的用戶id
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    @Override
    public int deleteUser(int id) {
        int rows = userDao.deleteUser(id);
        return rows;
    }

    /**
     * 刪除用戶 - 根據username
     * @param username 要刪除的用戶username
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    @Override
    public int deleteUser(String username) {
        int rows = userDao.deleteUser(username);
        return rows;
    }

    /**
     * 修改用戶 - 根據id
     * @param user 修改過的user
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    @Override
    public int alterUser(User user) {
        int rows = userDao.alterUser(user);
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
        int rows = userDao.alterUser(user, String.class);
        return rows;
    }

    /**
     * 查詢用戶名是否可用 (沒重複)
     * @param username 要檢查的username
     * @return true可用 false不可用 , 傳過來的是空數據也false
     */
    @Override
    public boolean usernameCheck(String username) {
        //如果傳過來的是空數據 返回false
        if(username == null || "".equals(username)) {
            return false;
        }

        User user = queryUser(username);

        //根據用戶名查不到用戶 代表用戶名可註冊
        if(user == null) {
            return true;
        }

        return false;
    }

    /**
     * 查詢一個用戶 - 根據id
     * @param id 要查詢的用戶id
     * @return
     */
    @Override
    public User queryUser(int id) {
        User user = userDao.queryUser(id);
        return user;
    }

    /**
     * 查詢一個用戶 - 根據username
     * @param username 要查詢的username
     * @return
     */
    @Override
    public User queryUser(String username) {
        User user = userDao.queryUser(username);
        return user;
    }

    /**
     * 查詢一個用戶 - 根據username, password
     * @param username 要查詢的username
     * @param password 要查詢的password
     * @return 失敗返回null
     */
    @Override
    public User queryUser(String username, String password) {
        User user = userDao.queryUser(username, password);
        return user;
    }

    /**
     * 查詢全部用戶
     * @return 全部用戶集合而成的List
     */
    @Override
    public List<User> queryUserAll() {
        List<User> users = userDao.queryUserAll();
        return users;
    }

    /**
     * 查詢用戶表數據數量
     * @return count數 (-1代表失敗)
     */
    @Override
    public int queryCountUser() {
        int count = (int) userDao.queryCountUser();
        return count;
    }


}
