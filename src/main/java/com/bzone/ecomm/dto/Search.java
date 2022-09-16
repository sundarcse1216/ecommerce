package com.bzone.ecomm.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Search {

    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String productName;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private String brand;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double price;
}
