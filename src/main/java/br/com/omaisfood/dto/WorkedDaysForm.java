package br.com.omaisfood.dto;

import br.com.omaisfood.model.WorkedWeek;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class WorkedDaysForm {
    @NotEmpty(message = "Informe todos os dias da semana")
    @Size(min=7, max=7, message = "Informe todos os sete dias, ou somente os sete dias!")
    private List<WorkedWeek> workedDays;

    public List<WorkedWeek> getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(List<WorkedWeek> workedDays) {
        this.workedDays = workedDays;
    }
}
