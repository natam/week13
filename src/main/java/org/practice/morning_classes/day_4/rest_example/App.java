package org.practice.morning_classes.day_4.rest_example;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        ResourceConfig resourceConfig = new ResourceConfig(new HashSet<>(Arrays.asList (UserResource.class,ProductResource.class)));
        GrizzlyHttpServerFactory.createHttpServer(URI.create("https://localhost:8001/"),resourceConfig);
    }
}
