package designpatterns;



/**
 * Created by zhaotang on 2017/10/19.
 */
public class equal {

    public static void main(String[] args) {
        System.out.println("111".equals("111"));
        Object ss="111";
        System.out.println("111".equals(ss));
        Object ss1="sss";
        System.out.println("ss".getClass()==ss);
        System.out.println(ss1==ss);

   /*     if(this == obj)
                    return true;
           if((obj == null) || (obj.getClass() != this.getClass()))
                       return false;
               // object must be Test at this point
              Test test = (Test)obj;*/
    }
}
