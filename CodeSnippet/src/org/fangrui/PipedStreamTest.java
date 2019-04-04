package org.fangrui;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.TimeUnit;

public class PipedStreamTest {
    public static void main(String[] args) {
        final PipedInputStream pipedInputStream=new PipedInputStream();
        final PipedOutputStream pipedOutputStream ;
        Thread otherThread=null;
        try {
            pipedOutputStream=new PipedOutputStream(pipedInputStream);
            otherThread=new Thread(){
                @Override
                public void run(){
                    try {
                        System.out.println(Thread.currentThread()+" write data");
                        pipedOutputStream.write(5);
                        System.out.println(Thread.currentThread()+" finish write data");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            otherThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(otherThread.isAlive());
        try {
            System.out.println(Thread.currentThread()+" first read data");
            pipedInputStream.read();
            System.out.println(Thread.currentThread()+" Second read data");
            pipedInputStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
