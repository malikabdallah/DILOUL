package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import daos.AnnulationDao;
import daos.CentreDao;
import daos.GroupeDao;
import modele.*;
import notification.Notification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnnulationDaoImpl extends Dao<Annulation> implements AnnulationDao {

    public AnnulationDaoImpl(Connection conn) {
        super(conn);
    }




    @Override
    public int insererAnnulation(Annulation annulation) {
       int res=0;
        String sql="INSERT INTO annulation (motif,idsejour,idclient) VALUES (?,?,?)";
        try {
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1, annulation.motif.get());
            ps.setString(2,annulation.idsejour.get());
            ps.setString(3,annulation.idclient.get());


            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;

    }

    @Override
    public List<Annulation> getAnnulationsParId(int id) {
        String sql="SELECT * FROM annulation WHERE idclient ='"+id+"'";
        List<Annulation>liste=new ArrayList<>();


        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                liste.add(new Annulation(rs.getInt(1)+"",rs.getString(2),rs.getString(3),
                        rs.getString(4)));

            }


        }catch (Exception e){

        }

        return liste;

    }

    @Override
    public List<Annulation> getAnnulartions() {

        GroupeDao groupeDao=new GroupeDaoImpl(connect);


        String sql="SELECT * FROM annulation ";
        List<Annulation>liste=new ArrayList<>();

        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();


            while(rs.next()){

                Groupe groupe=groupeDao.getGroupeParId(rs.getString(4));
                liste.add(new Annulation(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4)));



            }


        }catch (Exception e){

        }

        return liste;
    }
}
