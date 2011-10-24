package com.tw.timesheet.android.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cuke4DukeTask extends Task {
    private String libPath;
    private String classPath;
    private String jrubyCommand;
    private String folder;
    private boolean connection;

    private String feature;
    private String scenario;
    private String adbCommand;
    private int port;
    private String target;
    private String tag;

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }

    public void setJrubyCommand(String jrubyCommand) {
        this.jrubyCommand = jrubyCommand;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setAdbCommand(String adbCommand) {
        this.adbCommand = adbCommand;
    }

    public void setCuke4dukeCommand(String cuke4dukeCommand) {
    }

    public void setLibPath(String libPath) {
        this.libPath = libPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    @Override
    public void execute() throws BuildException {
        configureTest();

        try {
            runCucumberTests();
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }

    private void runCucumberTests() throws IOException {
        System.out.println("start testing...");
        final Process p = Runtime.getRuntime().exec(getCommandParameters());

        String failedScenarios = handleProcessInput(p);

        if (hasFailedScenarios(failedScenarios)) {
            throwBuildExceptionWithFailedScenarios(failedScenarios);
        }

        String processFailure = handleProcessError(p);

        if (hasErrorInput(processFailure)) {
            throwBuildExceptionWithReason(getCommandParameters(), processFailure);
        }
    }

    private String[] getCommandParameters() {
        List<String> parameters = new ArrayList<String>();
        parameters.addAll(Arrays.asList(jrubyCommand, "-J-Dapp=" + target, "-J-Dport=" + port, "-S", "cuke4duke", "--verbose", "--jar", libPath, "--require", classPath, "--color", "--format", "pretty", "-t"));

        if (hasValue(tag)) {
            parameters.add(tag);
        } else {
            parameters.add("@automated");
        }

        if (hasValue(feature)) {
            parameters.add(feature);
        } else {
            parameters.add("features/" + folder);
        }

        if (hasValue(scenario)) {
            parameters.add("--name=" + scenario);
        }
        return parameters.toArray(new String[0]);
    }

    private boolean hasValue(String toCheck) {
        return toCheck != null && !"".equalsIgnoreCase(toCheck);
    }

    private boolean hasFailedScenarios(String failedScenarios) {
        return !"".equalsIgnoreCase(failedScenarios.trim());
    }

    private boolean hasErrorInput(String processFailure) {
        return !"".equalsIgnoreCase(processFailure.trim());
    }

    private void throwBuildExceptionWithFailedScenarios(String failedScenarios) {
        throw new BuildException("Some scenarios failed: \r\n" + failedScenarios);
    }

    private void throwBuildExceptionWithReason(String[] progArray, String processFailure) {
        StringBuilder message = new StringBuilder("Execute command failed with arguments: \r\n");
        for (String parameter : progArray) {
            message.append(parameter).append("\r\n");
        }
        throw new BuildException(message.append(processFailure).toString());
    }

    private String handleProcessError(Process p) throws IOException {
        StringBuilder errors = new StringBuilder();
        String errorLine;

        BufferedReader buferror = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        while ((errorLine = buferror.readLine()) != null) {
            System.out.println(errorLine);
            errors.append(errorLine).append("\r\n");
        }

        return errors.toString();
    }

    private String handleProcessInput(Process p) throws IOException {
        StringBuilder failedScenarios = new StringBuilder();
        String line;
        boolean hasScenarioFailed = false;

        BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = buf.readLine()) != null) {
            System.out.println(line);

            if (hasScenarioFailed) {
                failedScenarios.append(line).append("\r\n");
            }
            if (isFailed(line)) {
                hasScenarioFailed = true;
            }
        }
        return failedScenarios.toString();
    }

    private void configureTest() {
        if (target.equals("timesheet")) {
            registerConnection();
            port = 54129;
            folder = "./";
        }
    }

    private void registerConnection() {
        if (!connection) return;
        System.out.println("register connection");
        try {
            Runtime.getRuntime().exec(new String[]{adbCommand, "shell", "am", "instrument", "com.tw.timesheet.android/com.tw.timesheet.android.instrument.TeInstrumentation"});
            Runtime.getRuntime().exec(new String[]{adbCommand, "forward", "tcp:54129", "tcp:54129"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isFailed(String line) {
//        return line.matches("\\d+ scenarios \\(.*failed.*\\)");
        return line.trim().lastIndexOf("Failing Scenarios:") >= 0;
    }
}
