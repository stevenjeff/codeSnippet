package org.fangrui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

/**
 * 1.jshell
 *
 */
public class Jdk9_17 {
    /**
     * 2. 接口私有方法
     */
    public interface MyInterface {
        //定义私有方法
        private void m1() {
            System.out.println("123");
        }

        //default中调用
        default void m2() {
            m1();
        }
    }

    /**
     * 3.新的关闭资源方式
     */
    public static void test1() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("abc.txt");
        FileOutputStream fos = new FileOutputStream("def.txt");
        //多资源用分号隔开
        try (fis; fos) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 4.下划线作为关键字
     */
    public static void test2() {
//        String _ = "monkey1024";
    }

    /**
     * 5.类型推导
     */
    public static void test3() {
        var newName = "jack";
        var newAge = 10;
        var newMoney = 88888888L;
        var newObj = new Object();
    }

    /**
     * 6.省略javac 编译 直接运行 java HelloWorld.java
     */

    /**
     * 7.String 新增方法
     * strip方法，可以去除首尾空格，与之前的trim的区别是还可以去除unicode编码的空白字符
     * isBlank方法，判断字符串长度是否为0，或者是否是空格，制表符等其他空白字符
     * repeat方法，字符串重复的次数
     */
    public static final void test4(){
        //Unicdoe空白字符
        char c = '\u2000';
        String str = c + "abc" + c;
        System.out.println(str.strip());
        System.out.println(str.trim());
        //去除前面的空格
        System.out.println(str.stripLeading());
        //去除后面的空格
        System.out.println(str.stripTrailing());
        String str1 = " ";
        System.out.println(str1.isBlank());
        String str2 = "monkey";
        System.out.println(str2.repeat(4));
    }

    /**
     * 8.jdk11中允许在lambda表达式的参数中使用var修饰
     */
    @FunctionalInterface
    public interface MyInterfaceFun {
        void m1(String a, int b);
    }

    public static final void test5(){
        //支持lambda表达式参数中使用var
        MyInterfaceFun mi = (var a,var b)->{
            System.out.println(a);
            System.out.println(b);
        };
        mi.m1("monkey",1024);
    }

    /**
     * 9.在jdk12之后我们可以省略全部的break和部分case，这样使用
     */
    public static final void test6(){
        int month = 3;
        switch (month) {
            case 3,4,5 -> System.out.println("spring");
            case 6,7,8 -> System.out.println("summer");
            case 9,10,11 -> System.out.println("autumn");
            case 12, 1,2 -> System.out.println("winter");
            default -> System.out.println("wrong");
        }
        int month2 = 3;
        String result = switch (month2) {
            case 3,4,5 -> "spring";
            case 6,7,8 -> "summer";
            case 9,10,11 -> "autumn";
            case 12, 1,2 -> "winter";
            default -> "wrong";
        };
        System.out.println(result);
    }

    /**
     * 10.字符串 所见即所得
     */
    public static final void test7(){
        String s = """
            Hello
            World
            Learn
            Java
           """;
        System.out.println(s);
    }

    /**
     * 11.instanceof模式匹配 ,该特性可以减少强制类型转换的操作，简化了代码
     */
    public static final void test8(){
        Object obj = 1;
        if(obj instanceof Integer i){
            int result = i + 10;
            System.out.println(i);
        }else{
            //作用域问题，这里是无法访问i的
        }
    }

    /**
     * 12.友好空指针
     */
    class Machine{
        public void start(){
            System.out.println("启动");
        }
    }

    static class Engine{
        public Machine machine;
    }

    static class Car{
        public Engine engine;
    }

    public static final void test9(){
        new Car().engine.machine.start();
    }

    /**
     * 13. record 数据类型
     */
    public record User(String name,Integer age){}

    public static final void test10(){
        User u = new User("jack",15);
        System.out.println(u);
        System.out.println(u.name());
    }

    /**
     * 14.Sealed Classes
     * 密封类和接口，作用是限制一个类可以由哪些子类继承或者实现
     */
    public static final void test11(){
        Cat c = new Cat();
        c.eat();
        Dog d = new Dog();
    }

    public static sealed class Animal
            permits Cat, Dog{//多个子类之间用,隔开

        public void eat(){}
    }

    public static final class Cat extends Animal{
        @Override
        public void eat(){
            System.out.println("123");
        }
    }

    public sealed static class Dog extends Animal
            permits Husky {}

    public final class Husky extends Dog{
    }

    /**
     * 15.CharSequence 该接口中新增了default方法isEmpty()，作用是判断CharSequence是否为空。
     */
    public static final void test12(){
        CharSequence sequence = new StringBuffer("");
        sequence.isEmpty();
    }

    /**
     * 16.treeMap 增加以下方法
     * putIfAbsent
     * computeIfAbsent
     * computeIfPresent
     * compute
     * merge
     */
    public static final void test13(){
        TreeMap treeMap = new TreeMap();
    }

    /**
     * 17.新增日时段
     * 在DateTimeFormatter.ofPattern传入B可以获取现在时间对应的日时段，上午，下午等
     */
    public static final void test14(){
        System.out.println(DateTimeFormatter.ofPattern("B").format(LocalDateTime.now()));
    }

    /**
     * 18.InvocationHandler新增方法 在该接口中添加了下面方法
     * public static Object invokeDefault(Object proxy, Method method, Object... args)
     */
    interface Girl{
        default void eat(){
            System.out.println("cucumber");
        }
    }

    public static class Lucy implements Girl{
        @Override
        public void eat(){
            System.out.println("banana");
        }
    }

    public static final void test15(){
        Girl girl = new Lucy();
        //不使用invokeDefault会调用重写的eat方法
        Girl proxy1 = (Girl) Proxy.newProxyInstance(girl.getClass().getClassLoader(),girl.getClass().getInterfaces(),
                (obj,method,params)->{
                    Object invoke = method.invoke(girl);
                    return invoke;
                });
        proxy1.eat();

        //使用invokeDefault会调用父接口中的default方法
        Girl proxy2 = (Girl)Proxy.newProxyInstance(Girl.class.getClassLoader(),new Class<?>[]{Girl.class},
                (obj,method,params)->{
                    if (method.isDefault()) {
                        return InvocationHandler.invokeDefault(obj, method, params);
                    }
                    return null;
                });
        proxy2.eat();
    }

    /**
     * 19.打包工具jpackage ZGC并发栈处理，弹性meta space
     * 集合加强（list.of()，map.of()，copyof等只读集合的快速创建），
     * 用List.of的List自然是不包含null，而用Arrays.asList的List包含null
     * List.of生成的List不能修改，Arrays.asList生成的List能修改
     * JDK11的Optional加强,InputStream.transferTo，
     * HTTP Client API正式版，很多时候可以抛弃apache http client了，
     * JDK16的Stream().toList()
     */

    public static void main(String[] args) {
//        test9();
        test14();
    }
}
