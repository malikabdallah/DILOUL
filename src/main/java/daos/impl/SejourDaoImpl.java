package daos.impl;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import controlleurvue.sejour.CreerSejour;
import daos.CentreDao;
import daos.SejourDao;
import javafx.beans.property.StringProperty;
import modele.Centre;
import modele.Client;
import modele.Groupe;
import modele.Sejour;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SejourDaoImpl extends Dao<Sejour> implements SejourDao {
    public SejourDaoImpl(Connection conn) {
        super(conn);
    }


    @Override
    public int insererSejour(Sejour sejour) {
        int res=0;

        String sql="INSERT INTO sejour (duree,date_debut,date_fin,type_sejour,centre_id,prix,age_min,age_max,capacite,ref_sejour,numero) VALUES (?,?,?,?,?,?,?" +
                ",?,?,?,?)";
        try {

            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1, sejour.duree.get());
            ps.setString(2,sejour.date_debut.get());
            ps.setString(3, sejour.date_fin.get());
            ps.setString(4, sejour.type.get());
            ps.setString(6,sejour.prix.get() );
            ps.setString(7,sejour.ageMin.get() );
            ps.setString(8,sejour.ageMax.get() );
            ps.setString(9,sejour.capacite.get() );
            ps.setString(5,sejour.nom_centre.get() );
            ps.setString(10,sejour.refSejour.get());
            ps.setString(11,sejour.numero.get());






            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerSejour.class.getName()).log(Level.SEVERE, null, ex);
        }

        return  res;
    }

    @Override
    public List<Sejour> listeSejour() {
        CentreDao centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        String sql="SELECT * FROM sejour";
        List<Sejour>liste=new ArrayList<>();

        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                Centre centre=centreDao.getCentreParId(rs.getString(6));
                liste.add(new Sejour(rs.getString(1),rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ,rs.getString(5),
                        centre.nom_centre.get(),
                        rs.getString(7),
                        rs.getString(8),
                      rs.getString(9),
                        rs.getString(10)  ,rs.getString(11),rs.getString(12)));

            }


        }catch (Exception e){

        }

        return liste;
    }

    @Override
    public Sejour getSejourParId(String id_sejour) {
        String sql="SELECT * FROM sejour WHERE id_sejour ='"+id_sejour+"'";
        Sejour sejour=null;
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                sejour=new Sejour(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                        ,rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),rs.getString(12));

            }


        }catch (Exception e){

        }


        return sejour;

    }

    @Override
    public int supprimerSejourParid(String toString) {


        int res=0;

        String sql="DELETE FROM sejour WHERE id_sejour=?";
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
    public List<Sejour> getSejourParType(String nom) {
        String sql="SELECT * FROM sejour WHERE type_sejour ='"+nom+"'";
        Sejour sejour=null;
        List<Sejour>liste=new ArrayList<>();
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                sejour=new Sejour(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                        ,rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),rs.getString(12));
                liste.add(sejour);

            }


        }catch (Exception e){

        }


        return liste;


    }

    @Override
    public List<Sejour> getSejourParTypeEtDate(String value, String arg, String arg1) {


        List<Sejour>list=new ArrayList<>();
        String sql="SELECT * FROM sejour where type_sejour ='"+value+"' AND date_debut='"+arg+"' AND date_fin='"+
                arg1+"'";



        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Sejour sejour=new Sejour(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9)
                ,rs.getString(10),
                        rs.getString(11),rs.getString(12));
                list.add(sejour);


            }
        }catch (Exception e){

        }
        return list;
    }

    @Override
    public List<Sejour> getSejourParCentre(String id) {

        List<Sejour>list=new ArrayList<>();
        String sql = "SELECT * FROM sejour WHERE centre_id ='" + id+ "'";




        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Sejour sejour=new Sejour(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9)
                        ,rs.getString(10),
                        rs.getString(11),
                        rs.getString(12));
                list.add(sejour);


            }
        }catch (Exception e){

        }
        return list;    }

    @Override
    public List<Sejour> getSejourParTypeEtDuree(Object value, String newItem) {
        List<Sejour>list=new ArrayList<>();
        String sql="SELECT * FROM sejour where type_sejour ='"+(String)value+"' AND duree ='"+newItem+"'";




        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Sejour sejour=new Sejour(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9)
                        ,rs.getString(10),rs.getString(11),
                        rs.getString(12));
                list.add(sejour);


            }
        }catch (Exception e){

        }
        return list;     }

    @Override
    public Sejour getSejourPartypeetdureeetdate(Object value, Object value1, String arg, String arg1) {
            Sejour sejour=null;

        String sql="SELECT * FROM sejour where type_sejour ='"+value+"' AND duree='"+value1+
                "' AND date_debut='"+arg+"' AND date_fin ='"+arg1+"'";

        try {
            PreparedStatement ps = (PreparedStatement) DBconnexion.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                 sejour=new Sejour(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9)
                        ,rs.getString(10),rs.getString(11),
                         rs.getString(12));

            }
        }catch (Exception e){

        }
return sejour;

    }

    public List<String> testCapaciteSejour(String id){
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
}
