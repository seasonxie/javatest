package designpatterns;

import org.assertj.db.type.Change;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Source;
import org.assertj.db.type.Value;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.List;

public class AssertJDB {
    @Test
    public void test11() throws InterruptedException, SQLException, ClassNotFoundException {
        Source source = new Source("jdbc:mysql://172.17.49.51:3306/Task?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull","root","root");
        Changes changes = new Changes(source);
        changes.setStartPointNow();
        Thread.sleep(2000);
        ttt();
        Thread.sleep(2000);
        changes.setEndPointNow();
        List<Change> changeList = changes.getChangesList();
        System.out.println(changeList.size()+"  ----------");
        for(Change s:changeList){
            String sql = "--- ";
            List<Value> valuesList = s.getRowAtEndPoint().getValuesList();
            for(Value value : valuesList){
                Object columenValue = value.getValue();
                sql = sql + "'" +columenValue+"',";
            }
            System.out.println(s.getChangeType()  +"  start:");
            System.out.println(sql);
        }
    }


    public static void ttt()throws ClassNotFoundException, SQLException, InterruptedException {
        Connection con = null;
        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //建立连接
        con = DriverManager.getConnection("jdbc:mysql://172.17.49.51:3306/Task?useUnicode=true&characterEncoding=UTF-8","root","root");
        //原本每一条执行的sql语句都是一件事物(在JDBC中)，但是在这里通过con对象来设置，不是默认的自动提交方式
        con.setAutoCommit(false);
        //Assume a valid connection object conn
        Statement statement = con.createStatement();
        //set a Savepoint
        Savepoint savepoint = con.setSavepoint("Savepoint");
        con.commit();
        Savepoint savepoint1 = con.setSavepoint("Savepoint1");
        statement.executeUpdate("insert into comments (taskId,content,personId,time) VALUES  ('33','sdss','-1','2017-01-06 15:32:59')");
        con.commit();
        Savepoint savepoint2 = con.setSavepoint("Savepoint2");
        statement.executeUpdate("insert into comments (taskId,content,personId,time) VALUES  ('33','sdss','-1','2017-01-06 15:32:59')");
        con.commit();
        try {
            con.rollback();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        //关闭
        statement.close();
        con.close();
    }
}
