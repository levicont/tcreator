package com.lvg.tcreator.persistence.repositories;

import com.lvg.tcreator.persistence.models.QuestionDB;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<QuestionDB, Long> {

}
