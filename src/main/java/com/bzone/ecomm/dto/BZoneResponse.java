package com.bzone.ecomm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BZoneResponse implements Serializable {

    private String code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object errors;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
