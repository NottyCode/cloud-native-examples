package test;

public class Prop {
    private String name;
    private String value;

    public Prop() {}

    public Prop(String n, String v) {
        name = n;
        value = v;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String n) {
        name = n;
    }

    public void setValue(String v) {
        value = v;
    }
}