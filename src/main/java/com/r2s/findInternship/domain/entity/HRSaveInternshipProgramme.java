package com.r2s.findInternship.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "hr_saved_internship_programme")
public class HRSaveInternshipProgramme implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "internship_programme_id")
    private InternshipProgramme internshipProgramme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hr_id")
    private HR hr;
}