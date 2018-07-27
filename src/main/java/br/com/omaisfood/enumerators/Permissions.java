package br.com.omaisfood.enumerators;

public enum Permissions {
    ADMIN(0), COMPANY(1), CLIENT(2);

    private int identifier;

    Permissions(int identify){
        this.identifier = identify;
    }

    public int getIdentifier(){
        return this.identifier;
    }
}
