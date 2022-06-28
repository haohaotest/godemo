package hao.hao.servlet;

import hao.hao.bean.User;
import hao.hao.bean.Word;
import hao.hao.service.UserService;
import hao.hao.service.WordService;
import hao.hao.service.impl.UserServiceImpl;
import hao.hao.service.impl.WordServiceImpl;
import hao.hao.utils.RandomUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @outhor Chotck
 * @create 2022-0619下午 04:06
 */
public class QueryOperate extends BaseServlet {

    //Service
    WordService wordService = new WordServiceImpl();
    UserService userService = new UserServiceImpl();



    /**
     * 出題
     */
    public void showQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取要操作的表
        HttpSession session = req.getSession();
        String tableName = (String) session.getAttribute("tableName");

        //題目List
        List<Word> words = wordService.queryAll(tableName);

        //總共有幾題
        int totalCount = (int) wordService.queryCount(tableName);

        //紀錄出過哪些題數的數組
        List<Integer> indexList = new ArrayList();

        //隨機數
        int indexRandom = -1;

        //獲取域中數據
        Object obj = session.getAttribute("indexList");

        //如果session域還沒值
        if(obj == null) {
            session.setAttribute("indexList", indexList);

        } else {
            //如果session域有值 就轉換為int類型 並在這邊決定題目的index 和更新域中數據
            indexList = (List<Integer>) obj;
        }

        //生成不重複的亂數  100個數字儲存在List 它的index應該是0~99 所以totalCount要-1
        indexRandom = RandomUtils.createNotRepeatNumber(indexList, 0, totalCount - 1);

        //把亂數儲存到list表示已經用過
        indexList.add(indexRandom);

        //並更新域中indexList
        session.setAttribute("indexList", indexList);

        //不重複亂數 非失敗-1 可以出題目
        if(indexRandom != -1) {
            //獲取題目
            Word word = words.get(indexRandom);

            //把word對象給前端
            req.setAttribute("word", word);

            //把總題數給前端
            req.setAttribute("totalCount", totalCount);

        } else {
            //session.removeAttribute("indexList");
            req.setAttribute("message", "沒有題目了");

            /*
                沒題目要刪除Session中的indexList 免得使用者更換表操作 還用已經有出過題數的List
                或是想重複一張表練習 但使用同個indexList 裡面能生成的亂數都用完了 就變成一直沒題數出
             */
            session.removeAttribute("indexList");

        }

        //請求轉發
        req.getRequestDispatcher("pages/edit/data/showQuery.jsp").forward(req, resp);

    }

    /**
     * 驗證答案
     */
    public void checkAnswer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //獲取當前用戶登入資訊 檢查是否登入了
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        //獲取題目
        String query = req.getParameter("query");
        //獲取答案
        String answer = req.getParameter("answer");

        //如果當前是登入的 會更新用戶答對和練習題數資訊
        if(user != null) {
            //獲取當前用數的練習題和答對題數
            int exerBingoQuery = user.getExerBingoQuery();
            int exerTotalQuery = user.getExerTotalQuery();

            //忽略大小寫判斷答案
            if(query.equalsIgnoreCase(answer)) {
                req.setAttribute("message", "答對了");

                //答對題數++ 儲存回user對像
                user.setExerBingoQuery(++exerBingoQuery);
            } else {
                req.setAttribute("message", "答錯了 答案是" + query);
            }

            //總練習題數++ 儲存回user對像
            user.setExerTotalQuery(++exerTotalQuery);

            //更新用戶 數據庫中資訊
            userService.alterUser(user);

        } else {
            //當前是沒登入的狀態 只判斷答案對錯

            //忽略大小寫判斷答案
            if(query.equalsIgnoreCase(answer)) {
                req.setAttribute("message", "答對了");
            } else {
                req.setAttribute("message", "答錯了 答案是" + query);
            }
        }

        //請求轉發 轉發給生成題目的方法Servlet 直接繼續出下一題
        //已知Bug 如果最後一題 答錯或答對的message儲存了 又轉發給出題 然後沒題目了 message又會被刷新成沒題目的資訊
        req.getRequestDispatcher("queryOperate?action=showQuery").forward(req, resp);

    }

}
