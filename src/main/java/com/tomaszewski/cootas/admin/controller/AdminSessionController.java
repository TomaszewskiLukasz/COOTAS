package com.tomaszewski.cootas.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin/upload-sessions")
public class AdminSessionController {

    @PostMapping()
    public ResponseEntity<String> createSession() {
        return ResponseEntity.ok("Session created successfully");
    }


}
