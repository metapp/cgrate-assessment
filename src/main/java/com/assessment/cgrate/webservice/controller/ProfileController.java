package com.assessment.cgrate.webservice.controller;

import com.assessment.cgrate.model.Profile;
import com.assessment.cgrate.service.interfaces.ProfileService;
import com.assessment.cgrate.webservice.payload.BaseResponse;
import com.assessment.cgrate.webservice.payload.request.CreateProfileRequestPayload;
import com.assessment.cgrate.webservice.payload.response.CreateProfileResponsePayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends BaseResponse> create(@RequestBody(required = false) CreateProfileRequestPayload payload) {
        if(payload != null && payload.isValid()) {
            try {
                Profile profile = profileService.create(payload);
                return ResponseEntity.ok(new CreateProfileResponsePayload(profile));
            } catch(Exception ex) {
                log.error("Error while creating profile: {}", ex);
                return ResponseEntity.status(500).body(getErrorObject(ex.getMessage()));
            }
        }
        return ResponseEntity.status(400).body(getErrorObject(payload));
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Profile>> list() {
        return ResponseEntity.ok(profileService.findAll());
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProfile(@PathVariable("id") Long id) {
        if(id > 0) {
            profileService.deleteProfile(id);
            return ResponseEntity.ok("profile delete successfully");
        }
        return ResponseEntity.status(400).body("profile id not found");
    }

    private CreateProfileResponsePayload getErrorObject(CreateProfileRequestPayload payload) {
        return getErrorObject(payload != null ? payload.getErrorMessage() : "missing payload in POST body");
    }

    private CreateProfileResponsePayload getErrorObject(String message) {
        return new CreateProfileResponsePayload(message);
    }

}
