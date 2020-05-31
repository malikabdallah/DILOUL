package daos.impl;

import com.mysql.jdbc.PreparedStatement;

import com.sun.org.apache.xerces.internal.xs.StringList;

import controlleurvue.centre.CreerCentre;
import controlleurvue.groupe.AssocierGroupeSejour;
import daos.AssociationGroupeSejourDao;
import daos.GroupeDao;
import modele.Associationgroupesejour;
import modele.Client;
import modele.Groupe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Date;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssociationGroupeSejourDaoImpl extends Dao<Associationgroupesejour> implements AssociationGroupeSejourDao {

    public AssociationGroupeSejourDaoImpl(Connection conn) {
        super(conn);
    }

    @Override

    public int inserrerAssociation(Associationgroupesejour associerGroupeSejour) {
        int res=0;
        String sql="INSERT INTO associationgroupesejour (prix_unitaire,groupe,id_sejour,nbplace) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1,associerGroupeSejour.prix_unitaire.get());
            ps.setString(2,associerGroupeSejour.groupe.get());
            ps.setString(3,associerGroupeSejour.sejour.get());

            ps.setString(4,associerGroupeSejour.nbPlace.get());

            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;

    }

    @Override
    public List<Associationgroupesejour> getListes() {
        GroupeDao groupeDao=new GroupeDaoImpl(connect);


        String sql="SELECT * FROM associationgroupesejour ";
        List<Associationgroupesejour>liste=new ArrayList<>();

        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();


            while(rs.next()){

                liste.add(new Associationgroupesejour(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5)));



            }


        }catch (Exception e){

        }

        return liste;
    }

    @Override
    public Associationgroupesejour getById(String s) {
        String sql="SELECT * FROM associationgroupesejour WHERE id ='"+s+"'";
        Associationgroupesejour associationgroupesejour=null;
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                associationgroupesejour=new Associationgroupesejour(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );

            }


        }catch (Exception e){

        }

        return associationgroupesejour;
    }

    @Override
    public int supprimerById(String text) {

        int res=0;
        String sql="DELETE FROM associationgroupesejour WHERE id=?";

        try {
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1, text);


            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public List<String> testCapaciteCentre(String id){
        String sql="SELECT id_sejour FROM sejour WHERE date_fin < (SELECT date_debut FROM `sejour` WHERE id_sejour='"+id+"')";
        List<String> listeId = new ArrayList<>();
        listeId.add(id);
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                listeId.add(rs.getString(1));
            }
            return listeId;
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public int nbReservationGroupSejourForId(String id) {

        int nbReserv=0;
        String sql="SELECT SUM(nbplace) FROM associationgroupesejour WHERE id_sejour ='"+id+"'";
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                nbReserv = rs.getInt(1);
            }
            return nbReserv;

        }catch (Exception e){

        }
        return nbReserv;
    }

    @Override
    public int mettreAJourAssociation(String id,String prix,String place) {
        int x=0;

        try{

            String query = "update associationgroupesejour set prix_unitaire = ? ,  nbplace=? where id = ?";

            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(query);
            ps.setString(3,id);
            ps.setString(2,place);
            ps.setString(1,prix);
            x=ps.executeUpdate();



        }catch (Exception e){
            return 0;

        }

        return x;
    }

}
