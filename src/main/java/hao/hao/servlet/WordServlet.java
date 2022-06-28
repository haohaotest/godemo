package hao.hao.servlet;

import hao.hao.bean.Word;
import hao.hao.service.impl.WordServiceImpl;
import hao.hao.service.WordService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @outhor Chotck
 * @create 2022-0618下午 04:33
 */
public class WordServlet extends BaseServlet{

    //創建service對象 用於調用業務邏輯 執行間接操作數據庫的方法
    WordService wordService = new WordServiceImpl();

    //決定調用哪一個表
    //String tableName = "java_word";

    /**
     * 選表
     */
    public void selectTableName(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //獲取表名
        String tableName = req.getParameter("tableName");

        //儲存表名到session域
        HttpSession session = req.getSession();
        session.setAttribute("tableName", tableName);

        resp.sendRedirect("pages/edit/data/crud.jsp");
    }

    /**
     * 添加一條數據 (id不添加 因自增主鍵)
     */
    public void addWord(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();

        String tableName = (String) session.getAttribute("tableName");
        /*
            session
            清空session裡面的message 以免跟其他方法衝突 例如上一個方法有errorMessage 但這邊只有okMessage
            這樣到前端又有ok又有error 因為session範圍是會話
         */

        session.removeAttribute("okMessage");
        session.removeAttribute("errorMessage");

        //獲取請求參數
        String wordInfo = req.getParameter("word");
        String translation = req.getParameter("translation");
        String remarks = req.getParameter("remarks");
        String createTime = req.getParameter("createTime");

        //封裝Bean
        Word word = new Word(wordInfo, translation, remarks, createTime);

        //調用業務邏輯 獲取影響行數
        int rows = wordService.addWord(tableName, word);

        //如果執行成功
        if(rows > 0) {
            session.setAttribute("okMessage", "數據添加成功");
        } else {
            session.setAttribute("errorMessage", "數據添加失敗");
        }

        //請求重定向回到CRUD頁面
        resp.sendRedirect("pages/edit/data/crud.jsp");
    }

    /**
     * 刪除一條數據 - 透過id
     */
    public void deleteWord(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        String tableName = (String) session.getAttribute("tableName");
        /*
            session
            清空session裡面的message 以免跟其他方法衝突 例如上一個方法有errorMessage 但這邊只有okMessage
            這樣到前端又有ok又有error 因為session範圍是會話
         */
        session.removeAttribute("okMessage");
        session.removeAttribute("errorMessage");

        //預設影響行數-1
        int rows = -1;

        //獲取請求參數
        String id = req.getParameter("id");

        /*
            如果請求參數不為空才調用業務邏輯 獲取影響行數
            因為bean已經判斷過 如果是空 就不賦值 這樣空串過去屬性是null 再調用Dao就會報異常 導致無法正常跳轉回首頁
         */
        if(id != null && !"".equals(id)) {
            rows = wordService.deleteWordById(tableName, id);

        }

        //如果執行成功
        if(rows > 0) {
            session.setAttribute("okMessage", "數據刪除成功");
        } else {
            session.setAttribute("errorMessage", "數據刪除失敗");
        }

        //請求重定向回到首頁index.jsp
        resp.sendRedirect("pages/edit/data/crud.jsp");
    }

    /**
     * 修改一條數據 - 根據傳遞的word.id決定修改哪條數據 (根據id值)
     */
    public void updateWordById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        String tableName = (String) session.getAttribute("tableName");
        /*
            session
            清空session裡面的message 以免跟其他方法衝突 例如上一個方法有errorMessage 但這邊只有okMessage
            這樣到前端又有ok又有error 因為session範圍是會話
         */
        session.removeAttribute("okMessage");
        session.removeAttribute("errorMessage");

        //獲取請求參數
        String id = req.getParameter("id");
        String wordInfo = req.getParameter("word");
        String translation = req.getParameter("translation");
        String remarks = req.getParameter("remarks");
        String createTime = req.getParameter("createTime");

        //封裝Bean
        Word word = new Word(id, wordInfo, translation, remarks, createTime);

        //預設影響行數-1
        int rows = -1;

        /*
            如果請求參數不為空才調用業務邏輯 獲取影響行數
            因為bean已經判斷過 如果是空 就不賦值 這樣空串過去屬性是null 再調用Dao就會報異常 導致無法正常跳轉回首頁
         */
        if( id != null && !"".equals(id) && wordInfo != null && !"".equals(wordInfo)
                       && translation != null && !"".equals(translation)
                       && createTime != null && !"".equals(createTime) ) {

            rows = wordService.updateWordById(tableName, word);
        }

        //如果執行成功
        if(rows > 0) {
            session.setAttribute("okMessage", "數據修改成功");
        } else {
            session.setAttribute("errorMessage", "數據修改失敗");
        }

        //請求重定向回到首頁index.jsp
        resp.sendRedirect("pages/edit/data/crud.jsp");
    }

    /**
     * 修改一條數據 - 根據傳遞的word.word決定修改哪條數據 (根據英文單字)
     */
    public void updateWordByWord(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        String tableName = (String) session.getAttribute("tableName");
        /*
            session
            清空session裡面的message 以免跟其他方法衝突 例如上一個方法有errorMessage 但這邊只有okMessage
            這樣到前端又有ok又有error 因為session範圍是會話
         */
        session.removeAttribute("okMessage");
        session.removeAttribute("errorMessage");

        //獲取請求參數
        String wordInfo = req.getParameter("word");
        String translation = req.getParameter("translation");
        String remarks = req.getParameter("remarks");
        String createTime = req.getParameter("createTime");

        //根據這個oldWord決定要修改的字段
        String oldWord = req.getParameter("oldWord");

        //封裝Bean
        Word word = new Word(wordInfo, translation, remarks, createTime);

        //預設影響行數-1
        int rows = -1;

        /*
            如果請求參數不為空才調用業務邏輯 獲取影響行數
            因為bean已經判斷過 如果是空 就不賦值 這樣空串過去屬性是null 再調用Dao就會報異常 導致無法正常跳轉回首頁
         */
        if( wordInfo != null && !"".equals(wordInfo)
                && translation != null && !"".equals(translation)
                && createTime != null && !"".equals(createTime) ) {

            rows = wordService.updateWordByWord(tableName, oldWord, word);
        }

        //如果執行成功
        if(rows > 0) {
            session.setAttribute("okMessage", "數據修改成功");
        } else {
            session.setAttribute("errorMessage", "數據修改失敗");
        }

        //請求重定向回到首頁index.jsp
        resp.sendRedirect("pages/edit/data/crud.jsp");
    }

    /**
     * 查詢一條數據 - 透過ID
     * @return 返回Bean (null代表失敗)
     */
    public void queryWordById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        String tableName = (String) session.getAttribute("tableName");
        /*
            session
            清空session裡面的message 以免跟其他方法衝突 例如上一個方法有errorMessage 但這邊只有okMessage
            這樣到前端又有ok又有error 因為session範圍是會話
         */
        session.removeAttribute("okMessage");
        session.removeAttribute("errorMessage");

        //獲取請求參數
        String id = req.getParameter("id");

        //預設獲取對象為null
        Word word = null;

        /*
            如果請求參數不為空才調用業務邏輯 獲取Bean
            因如果空 這樣空串再調用Service時會把String id 轉 int 會報異常 導致無法正常跳轉回首頁
         */
        if( !"".equals(id) ) {
            word = wordService.queryWordById(tableName, id);

        }

        //打印測試
        System.out.println(word);

        //如果執行獲取bean成功
        if(word != null) {
            session.setAttribute("okMessage", "數據查詢成功");
        } else {
            session.setAttribute("errorMessage", "數據查詢失敗");
        }

        //請求重定向回到首頁index.jsp
        resp.sendRedirect("pages/edit/data/crud.jsp");
    }

    /**
     * 查詢一條數據 - 透過英文單字
     * @return 返回Bean (null代表失敗)
     */
    public void queryWordByEnglish(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        String tableName = (String) session.getAttribute("tableName");
        System.out.println("wordService.addWord()" + tableName);
        /*
            session
            清空session裡面的message 以免跟其他方法衝突 例如上一個方法有errorMessage 但這邊只有okMessage
            這樣到前端又有ok又有error 因為session範圍是會話
         */
        session.removeAttribute("okMessage");
        session.removeAttribute("errorMessage");

        //獲取請求參數
        String englishWord = req.getParameter("word");

        //預設獲取對象為null
        Word word = null;

        /*
            如果請求參數不為空才調用業務邏輯 獲取Bean
            因如果空 這樣空串再調用Service時會把String id 轉 int 會報異常 導致無法正常跳轉回首頁
         */
        if( !"".equals(englishWord) ) {
            word = wordService.queryWordByEnglish(tableName, englishWord);
        }

        //打印測試
        System.out.println(word);

        //如果執行獲取bean成功
        if(word != null) {
            session.setAttribute("okMessage", "數據查詢成功");
        } else {
            session.setAttribute("errorMessage", "數據查詢失敗");
        }

        //請求重定向回到首頁index.jsp
        resp.sendRedirect("pages/edit/data/crud.jsp");

    }

    /**
     * 查詢全部數據
     * @return 返回List<Bean> (null代表失敗)
     */
    public void queryAll(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();

        String tableName = (String) session.getAttribute("tableName");
        /*
            session
            清空session裡面的message 以免跟其他方法衝突 例如上一個方法有errorMessage 但這邊只有okMessage
            這樣到前端又有ok又有error 因為session範圍是會話
         */
        session.removeAttribute("okMessage");
        session.removeAttribute("errorMessage");

        //調用業務邏輯 獲取List<Bean>
        List<Word> words = wordService.queryAll(tableName);

        //儲存到域中
        req.setAttribute("words", words);

        //請求轉發
        req.getRequestDispatcher("pages/edit/data/showAllData.jsp").forward(req, resp);
    }

    /**
     * 查詢表的數據個數
     * @return 紀錄個數 (-1代表失敗)
     */
    public void queryCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        String tableName = (String) session.getAttribute("tableName");
        /*
            session
            清空session裡面的message 以免跟其他方法衝突 例如上一個方法有errorMessage 但這邊只有okMessage
            這樣到前端又有ok又有error 因為session範圍是會話
         */
        session.removeAttribute("okMessage");
        session.removeAttribute("errorMessage");

        //調用業務邏輯 獲取數據個數
        long count = wordService.queryCount(tableName);

        //打印測試
        System.out.println(count);

        //如果執行成功
        if(count > 0) {
            session.setAttribute("okMessage", "數據個數" + count);
        } else if(count == 0) {
            session.setAttribute("okMessage", "無數據");
        } else {
            session.setAttribute("errorMessage", "數據查詢失敗");
        }

        //請求重定向回到首頁index.jsp
        resp.sendRedirect("pages/edit/data/crud.jsp");
    }


}
