package test;

import javax.enterprise.context.ApplicationScoped;
import java.util.Properties;

@ApplicationScoped
public class MyBean {
    public String getProperty(String name) {
        return System.getProperty(name);
    }
    
    public Properties getProperties() {
        return System.getProperties();
    }
}