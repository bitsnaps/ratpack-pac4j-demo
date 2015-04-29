package org.leleuj;

import java.io.File;
import java.net.URI;

import ratpack.error.ClientErrorHandler;
import ratpack.error.ServerErrorHandler;
import ratpack.groovy.template.TextTemplateModule;
import ratpack.guice.Guice;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;
import ratpack.session.SessionModule;
import ratpack.session.store.MapSessionsModule;

public class RatpackPac4jDemo {

    private final static int PORT = 8080;
    public final static String URL = "http://localhost:" + PORT;

    public static void main(final String[] args) throws Exception {

         RatpackServer.start(server -> server
                         .serverConfig(ServerConfig.baseDir(new File("src/main")).publicAddress(new URI(URL)).port(PORT).build())
                         .registry(Guice.registry(b -> {
                             b.add(new SessionModule());
                             b.add(new MapSessionsModule(10, 5));
                             b.add(TextTemplateModule.class);
                         }))
                        .handlers(chain -> new AppHandlerFactory())
        );

    }
}
