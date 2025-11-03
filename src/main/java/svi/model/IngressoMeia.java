package svi.model;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import svi.converter.ConverterTipoDeMeiaString;

@Entity
public class IngressoMeia extends Ingresso {
    @Convert(converter = ConverterTipoDeMeiaString.class)
    private TipodeMeia tipodeMeia;

    public TipodeMeia getTipodeMeia() {
        return tipodeMeia;
    }

    public void setTipodeMeia(TipodeMeia tipodeMeia) {
        this.tipodeMeia = tipodeMeia;
    }

    
}
