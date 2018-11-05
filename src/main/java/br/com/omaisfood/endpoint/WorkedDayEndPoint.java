package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.WorkedDay;
import br.com.omaisfood.service.WorkedDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("worked_day")
public class WorkedDayEndPoint {
    @Autowired
    private WorkedDayService workedDayService;

    @GetMapping(path = "/{companyId}")
    public ResponseEntity<List<WorkedDay>> getWorkedDays(@PathVariable Long companyId) {
        List<WorkedDay> workedDays = this.workedDayService.getWorkedDaysByCompanyId(companyId);
        return new ResponseEntity<List<WorkedDay>>(workedDays, HttpStatus.OK);
    }

    @PostMapping(path = "/{companyId}")
    public ResponseEntity<List<WorkedDay>> saveWorkedDay(@RequestBody List<WorkedDay> workedDays,@PathVariable Long companyId) {
        return new ResponseEntity<List<WorkedDay>>(this.workedDayService.saveWorkedDay(workedDays, companyId), HttpStatus.OK);
    }
}
