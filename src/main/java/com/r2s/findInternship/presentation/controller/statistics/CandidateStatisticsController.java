package com.r2s.findInternship.presentation.controller.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.findInternship.domain.constant.ApiURL;
import com.r2s.findInternship.domain.service.CandidateService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.CANDIDATE_STATISTICS)
public class CandidateStatisticsController {
    @Autowired
    private CandidateService candidateService;
}