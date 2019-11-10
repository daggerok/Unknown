package com.github.daggerok.microprofile.openliberty.blademvc;

import com.blade.Blade;
import com.blade.mvc.handler.RouteHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class BladeApplication {

    private Blade server;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        System.out.println("starting...");
        runBlade();
    }

    public void runBlade() {
        // Blade.of()
        //      .get("/", ctx -> ctx.text("Hello Blade"))
        //      .start();
        RouteHandler renderHelloHtml = ctx -> {
            ctx.attribute("name", "Max");
            ctx.render("hello.html");
        };
        server = Blade.of()
                      .get("/", renderHelloHtml)
                      .start();
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        System.out.println("Bye!");
        server.stop();
    }
}
