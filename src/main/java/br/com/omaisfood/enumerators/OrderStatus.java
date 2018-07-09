package br.com.omaisfood.enumerators;

public enum OrderStatus {
    PENDING(0), ACCEPTED(1), PROCESSING(2), DELIVERY(3), COMPLETED(4);

    private int identifier;

    OrderStatus(int identify){
        this.identifier = identify;
    }

    public int getIdentifier(){
        return this.identifier;
    }
}
