package org.zchzh.hellostartertest;

import java.util.concurrent.TimeUnit;

/**
 * @author zengchzh
 * @date 2021/2/1
 */
public class TestMain {

    public static void testMethod() throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(1000);
                    } catch (InterruptedException e){
                        this.interrupt();
                        e.printStackTrace();
                        System.out.println("pre end");
                    }
                    if (this.isInterrupted()){
                        System.out.println("end");
                        break;
                    }
                }

            }
        };
        thread.start();
        System.out.println("sleep start");
        TimeUnit.SECONDS.sleep(3);
        thread.interrupt();
        for (int j = 0; j < 10;j++){
            System.out.println("j" + j);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        testMethod();
    }
}
