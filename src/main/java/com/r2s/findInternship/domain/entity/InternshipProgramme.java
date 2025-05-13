package com.r2s.findInternship.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "internship_programme")
public class InternshipProgramme extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "title")
	private String title;

	@OneToMany(mappedBy = "internshipProgramme", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<InternshipMajor> internshipMajors = new ArrayList<>();

	@OneToMany(mappedBy = "internshipProgramme", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<InternshipPosition> internshipPositions = new ArrayList<>();

	@OneToMany(mappedBy = "internshipProgramme", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<InternshipSchedule> internshipSchedules = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id")
	private University university;

	@Column(name = "amount")
	private long amount;

	@Column(name = "students", length = 1000)
	private String students;

	@Column(name = "recommendation", length = 5000)
	private String recommendation;

	@Column(name = "location")
	private String location;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
}