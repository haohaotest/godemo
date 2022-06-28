package hao.hao.servlet;

import hao.hao.bean.User;
import hao.hao.service.UserService;
import hao.hao.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @outhor Chotck
 * @create 2022-0620上午 09:28
 */
public class UserServlet extends BaseServlet {

    //Service層
    UserService userService = new UserServiceImpl();

    /**
     * 註冊
     */
    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //需要轉換日期格式
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        //請求參數獲取
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //確認用戶名可否用
        boolean usernameIsExist = userService.usernameCheck(username);

        //可用就儲存到數據庫
        if(usernameIsExist) {
            //創建當前時間為指定格式
            Date date = new Date();
            String createAccountTime = sim.format(date);

            //封裝bean
            User user = new User(username, password, "", 0, 0, createAccountTime);

            //調用業務邏輯 儲存到數據庫
            int rows = userService.insertUser(user);

            //用戶名可用 也成功儲存到數據庫
            if(rows > 0) {
                //註冊成功資訊
                req.setAttribute("message", "註冊成功");
            } else {
                resp.getWriter().write("未知原因錯誤 請重試");
            }

        } else {
            //用戶名不可用
            req.setAttribute("message", "用戶名已被使用");

            //回顯用戶填寫的資訊
            req.setAttribute("username", username);
            req.setAttribute("password", password);
        }

        //請求轉發回原本頁面
        req.getRequestDispatcher("pages/user/regist.jsp").forward(req, resp);
    }

    /**
     * 登入功能
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //獲取請求參數
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //session
        HttpSession session = req.getSession();

        User user = userService.queryUser(username, password);

        //如果帳號密碼正確
        if(user != null) {
            //設置登入資訊到session
            session.setAttribute("user", user);

            //回首頁
            resp.sendRedirect("pages/index/index.jsp");
        } else {
            //登入失敗

            //回顯用戶填寫的資訊
            req.setAttribute("username", username);
            req.setAttribute("password", password);

            //設置錯誤資訊
            req.setAttribute("message", "帳號或密碼不正確");

            //請求轉發回原頁面
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
        }
    }

    /**
     * 登出
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        //請求轉發到首頁
        resp.sendRedirect("index.jsp");
    }

    /**
     * 修改密碼
     */
    public void editPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //獲取當前用戶登入對象
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        //獲取要修改的新密碼
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String checkNewPassword = req.getParameter("checkNewPassword");

        //session有用戶數據 代表登入中 可以修改密碼
        if(user != null) {

            //檢查輸入資訊是否正確
            //檢查舊密碼正確 新密碼跟確認新密碼也都正確
            if(user.getPassword().equals(oldPassword)) {
                if(newPassword.equals(checkNewPassword)) {
                    user.setPassword(newPassword);

                    //調用業務邏輯 獲取影響行數
                    int rows = userService.alterUser(user);
                    System.out.println("修改密碼成功嗎？" + rows);

                    //重定向回用戶中心
                    resp.sendRedirect("pages/user/center.jsp");
                } else {
                    //如果舊密碼正確 新密碼有問題
                }
            } else {
                //舊密碼不正確
            }

        } else {
            //未知原因session中沒登入中的用戶資訊
            //直接回首頁
            resp.sendRedirect("index.jsp");
        }
    }

}
