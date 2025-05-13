package com.r2s.findInternship.application.dto;

import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MajorDTO implements Serializable {
	private int id;
	private String name;
}