package br.com.dbserver.weatherapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class helloWorldController {
    
    @GetMapping("/hello")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("hello");
    }
}
