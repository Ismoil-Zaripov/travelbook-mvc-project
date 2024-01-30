package net.travelbook.application.controllers;

import lombok.RequiredArgsConstructor;
import net.travelbook.domain.models.request.TravelRequest;
import net.travelbook.domain.models.response.TravelResponse;
import net.travelbook.domain.usecases.TravelUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RootController {

    private final TravelUseCase<TravelRequest, TravelResponse> travelUseCase;

    @RequestMapping({"/", "/home"})
    public String root(Model model)
    {
        var travels = travelUseCase.findByTopFiveTravelPosts();
        model.addAttribute("topFiveTravels", travels);

        return "index";
    }
}
