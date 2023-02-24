package com.example.travel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/add-page")
    public String addTravelPage(Model model){
        model.addAttribute("travel", new TravelRequest());
        return "travel-add";
    }

    @PostMapping("/add")
    public String addTravel(
            @Valid
            @ModelAttribute("travel") TravelRequest request,
            BindingResult result
    ){
        if (result.hasErrors()) return "travel-add";
        travelService.save(request);
        return "index";
    }
}
