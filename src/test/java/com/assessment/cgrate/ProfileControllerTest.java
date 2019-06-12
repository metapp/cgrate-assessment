package com.assessment.cgrate;

import com.assessment.cgrate.model.Profile;
import com.assessment.cgrate.service.interfaces.ProfileService;
import com.assessment.cgrate.webservice.controller.ProfileController;
import com.assessment.cgrate.webservice.payload.BaseResponse;
import com.assessment.cgrate.webservice.payload.request.CreateProfileRequestPayload;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.assessment.cgrate.model.Profile.Gender.MALE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = ProfileController.class)
public class ProfileControllerTest {

    @Autowired
    private ProfileController controller;
    @MockBean
    private ProfileService profileService;

    @Test
    public void createProfileTest() {
        when(profileService.create(any(CreateProfileRequestPayload.class))).thenReturn(getTestProfile());
        final ResponseEntity<? extends BaseResponse> response = controller.create(getPayload());
        Assert.assertEquals(200, response.getStatusCodeValue());
        verify(profileService, atLeastOnce()).create(any(CreateProfileRequestPayload.class));
    }

    @Test
    public void createProfileWithBadRequestTest() {
        final ResponseEntity<? extends BaseResponse> response = controller.create(new CreateProfileRequestPayload());
        Assert.assertEquals(400, response.getStatusCodeValue());
        verify(profileService, never()).create(any(CreateProfileRequestPayload.class));
    }

    @Test
    public void createProfileWithExceptionTest() {
        when(profileService.create(any(CreateProfileRequestPayload.class))).thenThrow(RuntimeException.class);
        final ResponseEntity<? extends BaseResponse> response = controller.create(getPayload());
        Assert.assertEquals(500, response.getStatusCodeValue());
        verify(profileService, atLeastOnce()).create(any(CreateProfileRequestPayload.class));
    }

    @Test
    public void listAllProfilesTest() {
        when(profileService.findAll()).thenReturn(new ArrayList<>());
        final ResponseEntity<List<Profile>> response = controller.list();
        Assert.assertEquals(200, response.getStatusCodeValue());
        verify(profileService, atLeastOnce()).findAll();
    }

    @Test
    public void deleteProfileSuccessfullyTest() {
        doNothing().when(profileService).deleteProfile(anyLong());
        final ResponseEntity<String> response = controller.deleteProfile(1L);
        Assert.assertEquals(200, response.getStatusCodeValue());
        verify(profileService, atLeastOnce()).deleteProfile(anyLong());
    }

    @Test
    public void deleteProfileWithBadRequestTest() {
        when(profileService.findAll()).thenReturn(new ArrayList<>());
        final ResponseEntity<String> response = controller.deleteProfile(0L);
        Assert.assertEquals(400, response.getStatusCodeValue());
        verify(profileService, never()).deleteProfile(anyLong());
    }

    private CreateProfileRequestPayload getPayload() {
        final CreateProfileRequestPayload payload = new CreateProfileRequestPayload();
        payload.setFirstName("John");
        payload.setLastName("Doe");
        payload.setDob(new Date());
        payload.setGender(MALE);
        return payload;
    }

    private Profile getTestProfile() {
        final Profile profile = new Profile();
        profile.setId(1L);
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setDob(new Date());
        profile.setGender(MALE);
        return profile;
    }

}
