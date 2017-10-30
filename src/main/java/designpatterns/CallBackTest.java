package designpatterns;

/**
 * Created by zhaotang on 2017/10/18.
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
