package cn.com.myproject.mysql;

import java.sql.*;

/**
 * Created by liyang-macbook on 2017/8/28.
 */
public class DBHelper {
    public static final String url = "jdbc:mysql://192.168.1.204:3306/live?useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=true&useSSL=true&allowMultiQueries=true";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "live";
    public static final String password = "live";

    public Connection conn = null;
    public PreparedStatement pst = null;

    public DBHelper(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
