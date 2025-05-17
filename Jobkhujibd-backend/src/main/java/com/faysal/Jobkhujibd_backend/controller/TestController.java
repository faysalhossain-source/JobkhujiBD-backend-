package com.faysal.Jobkhujibd_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // Public API endpoints - no authentication required
    @GetMapping("/api/public/hello")
    public String publicHello() {
        return "Hello, public user! This is accessible to everyone.";
    }

    @GetMapping("/api/public/info")
    public String publicInfo() {
        return "This is public information about our API.";
    }

    // User API endpoints - requires REGULAR_USER role or higher
    @GetMapping("/api/user/profile")
    public String userProfile() {
        return "Welcome to your profile! This is accessible to authenticated users with REGULAR_USER role or higher.";
    }

    @GetMapping("/api/user/data")
    public String userData() {
        return "Here is your personal data. Only visible to authenticated users.";
    }

    // Manager API endpoints - requires MANAGER role or higher
    @GetMapping("/api/manager/reports")
    public String managerReports() {
        return "Manager reports dashboard. Only accessible to MANAGER role or higher.";
    }

    @GetMapping("/api/manager/team")
    public String managerTeam() {
        return "Team management panel. Only accessible to MANAGER role or higher.";
    }

    // Admin API endpoints - requires ADMIN role
    @GetMapping("/api/admin/dashboard")
    public String adminDashboard() {
        return "Admin dashboard with system statistics. Only accessible to ADMIN role.";
    }

    @GetMapping("/api/admin/settings")
    public String adminSettings() {
        return "System settings page. Only accessible to ADMIN role.";
    }

}
