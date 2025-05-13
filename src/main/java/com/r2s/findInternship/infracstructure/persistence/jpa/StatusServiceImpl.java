package com.r2s.findInternship.infracstructure.persistence.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.application.dto.StatusDTO;
import com.r2s.findInternship.domain.common.enumeration.Estatus;
import com.r2s.findInternship.domain.entity.Status;
import com.r2s.findInternship.domain.repository.StatusRepository;
import com.r2s.findInternship.domain.service.StatusService;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import com.r2s.findInternship.infracstructure.persistence.mapper.StatusMapper;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;

    @Override
    public StatusDTO findById(int id) {
        return statusMapper.toDTO(
                statusRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Status", "id", String.valueOf(id))));
    }

    @Override
    public List<StatusDTO> findAll() {
        return statusRepository.findAll().stream().map(r -> statusMapper.toDTO(r)).collect(Collectors.toList());
    }

    @Override
    public Status findByName(Estatus e) {
        Status status = null;
        switch (e) {
            case Active:
                status = this.statusRepository.findById(Estatus.activeStatus)
                        .orElseThrow(() -> new ResourceNotFoundException("Status", "name", "Active"));
                break;

            case Delete:
                status = this.statusRepository.findById(Estatus.deleteStatus)
                        .orElseThrow(() -> new ResourceNotFoundException("Status", "name", "delete"));
                break;

            case Lock:
                status = this.statusRepository.findById(Estatus.lockStatus)
                        .orElseThrow(() -> new ResourceNotFoundException("Status", "name", "lock"));
                break;

            case Disable:
                status = this.statusRepository.findById(Estatus.disableStatus)
                        .orElseThrow(() -> new ResourceNotFoundException("Status", "name", "Not disable"));
                break;

            default:
                status = this.statusRepository.findById(Estatus.notActiveStatus)
                        .orElseThrow(() -> new ResourceNotFoundException("Status", "name", "Not Available"));
                break;
        }

        return status;
    }

    @Override
    public StatusDTO findByName(String name) {
        return statusMapper.toDTO(
                statusRepository.findByName(name)
                        .orElseThrow(() -> new ResourceNotFoundException("Status", "name", name)));

    }

    @Override
    public Status findByNameEntity(String name) {
        return statusRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Status", "name", name));
    }

    @PostConstruct
    public void init() {
        List<Status> statusToSave = new ArrayList<>();
        for (Estatus eStatus : Estatus.values()) {
            if (!statusRepository.existsByName(eStatus.toString())) {
                Status status = new Status();
                status.setName(eStatus.toString());
                statusToSave.add(status);
            }
        }

        if (!statusToSave.isEmpty()) {
            statusRepository.saveAll(statusToSave);
        }
    }
}