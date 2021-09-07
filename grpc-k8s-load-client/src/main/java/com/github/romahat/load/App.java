package com.github.romahat.load;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new App().run("server", "/Users/roman.ivchenko/git/grpc-k8s-load/grpc-k8s-load-client/grpc-k8s-load.yml");
    }

    @Override
    public String getName() {
        return "grpc-k8s-load-client";
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }

    @Override
    public void run(AppConfig configuration, Environment environment) throws Exception {
        LoadService loadService = new LoadService(configuration);
        environment.lifecycle().manage(loadService);
        environment.jersey().register(new LoadResource(loadService));
    }
}
