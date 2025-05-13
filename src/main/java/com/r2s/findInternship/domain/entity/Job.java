package com.r2s.findInternship.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.auto.value.AutoValue.Builder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;

@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "job")
public class Job extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title", nullable = false, length = 225)
    private String title;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @EqualsAndHashCode.Exclude
    private List<JobMajor> jobMajors = new ArrayList<>();

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @EqualsAndHashCode.Exclude
    private List<JobPosition> jobPositions = new ArrayList<>();

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @EqualsAndHashCode.Exclude
    private List<JobSchedule> jobSchedules = new ArrayList<>();

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "posting_date", nullable = false)
    private Date postingDate;

    @Column(name = "application_deadline", nullable = false)
    private Date applicationDeadline;

    @Column(name = "min_allowance")
    private Double minAllowance;

    @Column(name = "max_allowance")
    private Double maxAllowance;

    @Column(name = "no_allowance", nullable = false)
    private boolean noAllowance;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description", length = 5000, nullable = false)
    private String description;

    @Column(name = "requirements", length = 5000)
    private String requirements;

    @Column(name = "benefits")
    private String benefits;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "job", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Fetch(FetchMode.SUBSELECT)
    private List<CandidateApplication> candidateApplications = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hr_id")
    private HR hr;
}