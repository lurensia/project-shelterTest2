package site.metacoding.projectshelter2.domain.shelter.shelterdto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.projectshelter2.domain.shelter.shelterdto.Shelter.Response.Body.Items.Item;

@RequiredArgsConstructor
@Service
public class ShelterService {

    private final ShelterRepository repository;

    @Transactional
    public List<ShelterDto> download(ShelterDto shelterDto) {

        List<ShelterDto> shelterList = new ArrayList<>();

        try {
            StringBuffer sb = new StringBuffer();

            sb.append("http://openapi.animal.go.kr/openapi/service/rest/animalShelterSrvc/shelterInfo?serviceKey=");
            sb.append(
                    "dmJX28HnrA9wKS9yRoXl2w9PhaYnhACCIE2AAh8TKcT0ouSflLuIbW3bxjjOcQVe2gCh9tClrm3bEdBorWN2pw%3D%3D");
            sb.append("&_type=json");
            URL url = new URL(sb.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

            StringBuilder responseJson = new StringBuilder();

            String response;
            while (true) {
                response = br.readLine();

                if (response == null) {
                    break;
                }
                responseJson = responseJson.append(response);
            }
            Gson gson = new Gson();
            Shelter responseDto = gson.fromJson(responseJson.toString(), Shelter.class);
            System.out.println(responseDto.getResponse().getBody().getItems().getItem().size());
            List<Item> result = responseDto.getResponse().getBody().getItems().getItem();
            for (int i = 0; i < result.size(); i++) {
                ShelterDto items = new ShelterDto(
                        i,
                        result.get(i).getCareNm(),
                        result.get(i).getOrgNm(),
                        result.get(i).getDivisionNm(),
                        result.get(i).getSaveTrgtAnimal(),
                        result.get(i).getCareAddr(),
                        result.get(i).getJibunAddr(),
                        // result.get(i).getLat(),
                        // result.get(i).getLng(),
                        // result.get(i).getDsignationDate(),
                        result.get(i).getWeekOprEtime(),
                        result.get(i).getWeekOprStime(),
                        result.get(i).getWeekCellEtime(),
                        result.get(i).getWeekCellStime(),
                        result.get(i).getWeekendOprEtime(),
                        result.get(i).getWeekendOprStime(),
                        result.get(i).getWeekendCellEtime(),
                        result.get(i).getWeekendCellStime(),
                        result.get(i).getCloseDay(),
                        result.get(i).getCareTel()
                // result.get(i).getMedicalCnt(),
                // result.get(i).getQuarabtineCnt(),
                // result.get(i).getFeedCnt(),
                // result.get(i).getTransCarCnt(),
                // result.get(i).getDataStdDt(),
                // result.get(i).getVetPersonCnt(),
                // result.get(i).getSpecsPersonCnt(),
                // result.get(i).getBreedCnt(),
                // result.get(i).getRnum();
                );
                shelterList.add(items);
            }
            System.out.println("저장된 리스트" + shelterList);
        } catch (Exception e) {
            System.out.println("실패" + e.getMessage());
        }
        List<ShelterDto> shelterEntity = repository.saveAll(shelterList);
        System.out.println("받은 엔티티 = " + shelterEntity);
        return null;
    }
}
