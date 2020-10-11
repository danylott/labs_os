package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class Manager {
    public String name;
    private final int value;
    private final int multiplier;
    private final int delay;
    private Process process = null;

    public Manager(String name, int value, int multiplier, int delay) {
        this.name = name;
        this.value = value;
        this.multiplier = multiplier;
        this.delay = delay;
    }

    public void runCommand(String path_to_pipe) {
        try
        {
            String baseCommand = "/home/neknu/.jdks/openjdk-15/bin/java -javaagent:/home/neknu/JetBrains/ideaIU-2020.2.2/idea-IU-202.7319.50/lib/idea_rt.jar=45241:/home/neknu/JetBrains/ideaIU-2020.2.2/idea-IU-202.7319.50/bin -Dfile.encoding=UTF-8 -classpath /home/neknu/University/3course/OS/lab1/out/production/lab1 com.company.Runner";
            String command = baseCommand + " " + path_to_pipe + " " + value + " " + multiplier + " " + delay + " " + name;
            var processBuilder = new ProcessBuilder();

            processBuilder.command("bash", "-c", command);
            this.process = processBuilder.start();

//            System.out.println(command);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void cancelCommand() {
        if (this.process != null) {
            this.process.destroy();
        }
    }
}