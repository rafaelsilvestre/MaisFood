package br.com.omaisfood.repository;

import br.com.omaisfood.model.WorkedWeek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkedWeekRepository extends JpaRepository<WorkedWeek, Long> {

    List<WorkedWeek> findByCompanyId(Long companyId);
}
