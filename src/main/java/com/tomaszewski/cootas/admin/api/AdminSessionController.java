package com.tomaszewski.cootas.admin.api;

import com.tomaszewski.cootas.admin.service.AdminSessionService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/sessions")
public class AdminSessionController {
    private final AdminSessionService adminSessionService;

    public AdminSessionController(AdminSessionService adminSessionService) {
        this.adminSessionService = adminSessionService;
    }

    @PostMapping()
    public ResponseEntity<String> createSession(@Email @NotBlank @RequestParam String email) {
        return ResponseEntity.ok(adminSessionService.createSession(email));
    }

}
