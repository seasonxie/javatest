package used;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Created by zhaotang on 2017/8/28.
 */
public class Enum {

    public enum base {
        BASE {
            @Override
            public MysqlDataSource getDbConnect() {
                return getDbConnect(base, user, pwd);
            }
        }, DEAL {
            @Override
            public MysqlDataSource getDbConnect() {
                return getDbConnect("", user, pwd);
            }
        };

        public abstract MysqlDataSource getDbConnect();

        private final static String user = "mysqlusers";
        private final static String pwd = "ffmysqlusers";
        private final static String base = "jdbc:mysql://172.17.48.20:3306/MEIZU_DURIAN_BASE?characterEncoding=utf-8";

        public MysqlDataSource getDbConnect(String url, String user, String pwd) {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setURL(url);
            ds.setUser(user);
            ds.setPassword(pwd);
            return ds;
        }



    }

    public enum SomeThing {
        INSTANCE;
        private String instance;

        SomeThing() {
            System.out.println("--");
            instance = new String("ssss");
        }

        public String getInstance() {
            return instance;
        }
    }

    public static void main(String[] args) {
        System.out.println(base.BASE.getDbConnect());
        ;
        System.out.println(SomeThing.INSTANCE);
        System.out.println(SomeThing.INSTANCE);
        System.out.println(DbeResource.BASE.getIndex());
    }

    public enum DbeResource {
        BASE("jdbc:mysql://172.17.48.20:3306/MEIZU_DURIAN_BASE?characterEncoding=utf-8", 1),
        DEAL("jdbc:mysql://172.17.48.20:3306/MEIZU_DURIAN_DEAL?characterEncoding=utf-8", 2),
        ADLIB("jdbc:mysql://172.17.48.20:3306/MEIZU_DSP_ADLIB?characterEncoding=utf-8", 3),
        STAT("jdbc:mysql://172.17.48.20:3306/MEIZU_DURIAN_STAT?characterEncoding=utf-8", 4),
        BASE_LINE("jdbc:mysql://172.16.187.23:3306/MEIZU_DURIAN_BASE?characterEncoding=utf-8", 5);
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private DbeResource(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static String getName(String name) {
            for (DbeResource c : DbeResource.values()) {
                if (c.getName().contains(name)) {
                    return c.name;
                }
            }
            return null;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
