package site.metacoding.projectshelter2.domain.shelter.web;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.projectshelter2.domain.shelter.service.ShelterTestService;

@RequiredArgsConstructor
@RestController
public class TestApiController {

    private final ShelterTestService service;

}
