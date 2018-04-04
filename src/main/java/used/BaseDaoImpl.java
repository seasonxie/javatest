package used;



import java.util.Map;

/**
 * Created by zhaotang on 2017/8/28.
 */
public class BaseDaoImpl<T,R extends type> implements BaseDao<T,R>{
    public static final String tag="BaseDaoImpl";



    @Override
    public  <T,R extends type>  T getObjectFromPrams(String tableName) {
        String baseSql="select * from "+tableName;
        R ss=null;
        ss.equals(null);
        T CO=null;
        return CO;
    }


    public String getWhereInfo(){
        Map<String, String> prams=null;
        String where="";
        for (String key : prams.keySet()) {
            if(key.contains(LIMIT)){
                continue;
            }
            if(key.contains(ORDER_BY)){
                continue;
            }
            if(where.isEmpty()){
                where+=" where "+key+" like \"%"+prams.get(key)+"%\"";
            }else{
                where+=" and "+key+" like \"%"+prams.get(key)+"%\"";
            }
        }
        if(prams.containsKey(ORDER_BY)){
            where+=" "+ORDER_BY+" "+prams.get(ORDER_BY);
        }
        if(prams.containsKey(LIMIT)){
            where+=" "+LIMIT+" "+prams.get(LIMIT);
        }
        return where;
    }

}
