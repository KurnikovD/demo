package com.example.demo;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class TomcatApplicationLauncher {
    public static void main(String[] args) throws LifecycleException{
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();
        Context ctx = tomcat.addContext("", null);
        Wrapper servlet = Tomcat.addServlet(ctx, "demoServlet", new demoServlet());
        servlet.setLoadOnStartup(2);
        servlet.addMapping("/add/*");
        servlet.addMapping("/top/*");
        tomcat.start();
    }
}
