package site.metacoding.projectshelter2.domain.shelter.shelterdto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Shelter {
    private Response response;

    @AllArgsConstructor
    @Data
    class Response {
        private Header Header;
        private Body Body;

        @AllArgsConstructor
        @Data
        class Header {
            private int resultCode;
            private String resultMsg;
        }

        @AllArgsConstructor
        @Data
        class Body {
            private Items items;
            private Integer numOfRows;
            private Integer pageNo;
            private Integer totalCount;

            @AllArgsConstructor
            @Data
            class Items {
                private List<Item> item;

                @AllArgsConstructor
                @Data
                class Item {
                    private String careNm;
                    private String orgNm;
                    private String divisionNm;
                    private String saveTrgtAnimal;
                    private String careAddr;
                    private String jibunAddr;
                    private String lat;
                    private String lng;
                    private String dsignationDate;
                    private String weekOprEtime;
                    private String weekOprStime;
                    private String weekCellEtime;
                    private String weekCellStime;
                    private String weekendOprEtime;
                    private String weekendOprStime;
                    private String weekendCellEtime;
                    private String weekendCellStime;
                    private String closeDay;
                    private String medicalCnt;
                    private String quarabtineCnt;
                    private String feedCnt;
                    private String transCarCnt;
                    private String careTel;
                    private String dataStdDt;
                    private Integer vetPersonCnt;
                    private Integer specsPersonCnt;
                    private Integer breedCnt;
                    private Integer rnum;
                }
            }
        }
    }
}