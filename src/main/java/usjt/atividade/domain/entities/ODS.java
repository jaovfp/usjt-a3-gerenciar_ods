package usjt.atividade.domain.entities;

import java.util.UUID;

public class ODS {

    private final UUID odsId;
    private final String odsName;
    private final String odsDescription;

    public ODS(UUID odsId, String odsName, String odsDescription){
        this.odsId = odsId;
        this.odsName = odsName;
        this.odsDescription = odsDescription;
    }

    public UUID getOdsId() {
        return odsId;
    }

    public String getOdsName() {
        return odsName;
    }

    public String getOdsDescription() {
        return odsDescription;
    }
}
