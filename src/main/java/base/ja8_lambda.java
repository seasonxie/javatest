package base;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ja8_lambda {

    /**
     * Lambda 表达式
     * 方法引用
     * Stream,filter
     * 默认方法
     * Optional 类是一个可以为null的容器对象
     */

    @Test
    public void thread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        new Thread(
                () -> System.out.println("")
        ).start();

        new Thread(() -> {
            System.out.println("In Java8, Lambda expression rocks !!");
            System.out.println("In Java8, Lambda expression rocks !!");
        }
        ).start();
    }

    @Test
    public void fori(){
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (Object feature : features) {
            System.out.println(feature);
        }
        features.forEach(n -> System.out.println(n));
        features.forEach(n -> {
            System.out.println(n);
            System.out.println(n+" ---");
        });
      // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
      // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }

    @Test
    public void mapi(){
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
    }

    @Test //过滤list
    public void filtered(){
        List<String> strList=Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        List<String> filtered = strList.stream().filter(x -> x.length()> 9).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);


        Predicate<String> startsWithJ = (n) -> n.startsWith("S");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        filtered=strList.stream()
                .filter(startsWithJ.or(fourLetterLong)).collect(Collectors.toList());
               // .forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);

        //转换成string并且加,
        String G7Countries = strList.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);

        //去重
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);

        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

    @Test //过滤list
    public void filtered1(){
        tt t=ss -> ss.contains("1")+"";
        System.out.println(t.st("ss"));
        System.out.println();

        final List< Car > cars = null;
        cars.forEach(Car::collide);
    }
}

 interface tt{
    public String st(String ss);
}

class Car{
    Car(){

    }
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }
}

