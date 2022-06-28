//import com.hao.bean.Word;
//import com.hao.dao.BaseDao;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import com.hao.bean.Word;
//import com.hao.dao.BaseDao;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import com.hao.bean.Word;
//import com.hao.dao.BaseDao;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
///**
// * @outhor Chotck
// * @create 2022-0618上午 11:16
// */
//public class BaseDaoTest {
//    BaseDao baseDao = new BaseDao();
//
//    /**
//     * 測試BaseDao的添加功能
//     */
//    @Test
//    public void insertTest() {
//        String sql = "INSERT INTO `testt` VALUES(?, ?)";
//        int rows = baseDao.update(sql, "aa", "bb");
//        System.out.println(rows);
//    }
//
//    /**
//     * 測試BaseDao的查詢一條數據功能
//     */
//    @Test
//    public void queryByIdTest() {
//        String sql = "SELECT id, word, translation, remarks, create_time createTime FROM `java_word` WHERE id = ?";
//        Word word = baseDao.query(Word.class, sql, 15);
//        System.out.println(word);
//    }
//
//    /**
//     * 測試BaseDao的查詢多條數據功能
//     */
//    @Test
//    public void queryMore() {
//        String sql = "SELECT id, word, translation, remarks, create_time createTime FROM `java_word`";
//        List<Word> words = baseDao.queryMore(Word.class, sql);
//        for (Word word : words) {
//            System.out.println(word);
//        }
//    }
//
//    /**
//     * 測試BaseDao的查詢特殊值功能
//     */
//    @Test
//    public void querySpecial() {
//        String sql = "SELECT COUNT(*) FROM `java_word`";
//        Object obj = baseDao.querySpecial(sql);
//        System.out.println(obj);
//    }
//}
