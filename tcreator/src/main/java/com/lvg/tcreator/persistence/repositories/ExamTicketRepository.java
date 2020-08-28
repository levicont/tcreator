package com.lvg.tcreator.persistence.repositories;

import com.lvg.tcreator.persistence.models.ExamTicketDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamTicketRepository extends JpaRepository<ExamTicketDB, Long> {
}
