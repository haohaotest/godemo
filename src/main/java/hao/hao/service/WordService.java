package hao.hao.service;

import hao.hao.bean.Word;

import java.util.List;

/**
 * @outhor Chotck
 * @create 2022-0618下午 03:18
 */
public interface WordService {

    /**
     * 添加一條數據 (id不添加 因自增主鍵)
     * @param tableName 要操作的表名
     * @param word 要添加的Bean
     * @return 影響行數 -1代表失敗
     */
    public int addWord(String tableName, Word word);

    /**
     * 刪除一條數據 - 透過id
     * @param tableName 要操作的表名
     * @param id 要刪除的id編號主鍵
     * @return 影響行數 0代表沒刪到數據 -1代表運行失敗
     */
    public int deleteWordById(String tableName, String id);

    /**
     * 修改一條數據 - 根據傳遞的word.id決定修改哪條數據 (根據id值)
     * @param tableName 要操作的表名
     * @param word 要修改的數據
     * @return 影響行數 0代表沒查到 -1代表失敗
     */
    public int updateWordById(String tableName, Word word);

    /**
     * 修改一條數據 - 根據oldWord修改哪條數據 (舊英文單字)
     * @param tableName 要操作的表名
     * @param oldWord 需要這個是因為對象裡面的英文單字已經是修改後 根據這個去查找數據並修改的話 會找不到要修改哪一個單字
     * @param word 要修改的數據
     * @return 影響行數 0代表沒查到 -1代表失敗
     */
    public int updateWordByWord(String tableName, String oldWord, Word word);

    /**
     * 查詢一條數據 - 透過ID
     * @param tableName 要操作的表名
     * @param id 要查詢的id
     * @return 返回Bean (null代表失敗)
     */
    public Word queryWordById(String tableName, String id);

    /**
     * 查詢一條數據 - 透過英文單字
     * @param tableName 要操作的表名
     * @param english 要查詢的英文單字
     * @return 返回Bean (null代表失敗)
     */
    public Word queryWordByEnglish(String tableName, String english);

    /**
     * 查詢全部數據
     * @param tableName 要操作的表名
     * @return 返回List<Bean> (null代表失敗)
     */
    public List<Word> queryAll(String tableName);

    /**
     * 查詢表的數據個數
     * @param tableName 要操作的表名
     * @return 紀錄個數 (-1代表失敗)
     */
    public long queryCount(String tableName);
}
