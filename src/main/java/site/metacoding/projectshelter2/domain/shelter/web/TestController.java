package site.metacoding.projectshelter2.domain.shelter.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.projectshelter2.domain.shelter.service.ShelterTestService;
import site.metacoding.projectshelter2.domain.shelter.shelterdto.ShelterDto;
import site.metacoding.projectshelter2.domain.shelter.shelterdto.ShelterService;
import site.metacoding.projectshelter2.domain.shelter.sheltertest.ShelterTestDto;

@RequiredArgsConstructor
@Controller
public class TestController {

    private final ShelterService service;
    private final ShelterTestService tService;

    @GetMapping("/")
    public String Download(ShelterDto shelterDto, Model model) {

        List<ShelterDto> shelterEntity = service.download(shelterDto);

        System.out.println("받은 엔티티" + shelterEntity);

        model.addAttribute("list", shelterEntity);

        return "/shelterDownload";
    }

    @GetMapping("/test")
    public String testDownload(ShelterTestDto shelterTestDto, Model model) {

        List<ShelterTestDto> shelterEntity = tService.다운로드(shelterTestDto);

        model.addAttribute("shelterlist", shelterEntity);

        return "/shelterTestDownload";

    }
}
