package hao.hao.service;

import hao.hao.bean.User;

import java.util.List;

/**
 * @outhor Chotck
 * @create 2022-0620上午 11:51
 */
public interface UserService {

    /**
     * 新增用戶 (對象內的id不儲存 表內自動自增)
     * @param user 要新增的user
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    public int insertUser(User user);

    /**
     * 刪除用戶 - 根據id
     * @param id 要刪除的用戶id
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    public int deleteUser(int id);

    /**
     * 刪除用戶 - 根據username
     * @param username 要刪除的用戶username
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    public int deleteUser(String username);

    /**
     * 修改用戶 - 根據id
     * @param user 修改過的user
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    public int alterUser(User user);

    /**
     * 修改用戶 - 根據username (需要username跟要修改的新username一致 不然新的username去找資料會找不到)
     * @param user 修改過的user
     * @param clazz 傳一個String的Class 代表區分重載方法 要用根據username修改的這個方法
     * @return 影響行數 (0執行沒效果, -1執行失敗)
     */
    public int alterUser(User user, Class<String> clazz);

    /**
     * 查詢用戶名是否可用 (沒重複)
     * @param username 要檢查的username
     * @return true可用 false不可用
     */
    public boolean usernameCheck(String username);

    /**
     * 查詢一個用戶 - 根據id
     * @param id 要查詢的用戶id
     * @return
     */
    public User queryUser(int id);

    /**
     * 查詢一個用戶 - 根據username
     * @param username 要查詢的username
     * @return
     */
    public User queryUser(String username);

    /**
     * 查詢一個用戶 - 根據username, password
     * @param username 要查詢的username
     * @param password 要查詢的password
     * @return 失敗返回null
     */
    public User queryUser(String username, String password);

    /**
     * 查詢全部用戶
     * @return 全部用戶集合而成的List
     */
    public List<User> queryUserAll();

    /**
     * 查詢用戶表數據數量
     * @return count數 (-1代表失敗)
     */
    public int queryCountUser();

}
