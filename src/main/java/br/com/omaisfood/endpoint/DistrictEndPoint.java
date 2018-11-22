package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.District;
import br.com.omaisfood.model.DistrictItem;
import br.com.omaisfood.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("district")
public class DistrictEndPoint {

    @Autowired
    private DistrictService districtService;

    @GetMapping
    public ResponseEntity<List<District>> getAllDistricts() {
        List<District> districts = this.districtService.getAllDistricts();
        return new ResponseEntity<List<District>>(districts, HttpStatus.OK);
    }

    @GetMapping(path = "/{companyId}")
    public ResponseEntity<List<District>> getDistrictsByCompany(@PathVariable Long companyId) {
        List<District> districts = this.districtService.getDistrictsByCompany(companyId);
        return new ResponseEntity<List<District>>(districts, HttpStatus.OK);
    }

    @PostMapping(path = "/{companyId}")
    public ResponseEntity<District> saveDistrictsByCompany(@RequestBody District district, @PathVariable Long companyId) {
        District districtItem = this.districtService.saveDistrictItem(district, companyId);
        return new ResponseEntity<District>(districtItem, HttpStatus.OK);
    }

    @PostMapping(path = "/{companyId}/delete")
    public ResponseEntity<Void> removeDistrictsByCompany(@RequestBody District district, @PathVariable Long companyId) {
        this.districtService.removeDistrictItem(district, companyId);
        return ResponseEntity.noContent().build();
    }
}
