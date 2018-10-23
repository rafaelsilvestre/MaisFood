package br.com.omaisfood.repository;

import br.com.omaisfood.model.WorkedDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkedDayRepository extends JpaRepository<WorkedDay, Long> {

    List<WorkedDay> findByCompanyId(Long companyId);
}
