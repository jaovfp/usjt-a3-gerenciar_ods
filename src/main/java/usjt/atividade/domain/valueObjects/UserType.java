package usjt.atividade.domain.valueObjects;

public enum UserType {
    NORMAL("NORMAL"),
    ADMIN("ADMIN");

    private final String description;

    UserType(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
