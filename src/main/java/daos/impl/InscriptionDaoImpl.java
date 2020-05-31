package daos.impl;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import controlleurvue.sejour.CreerSejour;
import daos.InscriptionDao;
import modele.Centre;
import modele.Client;
import modele.Inscription;
import modele.Sejour;
import notification.Notification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InscriptionDaoImpl extends Dao<Inscription> implements InscriptionDao {

    public InscriptionDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Inscription> getInscriptions() {
        String sql="SELECT * FROM inscription";
        List<Inscription>liste=new ArrayList<>();

        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                liste.add(new Inscription(rs.getInt(1)+"",rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6)));

            }


        }catch (Exception e){

        }

        return liste;
    }

    @Override
    public int insererInscription(Inscription inscription2) {
        int res=0;

        String sql="INSERT INTO inscription (paiement,date_inscription,code_client,id_sejour,depart)" +
                " VALUES (?,?,?,?,?)";
        try {


            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1, inscription2.paiement.get());
            ps.setString(2,inscription2.dateinscription.get());
            ps.setString(3, inscription2.code_client.get());
            ps.setString(4,inscription2.id_sejour.get());
            ps.setString(5,inscription2.depart.get() );
            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerSejour.class.getName()).log(Level.SEVERE, null, ex);
        }

        return  res;
    }

    @Override
    public int supperimerParId(String toString) {

        int res=0;
        String sql="DELETE FROM inscription WHERE id_inscription=?";
        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
            if(toString.length()!=0) {
                ps.setString(1, toString);
            }

            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public int mettreAjourPaiement(String idInscription, String result) {

        String sql="SELECT * FROM inscription WHERE id_inscription ='"+idInscription+"'";
        Inscription liste=null;
        int s=0;
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

             s=0;
            int paiement=0;
            while(rs.next()){
                 paiement=Integer.parseInt(rs.getString(2));


            }

            s=paiement+Integer.parseInt(result);



        }catch (Exception e){

        }

        int x=0;
        String sql2 = "update inscription set paiement="+s+" where id_inscription="+idInscription+"";
try {
             Statement stmt = DBconnexion.getConnection().createStatement() ;

            x=stmt.executeUpdate(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
return x;
    }

    @Override
    public List<Inscription> getInscriptionsParIdSejour(String s) {
        String sql="SELECT * FROM inscription WHERE id_sejour ='"+s+"'";
        List<Inscription>liste=new ArrayList<>();


        try{

            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                liste.add(new Inscription(rs.getInt(1)+"",rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6)));

            }


        }catch (Exception e){

        }

        return liste;

    }





    @Override
    public Inscription getInscriptionsParIdSejourEtIdClient(String id_sejour, String code_client) {
        String sql="SELECT * FROM inscription WHERE id_sejour ='"+id_sejour+"' AND code_client="+code_client;
        Inscription inscription =null;

        //Notification.affichageSucces("voici la requete ",sql);


        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            //Notification.affichageSucces(" trouve ",sql);

            while(rs.next()){

                inscription=new Inscription(rs.getInt(1)+"",rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6));

            }
        return inscription;

        }catch (Exception e){

        }

        return null;
    }

    @Override
    public List<Inscription> getInscriptiosnParIdClient(int id) {
        String sql="SELECT * FROM inscription WHERE code_client ='"+id+"'";
        List<Inscription>liste=new ArrayList<>();


        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                liste.add(new Inscription(rs.getInt(1)+"",rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6)));

            }


        }catch (Exception e){

        }

        return liste;    }


    public int  nbInscriptionForId(String id) {

        int nbInsc=0;
        String sql="SELECT COUNT(*) FROM inscription WHERE id_sejour ='"+id+"'";
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                nbInsc = rs.getInt(1);
            }
            return nbInsc;

        }catch (Exception e){

        }
        return nbInsc;

    }

    @Override
    public Inscription getInscritptionParIdInscription(String s) {
        String sql="SELECT * FROM inscription WHERE id_inscription ='"+s+"'";
        Inscription liste=new Inscription();


        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                liste=new Inscription(rs.getInt(1)+"",rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6));

            }


        }catch (Exception e){

        }

        return liste;
    }


}
