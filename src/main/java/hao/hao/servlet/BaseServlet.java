package hao.hao.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @outhor Chotck
 * @create 2022-0618下午 03:59
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如果是Get 就轉給Post執行 效果都一樣 省的一樣程式碼寫兩份
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解決請求中文亂碼
        req.setCharacterEncoding("UTF-8");
        //解決響應中文亂碼
        resp.setContentType("text/html; charset=UTF-8");

        //獲取要決定調用哪一個名稱的方法
        String action = req.getParameter("action");

        //動態反射調用對應action名稱的方法
        try {
            Class clazz = this.getClass();
            Object obj = clazz.getConstructor().newInstance();
            Method method = clazz.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

            //設置私有方法也能調用訪問
            method.setAccessible(true);

            //反射調用對應方法
            method.invoke(obj, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
