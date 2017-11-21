package designpatterns.fx;

public class BaseDaoImpl<T,E> implements BaseDao<T,E>  {
    @Override
    public T get(Class<T> entityClazz, E ss) {
        try {
            return (T) entityClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }



}
