package com.company;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int errorNotInt(String value) {
        int result = 0;
        try {
            value = value.replaceAll("\\p{C}", "");
            result = Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Provide correct value!");
            System.exit(0);
        }
        return result;
    }
    public static Manager createManager(String name) {
        System.out.println("Creating new Manager: " + name);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose value (int):");
        int value = errorNotInt(scanner.next());
        System.out.println("Choose multiplier (int):");
        int multiplier = errorNotInt(scanner.next());
        System.out.println("Choose delay (int):");
        int delay = errorNotInt(scanner.next());
        return new Manager(name, value, multiplier, delay);
    }

    public static void main(String[] args) throws IOException {
        String path_to_pipe = "/home/neknu/University/3course/OS/lab1/fifo";
        RandomAccessFile pipe = new RandomAccessFile(path_to_pipe, "rwd");
        Manager first = createManager("first");
        Manager second = createManager("second");
        first.runCommand(path_to_pipe);
        second.runCommand(path_to_pipe);
        String echoResponse = pipe.readLine();
        System.out.println("Response: " + echoResponse );
        List<String> resp1 = Arrays.asList(echoResponse.split(" "));
        if (errorNotInt(resp1.get(0)) == 0) {
            System.out.println("ERROR: 0 from response");
            first.cancelCommand();
            second.cancelCommand();
            System.exit(0);
        }
        echoResponse = pipe.readLine();
        System.out.println("Response: " + echoResponse );
    }
}
