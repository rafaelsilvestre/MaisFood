package br.com.omaisfood.model.enumerators;

public enum Permission {
    ADMIN(0, "ROLE_ADMIN"), COMPANY(1, "ROLE_COMPANY"), CLIENT(2, "ROLE_CLIENT");

    private int identifier;
    private String flag;

    Permission(int identify, String flag){
        this.identifier = identify;
        this.flag = flag;
    }

    public int getIdentifier(){
        return this.identifier;
    }

    public String getFlag(){
        return this.flag;
    }

    public static Permission toEnum(Integer identifier){
        if(identifier == null){
            return null;
        }

        for (Permission x : Permission.values()){
            if(identifier.equals(x.getIdentifier())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido" + identifier);
    }
}
