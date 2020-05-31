package dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modele.Centre;

public class CentreDto  extends RecursiveTreeObject<CentreDto> {


    public StringProperty nom_centre;

    public CentreDto(){
        super();
    }

    public CentreDto( String name) {
        this.nom_centre = new SimpleStringProperty(name);

    }
}
