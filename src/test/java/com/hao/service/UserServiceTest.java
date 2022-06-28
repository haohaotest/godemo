package com.hao.service;

import hao.hao.bean.User;
import hao.hao.dao.UserDao;
import hao.hao.dao.impl.UserDaoImpl;
import hao.hao.service.UserService;
import hao.hao.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @outhor Chotck
 * @create 2022-0620下午 12:03
 */
public class UserServiceTest {

    //測試對象
    UserService userService = new UserServiceImpl();

    /**
     * 新增用戶 (對象內的id不儲存 表內自動自增)
     * public int insertUser(User user)
     */
    @Test
    public void insertUserTest() {
        User user = new User(0, "haohao2", "a123456", "",  0, 0,  "12:05");
        int rows = userService.insertUser(user);
        System.out.println(rows);
    }

    /**
     * 刪除用戶 - 根據id
     * public int deleteUser(int id)
     */
    @Test
    public void deleteUserByIdTest() {
        int rows = userService.deleteUser(7);
        System.out.println(rows);
    }

    /**
     * 刪除用戶 - 根據username
     * public int deleteUser(String username)
     */
    @Test
    public void deleteUserByUsernameTest() {
        int rows = userService.deleteUser("haohao2");
        System.out.println(rows);
    }

    /**
     * 修改用戶 - 根據id
     * public int alterUser(User user)
     */
    @Test
    public void alterUserByIdTest() {
        User user = new User(4, "admin99", "a123456", "",  0, 0,  "12:05");
        int rows = userService.alterUser(user);
        System.out.println(rows);
    }

    /**
     * 修改用戶 - 根據username (需要username跟要修改的新username一致 不然新的username去找資料會找不到)
     * public int alterUser(User user, Class<String> clazz)
     */
    @Test
    public void alterUserTest() {
        User user = new User(4, "admin99", "a123456", "",  57, 0, "12:10");
        int rows = userService.alterUser(user, String.class);
        System.out.println(rows);
    }

    /**
     * 查詢一個用戶 - 根據id
     * public User queryUser(int id)
     */
    @Test
    public void queryUserByIdTest() {
        User user = userService.queryUser(8);
        System.out.println(user);
    }

    /**
     * 查詢一個用戶 - 根據username
     * public User queryUser(String username)
     */
    @Test
    public void queryUserByUsernameTest() {
        User user = userService.queryUser("admin99");
        System.out.println(user);
    }

    /**
     * 查詢全部用戶
     * public List<User> queryUserAll()
     */
    @Test
    public void queryUserAllTest() {
        for (User user : userService.queryUserAll()) {
            System.out.println(user);
        }
    }

    /**
     * 查詢用戶表數據數量
     * public long queryCountUser()
     */
    @Test
    public void queryCountUserTest() {
        int count = userService.queryCountUser();
        System.out.println(count);
    }
}
