package com.hao.dao;

import hao.hao.bean.Word;
import hao.hao.dao.WordDao;
import hao.hao.dao.impl.WordDaoImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @outhor Chotck
 * @create 2022-0618下午 01:38
 */
public class WordDaoTest {
    //先創建好實現類對象 好方便測試實現類的方法
    WordDao wordDao = new WordDaoImpl();

    /**
     * 添加一條數據 (id不添加 因自增主鍵)
     * public int addWord(String tableName, Word word)
     */
    @Test
    public void addWordTest() {
        Word word = new Word(2, "road", "道路", "我是備註欄", "2022-06-18");
        int rows = wordDao.addWord("live_word", word);
        System.out.println(rows);
    }

    /**
     * 刪除一條數據
     * public int deleteWordById(String tableName, int id)
     */
    @Test
    public void deleteWordByIdTest() {
        int rows = wordDao.deleteWordById("live_word", 39);
        System.out.println(rows);
    }

    /**
     * 修改一條數據 - 根據傳遞的word.id決定修改哪條數據 (也就是根據id值)
     * public int updateWordById(String tableName, Word word)
     */
    @Test
    public void updateWordByIdTest() {
        Word word = new Word(38, "KO", "擊倒", "備註欄", "2022-06-21 10:00");
        int rows = wordDao.updateWordById("live_word", word);
        System.out.println(rows);
    }

    /**
     * 修改一條數據 - 根據傳的oldWord決定修改哪條數據 (根據英文單字)
     * public int updateWordByWord(String tableName, String oldWord, Word word);
     */
    @Test
    public void updateWordByWordTest(){
        Word word = new Word(50, "City", "城市", "備註欄", "2022-06-21 10:00");
        int rows = wordDao.updateWordByWord("live_word", "KO", word);
        System.out.println(rows);
    }

    /**
     * 查詢一條數據 - 透過ID
     * public Word queryWord(String tableName, int id)
     */
    @Test
    public void queryWordByIdTest() {
        Word word = wordDao.queryWord("live_word", 7);
        System.out.println(word);
    }

    /**
     * 查詢一條數據 - 透過英文單字
     * public Word queryWord(String tableName, String word)
     */
    @Test
    public void queryWordByWordInfoTest() {
        Word word = wordDao.queryWord("live_word", "not");
        System.out.println(word);
    }

    /**
     * 查詢全部數據
     * public List<Word> queryAll(String tableName)
     */
    @Test
    public void queryAllTest() {
        List<Word> words = wordDao.queryAll("live_word");

        for (Word word : words) {
            System.out.println(word);
        }
    }

    /**
     * 查詢表的數據個數
     * public int queryCount(String tableName)
     */
    @Test
    public void queryCountTest() {
        long count = wordDao.queryCount("live_word");
        System.out.println(count);
    }

}

