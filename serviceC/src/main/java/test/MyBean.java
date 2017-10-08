package test;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyBean {
    public String getProperty(String name) {
        return System.getProperty(name);
    }
}