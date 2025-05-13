package com.r2s.findInternship.application.dto.partner;

import com.r2s.findInternship.application.dto.JobDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerJobCareDTO implements Serializable {
    private Long id;
    private JobDTO jobDTO;
    private PartnerDTO partnerDTO;
}