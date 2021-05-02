package com.amperas17.demo3.users.service;

import com.amperas17.demo3.users.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@RequestBody UserEntity user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserEntity>> read() {
        final List<UserEntity> users = userService.readAll();

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserEntity> read(@PathVariable(name = "id") int id) {
        final UserEntity user = userService.read(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody UserEntity user) {
        final UserEntity userUpdated = userService.update(user, id);

        return user != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}