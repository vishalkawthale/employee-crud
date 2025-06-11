package com.employee.controller;

import com.employee.entity.sql.postgres.User;
import com.employee.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/register")
    public Mono<ResponseEntity<User>> register(@Valid @RequestBody User user){
        log.info("save user: {}", user);
        return this.userService.register(user)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody User user) {
        log.info("verify user: {}", user);
        return this.userService.verify(user)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/getAll")
    public Flux<User> getAllUsers(){
        log.info("get all users");
        return this.userService.getAllUsers();
    }

    @GetMapping(path = "/get/{id}")
    public Mono<ResponseEntity<User>> getUser(@Valid @PathVariable Integer id){
        log.info("get user with id: {}", id);
        return this.userService.getUser(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/update/{id}")
    public Mono<ResponseEntity<Void>> updateUser(@Valid @RequestBody User user, @Valid @PathVariable(value = "id") Integer id){
        log.info("update user: {}", user);
        return this.userService.updateUser(id, user)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@Valid @PathVariable(value = "id") Integer id){
        log.info("delete user with id: {}", id);
        return this.userService.deleteUser(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/deleteAll")
    public Mono<ResponseEntity<Void>> deleteAllUsers(){
        log.info("delete all users");
        return this.userService.deleteAllUsers()
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
