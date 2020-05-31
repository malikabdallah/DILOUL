package gestiondocuments;

import basededonnee.DBconnexion;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import daos.*;
import daos.impl.*;
import javafx.scene.control.Alert;
import modele.*;
import notification.Notification;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.AttributedString;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GestionDocsImpl implements GestionDocs {
    private CentreDao centreDao;
    private InscriptionDao inscriptionDao;
    private ReservationDao reservationDao;
    private ClientDao clientDao;
    private GroupeDao groupeDao;
    private GroupeSejourClientDao groupeSejourClientDao;


    public GestionDocsImpl() {
        clientDao= new ClientDaoImpl(DBconnexion.getConnection());
        centreDao = new CentreDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        reservationDao=new ReservationDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        groupeSejourClientDao =new GroupeSejourClientDaoImpl(DBconnexion.getConnection());

        creerDossiers();
    }

    @Override
    public void genereAttestationFacture(Client client, Sejour sejour) {
        //this.creerDossiers();
        Centre centre = centreDao.getCentreParId(sejour.nom_centre.get());
        Inscription inscription = inscriptionDao.getInscriptionsParIdSejourEtIdClient(sejour.id.get(), client.id.get());
        int mtRestant=0;
        int mtPaye=0;
        int mtApayer=0;


        Document doc = new Document();
        FileOutputStream fileOS;
        File f;


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-yyy");


            String fact_ou_att = "";
            if (new Date().before(sdf.parse(sejour.date_fin.get()))) {
                fact_ou_att = "A participé";
            } else {
                fact_ou_att = "Est inscrit";

            }
            String aquitement = "";

            mtApayer = Integer.parseInt(sejour.prix.get());
            mtPaye = Integer.parseInt(inscription.paiement.get());
            mtRestant = mtApayer - mtPaye;
            if (mtRestant > 0) {
                aquitement = "NON ACQUITTEE";
            } else {
                aquitement = "ACQUITTEE";
            }

            f = new File("C:\\DocumentsOUL\\Clients\\ATT_Inscription_" + client.prenom_client.get() + "_" + client.nom_client.get() +
                    "_" + sejour.refSejour.get() + "_" +
                    LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRANCE)) + ".pdf");

            fileOS = new FileOutputStream(f);
            PdfWriter writer = PdfWriter.getInstance(doc, fileOS);
            doc.open();


            //Add Image
            Image img = Image.getInstance("src/main/resources/img/oul.jpg");
            //Fixed Positioning
            img.setAbsolutePosition(25f, 750f);
            //Scale to new height and new width of image
            img.scaleAbsolute(80, 80);
            //Add to document
            doc.add(img);
            //doc.add(Chunk.SPACETABBING);
            doc.add(new Paragraph("\n\n"));

            Font coord = FontFactory.getFont(FontFactory.HELVETICA, 8);
            Font let = FontFactory.getFont(FontFactory.HELVETICA, 12);

            Font font = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
            font.setColor(BaseColor.BLUE);

            Font f_titre = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
            f_titre.setColor(BaseColor.BLUE);


            Font font1 = FontFactory.getFont(FontFactory.HELVETICA, 10);
            font1.setColor(BaseColor.BLUE);
            // Créer une police
            java.awt.Font police = new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 24);

            Font font2 = FontFactory.getFont(FontFactory.HELVETICA, 10);


            doc.add(new Phrase("ŒUVRE UNIVERSITAIRE DU LOIRET\n", font));
            doc.add(new Phrase("2  rue des Deux Ponts \nCS 30724 \n45017 ORLEANS CEDEX 1 \nTél : 02.38.53.38.61\n", coord));
            doc.add(new Phrase("siege.asso@ouloiret.fr\nwww.ouloiret.fr \n", font1));
            doc.add(new Phrase("SIRET : 77550821100072 \nAPE : 552 E", coord));

            Paragraph dest = new Paragraph("Mme ou M. " + client.observation.get() + "              \n" + client.codePostale.get() + " " + client.adresse.get() + "             ", font2);
            dest.setAlignment(Element.ALIGN_RIGHT);
            doc.add(dest);

            Paragraph p = new Paragraph("\n\n");

            doc.add(p);

            Chunk underline = new Chunk("ATTESTATION / FACTURE");
            underline.setUnderline(1f, -2f); //0.1 thick, -2 y-location
            underline.setFont(f_titre);
            Paragraph titre = new Paragraph(underline);
            titre.setAlignment(Element.ALIGN_CENTER);
            doc.add(titre);

            doc.add(p);

            font.setColor(BaseColor.BLACK);


            doc.add(new Phrase("Je soussigné, M. JOBERT, Directeur de l’Œuvre Universitaire du Loiret, certifie que :\n\n" + "L’enfant : ", let));
            doc.add(new Phrase(client.prenom_client.get() + " " + client.nom_client.get() + "\n\n", font));
            doc.add(new Phrase("Né(e) le : ", let));
            doc.add(new Phrase(LocalDate.parse(client.datenaissance.get()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRANCE)) + "\n\n", font));
            doc.add(new Phrase(fact_ou_att + " à l'activité ", let));
            doc.add(new Phrase(sejour.type.get(), font));
            doc.add(new Phrase(" de l’Œuvre Universitaire du Loiret  à : ", let));
            doc.add(new Phrase(centre.nom_centre.get(), font));
            doc.add(new Phrase("\ndu " +
                    LocalDate.parse(sejour.date_debut.getValue()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRANCE)) + " au " +
                    LocalDate.parse(sejour.date_fin.getValue()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRANCE)) +
                    " soit :", let));
            doc.add(new Phrase(sejour.duree.get(), font));
            doc.add(new Phrase(" jours.\n\nLe coût du séjour  pour la famille s’élève à ", let));
            doc.add(new Phrase(sejour.prix.get() + " €       EUROS.\n", font));

            if (mtRestant > 0) {
                doc.add(new Phrase("Vous avez payé un acompte de ", let));
                doc.add(new Phrase(mtPaye + " €       EUROS.\n", font));

                doc.add(new Phrase("Il vous reste ", let));
                doc.add(new Phrase(mtRestant+ " €       EUROS", font));
                doc.add(new Phrase(" à payer.\n\n", let));

            }
            else {
                doc.add(new Phrase("Vous avez payé la totalité des : ", let));
                doc.add(new Phrase(mtRestant+ " €       EUROS.\n\n", font));

            }



            String numDec = "";
            if (sejour.type.get() == "classe de découverte") {
                numDec = "N°de Déclaration de l’Inspection Académique : "+sejour.numero.get();
            } else {
                numDec = "N° de Déclaration à la D.D.C.S du Loiret : "+sejour.numero.get();
            }
            doc.add(new Phrase(numDec,let));

            doc.add(p);

            Chunk underlin = new Chunk("FACTURE "+aquitement);
            underlin.setUnderline(1f, -2f); //0.1 thick, -2 y-location
            underlin.setFont(font);
            Paragraph acut = new Paragraph(underlin);
            acut.setAlignment(Element.ALIGN_CENTER);
            doc.add(acut);

            Paragraph p1 = new Paragraph("\n");
            doc.add(p1);


            DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL);

            Paragraph dt = new Paragraph("Orléans, le " + fullDateFormat.format(new Date()), let);
            dt.setAlignment(Element.ALIGN_CENTER);
            doc.add(dt);

            Paragraph dir = new Paragraph(" Le directeur, M. JOBERT", font);
            dir.setAlignment(Element.ALIGN_CENTER);
            doc.add(dir);

            //Add Image

            Image sign = Image.getInstance("src/main/resources/img/signature.png");
            //Fixed Positioning
            sign.setAlignment(Element.ALIGN_CENTER);

            //sign.setAbsolutePosition(25f, 750f);
            //Scale to new height and new width of image
            sign.scaleAbsolute(80, 60);
            //Add to document
            doc.add(sign);

            Image cachet = Image.getInstance("src/main/resources/img/cachet.png");
            //Fixed Positioning
            cachet.setAlignment(Element.ALIGN_CENTER);
            //cachet.setAbsolutePosition(1000f, 200f);
            //Scale to new height and new width of image
            cachet.scaleAbsolute(80, 60);
            //Add to document
            doc.add(cachet);

            doc.close();
            fileOS.close();
            writer.close();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION.INFORMATION);
            alert.setTitle("SUCCES");
            alert.setHeaderText(null);
            alert.setContentText("Attestation/Facture/Convocation générée avec succès");
//"Document généré avec succès!");
            alert.showAndWait();
        } catch (Exception e) {
           // e.printStackTrace();
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Ce document PDF à déjà été généré aujourd'hui et il est ouvert !\n" +
                    "Merci de le fermer afin de pouvoir généré un nouveau.");
            alert.showAndWait();

        }

    }

    @Override
    public void genereFactureReservation(Client client, Sejour sejour) {
        //this.creerDossiers();

        Centre centre = centreDao.getCentreParId(sejour.nom_centre.get());
        Reservation reservation =reservationDao.getReservationParIdClientEtIdSejour(sejour.id.get(), client.id.get());
        Document doc = new Document();
        FileOutputStream fileOS;
        File f;


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");




            String fact_ou_att = "A fait une réservation";

            String aquitement="NON ACQUITTEE";

            fileOS = new FileOutputStream(new File("C:\\DocumentsOUL\\Clients\\ATT_Reservation_" + client.prenom_client.get() +"_"+client.nom_client.get()+
                    "_"+sejour.refSejour.get()+"_"+
                    LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.FRANCE ))+".pdf"));

            PdfWriter writer = PdfWriter.getInstance(doc, fileOS);doc.open();


            //Add Image
            Image img = Image.getInstance("src/main/resources/img/oul.jpg");
            //Fixed Positioning
            img.setAbsolutePosition(25f, 750f);
            //Scale to new height and new width of image
            img.scaleAbsolute(80, 80);
            //Add to document
            doc.add(img);
            //doc.add(Chunk.SPACETABBING);
            doc.add(new Paragraph("\n\n"));

            Font coord = FontFactory.getFont(FontFactory.HELVETICA, 8);
            Font let = FontFactory.getFont(FontFactory.HELVETICA, 12);

            Font font = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
            font.setColor(BaseColor.BLUE);

            Font f_titre = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
            f_titre.setColor(BaseColor.BLUE);


            Font font1 = FontFactory.getFont(FontFactory.HELVETICA, 10);
            font1.setColor(BaseColor.BLUE);
            // Créer une police
            java.awt.Font police = new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 24);

            Font font2 = FontFactory.getFont(FontFactory.HELVETICA, 10);


            doc.add(new Phrase("ŒUVRE UNIVERSITAIRE DU LOIRET\n", font));
            doc.add(new Phrase("2  rue des Deux Ponts \nCS 30724 \n45017 ORLEANS CEDEX 1 \nTél : 02.38.53.38.61\n", coord));
            doc.add(new Phrase("siege.asso@ouloiret.fr\nwww.ouloiret.fr \n", font1));
            doc.add(new Phrase("SIRET : 77550821100072 \nAPE : 552 E", coord));

            Paragraph dest = new Paragraph("Mme ou M. " + client.observation.get() + "              \n" + client.codePostale.get() + " " + client.adresse.get() + "             ", font2);
            dest.setAlignment(Element.ALIGN_RIGHT);
            doc.add(dest);

            Paragraph p = new Paragraph("\n\n");

            doc.add(p);

            Chunk underline = new Chunk("ATTESTATION / FACTURE");
            underline.setUnderline(1f, -2f); //0.1 thick, -2 y-location
            underline.setFont(f_titre);
            Paragraph titre = new Paragraph(underline);
            titre.setAlignment(Element.ALIGN_CENTER);
            doc.add(titre);

            doc.add(p);

            font.setColor(BaseColor.BLACK);


            doc.add(new Phrase("Je soussigné, M. JOBERT, Directeur de l’Œuvre Universitaire du Loiret, certifie que :\n\n" + "L’enfant : ",let));
            doc.add(new Phrase(client.prenom_client.get() + " " + client.nom_client.get() + "\n\n",font));
            doc.add(new Phrase("Né(e) le : ",let));
            doc.add(new Phrase( LocalDate.parse(client.datenaissance.get()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.FRANCE )) + "\n\n",font));
            doc.add(new Phrase( fact_ou_att+" pour l'activité ",let));
            doc.add(new Phrase(sejour.type.get(),font));
            doc.add(new Phrase(" de l’Œuvre Universitaire du Loiret  à : " ,let));
            doc.add(new Phrase(centre.nom_centre.get() ,font));
            doc.add(new Phrase( "\ndu "+
                    LocalDate.parse(sejour.date_debut.getValue()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.FRANCE ))+" au "+
                    LocalDate.parse(sejour.date_fin.getValue()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.FRANCE )) +
                    " soit :",let));
            doc.add(new Phrase( sejour.duree.get(),font));
            doc.add(new Phrase( " jours.\n\nLe coût du séjour  pour la famille s’élève à : ",let));
            doc.add(new Phrase( sejour.prix.get() + " €       EUROS.\n\n",font));

            String numDec = "";
            if (sejour.type.get() == "classe de découverte") {
                numDec = "N°de Déclaration de l’Inspection Académique : "+sejour.numero.get();
            } else {
                numDec = "N° de Déclaration à la D.D.C.S du Loiret : "+sejour.numero.get();
            }
            doc.add(new Phrase(numDec,let));

            doc.add(p);

            Chunk underlin = new Chunk("FACTURE "+aquitement);
            underlin.setUnderline(1f, -2f); //0.1 thick, -2 y-location
            underlin.setFont(font);
            Paragraph acut = new Paragraph(underlin);
            acut.setAlignment(Element.ALIGN_CENTER);
            doc.add(acut);

            Paragraph p1 = new Paragraph("\n");
            doc.add(p1);


            DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL);

            Paragraph dt = new Paragraph("Orléans, le " + fullDateFormat.format(new Date()), let);
            dt.setAlignment(Element.ALIGN_CENTER);
            doc.add(dt);

            Paragraph dir = new Paragraph(" Le directeur, M. JOBERT", font);
            dir.setAlignment(Element.ALIGN_CENTER);
            doc.add(dir);

            //Add Image

            Image sign = Image.getInstance("src/main/resources/img/signature.png");
            //Fixed Positioning
            sign.setAlignment(Element.ALIGN_CENTER);

            //sign.setAbsolutePosition(25f, 750f);
            //Scale to new height and new width of image
            sign.scaleAbsolute(80, 60);
            //Add to document
            doc.add(sign);

            Image cachet = Image.getInstance("src/main/resources/img/cachet.png");
            //Fixed Positioning
            cachet.setAlignment(Element.ALIGN_CENTER);
            //cachet.setAbsolutePosition(1000f, 200f);
            //Scale to new height and new width of image
            cachet.scaleAbsolute(80, 60);
            //Add to document
            doc.add(cachet);

            doc.close();
            fileOS.close();
            writer.close();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION.INFORMATION);
            alert.setTitle("SUCCES");
            alert.setHeaderText(null);
            alert.setContentText("Document généré avec succès!");
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Ce document PDF à déjà été généré aujourd'hui et il est ouvert !\n" +
                    "Merci de le fermer afin de pouvoir généré un nouveau.");
            alert.showAndWait();
            /*Notification.affichageEchec("echec","Ce documents à déjà été généré aujourd'hui\n+" +
                    "Merci de le supprimer du dossier \"C/DocumentsOUL/Clients\" afin de pouvoir généré un nouveau.");*/
        }


    }

    @Override
    public void genereConfirmationInscription(Client client, Sejour sejour) {


        Centre centre = centreDao.getCentreParId(sejour.nom_centre.get());
        Inscription inscription = inscriptionDao.getInscriptionsParIdSejourEtIdClient(sejour.id.get(), client.id.get());
        if (Integer.parseInt(inscription.paiement.get() )== Integer.parseInt(sejour.prix.get())){
            this.genereAttestationFacture(client,sejour);
        }else {

            Document doc = new Document();

            FileOutputStream fileOS;
            File f;
            try {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                f = new File("C:\\DocumentsOUL\\Clients\\Confirmation_Inscription_" + client.prenom_client.get() +"_"+client.nom_client.get()+
                        "_"+sejour.refSejour.get()+"_"+
                        LocalDate.parse(sdf.format(new Date()
                        )).format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.FRANCE ))+".pdf");

                fileOS = new FileOutputStream(f);
                PdfWriter writer = PdfWriter.getInstance(doc, fileOS);doc.open();

                //Add Image
                Image img = Image.getInstance("src/main/resources/img/oul.jpg");
                //Fixed Positioning
                img.setAbsolutePosition(25f, 750f);
                //Scale to new height and new width of image
                img.scaleAbsolute(80, 80);
                //Add to document
                doc.add(img);
                //doc.add(Chunk.SPACETABBING);
                doc.add(new Paragraph("\n\n"));
                Font font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
                font.setColor(BaseColor.BLUE);

                Font font0 = FontFactory.getFont(FontFactory.HELVETICA, 8);

                Font font1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
                font1.setColor(BaseColor.BLUE);

                Font font2 = FontFactory.getFont(FontFactory.HELVETICA, 10);
                Font font3 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.UNDERLINE);


                doc.add(new Phrase("ŒUVRE UNIVERSITAIRE DU LOIRET\n", font));
                doc.add(new Phrase("2  rue des Deux Ponts \nCS 30724 \n45017 ORLEANS CEDEX 1 \nTél : 02.38.53.38.61\n", font0));
                doc.add(new Phrase("siege.asso@ouloiret.fr\nwww.ouloiret.fr \n", font1));
                doc.add(new Phrase("SIRET : 77550821100072 \nAPE : 552 E", font0));

                DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL);
                Paragraph jour = new Paragraph("Le " + fullDateFormat.format(new Date()), font2);
                jour.setAlignment(Element.ALIGN_CENTER);
                doc.add(jour);

                font.setColor(BaseColor.BLACK);

                Paragraph dest = new Paragraph("à   Mme ou M. " + client.observation.get() + "          \n" + client.codePostale.get() + " " + client.adresse.get() + "         ", font2);
                dest.setAlignment(Element.ALIGN_RIGHT);
                doc.add(dest);

                font2.setSize(9);


                doc.add(new Phrase("Référence à rappeler : ", font2));
                doc.add(new Phrase(sejour.refSejour.get() + "\n", font));
                Image alerte = Image.getInstance("src/main/resources/img/alerte.jpg");

                alerte.setAbsolutePosition(33f, 570f);

                alerte.scaleAbsolute(10, 10);
                doc.add(alerte);

                String alrt = "    (votre dossier ne pourra être pris en compte sans cette référence)";
                Paragraph p = new Paragraph(alrt + "\n\n", font);
                doc.add(p);

                Chunk underline = new Chunk("CONFIRMATION D’INSCRIPTION");
                underline.setUnderline(1f, -2f); //0.1 thick, -2 y-location
                underline.setFont(font1);
                Paragraph titre = new Paragraph(underline);
                titre.setAlignment(Element.ALIGN_CENTER);
                doc.add(titre);


                Chunk s1 = new Chunk(" ainsi que l’adhésion de 3 € (sauf si elle a déjà été réglée lors d’un précédent séjour", font3);
                //s1.setUnderline(1f, -2f); //0.1 thick, -2 y-location

                Chunk s2 = new Chunk("obligatoire ");
                s2.setUnderline(1f, -2f); //0.1 thick, -2 y-location
                s2.setFont(font);

                Chunk s3 = new Chunk("Attention ", font);
                s3.setUnderline(1f, -2f); //0.1 thick, -2 y-location
                //s3.setFont(font);

                Chunk s4 = new Chunk("(si séjour NON SOLDÉ ou non pris en charge)");
                s4.setUnderline(1f, -2f); //0.1 thick, -2 y-location
                s4.setFont(font);

                doc.add(new Phrase("     Madame, Monsieur,\n " +
                        "     Nous avons le plaisir de vous confirmer l’inscription de votre enfant: ", font2));
                doc.add(new Phrase(client.prenom_client.get() + " " + client.nom_client.get() + " ", font));
                doc.add(new Phrase(" né le ", font2));
                doc.add(new Phrase(LocalDate.parse(client.datenaissance.get()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.FRANCE ))+"\n",font));

                doc.add(new Phrase("     •  au centre de vacances de ", font2));
                doc.add(new Phrase(centre.nom_centre.get(), font));
                doc.add(new Phrase(",\n     •  pour le séjour du ", font2));
                Phrase dDs= new Phrase(LocalDate.parse(sejour.date_debut.getValue()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.FRANCE )), font);
                doc.add(dDs);
                doc.add(new Phrase(" au ", font2));
                Phrase dFs =new Phrase(LocalDate.parse(sejour.date_fin.getValue()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.FRANCE )), font);
                doc.add(dFs);
                doc.add(new Phrase("\n     •  pour une durée de ", font2));
                doc.add(new Phrase(sejour.duree.get(), font));
                doc.add(new Phrase(" jours.\n", font2));
                doc.add(new Phrase("     Les frais de séjour et de voyage s’élèvent  à : ", font2));
                doc.add(new Phrase(sejour.prix.get() + " €", font));
                doc.add(new Phrase(", auxquels s’ajoute le montant de l’adhésion, soit ", font2));
                doc.add(new Phrase("3,00 €", font));
                doc.add(new Phrase(" par famille et par année.\n\n", font2));
                doc.add(new Phrase("     Vous avez versé des arrhes pour un montant de  ", font2));
                doc.add(new Phrase(inscription.paiement.get() + "€ ", font));
                doc.add(new Phrase(s1));
                doc.add(new Phrase(". Le solde est à régler entre trois et quatre semaines avant le départ. Nous vous rappelons que notre association est habilitée pour accepter les paiements par Chèques-Vacances.\n\n" +
                        "     Deux à trois semaines avant le départ, une fois le séjour soldé, nous vous ferons parvenir les documents suivants :\n" +
                        "     •  la convocation avec les lieux et heures de départ et retour\n" +
                        "     •  le dossier du jeune ou de l’enfant à compléter\n" +
                        "     •  la lettre du directeur vous donnant des informations sur le déroulement du séjour.\n", font2));

                doc.add(new Phrase("Nous vous rappelons que", font2));
                font.setSize(9);
                doc.add(new Phrase("pour toutes les activités nautiques ", font));
                doc.add(new Phrase("(voile, kayak, canyoning, surf, plongée…)", font2));
                doc.add(new Phrase(" le test de natation est ", font));
                doc.add(new Phrase(s2));
                doc.add(new Phrase("( le brevet de 25 ou 50 m est non valable).\n", font2));
                doc.add(new Phrase(s3));
                doc.add(new Phrase(" : Pour la plongée un certificat médical est également demandé.\n", font));

                doc.add(new Phrase("     Si vous êtes en possession de Bons-Vacances CAF ou MSA et si vous ne nous les avez pas encore transmis, veuillez nous les adresser dans les meilleurs délais (ou votre numéro d’allocataire pour les VACAF).\n" +
                        "     En souhaitant que votre enfant passe un agréable séjour en Centre de Vacances, nous vous adressons nos respectueuses salutations.\n", font2));

                Paragraph dir = new Paragraph(" Le directeur: \nM. JOBERT", font);
                dir.setAlignment(Element.ALIGN_CENTER);
                doc.add(dir);


                doc.add(new Phrase("---------------------------------------------------------------------------------------------------------------------------------- "));
                Paragraph p1 = new Paragraph(" PAPILLON à DETACHER ET à RETOURNER A L’O.U.L.AVEC VOTRE REGLEMENT\n" + s4, font);
                p1.setAlignment(Element.ALIGN_CENTER);
                doc.add(p1);
                doc.add(new Phrase("NOM :", font2));
                doc.add(new Phrase(client.nom_client.get() + "\n", font));
                doc.add(new Phrase("REFERENCE :", font2));
                doc.add(new Phrase(sejour.refSejour.get() + "  ", font));
                doc.add(new Phrase(centre.nom_centre.get() + "  ", font));
                doc.add(dDs);
                doc.add(new Phrase(" au ", font2));
                doc.add(dFs);

                doc.add(p);
                alerte.setAbsolutePosition(33f, 104f);
                doc.add(alerte);

                doc.add(new Paragraph("Le transport n’est pas compris pour les centres du Loiret, (INGRANNES, LES CAILLETTES, L’ETANG DU PUITS)\n" +
                        "vous devez conduire votre enfant sur place. Cependant un départ d’ORLEANS est possible (20 € ALLER/RETOUR),\n" +
                        "        le nombre de places étant limité, veuillez nous en informer par téléphone ou par courrier.", font));
                doc.close();
                fileOS.close();
                writer.close();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION.INFORMATION);
                alert.setTitle("SUCCES");
                alert.setHeaderText(null);
                alert.setContentText("Une attestation d'inscription à été généré avec succès!");
                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Ce document PDF à déjà été généré aujourd'hui et il est ouvert !\n" +
                        "Merci de le fermer afin de pouvoir généré un nouveau.");
                alert.showAndWait();

            }
        }
    }

    @Override
    public void genereListeInscritPdf(List<Inscription> inscptListBySej, List<GroupeSejourClient> gscListBySej, Sejour sejour, Centre centre) throws IOException, DocumentException {
        Document doc = new Document();

        FileOutputStream fileOS;
        File f;

        try {

            f = new File("C:\\DocumentsOUL\\Centres\\ListeInscrits-"+sejour.type.get()+"_"+
                    LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.FRANCE ))+".pdf");

            fileOS = new FileOutputStream(f);
            PdfWriter writer = PdfWriter.getInstance(doc, fileOS);
            doc.open();

            //Add Image
            Image img = Image.getInstance("src/main/resources/img/oul.jpg");
            //Fixed Positioning
            img.setAbsolutePosition(25f, 750f);
            //Scale to new height and new width of image
            img.scaleAbsolute(80, 80);
            //Add to document
            doc.add(img);
            //doc.add(Chunk.SPACETABBING);
            doc.add(new Paragraph("\n\n"));
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
            font.setColor(BaseColor.BLUE);
            Font font1 =FontFactory.getFont(FontFactory.HELVETICA, 10);
            font1.setColor(BaseColor.BLUE);

            Font font0 =FontFactory.getFont(FontFactory.HELVETICA, 8);

            Font font2 =FontFactory.getFont(FontFactory.HELVETICA, 10);


            doc.add(new Phrase("ŒUVRE UNIVERSITAIRE DU LOIRET\n",font));
            doc.add(new Phrase("2  rue des Deux Ponts \nCS 30724 \n45017 ORLEANS CEDEX 1 \nTél : 02.38.53.38.61\n",font0));
            doc.add(new Phrase("siege.asso@ouloiret.fr\nwww.ouloiret.fr \n",font1));
            doc.add(new Phrase("SIRET : 77550821100072 \nAPE : 552 E",font0));

            Paragraph pCentre = new Paragraph("CENTRE"+"    " +centre.nom_centre.get()+" avec capacite "+centre.capacite_centre.get());
            Paragraph pdateSejour = new Paragraph("DATES DU SEJOUR"+"     ");
            pCentre.setAlignment(Element.ALIGN_CENTER);
            pdateSejour.setAlignment(Element.ALIGN_CENTER);

            doc.add(pCentre);
            doc.add(pdateSejour);


            Paragraph titre =new Paragraph(" \nListe des enfants inscritent à ce sejour:\n");
            titre.setAlignment(Element.ALIGN_CENTER);
            doc.add(titre);

            doc.add(new Paragraph(" "));


            PdfPTable table = new PdfPTable(16);
            table.setWidthPercentage(100);

            PdfPCell cell;

            cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Prenom", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Sexe", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Date de nais", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Parent/Mairie", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Email", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Adresse", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);


            cell = new PdfPCell(new Phrase("Postal", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Commune", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Tiers", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Observ", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Lieu de dep", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Tél", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Présences", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Date MAJ SIEGE", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Date MAJ CENTRE", FontFactory.getFont("Comic Sans MS", 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            /////////////////////////////////////////////////////////////////////////////////////////////////////


            if (gscListBySej != null) {
                for (GroupeSejourClient gsc : gscListBySej) {
                    Client client = clientDao.getClientParId(gsc.idClient);
                    Groupe g = groupeDao.getGroupeParId(client.groupe.get());

                    cell = new PdfPCell(new Phrase(client.nom_client.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client.prenom_client.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);


                    cell = new PdfPCell(new Phrase(client.sexe.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client.datenaissance.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(g.nom_groupe.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);


                    cell = new PdfPCell(new Phrase(client.email.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client.adresse.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client.codePostale.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(g.commune.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(g.code_tiers.get(), FontFactory.getFont("Arial", 12)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client.observation.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(gsc.depart, FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client.numero.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                }
            }

            if (inscptListBySej != null) {
                for (Inscription inscription : inscptListBySej) {
                    Client client1 = clientDao.getClientParId(inscription.code_client.get());
                    Groupe g1 = groupeDao.getGroupeParId(client1.groupe.get());

                    cell = new PdfPCell(new Phrase(client1.nom_client.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client1.prenom_client.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client1.sexe.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client1.datenaissance.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(g1.nom_groupe.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client1.email.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client1.adresse.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client1.codePostale.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(g1.commune.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(g1.code_tiers.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client1.observation.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(inscription.depart.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(client1.numero.get(), FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("", FontFactory.getFont("Arial", 10)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }

            }
            doc.add(table);

            doc.close();
            fileOS.close();
            writer.close();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION.INFORMATION);
            alert.setTitle("SUCCES");
            alert.setHeaderText(null);
            alert.setContentText("Document PDF généré avec succès!");
            alert.showAndWait();

        } catch (Exception e) {
            //e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Ce document PDF à déjà été généré aujourd'hui et il est ouvert !\n" +
                    "Merci de le fermer afin de pouvoir généré un nouveau.");
            alert.showAndWait();

        }
    }


    @Override
    public void genereListeInscritExcel(List<Inscription> inscptListBySej, List<GroupeSejourClient> gscListBySej, Sejour sejour, Centre centre) {

        FileOutputStream fileOS;
        File f;

        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet =wb.createSheet("Document Centre Excel");
            XSSFRow r1 = sheet.createRow(0);
            r1.createCell(0).setCellValue("O.U.L");
            r1.createCell(3).setCellValue("Oeuvre Universitaire du Loiret");

            XSSFRow r2 = sheet.createRow(2);
            r2.createCell(0).setCellValue("CENTRE");
            r2.createCell(3).setCellValue(centre.nom_centre.get());

            XSSFRow r3 = sheet.createRow(4);
            r3.createCell(0).setCellValue("DATE DU SEJOUR");
            r3.createCell(2).setCellValue(LocalDate.parse(sejour.date_debut.getValue()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.FRANCE )));
            r3.createCell(4).setCellValue(" AU ");
            r3.createCell(6).setCellValue(LocalDate.parse(sejour.date_fin.getValue()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.FRANCE )));

            XSSFRow r4 = sheet.createRow(6);
            r4.createCell(0).setCellValue("Nombre d'enfants présents Au débutndu séjour ");
            XSSFRow r5 = sheet.createRow(7);
            r5.createCell(3).setCellValue("A la fin du séjour ");

            XSSFRow header = sheet.createRow(9);

            header.createCell(0).setCellValue("NOM");
            header.createCell(1).setCellValue("PRENOM");
            header.createCell(2).setCellValue("SEXE");
            header.createCell(3).setCellValue("DATE DE NAISS");
            header.createCell(4).setCellValue("NOM PARENT/MAIRIE");
            header.createCell(5).setCellValue("EMAIL");
            header.createCell(6).setCellValue("ADRESSE");
            header.createCell(7).setCellValue("CODE POSTAL");
            header.createCell(8).setCellValue("COMMUNE");
            header.createCell(9).setCellValue("TIERS");
            header.createCell(10).setCellValue("LIEU DE DEPART");
            header.createCell(11).setCellValue("TELEPHONE");
            header.createCell(12).setCellValue("PRESENCES");
            header.createCell(13).setCellValue("DATE MAJ SIEGE");
            header.createCell(14).setCellValue("DATE MAJ CENTRE");

            int index = 10;

            if (gscListBySej != null) {
                for (GroupeSejourClient gsc : gscListBySej) {
                    Client client = clientDao.getClientParId(gsc.idClient);
                    Groupe g = groupeDao.getGroupeParId(client.groupe.get());

                    XSSFRow r = sheet.createRow(index);
                    r.createCell(0).setCellValue(client.nom_client.get());
                    r.createCell(1).setCellValue(client.prenom_client.get());
                    r.createCell(2).setCellValue(client.sexe.get());
                    r.createCell(3).setCellValue(client.datenaissance.get());
                    r.createCell(4).setCellValue(g.nom_groupe.get());
                    r.createCell(5).setCellValue(client.email.get());
                    r.createCell(6).setCellValue(client.adresse.get());
                    r.createCell(7).setCellValue(client.codePostale.get());
                    r.createCell(8).setCellValue(g.commune.get());
                    r.createCell(9).setCellValue(g.code_tiers.get());
                    r.createCell(10).setCellValue(gsc.depart);
                    r.createCell(11).setCellValue(client.numero.get());
                    r.createCell(12).setCellValue("");
                    r.createCell(13).setCellValue("");
                    r.createCell(14).setCellValue("");

                    index ++;

                }
            }
            if (inscptListBySej != null) {
                for (Inscription inscription : inscptListBySej) {
                    Client client1 = clientDao.getClientParId(inscription.code_client.get());
                    Groupe g1 = groupeDao.getGroupeParId(client1.groupe.get());

                    XSSFRow r = sheet.createRow(index);
                    r.createCell(0).setCellValue(client1.nom_client.get());
                    r.createCell(1).setCellValue(client1.prenom_client.get());
                    r.createCell(2).setCellValue(client1.sexe.get());
                    r.createCell(3).setCellValue(client1.datenaissance.get());
                    r.createCell(4).setCellValue(g1.nom_groupe.get());
                    r.createCell(5).setCellValue(client1.email.get());
                    r.createCell(6).setCellValue(client1.adresse.get());
                    r.createCell(7).setCellValue(client1.codePostale.get());
                    r.createCell(8).setCellValue(g1.commune.get());
                    r.createCell(9).setCellValue(g1.code_tiers.get());
                    r.createCell(10).setCellValue(inscription.depart.get());
                    r.createCell(11).setCellValue(client1.numero.get());
                    r.createCell(12).setCellValue("");
                    r.createCell(13).setCellValue("");
                    r.createCell(14).setCellValue("");

                    index++;
                }
            }

            f = new File("C:\\DocumentsOUL\\Centres\\ListeInscrits_"+sejour.type.get()+"_"+sejour.type.get()+"_"+
                    LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.FRANCE ))+".xlsx");

            fileOS =new FileOutputStream(f);

            wb.write(fileOS);
            fileOS.close();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION.INFORMATION);
            alert.setTitle("SUCCES");
            alert.setHeaderText(null);
            alert.setContentText("Document Excel généré avec succès!");
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Ce document Excel à déjà été généré aujourd'hui et il est ouvert !\n" +
                    "Merci de le fermer afin de pouvoir généré un nouveau.");
            alert.showAndWait();

        }

    }

    @Override
    public void creerDossiers() {
        try {
            //Sous Windows
            File docs = new File("C:\\DocumentsOUL");
            File centre = new File("C:\\DocumentsOUL\\Centres");
            File client = new File("C:\\DocumentsOUL\\Clients");

            //Sous Linux
            //File   file = new File("/home/temp");
            if (docs.exists()) {
                System.out.println("Le dossier existe déjà : " + docs.getAbsolutePath());
            } else {
                if (docs.mkdir()) {
                    if (centre.exists()) {
                        System.out.println("Le dossier existe déjà : " + centre.getAbsolutePath());
                    } else {
                        if (centre.mkdir()) {
                            System.out.println("Ajout du dossier : " + centre.getAbsolutePath());
                        } else {
                            System.out.println("Echec sur le dossier : " + centre.getAbsolutePath());
                            Notification.affichageEchec("echec","Problème de creation des dossier qui contiendrons les documents qui seront générés\n"+
                                    "Merci de contacter les developpeue de l'application ");
                        }
                    }

                    if (client.exists()) {
                        System.out.println("Le dossier existe déjà : " + client.getAbsolutePath());
                    } else {
                        if (client.mkdir()) {
                            System.out.println("Ajout du dossier : " + client.getAbsolutePath());
                        } else {
                            System.out.println("Echec sur le dossier : " + client.getAbsolutePath());
                            Notification.affichageEchec("echec","Problème de creation des dossier qui contiendrons les documents qui seront générés\n"+
                                    "Merci de contacter les developpeue de l'application ");
                        }
                    }

                    System.out.println("Ajout du dossier : " + docs.getAbsolutePath());
                } else {
                    System.out.println("Echec sur le dossier : " + docs.getAbsolutePath());
                    Notification.affichageEchec("echec","Problème de creation des dossier qui contiendrons les documents qui seront générés\n"+
                            "Merci de contacter les developpeue de l'application ");
                }
            }


        }catch (Exception e){
            Notification.affichageEchec("echec","Problème de creation des dossier qui contiendrons les documents qui seront générés\n"+
                    "Merci de contacter les developpeue de l'application ");
        }
    }
}