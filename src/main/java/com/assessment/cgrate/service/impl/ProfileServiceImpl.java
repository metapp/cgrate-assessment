package com.assessment.cgrate.service.impl;

import com.assessment.cgrate.exception.ServiceException;
import com.assessment.cgrate.model.Profile;
import com.assessment.cgrate.repository.ProfileRepository;
import com.assessment.cgrate.service.interfaces.ProfileService;
import com.assessment.cgrate.util.ProfileFactory;
import com.assessment.cgrate.webservice.payload.request.CreateProfileRequestPayload;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository repository;

    @Override
    public Profile create(CreateProfileRequestPayload payload) throws ServiceException {
        try {
            Assert.notNull(payload, "parameter [payload] cannot be null");
            Profile profile = ProfileFactory.create(payload);
            return repository.save(profile);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Profile> findAll() throws ServiceException {
        try {
            Iterable<Profile> profiles = repository.findAll();
            return IteratorUtils.toList(profiles.iterator());
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void deleteProfile(Long id) throws ServiceException {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
