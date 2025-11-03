package svi.model;

import jakarta.persistence.Entity;

@Entity
public class Sessao3d extends Sessao{
    private Double taxa3d;

    public Double getTaxa3d() {
        return taxa3d;
    }

    public void setTaxa3d(Double taxa3d) {
        this.taxa3d = taxa3d;
    }
    
}
