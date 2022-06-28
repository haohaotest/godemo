package hao.hao.service.impl;

import hao.hao.bean.Word;
import hao.hao.dao.WordDao;
import hao.hao.dao.impl.WordDaoImpl;
import hao.hao.service.WordService;

import java.util.List;

/**
 * @outhor Chotck
 * @create 2022-0618下午 03:19
 */
public class WordServiceImpl implements WordService {

    //創建調用Dao層的對象
    WordDao wordDao = new WordDaoImpl();

    /**
     * 添加一條數據 (id不添加 因自增主鍵)
     * @param tableName 要操作的表名
     * @param word 要添加的Bean
     * @return 影響行數 -1代表失敗
     */
    @Override
    public int addWord(String tableName, Word word) {
        int rows = wordDao.addWord(tableName, word);
        return rows;
    }

    /**
     * 刪除一條數據 - 透過id
     * @param tableName 要操作的表名
     * @param id 要刪除的id編號主鍵
     * @return 影響行數 0代表沒刪到數據 -1代表運行失敗
     */
    @Override
    public int deleteWordById(String tableName, String id) {
        int rows = wordDao.deleteWordById(tableName, Integer.parseInt(id));
        return rows;
    }

    /**
     * 修改一條數據 - 根據傳遞的word.id決定修改哪條數據 (根據id值)
     * @param tableName 要操作的表名
     * @param word 要修改的數據
     * @return 影響行數 0代表沒查到 -1代表失敗
     */
    @Override
    public int updateWordById(String tableName, Word word) {
        int rows = wordDao.updateWordById(tableName, word);
        return rows;
    }

    /**
     * 修改一條數據 - 根據oldWord修改哪條數據 (舊英文單字)
     * @param tableName 要操作的表名
     * @param oldWord 需要這個是因為對象裡面的英文單字已經是修改後 根據這個去查找數據並修改的話 會找不到要修改哪一個單字
     * @param word 要修改的數據
     * @return 影響行數 0代表沒查到 -1代表失敗
     */
    @Override
    public int updateWordByWord(String tableName, String oldWord, Word word) {
        int rows = wordDao.updateWordByWord(tableName, oldWord, word);
        return rows;
    }

    /**
     * 查詢一條數據 - 透過ID
     * @param tableName 要操作的表名
     * @param id 要查詢的id
     * @return 返回Bean (null代表失敗)
     */
    @Override
    public Word queryWordById(String tableName, String id) {
        Word word = wordDao.queryWord(tableName, Integer.parseInt(id));
        return word;
    }

    /**
     * 查詢一條數據 - 透過英文單字
     * @param tableName 要操作的表名
     * @param english 要查詢的英文單字
     * @return 返回Bean (null代表失敗)
     */
    @Override
    public Word queryWordByEnglish(String tableName, String english) {
        Word resultWord = wordDao.queryWord(tableName, english);
        return resultWord;
    }

    /**
     * 查詢全部數據
     * @param tableName 要操作的表名
     * @return 返回List<Bean> (null代表失敗)
     */
    @Override
    public List<Word> queryAll(String tableName) {
        List<Word> words = wordDao.queryAll(tableName);
        return words;
    }

    /**
     * 查詢表的數據個數
     * @param tableName 要操作的表名
     * @return 紀錄個數 (-1代表失敗)
     */
    @Override
    public long queryCount(String tableName) {
        long count = wordDao.queryCount(tableName);
        return count;
    }
}
