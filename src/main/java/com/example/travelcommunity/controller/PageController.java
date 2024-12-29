package com.example.travelcommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
//    @GetMapping("/board")
//    public String showBoardPage() {
//        return "board"; // templates/board.html
//    }

    @GetMapping("/board/write")
    public String showWritePage() {
        return "write"; // templates/schedule.html
    }

    @GetMapping("/schedule")
    public String showSchedulePage() {
        return "schedule"; // templates/schedule.html
    }

    @GetMapping("/profile")
    public String showProfilePage() {
        return "profile"; // templates/profile.html
    }

    @GetMapping("/trips")
    public String showTripsPage() {
        return "trips"; // templates/trips.html
    }
}
