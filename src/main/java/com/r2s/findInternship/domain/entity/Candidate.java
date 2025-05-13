package com.r2s.findInternship.domain.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.auto.value.AutoValue.Builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "candidate")
public class Candidate extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id")
	private University university;

	@Column(name = "CV", length = 500)
	private String CV;

	@Column(name = "reference_letter", length = 5000)
	private String referenceLetter;

	@Column(name = "desired_job", length = 1000)
	private String desiredJob;

	@Column(name = "desired_working_province", length = 255)
	private String desiredWorkingProvince;

	@OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<CandidateMajor> candidateMajors = new ArrayList<>();

	@OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<CandidatePosition> candidatePositions = new ArrayList<>();

	@OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<CandidateSchedule> candidateSchedules = new ArrayList<>();

	@Column(name = "searchable")
	boolean searchable; // permit hr to search
}