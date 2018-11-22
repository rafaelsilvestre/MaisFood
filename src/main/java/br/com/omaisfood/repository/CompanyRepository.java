package br.com.omaisfood.repository;

import br.com.omaisfood.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company getCompanyByUserId(Long userId);

    Boolean existsByUserId(Long userId);

    List<Company> findAllByOrderByIdDesc();

    List<Company> findByDistricts_DistrictId(Long districtId);
}
