package com.hao.service;

import hao.hao.bean.Word;
import hao.hao.service.impl.WordServiceImpl;
import hao.hao.service.WordService;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @outhor Chotck
 * @create 2022-0618下午 03:34
 */
public class WordServiceTest {

    //創建要測試的對象
    WordService wordService = new WordServiceImpl();

    /**
     * 添加一條數據 (id不添加 因自增主鍵)
     * public int addWord(Word word)
     */
    @Test
    public void addWordTest() {
        Word word = new Word(5, "Sex", "愛", "害羞", "2022-06-18 15:38");
        int rows = wordService.addWord("live_word", word);
        System.out.println(rows);
    }

    /**
     * 刪除一條數據
     * public int deleteWordById(int id)
     */
    @Test
    public void deleteWordByIdTest() {
        int rows = wordService.deleteWordById("live_word", "40");
        System.out.println(rows);
    }

    /**
     * 修改一條數據 - 根據傳遞的word.id決定修改哪條數據 (根據id值)
     * public int updateWordById(Word word)
     */
    @Test
    public void updateWordByIdTest() {
        Word word = new Word(38, "city", "可樂", "好喝", "2022-06-18 15:42");
        int rows = wordService.updateWordById("live_word", word);
        System.out.println(rows);
    }

    /**
     * 修改一條數據 - 根據傳的oldWord決定修改哪條數據 (根據英文單字)
     * public int updateWordByWord(String oldWord, Word word)
     */
    @Test
    public void updateWordByWordTest() {
        Word word = new Word(0, "yellow", "可樂", "好喝", "2022-06-18 15:42");

        int rows = wordService.updateWordByWord("live_word", "city", word);
        System.out.println(rows);
    }

    /**
     * 查詢一條數據 - 透過ID
     * public Word queryWordById(String id)
     */
    @Test
    public void queryWordByIdTest() {
        Word word = wordService.queryWordById("live_word", "38");
        System.out.println(word);
    }

    /**
     * 查詢一條數據 - 透過英文單字
     * public Word queryWord(String word)
     */
    @Test
    public void queryWordByEnglishTest() {
        Word word = wordService.queryWordByEnglish("live_word", "offer");
        System.out.println(word);
    }

    /**
     * 查詢全部數據
     * public List<Word> queryAll()
     */
    @Test
    public void queryAllTest() {
        List<Word> words = wordService.queryAll("live_word");

        for(Word word : words) {
            System.out.println(word);
        }
    }

    /**
     * 查詢表的數據個數
     * public long queryCount()
     */
    @Test
    public void queryCountTest() {
        long count = wordService.queryCount("live_word");
        System.out.println(count);
    }

}
