package com.r2s.findInternship.domain.service;

import java.util.List;

import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.domain.entity.Schedule;

public interface JobTypeService {

	boolean deleteById(Integer id);

	boolean existsById(Integer id);

	ScheduleDTO findById(Integer id);

	List<ScheduleDTO> findAll();

	ScheduleDTO update(int id, ScheduleDTO jobTypeDTO);

	Schedule getById(Integer id);

	ScheduleDTO create(ScheduleDTO jobTypeDTO);

	boolean existsByName(String name);
}