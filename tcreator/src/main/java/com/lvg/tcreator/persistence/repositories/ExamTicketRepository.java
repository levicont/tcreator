package com.lvg.tcreator.persistence.repositories;

import com.lvg.tcreator.persistence.models.ExamDB;
import com.lvg.tcreator.persistence.models.ExamTicketDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamTicketRepository extends JpaRepository<ExamTicketDB, Long> {
    List<ExamTicketDB> getByExam(ExamDB exam);
}
