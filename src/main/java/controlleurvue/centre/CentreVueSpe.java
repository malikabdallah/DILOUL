package controlleurvue.centre;

import controlleurvue.Vue;
import principale.Controlleur;

public class CentreVueSpe implements Vue {
    private Controlleur controlleur;



    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }
}
