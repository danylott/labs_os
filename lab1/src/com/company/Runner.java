package com.company;

import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

public class Runner {
    public static int multiplier(String first, String second) {
        return Integer.parseInt(first) * Integer.parseInt(second);
    }
    public static void main(String[] args) {

        System.out.println(args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4]);
        try {
            TimeUnit.SECONDS.sleep(Integer.parseInt(args[3]));
            RandomAccessFile pipe = new RandomAccessFile(args[0], "rwd");
            pipe.writeChars(multiplier(args[1], args[2])+ " " + args[4] + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
