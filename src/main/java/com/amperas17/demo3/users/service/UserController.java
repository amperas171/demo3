package com.amperas17.demo3.users.service;

import com.amperas17.demo3.users.data.User;
import com.amperas17.demo3.users.data.UserCreds;
import com.amperas17.demo3.users.data.UserCredsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users/create")
    public ResponseEntity<?> create(@RequestBody UserCredsEntity user) {
        return userService.create(user)
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // TODO delete before release
    @GetMapping(value = "/users/getAll")
    public ResponseEntity<List<UserCredsEntity>> read() {
        final List<UserCredsEntity> users = userService.readAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/users/login")
    public ResponseEntity<User> login(@RequestBody UserCreds userCreds) {
        final UserCredsEntity uce = userService.findByCreds(userCreds);

        return uce != null
                ? new ResponseEntity<>(
                new User(uce.getUuid(), uce.getName(), uce.getSurname(), uce.getEmail(), uce.getPhone()),
                HttpStatus.OK
        )
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/update")
    public ResponseEntity<?> update(@RequestBody UserCredsEntity user) {
        final UserCredsEntity userUpdated = userService.update(user);

        return user != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/users/delete/{uuid}")
    public ResponseEntity<?> delete(@PathVariable(name = "uuid") UUID uuid) {
        final boolean deleted = userService.delete(uuid);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}