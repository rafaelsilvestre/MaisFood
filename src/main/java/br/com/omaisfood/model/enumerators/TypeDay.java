package br.com.omaisfood.model.enumerators;

public enum TypeDay {
    SUNDAY(0), MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6);

    private int day;

    TypeDay(int day){
        this.day = day;
    }

    public int getDay(){
        return this.day;
    }
}
