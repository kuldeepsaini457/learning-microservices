package com.kuldeeplearning.monolithic.user;

import com.kuldeeplearning.monolithic.report.CreateReportRequest;
import com.kuldeeplearning.monolithic.report.ReportResponse;
import com.kuldeeplearning.monolithic.report.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(
            @RequestParam String username,
            @RequestParam String email) {

        return userService.createUser(
                username,
                email
        );
    }
}