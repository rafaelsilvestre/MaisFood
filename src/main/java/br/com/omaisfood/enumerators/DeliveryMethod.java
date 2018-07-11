package br.com.omaisfood.enumerators;

public enum DeliveryMethod {
    WITHDRAW_IN_STORE(0), DELIVER_IN_AN_ADDRESS(1);

    private int identifier;

    DeliveryMethod(int identify){
        this.identifier = identify;
    }

    public int getIdentifier(){
        return this.identifier;
    }
}
