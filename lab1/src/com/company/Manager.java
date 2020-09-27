package com.company;

import java.io.IOException;

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

    public void runCommand(String path_to_pipe) throws IOException {
        String command = "sleep " + delay + " && echo " + this.name + " " +
                "$((" + this.value + " * " + this.multiplier + ")) > " + path_to_pipe;
        try
        {
            var processBuilder = new ProcessBuilder();

            processBuilder.command("bash", "-c", command);
            this.process = processBuilder.start();

            System.out.println(command);
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