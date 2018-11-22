package br.com.omaisfood.repository;

import br.com.omaisfood.model.District;
import br.com.omaisfood.model.DistrictItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictItemRepository extends JpaRepository<DistrictItem, Long> {
    List<DistrictItem> findAllByCompanyId(Long companyId);

    Boolean existsByCompanyIdAndDistrictId(Long CompanyId, Long districtId);

    DistrictItem findDistrictItemByCompanyIdAndDistrictId(Long companyId, Long distrinctId);
}
