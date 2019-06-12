package com.assessment.cgrate.util;

import com.assessment.cgrate.model.Profile;
import com.assessment.cgrate.webservice.payload.request.CreateProfileRequestPayload;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileFactory {

    public static Profile create(CreateProfileRequestPayload payload) {
        Assert.notNull(payload, "parameter [payload] cannot be null");
        return new Profile(payload.getFirstName(), payload.getLastName(), payload.getGender(), payload.getDob());
    }

}
