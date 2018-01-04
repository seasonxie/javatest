package senior;

public class oov {

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        dodo d=new dodo();
        dodo d1= new dodo(){
            @Override
            public void iwilldo( classdo1 ss){
                  ss.dds(new classdo(){
                      @Override
                      public void before(String aa){
                          System.out.println(aa+" ddddddddd");
                      }

                      @Override
                      public void after(String aa){
                          System.out.println(aa+" ddddddddd");
                      }

                  });

            }
        };


        d=d1;
        d.iwilldo(get());

    }

    public static classdo1 get(){
        classdo1 ss=  new classdo1();
        ss.dds(new classdo(){
            @Override
            public void before(String aa){
                System.out.println(aa+" fffffff");
            }

            @Override
            public void after(String aa){
                System.out.println(aa+" ffffff");
            }

        });
        return ss;
    }

}

class dodo{
    public void iwilldo(classdo1 ss){
    }
}


class classdo1{
    public void dds(classdo aa){
        aa.before("ss");
        aa.after("bb");
    }

}

class classdo{
    public void before(String aa){
        System.out.println(aa+"---");
    }

    public void after(String aa){
        System.out.println(aa+"---");
    }
}
