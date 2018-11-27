package br.com.omaisfood.service;

import br.com.omaisfood.model.*;
import br.com.omaisfood.model.enumerators.Permission;
import br.com.omaisfood.model.enumerators.TypeDay;
import br.com.omaisfood.repository.CompanyRepository;
import br.com.omaisfood.security.UserSecurity;
import br.com.omaisfood.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkedDayService workedDayService;

    @Autowired
    private FilterItemService filterItemService;

    CompanyService(CompanyRepository companyRepository, UserService userService){
        this.companyRepository = companyRepository;
        this.userService = userService;
    }

    public List<Company> getAllCompanies(){
        return this.companyRepository.findAllByOrderByIdDesc();
    }

    public Company saveCompany(Company company, User user) {
        if(this.userService.existsByEmail(user.getEmail()))
            throw new EmailExistsException("Já existe uma conta com este email");

        user.setPermissions(Permission.COMPANY);
        User newUser = this.userService.saveUser(user);
        if(newUser.getId() == null)
            throw new ErrorCreatingUserException("Erro ao criar um usuário e associar a empresa");

        company.setUser(newUser);
        // Save this company
        Company c = this.companyRepository.save(company);

        List<WorkedDay> workedDays = new ArrayList<WorkedDay>();
        workedDays.add(new WorkedDay(TypeDay.SUNDAY, "08:00", "18:00", false, c));
        workedDays.add(new WorkedDay(TypeDay.MONDAY, "08:00", "18:00", true, c));
        workedDays.add(new WorkedDay(TypeDay.TUESDAY, "08:00", "18:00", true, c));
        workedDays.add(new WorkedDay(TypeDay.WEDNESDAY, "08:00", "18:00", true, c));
        workedDays.add(new WorkedDay(TypeDay.THURSDAY, "08:00", "18:00", true, c));
        workedDays.add(new WorkedDay(TypeDay.FRIDAY, "08:00", "18:00", true, c));
        workedDays.add(new WorkedDay(TypeDay.SATURDAY, "08:00", "18:00", false, c));
        this.workedDayService.saveWorkedDay(workedDays, c.getId());

        return company;
    }

    public Company saveCompany(Company company) {
        return this.companyRepository.save(company);
    }

    public void deleteCompany(Company company) {
        try {
            this.companyRepository.delete(company);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir a empresa");
        }
    }

    public Company findById(Long id){
        Boolean isExists = this.companyRepository.existsById(id);
        if(!isExists)
            throw new ObjectNotFoundException("Empresa não encontrada");

        Company company = this.companyRepository.findById(id).get();
        return this.verifyCompanyIsOpened(company);
    }

    public Company getCompanyByUserLogged() {
        UserSecurity userLogged = UserService.getUserAuthenticated();
        User user = this.userService.findById(userLogged.getId());

        if(!userLogged.hasRole(Permission.COMPANY) || user.getCompany().getId() == null)
            throw new BadRequestException("Erro ao buscar empresa");

        return this.companyRepository.findById(user.getCompany().getId()).get();
    }

    public Company updateCompany(Company newCompany, List<Filter> filters, Long companyId) {
        UserSecurity userLogged = UserService.getUserAuthenticated();

        Boolean companyExists = this.companyRepository.existsById(companyId);
        if(!companyExists)
            throw new BadRequestException("Error fetching company");

        Company company = this.companyRepository.findById(companyId).get();

        if(userLogged == null || !userLogged.hasRole(Permission.ADMIN) && !company.getUser().getId().equals(userLogged.getId()))
            throw new PermissionDaniedException("Permission danied");

        company.setName(newCompany.getName());
        company.setDescription(newCompany.getDescription());
        company.setMinimumValue(newCompany.getMinimumValue());

        this.filterItemService.removeFilterItem(companyId);

        filters.forEach(filter -> {
            Boolean exists = this.filterItemService.filterExists(companyId, filter.getId());
            // Save this Filte in company
            if(!exists) {
                this.filterItemService.save(new FilterItem(filter, company));
            }
        });

        return this.companyRepository.save(company);
    }

    public Company getCompanyByUserId(Long userId) {
        Boolean companyExists = this.companyRepository.existsByUserId(userId);
        if(!companyExists){
            return null;
        }

        Company company = this.companyRepository.getCompanyByUserId(userId);
        return this.verifyCompanyIsOpened(company);
    }

    public List<Company> getCompanyByDistrinct(Long distrinctId) {
        List<Company> companies = this.companyRepository.findByDistricts_DistrictId(distrinctId);

        for(int i = 0; i < companies.size(); i++){
            Company company = companies.get(i);
            Calendar calendar = Calendar.getInstance();

            WorkedDay workedDay = null;
            switch (calendar.get(Calendar.DAY_OF_WEEK)){
                case 1:
                    workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.SUNDAY, company.getId());
                    break;
                case 2:
                    workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.MONDAY, company.getId());
                    break;
                case 3:
                    workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.TUESDAY, company.getId());
                    break;
                case 4:
                    workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.WEDNESDAY, company.getId());
                    break;
                case 5:
                    workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.THURSDAY, company.getId());
                    break;
                case 6:
                    workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.FRIDAY, company.getId());
                    break;
                case 7:
                    workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.SATURDAY, company.getId());
                    break;
            }

            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);

            try {
                String a = String.format("%s%s%s %s", day, month, year, workedDay.getStartTime());
                String b = String.format("%s%s%s %s", day, month, year, workedDay.getEndTime());
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy hh:mm");

                Date now = new Date();
                Date startTime = new Date(format.parse(a).getTime());
                Date endTime = new Date(format.parse(b).getTime());

                if(now.after(startTime) && now.before(endTime)){
                    if(workedDay.isEnabled()){
                        companies.get(i).setOpened(true);
                    }
                }else{
                    companies.get(i).setOpened(false);
                }
            } catch (ParseException e) {
                companies.get(i).setOpened(false);
            }
        }

        return companies;
    }

    public Company verifyCompanyIsOpened(Company company) {
        Calendar calendar = Calendar.getInstance();

        WorkedDay workedDay = null;
        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case 1:
                workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.SUNDAY, company.getId());
                break;
            case 2:
                workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.MONDAY, company.getId());
                break;
            case 3:
                workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.TUESDAY, company.getId());
                break;
            case 4:
                workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.WEDNESDAY, company.getId());
                break;
            case 5:
                workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.THURSDAY, company.getId());
                break;
            case 6:
                workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.FRIDAY, company.getId());
                break;
            case 7:
                workedDay = this.workedDayService.getWorkedDayByDayAndCompanyId(TypeDay.SATURDAY, company.getId());
                break;
        }

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        try {
            String a = String.format("%s%s%s %s", day, month, year, workedDay.getStartTime());
            String b = String.format("%s%s%s %s", day, month, year, workedDay.getEndTime());
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy hh:mm");

            Date now = new Date();
            Date startTime = new Date(format.parse(a).getTime());
            Date endTime = new Date(format.parse(b).getTime());

            if(now.after(startTime) && now.before(endTime)){
                if(workedDay.isEnabled()){
                    company.setOpened(true);
                }
            }else{
                company.setOpened(false);
            }
        } catch (ParseException e) {
            company.setOpened(false);
        } catch (NullPointerException e) {
            company.setOpened(false);
        }

        return company;
    }
}
