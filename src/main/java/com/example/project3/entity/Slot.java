package com.example.project3.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "slots")
public class Slot {

    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "date")
    @JsonProperty("date")
    private LocalDate date;

    @Column(name = "start_time")
    @JsonProperty("start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    @JsonProperty("end_time")
    private LocalTime endTime;

    @Column(name = "total_seats")
    @JsonProperty("total_seats")
    private Long totalSeats;

    @Column(name = "available_seats")
    @JsonProperty("available_seats")
    private Long availableSeats;
}
