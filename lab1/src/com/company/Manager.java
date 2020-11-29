package com.company;

public class Manager {
    public String name;
    private final int value;
    private Process process = null;

    public Manager(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public void runCommand(String path_to_pipe) {
        try
        {
            String baseCommand = "/home/neknu/.jdks/openjdk-15/bin/java -javaagent:/home/neknu/JetBrains/ideaIU-2020.2.2/idea-IU-202.7319.50/lib/idea_rt.jar=38229:/home/neknu/JetBrains/ideaIU-2020.2.2/idea-IU-202.7319.50/bin -Dfile.encoding=UTF-8 -classpath /home/neknu/University/3course/OS/lab1/out/production/lab1:/home/neknu/University/3course/OS/lab1/lib/hamcrest-core-1.3.jar:/home/neknu/University/3course/OS/lab1/lib/junit-jupiter-5.4.2.jar:/home/neknu/University/3course/OS/lab1/lib/junit-jupiter-engine-5.4.2.jar:/home/neknu/University/3course/OS/lab1/lib/junit-platform-commons-1.4.2.jar:/home/neknu/University/3course/OS/lab1/lib/junit-jupiter-params-5.4.2.jar:/home/neknu/University/3course/OS/lab1/lib/apiguardian-api-1.0.0.jar:/home/neknu/University/3course/OS/lab1/lib/opentest4j-1.1.1.jar:/home/neknu/University/3course/OS/lab1/lib/junit-platform-engine-1.4.2.jar:/home/neknu/University/3course/OS/lab1/lib/junit-4.12.jar:/home/neknu/University/3course/OS/lab1/lib/junit-jupiter-api-5.4.2.jar:/home/neknu/University/3course/OS/lab1/lib/lab1.jar com.company.Runner";
            String command = baseCommand + " " + path_to_pipe + " " + value + " " + name;
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