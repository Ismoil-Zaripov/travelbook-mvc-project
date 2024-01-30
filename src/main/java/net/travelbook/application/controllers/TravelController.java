package net.travelbook.application.controllers;

import lombok.RequiredArgsConstructor;
import net.travelbook.domain.models.request.TravelRequest;
import net.travelbook.domain.models.response.TravelResponse;
import net.travelbook.domain.usecases.TravelUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/travel")
public class TravelController {

    private final TravelUseCase<TravelRequest, TravelResponse> travelUseCase;

    @GetMapping
    public String search(
            @RequestParam("search") String searchQuery,
            Model model
    )
    {
        var travels = travelUseCase.searchTravelPost(searchQuery);
        if (travels.isEmpty()) return "redirect:/";

        model.addAttribute("searchedTravelPosts", travels);
        return "travel/travel-search";
    }

    @GetMapping("/add")
    public String addTravelPage(Model model)
    {
        model.addAttribute(
                "createTravelRequest",
                TravelRequest.builder().build()
        );

        return "travel/travel-add";
    }

    @PostMapping("/add")
    public String saveTravel(
            @ModelAttribute("createTravelRequest")
            TravelRequest createTravelRequest
    )
    {
        travelUseCase.saveTravelPost(createTravelRequest);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String travelsList(Model model)
    {
        var travels = travelUseCase.getAllTravelPosts();
        model.addAttribute("travels", travels);

        return "travel/travel-list";
    }

    @GetMapping("/update/{travelId}")
    public String updateTravel(Model model, @PathVariable Integer travelId)
    {
        var travel = travelUseCase.getTravelPostById(travelId);
        var travelUpdateRequest = TravelRequest.builder()
                .title(travel.title())
                .description(travel.description())
                .photoUrl(travel.photoUrl())
                .build();

        model.addAttribute("travelId", travelId);
        model.addAttribute("travelUpdateRequest", travelUpdateRequest);

        return "travel/travel-update";
    }

    @PostMapping("/update/{travelId}")
    public String updateTravel(
            @PathVariable Integer travelId,
            @ModelAttribute("travelUpdateRequest")
            TravelRequest travelUpdateRequest
    ){
        boolean travelUpdated = travelUseCase
                .updateTravelPost(travelId, travelUpdateRequest);
        if (!travelUpdated) return "redirect:/travel/update/"+travelId;

        return "redirect:/";
    }

    @GetMapping("/delete/{travelId}")
    public String deleteTravel(@PathVariable Integer travelId){
        travelUseCase.deleteTravelPost(travelId);
        return "redirect:/";
    }
}
