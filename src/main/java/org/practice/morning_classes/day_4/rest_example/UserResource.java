package org.practice.morning_classes.day_4.rest_example;

import org.practice.morning_classes.day_4.rest_example.dao.UserDao;
import org.practice.morning_classes.day_4.rest_example.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserResource {
    UserDao userDao = new UserDao();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers(){
        return userDao.getAllUsers().toString();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserById(@PathParam("id") int id){
        return userDao.getUser(id).toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user) {
        userDao.addUser(user); }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(@PathParam("id") int id, User user) {
        user.setId(id);
        userDao.updateUser(user); }
    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") int id) {
        userDao.deleteUser(id); }
}
