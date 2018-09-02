package br.com.omaisfood.model.enumerators;

public enum TransactionStatus {
    PENDING(0), PROCESSING(1), SUCCESSFULLT(2);

    private int identifier;

    TransactionStatus(int identify){
        this.identifier = identify;
    }

    public int getIdentifier(){
        return this.identifier;
    }
}
