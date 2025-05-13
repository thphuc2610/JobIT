package com.r2s.findInternship.infracstructure.persistence.jpa.hr;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.application.dto.HRApplicationDTO;
import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.HRApplication;
import com.r2s.findInternship.domain.repository.HRApplicationRepository;
import com.r2s.findInternship.domain.service.HRApplicationService;
import com.r2s.findInternship.domain.service.MailService;
import com.r2s.findInternship.domain.service.StatusService;
import com.r2s.findInternship.infracstructure.persistence.mapper.HRApplicationMapper;

@Service
@RequiredArgsConstructor
public class HRApplicationServiceImpl implements HRApplicationService {

    private final HRApplicationRepository hrApplicationRepository;
    private final MessageSource messageSource;
    private final HRApplicationMapper hrApplicationMapper;
    private final MailService mailService;
    public final static Logger LOGGER = LoggerFactory.getLogger("info");

    @Override
    public HRApplicationDTO findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<HRApplicationDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public PaginationDTO findAll(int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public PaginationDTO findAllActive(int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllActive'");
    }

    @Override
    public HRApplicationDTO create(HRApplicationDTO hrApplicationDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public HRApplicationDTO update(int id, HRApplicationDTO hrApplicationDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public HRApplication getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public boolean deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public MessageResponse deleteById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}
