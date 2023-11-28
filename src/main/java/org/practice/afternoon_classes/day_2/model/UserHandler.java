package org.practice.afternoon_classes.day_2.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.practice.afternoon_classes.day_2.entity.UsersService;

import java.io.*;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.net.HttpURLConnection.*;


public class UserHandler implements HttpHandler {
    private UsersService usersService;
    private int statusCode;
    private String response;

    public UserHandler(UsersService users) {
        this.usersService = users;
        this.response = "";
        this.statusCode = HTTP_OK;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        switch (method) {
            case "GET":
                handleGetRequest(exchange);
                break;
            case "POST":
                handlePostRequest(exchange);
                break;
            case "PUT":
                handlePutRequest(exchange);
                break;
            case "DELETE":
                handleDeleteRequest(exchange);
                break;
            default:
                statusCode = HTTP_BAD_METHOD;
                response = "Method is not allowed";
        }
        exchange.getResponseHeaders().add("Content-type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static Map<String, String> getParamMap(String query) {
        if (query == null || query.isEmpty()) return Collections.emptyMap();
        return Stream.of(query.split("&"))
                .filter(s -> !s.isEmpty())
                .map(kv -> kv.split("=", 2))
                .collect(Collectors.toMap(x -> x[0], x -> x[1]));
    }

    public void handleGetRequest(HttpExchange exchange) {
        Map<String, String> params = getParamMap(exchange.getRequestURI().getQuery());
        if (!params.isEmpty()) {
            if (params.containsKey("userId")) {
                response = usersService.getUserById(params.get("userId"));
                statusCode = HTTP_OK;
            } else {
                response = "Bad request. User id should be specified";
                statusCode = HTTP_BAD_REQUEST;
            }
        } else {
            response = "Bad request. User id should be specified";
            statusCode = HTTP_BAD_REQUEST;
        }
    }

    public void handleDeleteRequest(HttpExchange exchange) {
        Map<String, String> params = getParamMap(exchange.getRequestURI().getQuery());
        response = "Bad request. User id should be specified";
        statusCode = HTTP_BAD_REQUEST;
        if (!params.isEmpty()) {
            if (params.containsKey("userId")) {
                response = "";
                if(usersService.deleteUser(params.get("userId"))) {
                    statusCode = HTTP_OK;
                }else {
                    statusCode = HTTP_NOT_FOUND;
                }
            }
        }
    }

    public void handlePostRequest(HttpExchange exchange) {
        String contentType = exchange.getRequestHeaders().getFirst("Content-Type");
        if (contentType.contains("json")) {
            InputStream is = exchange.getRequestBody();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
                JsonReader jsonReader = new JsonReader(in);
                JsonElement json = JsonParser.parseReader(jsonReader);
                String name = json.getAsJsonObject().get("name").getAsString();
                int age = json.getAsJsonObject().get("age").getAsInt();
                response = usersService.createUser(name, age);
                statusCode = HTTP_OK;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            statusCode = HTTP_BAD_REQUEST;
            response = "";
        }
    }

    public void handlePutRequest(HttpExchange exchange) {
        String contentType = exchange.getRequestHeaders().getFirst("Content-Type");
        if (contentType.contains("json")) {
            InputStream is = exchange.getRequestBody();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
                JsonReader jsonReader = new JsonReader(in);
                JsonElement json = JsonParser.parseReader(jsonReader);
                String id = json.getAsJsonObject().get("userId").getAsString();
                String name = json.getAsJsonObject().get("name").getAsString();
                int age = json.getAsJsonObject().get("age").getAsInt();
                response = usersService.updateUser(id, name, age);
                if (response.isEmpty()) {
                    statusCode = HTTP_NOT_FOUND;
                } else statusCode = HTTP_OK;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            statusCode = HTTP_BAD_REQUEST;
            response = "";
        }
    }
}
