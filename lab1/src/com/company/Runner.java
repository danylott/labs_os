package com.company;

import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;
import spos.lab1.demo.IntOps;

public class Runner {
    public static int f(Integer value) throws Exception {
        switch (value) {
            case 1:
                TimeUnit.SECONDS.sleep(3);
                return value;
            case 2:
                TimeUnit.SECONDS.sleep(8);
                return -1;
            case 3:
                return 0;
            case 4:
                TimeUnit.SECONDS.sleep(value);
                return 10;
            case 5:
                return 1;
            case 6:
                TimeUnit.SECONDS.sleep(10000);
            default:
                throw new Exception("Error");
        }
    }

    public static int g(Integer value) throws Exception {
        switch (value) {
            case 1:
                TimeUnit.SECONDS.sleep(5);
                return 10;
            case 2:
                TimeUnit.SECONDS.sleep(3);
                return -1;
            case 3:
                TimeUnit.SECONDS.sleep(value);
                return 2;
            case 4:
                return 0;
            case 5:
                TimeUnit.SECONDS.sleep(10000);

            case 6:
                TimeUnit.SECONDS.sleep(value);
                return value * value;
            default:
                throw new Exception("Error");
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(args[0] + " " + args[1] + " " + args[2]);
            String path_to_pipe = args[0];
            int value = Integer.parseInt(args[1]);
            String name = args[2];
            RandomAccessFile pipe = new RandomAccessFile(path_to_pipe, "rwd");
            int result;
            switch (name) {
                case "Function_F" -> result = f(value);
                case "Function_G" -> result = g(value);
                case "Lab1.jar_F" -> result = IntOps.funcF(value);
                default -> result = IntOps.funcG(value);
            }

            pipe.writeChars(result + " " + args[2] + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
