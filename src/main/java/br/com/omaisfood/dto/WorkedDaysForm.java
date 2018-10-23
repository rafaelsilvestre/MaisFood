package br.com.omaisfood.dto;

import br.com.omaisfood.model.WorkedDay;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class WorkedDaysForm {
    @NotEmpty(message = "Informe todos os dias da semana")
    private List<WorkedDay> workedDays;

    public List<WorkedDay> getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(List<WorkedDay> workedDays) {
        this.workedDays = workedDays;
    }
}
