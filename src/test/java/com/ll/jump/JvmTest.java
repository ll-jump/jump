package com.ll.jump;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiLin
 * @desc jvm测试类
 * @create 2021-03-25 09:43
 **/
public class JvmTest {
    public static void main(String[] args) {
//        //模拟 Metaspace内存溢出现象
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(Car.class);
//        enhancer.setUseCache(false);
//        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
//            if (method.getName().equals("run")){
//                System.out.println("启动汽车之间，先进行自动的安全检查......");
//                return methodProxy.invokeSuper(o,objects);
//            }else {
//                return methodProxy.invokeSuper(o,objects);
//            }
//        });
//        int count = 0;
//        while (true){
//            System.out.println("目前创建了" + (++count) + "个Car的子类了");
//            Car car = (Car) enhancer.create();
//            car.run();
//        }

//        //模拟 栈内存溢出现象
//        User user = new User();
//        user.run(0L);

        //模拟 堆内存溢出现象
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long count = 0;
        List<Object> list = new ArrayList<>();
        while (true) {
            Car car = new Car();
            car.setName("abc");
            list.add(car);
            System.out.println("当前创建了第" + (++count) + "个对象");
        }
    }

    static class Car {
        static {
            System.out.println("abc");
        }
        private String name;
        public void run() {
            System.out.println("汽车启动，开始行使......");
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class User {
        public void run(Long count) {
            System.out.println("汽车启动，开始行使......" + count);
            run(++count);
        }
    }
}
