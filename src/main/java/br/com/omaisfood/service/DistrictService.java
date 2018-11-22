package br.com.omaisfood.service;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.District;
import br.com.omaisfood.model.DistrictItem;
import br.com.omaisfood.repository.DistrictItemRepository;
import br.com.omaisfood.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictItemRepository districtItemRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired UserService userService;

    public List<District> getAllDistricts() {
        return this.districtRepository.findAll();
    }

    @Transactional
    public District saveDistrictItem(District district, Long companyId) {
        Company company = this.companyService.findById(companyId);

        Boolean existsDistrict = this.districtItemRepository.existsByCompanyIdAndDistrictId(company.getId(), district.getId());

        if(existsDistrict)
            return district;

        return this.districtItemRepository.save(new DistrictItem(district, company)).getDistrict();
    }

    public List<District> getDistrictsByCompany(Long companyId) {
        Company company = this.companyService.findById(companyId);

        List<District> districts = new ArrayList<District>();
        List<DistrictItem> districtItems = this.districtItemRepository.findAllByCompanyId(company.getId());

        districtItems.forEach(districtItem -> districts.add(districtItem.getDistrict()));

        return districts;
    }

    public void removeDistrictItem(District district, Long companyId) {
        Company company = this.companyService.findById(companyId);

        Boolean existsDistrict = this.districtItemRepository.existsByCompanyIdAndDistrictId(company.getId(), district.getId());

        if(!existsDistrict){
            // not exists
        }

        DistrictItem districtItem = this.districtItemRepository.findDistrictItemByCompanyIdAndDistrictId(company.getId(),
                district.getId());

        this.districtItemRepository.delete(districtItem);
    }
}
