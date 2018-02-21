package test;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

public class MyFallback implements FallbackHandler<Prop> {
    public Prop handle(ExecutionContext ctx) {
        String name = (String)ctx.getParameters()[0];
        return new Prop(name, System.getProperty(name));
    }
}