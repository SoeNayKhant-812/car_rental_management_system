package com.crs.main.controller;

import com.crs.main.dto.UserDTO;
import com.crs.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> cars = userService.findAll();
        return ResponseEntity.ok(cars);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO car = userService.findById(id);
        return ResponseEntity.ok(car);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','USER')")
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO carDTO) {
        UserDTO createdCar = userService.save(carDTO);
        return ResponseEntity.ok(createdCar);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','USER')")
    @PutMapping("/{id}/update")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO carDTO) {
        UserDTO updatedCar = userService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
