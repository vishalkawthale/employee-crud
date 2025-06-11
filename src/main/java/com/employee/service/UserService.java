package com.employee.service;

import com.employee.config.jwt.JWTService;
import com.employee.entity.sql.postgres.User;
import com.employee.repository.sql.postgres.UserRepository;
import com.employee.utils.LogExecutionTime;
import com.employee.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService{

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authManager;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    @LogExecutionTime
    public Mono<User> register(User user) {
        User userToSave = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .phone(user.getPhone())
                .role(user.getRole())
                .status(user.getStatus())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
        return Mono.just(this.userRepository.save(userToSave));
    }

    public Mono<String> verify(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            log.info("User {} is authenticated", user.getUsername());
            return Mono.just(jwtService.generateToken(user.getUsername()));
        } else {
            return Mono.just("fail");
        }
    }

    public Mono<User> getUser(Integer id) {
        return Mono.just(this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found")));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> deleteUser(Integer id) {
        this.userRepository.deleteById(id);
        return Mono.empty();
    }

    public Mono<Void> updateUser(Integer id, User user) {
        this.userRepository.findById(id).ifPresentOrElse(
                userFetched -> {
                    User userToUpdate = User.builder()
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .email(user.getEmail())
                            .phone(user.getPhone())
                            .role(user.getRole())
                            .status(user.getStatus())
                            .updatedAt(Timestamp.from(Instant.now()))
                            .build();
                    this.userRepository.save(userToUpdate);
                },
                () -> {
                    throw new NotFoundException("User not found");
                }
        );
        return Mono.empty();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> deleteAllUsers() {
        this.userRepository.deleteAll();
        return Mono.empty();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> saveAllUsers(Iterable<User> users) {
        this.userRepository.saveAll(users);
        return Mono.empty();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Flux<User> getAllUsers() {
        return Flux.fromIterable(this.userRepository.findAll());
    }

}
