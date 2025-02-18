package org.example.montenegropizzeria.user.infrastructure;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserEntityController {

    @GetMapping("/admin/adminAcces")
    @PreAuthorize("hasRole('ADMIN')")
    public String accesAdmin(){
        return "admin";
    }
}
