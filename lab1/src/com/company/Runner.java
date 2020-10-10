package com.company;

import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) {

        System.out.println(args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4]);
        try {
            TimeUnit.SECONDS.sleep(Integer.parseInt(args[3]));
            RandomAccessFile pipe = new RandomAccessFile(args[0], "rwd");
            pipe.writeChars(Integer.parseInt(args[1]) * Integer.parseInt(args[2]) + " " + args[4] + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
