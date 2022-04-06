package site.metacoding.projectshelter2.domain.shelter.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import site.metacoding.projectshelter2.domain.shelter.sheltertest.ShelterResponseDto;
import site.metacoding.projectshelter2.domain.shelter.sheltertest.ShelterTestDto;
import site.metacoding.projectshelter2.domain.shelter.sheltertest.ShelterTestRepository;

@RequiredArgsConstructor
@Service
public class ShelterTestService {

    private final ShelterTestRepository repository;

    @Transactional
    public List<ShelterTestDto> 다운로드(ShelterTestDto shelterTestDto) {
        List<ShelterTestDto> lists = new ArrayList<>();

        try {
            String key = "dmJX28HnrA9wKS9yRoXl2w9PhaYnhACCIE2AAh8TKcT0ouSflLuIbW3bxjjOcQVe2gCh9tClrm3bEdBorWN2pw%3D%3D";
            URI uri = new URI(
                    "http://openapi.animal.go.kr/openapi/service/rest/animalShelterSrvc/shelterInfo?&ServiceKey="
                            + key);
            RestTemplate restTemplate = new RestTemplate();

            ShelterResponseDto response = restTemplate.getForObject(uri, ShelterResponseDto.class);

            System.out.println(response);

            int respon = response.getResponse().getBody().getItems().getItem().size();

            List<ShelterResponseDto> shelterList = new ArrayList<>();

            for (int i = 0; i < respon; i++) {
                shelterList.add(response);
            }

            System.out.println(shelterList);

            for (int i = 0; i < shelterList.size(); i++) {
                ShelterTestDto result = new ShelterTestDto(
                        i,
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getCareNm(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getOrgNm(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getDivisionNm(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getSaveTrgtAnimal(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getCareAddr(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getJibunAddr(),
                        // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getLat(),
                        // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getLng(),
                        // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getDsignationDate(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getWeekOprEtime(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getWeekOprStime(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getWeekCellEtime(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getWeekCellStime(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getWeekendOprEtime(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getWeekendOprStime(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getWeekendCellEtime(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getWeekendCellStime(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getCloseDay(),
                        shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getCareTel()
                // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getMedicalCnt(),
                // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getQuarabtineCnt(),
                // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getFeedCnt(),
                // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getTransCarCnt(),
                // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getDataStdDt(),
                // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getVetPersonCnt(),
                // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getSpecsPersonCnt(),
                // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getBreedCnt(),
                // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).getRnum()
                // shelterList.get(i).getResponse().getBody().getItems().getItem().get(i).get()
                );
                lists.add(result);
            }
            System.out.println("사이즈 = " + shelterList.size());
            System.out.println(lists);

            List<ShelterTestDto> shelterEntity = repository.saveAll(lists);

            return shelterEntity;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
