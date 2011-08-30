package com.tw.timesheet.android.instrument.handler;

import org.openqa.selenium.remote.server.DriverSessions;
import org.openqa.selenium.remote.server.JsonParametersAware;
import org.openqa.selenium.remote.server.handler.GetElementDisplayed;
import org.openqa.selenium.remote.server.rest.ResultType;

import java.util.Map;

public class IsElementDisplayed extends GetElementDisplayed implements JsonParametersAware {
    private static final String ID = "id";

    public IsElementDisplayed(DriverSessions sessions) {
        super(sessions);
    }

    @Override
    public ResultType call() throws Exception {
        return super.call();
    }

    @Override
    public void setJsonParameters(Map<String, Object> allParameters) throws Exception {
        if (allParameters.containsKey(ID)) {
            String elementId = (String) allParameters.get(ID);
            setId(elementId);
            return;
        }
        throw new Exception("please select an element.");
    }
}
