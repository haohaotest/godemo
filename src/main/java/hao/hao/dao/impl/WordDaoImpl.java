package hao.hao.dao.impl;

import hao.hao.bean.Word;
import hao.hao.dao.BaseDao;
import hao.hao.dao.WordDao;

import java.util.List;

/**
 * @outhor Chotck
 * @create 2022-0618下午 12:57
 */
public class WordDaoImpl extends BaseDao implements WordDao {

    /**
     * 添加一條數據 (id不添加 因自增主鍵)
     * @param word 要添加的Bean
     * @return 影響行數 -1代表失敗
     */
    @Override
    public int addWord(String tableName, Word word) {
        //轉換當前時間戳為指定數據庫儲存的格式
        //但怕有時候想插入的時間不是當下時間 所以先註釋掉
        //SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //String dateInfo = sim.format(new Date());

        //獲取word對象屬性值
        String wordInfo = word.getWord();
        String translation = word.getTranslation();
        String remarks = word.getRemarks();
        String createTime = word.getCreateTime();


        //寫SQL並執行
        String sql = "INSERT INTO " + tableName + " VALUES(null, ?, ?, ?, ?)";
        int rows = update(sql, wordInfo, translation, remarks, createTime);

        return rows;
    }

    /**
     * 刪除一條數據 - 透過id
     * @param id 要刪除的id編號主鍵
     * @return 影響行數 -1代表失敗
     */
    @Override
    public int deleteWordById(String tableName, int id) {
        String sql = "DELETE FROM " + tableName + " WHERE ID = ?";
        int rows = update(sql, id);

        return rows;
    }

    /**
     * 修改一條數據 - 根據傳遞的word.id決定修改哪條數據 (根據id值)
     * @param word 要修改的數據
     * @return 影響行數 0代表沒查到 -1代表失敗
     */
    @Override
    public int updateWordById(String tableName, Word word) {
        int id = word.getId();
        String wordInfo = word.getWord();
        String translation = word.getTranslation();
        String remarks = word.getRemarks();
        String createTime = word.getCreateTime();

        String sql = "UPDATE " + tableName + " SET word = ?, translation = ?, remarks = ?, create_time = ? WHERE id = ?";
        int rows = update(sql, wordInfo, translation, remarks, createTime, id);

        return rows;
    }

    /**
     * 修改一條數據 - 根據oldWord修改哪條數據 (舊英文單字)
     * @param oldWord 需要這個是因為對象裡面的英文單字已經是修改後 根據這個去查找數據並修改的話 會找不到要修改哪一個單字
     * @param word 要修改的數據
     * @return 影響行數 0代表沒查到 -1代表失敗
     */
    @Override
    public int updateWordByWord(String tableName, String oldWord, Word word) {
        //不需要id 因為查找用不到 修改也不會動到id值
        //int id = word.getId();

        String wordInfo = word.getWord();
        String translation = word.getTranslation();
        String remarks = word.getRemarks();
        String createTime = word.getCreateTime();

        String sql = "UPDATE " + tableName + " SET word = ?, translation = ?, remarks = ?, create_time = ? WHERE word = ?";
        int rows = update(sql, wordInfo, translation, remarks, createTime, oldWord);

        return rows;
    }

    /**
     * 查詢一條數據 - 透過ID
     * @param tableName 要操作的表名
     * @param id 要查詢的id
     * @return 返回Bean (null代表失敗)
     */
    @Override
    public Word queryWord(String tableName, int id) {
        String sql = "SELECT id, word, translation, remarks, create_time createTime FROM " + tableName + " WHERE id = ?";
        Word word = query(Word.class, sql, id);

        return word;
    }

    /**
     * 查詢一條數據 - 透過英文單字
     * @param tableName 要操作的表名
     * @param word 要查詢的英文單字
     * @return 返回Bean (null代表失敗)
     */
    @Override
    public Word queryWord(String tableName, String word) {
        String sql = "SELECT id, word, translation, remarks, create_time createTime FROM " + tableName + " WHERE word = ?";
        Word wordObj = query(Word.class, sql, word);

        return wordObj;
    }

    /**
     * 查詢全部數據
     * @return 返回List<Bean> (null代表失敗)
     */
    @Override
    public List<Word> queryAll(String tableName) {
        String sql = "SELECT id, word, translation, remarks, create_time createTime FROM " + tableName;
        List<Word> words = queryMore(Word.class, sql);

        return words;
    }

    /**
     * 查詢表的數據個數
     * @return 紀錄個數 (-1代表失敗)
     */
    @Override
    public long queryCount(String tableName) {
        //先給默認值 沒被賦值代表失敗返回-1
        long count = -1;

        String sql = "SELECT COUNT(*) FROM " + tableName;
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
