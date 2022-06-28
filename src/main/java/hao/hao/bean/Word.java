package hao.hao.bean;

/**
 * @outhor Chotck
 * @create 2022-0618上午 11:50
 */
public class Word {
    private int id;
    private String word;
    private String translation;
    private String remarks;
    private String createTime;

    public Word() {
    }

    public Word(String word, String translation, String remarks, String createTime) {
        //非空串才賦值
        if(notNull(word) && notNull(translation) && notNull(createTime)) {
            this.word = word;
            this.translation = translation;
            this.createTime = createTime;
        }

        this.remarks = remarks;
    }

    public Word(int id, String word, String translation, String remarks, String createTime) {
        //非空串才賦值
        if(notNull(word) && notNull(translation) && notNull(createTime)) {
            this.word = word;
            this.translation = translation;
            this.createTime = createTime;
        }

        this.id = id;
        this.remarks = remarks;
    }

    /**
     * id是int 但參數可以丟String類型 在這邊去做轉換
     * 等於這是一個重載的構造器
     */
    public Word(String id, String word, String translation, String remarks, String createTime) {
        //非空串才賦值
        if(notNull(id) && notNull(word) && notNull(translation) && notNull(createTime)) {
            this.id = Integer.parseInt(id);
            this.word = word;
            this.translation = translation;
            this.createTime = createTime;
        }

        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        //非空串才賦值
        if(notNull(word)) {
            this.word = word;
        }
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        //非空串才賦值
        if(notNull(translation)) {
            this.translation = translation;
        }
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        //非空串才賦值
        if(notNull(translation)) {
            this.createTime = createTime;
        }
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", translation='" + translation + '\'' +
                ", remarks='" + remarks + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }



    /**
     * 判斷插入的值是否非空串 用來解決數據表字段not null 但空串還是能插入的問題
     * 只要這邊判斷是空串 就不給插入數據
     * @param str 要判斷的String
     * @return非空串=true 空串=false
     */
    private boolean notNull(String str) {
        //默認值是false 如果沒被賦值就直接返回 代表判斷失敗 為false
        boolean isFlag = false;

        //非空就把boolean賦值true代表該字段可以被賦值
        if( (!"".equals(str)) ) {
            isFlag = true;
        }

        return isFlag;
    }

    //判斷插入的值 是否是負數 或0

}
