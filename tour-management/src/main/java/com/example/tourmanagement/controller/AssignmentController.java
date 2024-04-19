package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.Tour;
import com.example.tourmanagement.model.TourAssignment;
import com.example.tourmanagement.model.TourGuide;
import com.example.tourmanagement.service.TourAssignmentService;
import com.example.tourmanagement.service.TourGuideService;
import com.example.tourmanagement.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {

    private final TourAssignmentService tourAssignmentService;
    private final TourService tourService;
    private final TourGuideService tourGuideService;

    @Autowired
    public AssignmentController(TourAssignmentService tourAssignmentService, TourService tourService, TourGuideService tourGuideService) {
        this.tourAssignmentService = tourAssignmentService;
        this.tourService = tourService;
        this.tourGuideService = tourGuideService;
    }

    @GetMapping
    public String showTourAssignments(Model model) {
        // Show available tour assignments
        Object[] tourAssignmentList = tourAssignmentService.getAllTourAssignment().stream().map(i -> Arrays.asList(i.getTour(), i.getTourGuide())).toArray();
        model.addAttribute("tourAssignmentList", tourAssignmentList);
        // Show adding tour assignment form
        List<Tour> tours = tourService.getAllTour();
        List<TourGuide> tourGuides = tourGuideService.getAllTourGuides();
        //
        model.addAttribute("tours", tours);
        model.addAttribute("tourguides", tourGuides);
        TourAssignment tourAssignment = new TourAssignment();
        model.addAttribute(tourAssignment);
        return "tour_assignment/tour_assign";
    }

    @PostMapping("/add")
    public String addTourAssignment(@ModelAttribute("tourAssignment") TourAssignment tourAssignment){
        // Validate received tourAssignment

        //
        tourAssignmentService.save(tourAssignment);
        return "redirect:/assignments";
    }



}