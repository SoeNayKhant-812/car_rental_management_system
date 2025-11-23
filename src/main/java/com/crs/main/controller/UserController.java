package com.crs.main.controller;

import com.crs.main.dto.UserDTO;
import com.crs.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllCars() {
        List<UserDTO> cars = userService.findAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getCarById(@PathVariable Long id) {
        UserDTO car = userService.findById(id);
        return ResponseEntity.ok(car);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createCar(@RequestBody UserDTO carDTO) {
        UserDTO createdCar = userService.save(carDTO);
        return ResponseEntity.ok(createdCar);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<UserDTO> updateCar(@PathVariable Long id,@RequestBody UserDTO carDTO) {
        UserDTO updatedCar = userService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
