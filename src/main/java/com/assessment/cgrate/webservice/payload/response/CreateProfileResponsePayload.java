package com.assessment.cgrate.webservice.payload.response;

import com.assessment.cgrate.model.Profile;
import com.assessment.cgrate.webservice.payload.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProfileResponsePayload extends BaseResponse {

    private Profile profile;

    public CreateProfileResponsePayload(String error) {
        super(error);
    }
}
