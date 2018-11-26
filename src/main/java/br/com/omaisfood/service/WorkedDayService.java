package br.com.omaisfood.service;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.WorkedDay;
import br.com.omaisfood.model.enumerators.Permission;
import br.com.omaisfood.model.enumerators.TypeDay;
import br.com.omaisfood.repository.WorkedDayRepository;
import br.com.omaisfood.security.UserSecurity;
import br.com.omaisfood.service.exception.BadRequestException;
import br.com.omaisfood.service.exception.PermissionDaniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkedDayService {
    @Autowired
    private WorkedDayRepository workedDayRepository;

    @Autowired
    private CompanyService companyService;

    public List<WorkedDay> getWorkedDaysByCompanyId(Long companyId) {
        List<WorkedDay> workedDays = this.workedDayRepository.getWorkedDayByCompanyId(companyId);

        return workedDays;
    }

    public List<WorkedDay> saveWorkedDay(List<WorkedDay> workedDays, Long companyId) {
        if(workedDays.size() < 7){
            throw new BadRequestException("Please report every day of the week");
        }

        UserSecurity userLogged = UserService.getUserAuthenticated();
        Company company = this.companyService.findById(companyId);
        if(userLogged == null || !userLogged.hasRole(Permission.ADMIN) &&
                !company.getUser().getId().equals(userLogged.getId())){
            throw new PermissionDaniedException("Permission danied");
        }

        List<WorkedDay> currentDays = this.verifyWorkedDays(workedDays, company);
        if(currentDays == null){
            throw new BadRequestException("Some of the dates may be wrong or do not exist");
        }

        try{
            if(company.getWorkedDays().size() == 7){
                currentDays.forEach(workedDay -> {
                    WorkedDay day = this.workedDayRepository.getWorkedDayByDayAndCompanyId(workedDay.getDay(), companyId);
                    if(day != null) workedDay.setId(day.getId());
                });
            }
        }catch (NullPointerException e){ }

        return this.workedDayRepository.saveAll(currentDays);
    }

    private List<WorkedDay> verifyWorkedDays(List<WorkedDay> workedDays, Company company) {
        WorkedDay[] days = new WorkedDay[7];
        int daysFound = 0;
        for(WorkedDay workedDay: workedDays){
            if(workedDay.getStartTime() == null && workedDay.getEndTime() == null){
                continue;
            }

            Integer startTime = Integer.parseInt(workedDay.getStartTime().substring(0, 2));
            Integer endTime = Integer.parseInt(workedDay.getEndTime().substring(0, 2));

            if(workedDay.getDay().equals(TypeDay.SUNDAY) && days[0] == null) {
                if(startTime >= endTime) continue;

                workedDay.setCompany(company);
                days[0] = workedDay;
                daysFound++;
            }

            if(workedDay.getDay().equals(TypeDay.MONDAY) && days[1] == null) {
                if(startTime >= endTime) continue;

                workedDay.setCompany(company);
                days[1] = workedDay;
                daysFound++;
            }

            if(workedDay.getDay().equals(TypeDay.TUESDAY) && days[2] == null) {
                if(startTime >= endTime) continue;

                workedDay.setCompany(company);
                days[2] = workedDay;
                daysFound++;
            }

            if(workedDay.getDay().equals(TypeDay.WEDNESDAY) && days[3] == null) {
                if(startTime >= endTime) continue;

                workedDay.setCompany(company);
                days[3] = workedDay;
                daysFound++;
            }

            if(workedDay.getDay().equals(TypeDay.THURSDAY) && days[4] == null) {
                if(startTime >= endTime) continue;

                workedDay.setCompany(company);
                days[4] = workedDay;
                daysFound++;
            }

            if(workedDay.getDay().equals(TypeDay.FRIDAY) && days[5] == null) {
                if(startTime >= endTime) continue;

                workedDay.setCompany(company);
                days[5] = workedDay;
                daysFound++;
            }

            if(workedDay.getDay().equals(TypeDay.SATURDAY) && days[6] == null) {
                if(startTime >= endTime) continue;

                workedDay.setCompany(company);
                days[6] = workedDay;
                daysFound++;
            }
        }

        if(daysFound < 7) return null;

        return workedDays;
    }

    public WorkedDay getWorkedDayByDayAndCompanyId(TypeDay day, Long companyId) {
        return this.workedDayRepository.getWorkedDayByDayAndCompanyId(day, companyId);
    }
}
