package com.example.project3.repository;

import com.example.project3.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository  extends JpaRepository<Slot, Long> {
}
