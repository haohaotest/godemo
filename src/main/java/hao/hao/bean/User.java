package hao.hao.bean;

/**
 * @outhor Chotck
 * @create 2022-0620上午 09:41
 */
public class User {
    //id主鍵自增 id INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE
    private int id;

    //用戶名 username VARCHAR(30) NOT NULL UNIQUE
    private String username;

    //密碼`password` VARCHAR(30) NOT NULL,
    private String password;

    //練習總時長exer_time VARCHAR(50)
    private String exerTime = "0m";

    //練習總題數 exer_total_query INT,
    private int exerTotalQuery;

    //總題數中 答對的題數 exer_bingo_query INT,
    private int exerBingoQuery;

    //使用者帳戶創建時間 create_account_time VARCHAR(20) NOT NULL
    private String createAccountTime;

    public User() {
    }

    public User(String username, String password, String exerTime, int exerTotalQuery, int exerBingoQuery, String createAccountTime) {
        this.username = username;
        this.password = password;
        this.exerTime = exerTime;
        this.exerTotalQuery = exerTotalQuery;
        this.exerBingoQuery = exerBingoQuery;
        this.createAccountTime = createAccountTime;
    }

    public User(int id, String username, String password, String exerTime, int exerTotalQuery, int exerBingoQuery, String createAccountTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.exerTime = exerTime;
        this.exerTotalQuery = exerTotalQuery;
        this.exerBingoQuery = exerBingoQuery;
        this.createAccountTime = createAccountTime;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExerTime() {
        return exerTime;
    }

    public void setExerTime(String exerTime) {
        this.exerTime = exerTime;
    }

    public int getExerTotalQuery() {
        return exerTotalQuery;
    }

    public void setExerTotalQuery(int exerTotalQuery) {
        this.exerTotalQuery = exerTotalQuery;
    }

    public int getExerBingoQuery() {
        return exerBingoQuery;
    }

    public void setExerBingoQuery(int exerBingoQuery) {
        this.exerBingoQuery = exerBingoQuery;
    }

    public String getCreateAccountTime() {
        return createAccountTime;
    }

    public void setCreateAccountTime(String createAccountTime) {
        this.createAccountTime = createAccountTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", exerTime='" + exerTime + '\'' +
                ", exerTotalQuery=" + exerTotalQuery +
                ", exerBingoQuery=" + exerBingoQuery +
                ", createAccountTime='" + createAccountTime + '\'' +
                '}';
    }
}
