package controlleurvue.groupe;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.CentreDao;
import daos.GroupeDao;
import daos.PaiementMairieDao;
import daos.SejourDao;
import daos.impl.CentreDaoImpl;
import daos.impl.GroupeDaoImpl;
import daos.impl.PaiementMairieDaoImpl;
import daos.impl.SejourDaoImpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import modele.*;
import principale.Controlleur;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Historique implements Initializable, Vue {

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageConsulterGroupeSejour();
    }

    public static  String id_sejour;
    public static  String id_groupe;

    public JFXTreeTableView inscriptions;
    public Label sejour;
    public Label duree;
    public Label debut;
    public Label fin;
    public Label centre;
    public Label groupe;
    private Controlleur controlleur;
    public JFXTreeTableView<PaiementMarie> paiementMarieJFXTreeTableView;
    private PaiementMairieDao paiementMairieDao;
    private SejourDao sejourDao;
    private CentreDao centreDao;
    private GroupeDao groupeDao;


    @Override
    public void setController(Controlleur controller) {

        this.controlleur=controller;
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        paiementMairieDao=new PaiementMairieDaoImpl(DBconnexion.getConnection());
        remplirData();
        chargerPaiement();

    }

    private void remplirData() {

        Sejour sejour=sejourDao.getSejourParId(id_sejour);
        Centre centre=centreDao.getCentreParId(sejour.nom_centre.get());
        Groupe groupe=groupeDao.getGroupeParId(id_groupe);


        this.duree.setText(sejour.duree.get());
        this.centre.setText(centre.nom_centre.get());
        this.debut.setText(sejour.date_debut.get());
        this.fin.setText(sejour.date_fin.get());
        this.groupe.setText(groupe.nom_groupe.get());
        this.sejour.setText(sejour.type.get());
    }


    private JFXTreeTableColumn<PaiementMarie, String> creationColomnId() {
        JFXTreeTableColumn<PaiementMarie, String> capaciteCentre = new JFXTreeTableColumn<>("id");
        capaciteCentre.setPrefWidth(110);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PaiementMarie, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PaiementMarie, String> param) {
                return param.getValue().getValue().id;
            }
        });

        return capaciteCentre;

    }

    private JFXTreeTableColumn<PaiementMarie, String> creationColumGroupe() {
        JFXTreeTableColumn<PaiementMarie, String> capaciteCentre = new JFXTreeTableColumn<>("groupe");
        capaciteCentre.setPrefWidth(110);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PaiementMarie, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PaiementMarie, String> param) {
                return param.getValue().getValue().groupe;
            }
        });

        return capaciteCentre;

    }


    private JFXTreeTableColumn<PaiementMarie, String> creationCollumSejour() {
        JFXTreeTableColumn<PaiementMarie, String> capaciteCentre = new JFXTreeTableColumn<>("sejour");
        capaciteCentre.setPrefWidth(110);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PaiementMarie, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PaiementMarie, String> param) {
                return param.getValue().getValue().sejour;
            }
        });

        return capaciteCentre;

    }


    private JFXTreeTableColumn<PaiementMarie, String> creationCollumPaiement() {
        JFXTreeTableColumn<PaiementMarie, String> capaciteCentre = new JFXTreeTableColumn<>("somme paye");
        capaciteCentre.setPrefWidth(110);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PaiementMarie, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PaiementMarie, String> param) {
                return param.getValue().getValue().paiement;
            }
        });

        return capaciteCentre;

    }


    private JFXTreeTableColumn<PaiementMarie, String> creationCollumMethode() {
        JFXTreeTableColumn<PaiementMarie, String> capaciteCentre = new JFXTreeTableColumn<>("methode");
        capaciteCentre.setPrefWidth(110);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PaiementMarie, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PaiementMarie, String> param) {
                return param.getValue().getValue().methode;
            }
        });

        return capaciteCentre;

    }




    public void loadallcentre() {






    }

    private void chargerPaiement() {
        JFXTreeTableColumn<PaiementMarie, String> id = this.creationColomnId();
        JFXTreeTableColumn<PaiementMarie, String> groupe = this.creationColumGroupe();
        JFXTreeTableColumn<PaiementMarie, String> sejour = this.creationCollumSejour();
        JFXTreeTableColumn<PaiementMarie, String> paiement = this.creationCollumPaiement();
        JFXTreeTableColumn<PaiementMarie, String> methode = this.creationCollumMethode();


        ObservableList<PaiementMarie> centres = FXCollections.observableArrayList();
        List<PaiementMarie> liste = paiementMairieDao.listePaimenentBis(Integer.parseInt(id_sejour),Integer.parseInt(id_groupe));
        for (PaiementMarie paiementMarie : liste) {
            centres.add(paiementMarie);
        }
        final TreeItem<PaiementMarie> root = new RecursiveTreeItem<PaiementMarie>(centres, RecursiveTreeObject::getChildren);


        paiementMarieJFXTreeTableView.getColumns().setAll(id,groupe,sejour,paiement,methode);
        paiementMarieJFXTreeTableView.setRoot(root);
        paiementMarieJFXTreeTableView.setShowRoot(false);

    }


    public void retourmenuclient(MouseEvent mouseEvent) {
        this.controlleur.lancerPageConsulterGroupeSejour();
    }
}
