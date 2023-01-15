package com.shafiqul.studentapi.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "uuid")
	@GeneratedValue(strategy = GenerationType.UUID)
  private UUID uuid;

	@Column(name = "name")
	private String name;

	@Column(name = "address", length = 600)
	private String address;
}
