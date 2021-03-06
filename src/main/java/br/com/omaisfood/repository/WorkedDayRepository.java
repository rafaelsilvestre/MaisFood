package br.com.omaisfood.repository;

import br.com.omaisfood.model.WorkedDay;
import br.com.omaisfood.model.enumerators.TypeDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkedDayRepository extends JpaRepository<WorkedDay, Long> {
    List<WorkedDay> getWorkedDayByCompanyId(Long companyId);

    WorkedDay getWorkedDayByDayAndCompanyId(TypeDay day, Long companyId);
}
