package y.w.security.basic.ch3_4_5_security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello/{name}")
    public ResponseEntity<String> helloWorld(@PathVariable("name") String name) {
        return ResponseEntity.ok("Hello, " + (name == null ? "World!" : name));
    }
}
