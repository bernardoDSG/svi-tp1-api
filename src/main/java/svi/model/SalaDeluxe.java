package svi.model;

import jakarta.persistence.Entity;

@Entity
public class SalaDeluxe extends Sala{
    private Double taxaDeluxe;

    public Double getTaxaDeluxe() {
        return taxaDeluxe;
    }

    public void setTaxaDeluxe(Double taxaDeluxe) {
        this.taxaDeluxe = taxaDeluxe;
    }
    
}
