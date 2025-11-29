package svi.converter;



import svi.model.Genero;
import svi.model.Premio;

public class ConverterIdObjeto {
    public Premio premioFromId(Long id) {
        int i = 0;
        Premio[] vetPremios = Premio.values();
        Premio premio = null;
        boolean achouPremio = false;
        while( achouPremio == false && i<vetPremios.length) {
            if(vetPremios[i] != null) {
                if( vetPremios[i].getId() == id) {
                achouPremio = true;
                premio = vetPremios[i];
            }
            }
            
            i++;
        }
        return premio;
    }
    public Genero generoFromId(Long id) {
        int i = 0;
        Genero[] vetGeneros = Genero.values();
        Genero genero = null;
        boolean achouGenero = false;
        while( achouGenero == false && i<vetGeneros.length) {
            if(vetGeneros[i] != null) {
                if( vetGeneros[i].getId() == id) {
                achouGenero = true;
                genero = vetGeneros[i];
            }
            }
            
            i++;
        }
        return genero;
    }
    
}
