package com.faysal.Jobkhujibd_backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    // Public endpoints
    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello, public user! This is a test message.";
    }

    @GetMapping("/public/info")
    public String publicInfo() {
        return "This is public information about our API.";
    }

    // User-level endpoints (requires REGULAR_USER or higher)
    @GetMapping("/user/profile")
    public String userProfile(Authentication authentication) {
        return "Hello " + authentication.getName() + ", welcome to your profile!";
    }

    @GetMapping("/user/data")
    public String userData() {
        return "Here is your personal data. Only visible to authenticated users.";
    }

    // Manager-level endpoints (requires MANAGER or higher)
    @GetMapping("/employer/reports")
    public String managerReports() {
        return "Manager reports dashboard. Only accessible to MANAGER role or higher.";
    }

    @GetMapping("/employer/team")
    public String managerTeam() {
        return "Team management panel. Only accessible to MANAGER role or higher.";
    }

    // Admin-level endpoints (requires ADMIN)
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Admin dashboard with system statistics. Only accessible to ADMIN role.";
    }

    @GetMapping("/admin/settings")
    public String adminSettings() {
        return "System settings page. Only accessible to ADMIN role.";
    }
}
