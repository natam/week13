package org.practice.afternoon_classes.day_2.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersService {
    private List<User> users = new ArrayList<>();

    public String createUser(String name, int age){
        User newUser = new User(name, age);
        users.add(newUser);
        return newUser.toString();
    }

    public List<User> getUsers() {
        return users;
    }

    public String getUserById(String userId){
        return users.stream().filter(user -> user.getId().equals(userId)).findFirst().get().toString();
    }

    public boolean deleteUser(String userId){
        Optional<User> userToDelete = users.stream().filter(user -> user.getId().equals(userId)).findFirst();
        if(userToDelete.isPresent()){
            users.remove(userToDelete.get());
            return true;
        }else {
            return false;
        }
    }

    public String updateUser(String userId, String newName, int newAge){
        Optional<User> userToUpdate = users.stream().filter(user -> user.getId().equals(userId)).findFirst();
        if(userToUpdate.isPresent()){
            if(!newName.isEmpty()) {
                userToUpdate.get().setName(newName);
            }
            if(newAge>0){
                userToUpdate.get().setAge(newAge);
            }
            return userToUpdate.get().toString();
        }else {
            return "";
        }
    }
}
