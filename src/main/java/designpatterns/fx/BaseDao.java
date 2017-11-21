package designpatterns.fx;

public interface BaseDao<T,E> {
    T get(Class<T> entityClazz,E ss);
}
