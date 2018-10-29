package br.com.omaisfood.service;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.WorkedDay;
import br.com.omaisfood.repository.WorkedDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkedDayService {
    @Autowired
    private WorkedDayRepository workedDayRepository;

    @Autowired
    private CompanyService companyService;

    WorkedDayService() { }

    WorkedDayService(WorkedDayRepository workedDayRepository, CompanyService companyService) {
        this.workedDayRepository = workedDayRepository;
        this.companyService = companyService;
    }
}
