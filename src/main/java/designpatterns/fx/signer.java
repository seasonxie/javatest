package designpatterns.fx;

import java.util.List;

public class signer {
    private static final signer INSTANCE = new signer();

    public static signer instance() {
        return INSTANCE;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        SuperClass<Person> superClass = new SubClass();
        superClass.getClazz().newInstance().function();

        BaseDao<Person, Integer> a = new BaseDaoImpl();
        //不需要强转
        Person aa = a.get(Person.class, 22);
    }

   /*     public interface OperationLogStrategy<E>
         List<OperationLog> handle(E original, E newBusiness );

         public class OperationLogStrategyPlans implements OperationLogStrategy<Plans> {
        @Override
        public List<OperationLog> handle( Plans original, Plans newPlans ) {
        }*/
}