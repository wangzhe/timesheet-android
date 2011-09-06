package com.tw.timesheet.android.instrument;

import com.google.android.testing.nativedriver.server.ServerInstrumentation;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.servlet.ServletHolder;

public class TeInstrumentation extends ServerInstrumentation {

    @Override
    protected Handler createHandler() {
        org.mortbay.jetty.servlet.Context root
                = new org.mortbay.jetty.servlet.Context(getServer(), "/hub",
                org.mortbay.jetty.servlet.Context.SESSIONS);
        root.addServlet(new ServletHolder(new TeNativeDriverServlet()), "/*");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(
                new org.mortbay.jetty.Handler[]{root, new DefaultHandler()});
        return handlers;
    }
}
