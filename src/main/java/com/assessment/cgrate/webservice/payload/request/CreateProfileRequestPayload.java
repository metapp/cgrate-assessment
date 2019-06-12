package com.assessment.cgrate.webservice.payload.request;

import com.assessment.cgrate.model.Profile;
import com.assessment.cgrate.webservice.payload.BaseRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProfileRequestPayload extends BaseRequest {

    private String firstName;
    private String lastName;
    private Profile.Gender gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dob;

    @Override
    protected String performValidation() {
        StringBuilder errors = new StringBuilder();
        if(StringUtils.isEmpty(firstName)) {
            errors.append(getNotNullOrEmptyErrorMessage("firstName"));
        }
        if(StringUtils.isEmpty(lastName)) {
            errors.append(getNotNullOrEmptyErrorMessage("lastName"));
        }
        if(gender == null) {
            errors.append(getNotNullMessage("gender"));
        }
        if(dob == null) {
            errors.append(getNotNullMessage("dob"));
        }
        return errors.toString();
    }

}
