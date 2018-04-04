package useful;

import org.assertj.db.type.*;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJDB {

    public static void main(String[] args) {
        assertThat("abc").as("校验abc")
                .startsWith("ab")
                .endsWith("c").contains("","").doesNotContain("");
//                .isEqualToIgnoringCase("frodo")
//                .hasSize(9);
    }

    @Test
    public void test11() throws InterruptedException, SQLException, ClassNotFoundException {
        //Source source = new Source("jdbc:mysql://172.17.49.51:3306/Task?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull","root","root");
        Source source = new Source("jdbc:mysql://172.17.48.20:3306/meizu_adx?characterEncoding=utf-8&cacheServerConfiguration=true&useLocalSessionState=true&zeroDateTimeBehavior=convertToNull","mysqluser","mysqluser");
        Changes changes = new Changes(source);
        changes.setStartPointNow();
        System.out.println("--------start");
        Thread.sleep(20000);
        changes.setEndPointNow();
        List<Change> changeList = changes.getChangesList();
        System.out.println(changeList.size()+"  ----------");
        printSql(changeList);


   /*     changes.setStartPointNow();
        System.out.println("--------start");
        Thread.sleep(20000);
        changes.setEndPointNow();
        changeList = changes.getChangesList();
        System.out.println(changeList.size()+"  ----------");
        printSql(changeList);*/
    }

    public static void printSql(List<Change> changeList){
        for(Change change:changeList){
            ChangeType type = change.getChangeType();
            String tableName = change.getDataName();
            if("CREATION".equals(type.name())){
                String id = change.getRowAtEndPoint().getValuesList().get(0).getColumnName();
                Object value = change.getRowAtEndPoint().getValuesList().get(0).getValue();
                String sql = "delete from "+tableName+" where "+id+" = "+value+"";
                System.out.println(sql);
            }else if("DELETION".equals(type.name())){
                String sql = "insert into "+tableName+" values(";
                List<Value> valuesList = change.getRowAtStartPoint().getValuesList();
                for(Value value : valuesList){
                    Object columenValue = value.getValue();
                    sql = sql + "'" +columenValue+"',";
                }
                sql = sql.substring(0, sql.length()-1);
                sql = sql +")";
                System.out.println(sql);
            }else if("MODIFICATION".equals(type.name())){
                String sql = "update "+tableName+" SET ";
                List<Value> valuesList = change.getRowAtStartPoint().getValuesList();
                for(Value value : valuesList){
                    Object columenValue = value.getValue();
                    String columnName = value.getColumnName();
                    sql = sql + columnName +"='"+columenValue+"' ,";
                }
                sql = sql.substring(0, sql.length()-1);
                sql = sql + " where "+valuesList.get(0).getColumnName()+" = "+valuesList.get(0).getValue();
                System.out.println(sql);
            }

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
