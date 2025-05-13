package com.r2s.findInternship.infracstructure.persistence.jpa;

import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.common.MessageResponse;
import com.r2s.findInternship.domain.entity.Schedule;
import com.r2s.findInternship.domain.repository.ScheduleRepository;
import com.r2s.findInternship.domain.service.ScheduleService;
import com.r2s.findInternship.infracstructure.exception.exception_v1.InternalServerErrorException;
import com.r2s.findInternship.infracstructure.exception.exception_v1.ResourceNotFoundException;
import com.r2s.findInternship.infracstructure.persistence.mapper.ScheduleMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public ScheduleDTO findById(Integer id) {
        return null;
    }

    @Override
    public List<ScheduleDTO> findAll() {
        return this.scheduleRepository.findAll().stream().map(item -> this.scheduleMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse create(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        if (scheduleRepository.existsByName(schedule.getName()))
            throw new InternalServerErrorException(
                    String.format("Exists major named %s", schedule.getName()));
        scheduleRepository.save(schedule);

        return new MessageResponse(HttpServletResponse.SC_OK, null, null);
    }

    @Override
    public MessageResponse deleteById(Integer id) {
        this.scheduleRepository.delete(this.scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "id",
                        String.valueOf(id))));

        return new MessageResponse(HttpServletResponse.SC_OK, null, null);
    }
}
