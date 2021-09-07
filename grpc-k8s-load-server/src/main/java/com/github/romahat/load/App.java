package com.github.romahat.load;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "grpc-k8s-load-server";
    }

    @Override
    public void run(AppConfig configuration, Environment environment) throws Exception {
        environment.lifecycle().manage(new GrpcServer(configuration));
    }
}
