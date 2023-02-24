package com.example.travel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/travel")
public class TravelController {
    private final TravelService travelService;

    @GetMapping("/")
    public String index(Model model){

        List<TravelResponse> travels = travelService.findByTopFiveTravel();
        model.addAttribute("travels", travels);
        return "index";
    }
}
