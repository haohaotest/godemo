package com.hao.dao;

import hao.hao.bean.User;
import hao.hao.dao.UserDao;
import hao.hao.dao.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * @outhor Chotck
 * @create 2022-0620上午 10:37
 */
public class UserDaoTest {

    //測試對象
    UserDao userDao = new UserDaoImpl();

    /**
     * 新增用戶 (對象內的id不儲存 表內自動自增)
     * public int insertUser(User user)
     */
    @Test
    public void insertUserTest() {
        //User user = new User("admin", "a123456", "", 0, 0,  "2022-06-20 11:30");
        User user = new User("admin5", "a123456", "", 0, 0,  "2022-06-20 11:36");
        int rows = userDao.insertUser(user);
        System.out.println(rows);
    }

    /**
     * 刪除用戶 - 根據id
     * public int deleteUser(int id)
     */
    @Test
    public void deleteUserByIdTest() {
        int rows = userDao.deleteUser(2);
        System.out.println(rows);
    }

    /**
     * 刪除用戶 - 根據username
     * public int deleteUser(String username)
     */
    @Test
    public void deleteUserByUsernameTest() {
        int rows = userDao.deleteUser("admin3");
        System.out.println(rows);
    }

    /**
     * 修改用戶 - 根據id
     * public int alterUser(User user)
     */
    @Test
    public void alterUserByIdTest() {
        User user = new User(4, "admin5", "a123456", "", 100, 100,  "2022-06-20 11:40");
        int rows = userDao.alterUser(user);
        System.out.println(rows);
    }

    /**
     * 修改用戶 - 根據username (需要username跟要修改的新username一致 不然新的username去找資料會找不到)
     * public int alterUser(User user, Class<String> clazz)
     */
    @Test
    public void alterUserByUsernameTest() {
        User user = new User(4, "admin5", "a123456", "", 1200, 900, "2022-06-20 11:40");
        int rows = userDao.alterUser(user, String.class);
        System.out.println(rows);
    }

    /**
     * 查詢一個用戶 - 根據id
     * public User queryUser(int id)
     */
    @Test
    public void queryUserByIdTest() {
        User user = userDao.queryUser(4);
        System.out.println(user);
    }

    /**
     * 查詢一個用戶 - 根據username
     * public User queryUser(String username)
     */
    @Test
    public void queryUserByUsernameTest() {
        String username = "admin100";
        User user = userDao.queryUser(username);
        System.out.println(user);
    }

    /**
     * 查詢全部用戶
     * public List<User> queryUserAll()
     */
    @Test
    public void queryUserAllTest() {
        List<User> users = userDao.queryUserAll();
        for(User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查詢用戶表數據數量
     * public long queryCountUser()
     */
    @Test
    public void queryCountUser() {
        long count = userDao.queryCountUser();
        System.out.println(count);
    }
}
