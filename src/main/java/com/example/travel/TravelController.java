package com.example.travel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("query", "");
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
        return "redirect:/travel/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("query") String query,
            Model model
    ){
        if (query.isEmpty()) {
            return "redirect:/travel/";
        }
        List<TravelResponse> travels = travelService.search(query);
        model.addAttribute("travels", travels);
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteTravel(
            @PathVariable("id") Integer id
    ){
        travelService.delete(id);
        return "redirect:/travel/";
    }

    @GetMapping("/update/{id}")
    public String update(
            @PathVariable("id") Integer id,
            Model model
    ){
        TravelResponse response = travelService.getById(id);
        if (response == null) {
            return "redirect:/travel/";
        }
        response.setId(id);

        model.addAttribute("newTravel", response);
        return "travel-update";
    }

    @PostMapping("/update/{id}")
    public String updateTravel(
            @PathVariable Integer id,
            @Valid @ModelAttribute("newTravel") TravelResponse request,
            BindingResult result
    ){
        if (result.hasErrors()) return "travel-update";

        boolean hasUpdated = travelService.update(id,request);
        if (!hasUpdated) return "travel-update";
        return "redirect:/travel/";
    }
}
