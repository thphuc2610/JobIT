package com.r2s.findInternship.infracstructure.persistence.jpa;

import com.r2s.findInternship.application.dto.PaginationDTO;
import com.r2s.findInternship.application.dto.RateDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.service.CompanyService;
import com.r2s.findInternship.domain.service.RateService;
import com.r2s.findInternship.domain.service.StatusService;
import com.r2s.findInternship.domain.service.UserService;
import com.r2s.findInternship.infracstructure.persistence.mapper.RateMapper;
import com.r2s.findInternship.infracstructure.persistence.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final MessageSource messageSource;
    private final RateMapper rateMapper;
    private final UserMapper userMapper;
    private final StatusService statusService;
    private final UserService userService;
    private final CompanyService companyService;
    public final static Logger LOGGER = LoggerFactory.getLogger("info");

    @Override
    public List<RateDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<RateDTO> findAllActive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllActive'");
    }

    @Override
    public RateDTO findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public RateDTO findByCompanyIdAndUsername(int companyId, String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCompanyIdAndUsername'");
    }

    @Override
    public RateDTO findActiveByUsernameAndCompanyId(int companyId, String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findActiveByUsernameAndCompanyId'");
    }

    @Override
    public PaginationDTO findAllByCompanyId(int companyId, int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByCompanyId'");
    }

    @Override
    public RateDTO create(RateDTO rateDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public RateDTO update(int id, RateDTO rateDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public MessageResponse deleteById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public MessageResponse recoverById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recoverById'");
    }
}