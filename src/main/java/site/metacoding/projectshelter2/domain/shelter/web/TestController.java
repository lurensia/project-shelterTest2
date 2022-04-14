package site.metacoding.projectshelter2.domain.shelter.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.projectshelter2.domain.shelter.service.ShelterTestService;
import site.metacoding.projectshelter2.domain.shelter.sheltertest.ShelterTestDto;

@RequiredArgsConstructor
@Controller
public class TestController {

    private final ShelterTestService tService;

    @GetMapping("/test")
    public String testDownload(ShelterTestDto shelterTestDto, Model model) {

        List<ShelterTestDto> shelterEntity = tService.다운로드(shelterTestDto);

        model.addAttribute("shelterlist", shelterEntity);

        return "/shelterTestDownload";

    }
}
