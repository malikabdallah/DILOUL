package daos.impl;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import daos.InscriptionDao;
import daos.PaiementMairieDao;
import modele.Inscription;
import modele.PaiementMarie;
import modele.Sejour;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaiementMairieDaoImpl extends Dao<Dao> implements PaiementMairieDao {



    public PaiementMairieDaoImpl(Connection conn) {
        super(conn);
    }


    @Override
    public int inserrerPaiement(PaiementMarie paiementMarie) {
        int res=0;
        String sql="INSERT INTO paiement_mairie (groupe,paiement,sejour,methode) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1, paiementMarie.groupe.get());
            ps.setString(2, paiementMarie.paiement.get());
            ps.setString(3, paiementMarie.sejour.get());
            ps.setString(4, paiementMarie.methode.get());



            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    @Override
    public List<PaiementMarie> listePaimenent(int idSejour, int idGroupe) {

        List<PaiementMarie>list=new ArrayList<>();
        String sql="SELECT * FROM paiement_mairie where groupe ='"+idGroupe+"' AND sejour='"+idSejour+"'";



        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PaiementMarie paiementMarie=new PaiementMarie(rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5));
                list.add(paiementMarie);


            }
        }catch (Exception e){

        }
        return list;
    }



    @Override
    public List<PaiementMarie> listePaimenentBis(int idSejour, int idGroupe) {

        List<PaiementMarie>list=new ArrayList<>();
        String sql="SELECT * FROM paiement_mairie where groupe ='"+idGroupe+"' AND sejour='"+idSejour+"'";



        Connection connection= DBconnexion.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PaiementMarie paiementMarie=new PaiementMarie(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5));
                list.add(paiementMarie);


            }
        }catch (Exception e){

        }
        return list;
    }
}
