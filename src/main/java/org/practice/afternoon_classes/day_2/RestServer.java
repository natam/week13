package org.practice.afternoon_classes.day_2;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.practice.afternoon_classes.day_2.entity.UsersService;
import org.practice.afternoon_classes.day_2.model.UserHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RestServer {

    private void runServer() throws IOException {
        UsersService usersService = new UsersService();
        String user = usersService.createUser("Natallia", 36);
        System.out.println(user);
        HttpServer server = HttpServer.create(new
                InetSocketAddress("localhost",8000),0);
        server.createContext("/api/user", new UserHandler(usersService));
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on " + server.getAddress());
    }


    public static void main(String[] args){
        try {
            RestServer server = new RestServer();
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
