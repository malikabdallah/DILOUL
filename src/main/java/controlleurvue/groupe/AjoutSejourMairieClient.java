package controlleurvue.groupe;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controlleurvue.Vue;
import daos.*;
import daos.impl.*;
import enumerations.Depart;
import enumerations.Sexe;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import modele.*;
import notification.Notification;
import principale.Controlleur;


import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.input.KeyCode.Z;
import static javax.print.attribute.standard.MediaSizeName.A;

public class AjoutSejourMairieClient implements Vue, Initializable {
    public Label lgrouped;
    public Label lsejour;
    public Label date;
    public Label lcentre;
    public JFXTextField groupecomplet;
    
    
    public JFXTextField portable;
    public JFXTextField observation;
    public JFXTextField email;
    public JFXTextField poste;
    public JFXDatePicker annee;
    public JFXTextField adresse;
    public JFXTextField nom;
    public JFXTextField prenom;
    public Label prix_unitaire;
    public JFXComboBox depart;

    public String dD;
    public String dF;

    public int aMin;
    public int aMax;
    public JFXComboBox sexe;

    private Controlleur controlleur;

    public static String sejourId;
    public   static  String assocId;
    public  static  String groupe;
    private AssociationGroupeSejourDao associationGroupeSejourDao;
    private SejourDao sejourDao;
    private CentreDao centreDao;
    private GroupeDao groupeDao;
    private ClientDao clientDao;
    private GroupeSejourClientDao groupeSejourClientDao;
    Associationgroupesejour associationgroupesejour;
    Sejour sejour;
    Centre centre;





    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageConsulterGroupeSejour();
    }

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        associationGroupeSejourDao=new AssociationGroupeSejourDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        this.groupeSejourClientDao=new GroupeSejourClientDaoImpl(DBconnexion.getConnection());
        for(Sexe sexe: Sexe.values()){
            this.sexe.getItems().add(sexe);
        }
        for(Depart depart:Depart.values()){
            this.depart.getItems().add(depart);

        }
        remplirData();
    }

    private void remplirData() {
        sejour=sejourDao.getSejourParId(sejourId);
        centre=centreDao.getCentreParId(sejour.nom_centre.get());

        this.dD = sejour.date_debut.get();
        this.dF = sejour.date_fin.get();
        this.aMin = Integer.parseInt(sejour.ageMin.get());
        this.aMax = Integer.parseInt(sejour.ageMax.get());

        this.date.setText(dD+" au "+dF);

        this.lsejour.setText(sejour.type.get());
        this.lcentre.setText(centre.nom_centre.get());
        this.lgrouped.setText(groupe);
        this.groupecomplet.setText(groupe);
        associationgroupesejour=associationGroupeSejourDao.getById(AjoutSejourMairieClient.assocId);
        this.prix_unitaire.setText(associationgroupesejour.prix_unitaire.get());

    }


    public void creer(MouseEvent mouseEvent) {
        try {
            Groupe groupe=groupeDao.getGroupeParId(associationgroupesejour.groupe.get());
            String prenom=this.prenom.getText();
            String nom=this.nom.getText();
            String id_group=groupe.id.get();
            String portable=this.portable.getText();
            String observation=this.observation.getText();
            String email=this.email.getText();
            String adresse=this.adresse.getText();
            String poste=this.poste.getText();
            String dateNaissance=this.annee.getValue().toString();

            Client clientGroup;

            if (!nom.isEmpty() && !prenom.isEmpty() && !this.sexe.getValue().toString().isEmpty() && !portable.isEmpty() && !adresse.isEmpty() && !poste.isEmpty() && !dateNaissance.isEmpty()) {
                if (controleDate(dateNaissance, dD, dF)) {
                    if (isEmailAdress(email)) {
                        if (isCodePostale(poste)) {
                            clientGroup = testClientExiste(nom, prenom, dateNaissance, id_group);
                            if (clientGroup == null) {
                                Client client = new Client("", nom, prenom, id_group, portable, observation, email, adresse, poste, dateNaissance, this.sexe.getValue().toString());

                                int[] res = this.clientDao.insererClientMairie(client);

                                if (res[0] > 0) {
                                    groupeSejourClientDao.insererGroupeSejourClient(new GroupeSejourClient(id_group, sejourId, String.valueOf(res[1]), this.depart.getValue().toString()));
                                    // groupeSejourClientDao.insererGroupeSejourClient(new GroupeSejourClient(id_group,sejourId,))
                                    Notification.affichageSucces("succes", "client ajouté avec succes ");

                                } else {
                                    Notification.affichageEchec("erreur", "il y a eu une erreur au moment de la creation du client");

                                }
                            } else if (testGroupeSejourClienExiste(id_group, clientGroup.id.get(), clientGroup.groupe.get()) != null) {
                                Notification.affichageEchec("erreur", "le client existe déjà avec ce groupe ");
                            } else {
                                int response = groupeSejourClientDao.insererGroupeSejourClient(new GroupeSejourClient(id_group, sejourId, clientGroup.id.get(), this.depart.getValue().toString()));
                                if (response > 0) {
                                    Notification.affichageSucces("succes", "client ajouté avec succes ");

                                } else {
                                    Notification.affichageEchec("erreur", "il y a eu une erreur au moment de la creation du client");
                                }
                            }
                        } else {
                            Notification.affichageEchec("erreur", "le code poste est incorrect ");
                        }
                    } else {
                        Notification.affichageEchec("erreur", "l'Email est incorrect ");
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("L'enfant n'a pas l'âge requise pour ce séjour.");
                    alert.showAndWait();
                }
            }else {
                Notification.affichageEchec("Problème de donnees", "veuillez saisir de(s) champ(s) non vide(s)");

            }
        }catch (Exception e){
            e.printStackTrace();
            Notification.affichageEchec("Saisi incorrecte","Veuillez saisir de(s) champ(s) non vide(s) et valide(s) ");

        }
    }

    public Client testClientExiste(String nom , String prenom, String dN, String idGrp){
        return clientDao.getClient( nom , prenom, dN, idGrp);
    }

    public GroupeSejourClient testGroupeSejourClienExiste(String idG , String idS, String idC){
        return groupeSejourClientDao.getGroupeSejourClient( idG , idS, idC);
    }

    public boolean controleDate(String dateNaiss, String dateDebut, String dateFin){

        LocalDate dN = LocalDate.parse(dateNaiss);
        LocalDate dD = LocalDate.parse(dateDebut);
        LocalDate dF = LocalDate.parse(dateFin);
        Boolean b ;
        if (Period.between(dN, dD).getYears() < aMin) {
            b=false;
             System.out.println("Y"+Period.between(dN, dD).getYears()+" L'enfant n'auras pas l'age min "+aMin+" ans avant le debut du sejour");
             Notification.affichageEchec("Date incorrecte"," L'enfant n'auras pas l'age min "+aMin+" ans avant le debut du sejour");

        } else if (Period.between(dN, dD).getYears() == aMin) {
            if (Period.between(dN, dD).getMonths() < 0) {
                b=false;
                System.out.println("M"+Period.between(dN, dD).getMonths()+" L'enfant n'auras pas l'age min "+aMin+" ans avant le debut du sejour");
                Notification.affichageEchec("Date incorrecte"," L'enfant n'auras pas l'age min "+aMin+" ans avant le debut du sejour");

            } else if (Period.between(dN, dD).getMonths() == 0) {
                if (Period.between(dN, dD).getDays() < 0) {
                    b=false;
                    System.out.println("D"+Period.between(dN, dD).getMonths()+" L'enfant n'auras pas l'age min "+aMin+" ans avant le debut du sejour");
                    Notification.affichageEchec("Date incorrecte"," L'enfant n'auras pas l'age min "+aMin+" ans avant le debut du sejour");

                } else {
                    b=true;
                    System.out.println("OK");

                }
            } else {
                b=true;
                System.out.println("OK");

            }

            } else {
                b=true;
                System.out.println("OK");
            }

        if (Period.between(dN, dF).getYears() > aMax) {
            b=false;
            System.out.println("Y"+Period.between(dN, dF).getYears()+"L'enfant auras dépassé l'age max "+aMax+" ans avant la fin du sejour");
            Notification.affichageEchec("Date incorrecte"," L'enfant auras dépassé l'age max "+aMax+" ans avant la fin du sejour");

        } else if (Period.between(dN, dF).getYears() == aMax) {
            if (Period.between(dN, dF).getMonths() < 0) {
                b=false;
                System.out.println("M"+Period.between(dN, dD).getMonths()+" L'enfant auras dépassé l'age max "+aMax+" ans avant la fin du sejour");
                Notification.affichageEchec("Date incorrecte"," L'enfant auras dépassé l'age max "+aMax+" ans  avant la fin du sejour");

            } else if (Period.between(dN, dF).getMonths() == 0) {
                if (Period.between(dN, dF).getDays() < 0) {
                    b=false;
                    System.out.println("D"+Period.between(dN, dF).getMonths()+" L'enfant auras dépassé l'age max "+aMax+" ans avant la fin du sejour");
                    Notification.affichageEchec("Date incorrecte"," L'enfant auras dépassé l'age max "+aMax+" ans avant la fin du sejour");

                } else {
                    b=true;
                    System.out.println("OK");

                }
            } else {
                b=true;
                System.out.println("OK");

            }

        } else {
            b=true;
            System.out.println("OK");
        }
        System.out.println("Annee= "+Period.between(dN, dD).getYears()+" Mois= "+Period.between(dN, dD).getMonths()+" Days= "+Period.between(dN, dD).getDays());

        System.out.println("Annee= "+Period.between(dN, dD).getYears()+" Mois= "+Period.between(dN, dD).getMonths()+" Days= "+Period.between(dN, dD).getDays());
        return b;
    }


       /* if (ChronoUnit.YEARS.between(LocalDate.parse(dN),LocalDate.parse(dD) >= this.aMin){

        }*/

        //Boolean b = true;
        /*

            if(!(dD.after(new Date()))){
                Notification.affichageEchec("echec","la date du debut est incorrecte");
                b=false;
                return b;
            }else if(!(dF.after(dD))){
                Notification.affichageEchec("echec","la date de fin doit etre apres celle du debut");
                b=false;
                return b;
            }else{
                return b;
            }
        }catch(ParseException e){
            Notification.affichageEchec("echec","echec le format de la date incorrect");
        }
        return b;*/


    public static boolean isEmailAdress(String email){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m;
        m = p.matcher(email.toUpperCase());
        return m.matches();
    }

    /*public static boolean isCodePostale(String email){
        Pattern p = Pattern.compile("^(0[1-9]{0,1}|[1-9][0-9]{0,1})[0-9]{0,3}$");
        Matcher m;
        m = p.matcher(email.toUpperCase());
        return m.matches();
    }*/


    public static boolean isCodePostale(String codePostale){
        Pattern p = Pattern.compile("^(([0-8][0-9])|(9[0-5])|(2[ab]))[0-9]{3}$");
        Matcher m;
        m = p.matcher(codePostale.toUpperCase());
        return m.matches();
    }

}
    