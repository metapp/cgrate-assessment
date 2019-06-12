package com.assessment.cgrate;

import com.assessment.cgrate.exception.ServiceException;
import com.assessment.cgrate.model.Profile;
import com.assessment.cgrate.repository.ProfileRepository;
import com.assessment.cgrate.service.impl.ProfileServiceImpl;
import com.assessment.cgrate.webservice.payload.request.CreateProfileRequestPayload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;

import static com.assessment.cgrate.model.Profile.Gender.MALE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProfileServiceImpl.class)
public class ProfileServiceImplTest {

    @Autowired
    private ProfileServiceImpl profileService;
    @MockBean
    private ProfileRepository repository;

    @Test
    public void createProfileSuccessfullyTest() {
        when(repository.save(any(Profile.class))).thenReturn(getTestProfile());
        profileService.create(getPayload());
        Mockito.verify(repository, atLeastOnce()).save(Mockito.any(Profile.class));
    }

    @Test(expected = ServiceException.class)
    public void createProfileWithExceptionTest() {
        profileService.create(null);
        Mockito.verify(repository, never()).save(Mockito.any(Profile.class));
    }

    @Test
    public void findAllSuccessfullyTest() {
        when(repository.findAll()).thenReturn(new ArrayList());
        profileService.findAll();
        Mockito.verify(repository, atLeastOnce()).findAll();
    }

    @Test(expected = ServiceException.class)
    public void findAllWithExceptionTest() {
        when(repository.findAll()).thenThrow(new RuntimeException());
        profileService.findAll();
    }

    @Test
    public void deleteProfileSuccessfullyTest() {
        doNothing().when(repository).deleteById(anyLong());
        profileService.deleteProfile(1L);
        Mockito.verify(repository, atLeastOnce()).deleteById(anyLong());
    }

    @Test(expected = ServiceException.class)
    public void deleteProfileWithExceptionTest() {
        Mockito.doThrow(new RuntimeException()).when(repository).deleteById(anyLong());
        profileService.deleteProfile(1L);
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
