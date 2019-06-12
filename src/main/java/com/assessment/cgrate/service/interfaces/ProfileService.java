package com.assessment.cgrate.service.interfaces;

import com.assessment.cgrate.exception.ServiceException;
import com.assessment.cgrate.model.Profile;
import com.assessment.cgrate.webservice.payload.request.CreateProfileRequestPayload;

import java.util.List;

public interface ProfileService {
    Profile create(CreateProfileRequestPayload payload) throws ServiceException;
    List<Profile> findAll() throws ServiceException;
    void deleteProfile(Long id) throws ServiceException;
}
