package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import daos.GroupeDao;
import java.sql.Array;
import modele.Centre;
import modele.Groupe;
import notification.Notification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupeDaoImpl extends Dao<Groupe> implements GroupeDao {

    public GroupeDaoImpl(Connection conn) {
        super(conn);
    }


    @Override
    public int inserrerGroupe(String nom_groupe, String tiers,String commune) {

        int res = 0;
        String sql = "INSERT INTO groupe (nom_groupe,tiers,commune) VALUES (?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
            ps.setString(1, nom_groupe);
            ps.setString(2, tiers);
            ps.setString(3,commune);



            res = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    @Override
    public int mettreAjourGroupe(String id, Groupe groupe) {
        int x=0;

        try{

            String query = "update groupe set nom_groupe = ? ,  tiers=? ,commune=? where id_groupe = ?";

            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(query);
            ps.setString(3,groupe.commune.get());
            ps.setString(2,groupe.code_tiers.get());
            ps.setString(1,groupe.nom_groupe.get());
            ps.setString(4,id);
            x=ps.executeUpdate();



        }catch (Exception e){
            e.printStackTrace();
            return 0;

        }

        return x;
    }

    @Override
    public int supprimerGroupe(String id) {
        String sql = "DELETE FROM groupe WHERE id_groupe=?";

        int res = 0;

        try {


            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
            ps.setString(1, id);



            res = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;

    }

    @Override
    public List<Groupe> listeGroupes() {
        String sql = "SELECT * FROM groupe";
        List<Groupe> liste;
        liste = new ArrayList<>() ;

        try {
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                liste.add(new Groupe(rs.getInt(1) + "", rs.getString(2),rs.getString(3),
                        rs.getString(4)));

            }


        } catch (Exception e) {

        }

        return liste;
    }

    @Override
    public Groupe trouverGroupeParNomGroupe(String nom_groupe) {
        String sql = "SELECT * FROM groupe WHERE nom_groupe ='"+nom_groupe+"'";
        Groupe g = null;
        try {
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                g = new Groupe(rs.getInt(1) + "", rs.getString(2),rs.getString(3),
                        rs.getString(4));

            }
            return g;


        } catch (Exception e) {
e.printStackTrace();
        }

        return null;
    }

    @Override
    public Groupe trouverGroupeParCodeTiers(String tiers) {
        String sql = "SELECT * FROM groupe WHERE tiers ='" + tiers + "'";
        Groupe liste = null;
        try {
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                liste = new Groupe(rs.getInt(1) + "", rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));

            }

            return liste;

        } catch (Exception e) {

        }
        return null;
    }



    @Override
    public Groupe getGroupeParId(String id) {
        String sql="SELECT * FROM groupe WHERE id_groupe ='"+id+"'";
        Groupe liste=null;
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                liste=new Groupe(rs.getInt(1)+"",rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));

            }


        }catch (Exception e){

        }

        return liste;    }
}
