package br.com.omaisfood.service;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.WorkedWeek;
import br.com.omaisfood.repository.WorkedWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkedWeekService {
    @Autowired
    private WorkedWeekRepository workedDayRepository;

    @Autowired
    private CompanyService companyService;

    WorkedWeekService() { }

    WorkedWeekService(WorkedWeekRepository workedDayRepository, CompanyService companyService) {
        this.workedDayRepository = workedDayRepository;
        this.companyService = companyService;
    }

    public List<WorkedWeek> getWorkedDayByCompany(Long companyId) {
        Company company = this.companyService.findById(companyId);
        if(company != null){
            List<WorkedWeek> days = this.workedDayRepository.findByCompanyId(companyId);
            return days;
        }
        return null;
    }

    @Transactional
    public List<WorkedWeek> saveWorkedDays(Long companyId, List<WorkedWeek> workedDays) {


        return null;
    }
}
