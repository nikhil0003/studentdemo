package com.example.project3.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false, unique = true)
    @JsonProperty("student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    @JsonProperty("slot_id")
    private Slot slot;
}
