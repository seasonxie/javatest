package algorithm;

public class MaoSort {

    public static void main(String[] args) {
        int[] app={1,33,44,5,6,55,99,54};
        for (int i=0;i<app.length;i++){
            for(int j=app.length-1;j>i;j--){
                if(app[j]>app[j-1]){
                    int re=app[j-1];
                    app[j-1]=app[j];
                    app[j]=re;
                }

            }

        }
        for(int a:app){
            System.out.println(a);
        }
    }

}
