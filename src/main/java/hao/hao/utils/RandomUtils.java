package hao.hao.utils;

import java.util.List;

/**
 * @outhor Chotck
 * @create 2022-0619下午 05:45
 */
public class RandomUtils {

    /**
     * 生成一個 指定範圍內的亂數 且沒有在List內重複
     * @param list 要檢查的List
     * @param start 起始範圍(包含)
     * @param end 結束範圍(包含)
     * @return 返回沒重複的亂數 (-1代表list內 於指定範圍內沒有不重複的值了)
     */
    public static int createNotRepeatNumber(List<Integer> list, int start, int end) {

        /*
            檢查亂數有無跟List內值重複 有就重新生成亂數
            判斷list內有的值的數量 是否小於要生成亂數的範圍 如果是 代表還有沒重複的亂數可以生成
            (end - start + 1) 是因為 假設要1~5包含頭尾的範圍 公式結尾若不+1 end-start算出來是4 不正確
         */
        while(list.size() < (end - start + 1)) {
            //生成指定範圍內的亂數值
            int random = (int) (Math.random() * (end - start + 1)) + start;

            //檢查有沒有跟list內值重複
            boolean notRepeat = randomCheck(list, random);

            //若沒重複，結束循環，返回亂數值
            if(notRepeat) {
                return random;
            }
        }

        //沒有不重複的值可以生成了 所以失敗 返回-1
        return -1;
    }

    /**
     * 檢查一個亂數值是否在List內有重複 (缺點是數組有些元素還沒儲存值 但該方法會檢查數組全部值)
     * 且如果數組元素默認值都是
     * @param list 要檢查的數組
     * @param random 要檢查的亂數
     * @return ture代表亂數沒重複可用 false代表亂數重複了不可用
     */
    public static boolean randomCheck(List<Integer> list, int random) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == random) {
                return false;
            }
        }

        return true;
    }
}
