package designpatterns;

/**
 * Created by zhaotang on 2017/10/18
 * 新建接口和定义方法，在主运行中需要引用实现这个接口的类并回调其中方法
 */
public class CallBackTest {
    public  void main(String[] args) {
        CallBackTest ct=new CallBackTest();
        ct.send(1,getCallBack());
    }

        public void send(int a,callback callback){
            if(a>1){
                callback.onStart();
            }else{
                callback.onFailed();
            }
        }

    public callback getCallBack(){
            return new callbackimpl();
    }

    public class callbackimpl implements callback{

        @Override
        public void onStart() {

        }

        @Override
        public void onFailed() {

        }
    }


    public interface callback{

        public void onStart();

        public void onFailed();


    }
}
