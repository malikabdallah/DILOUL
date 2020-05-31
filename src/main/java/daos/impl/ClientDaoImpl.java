package daos.impl;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.sejour.CreerSejour;
import daos.ClientDao;
import daos.GroupeDao;
import modele.Client;
import modele.Groupe;
import modele.Sejour;
import notification.Notification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDaoImpl extends Dao<Client> implements ClientDao {



    public ClientDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public int[] insererClientMairie(Client client) {
        String sql="INSERT INTO client (nom_client,prenom_client,groupe_client,numero,observation,email,adresse,code_postale,datenaissance,sexe) VALUES (?,?,?,?,?,?,?,?,?,?)";


        int []tab=new int[2];
        ResultSet ress=null;
        int res=0;
        try {


            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, client.prenom_client.get());
            ps.setString(2, client.nom_client.get());
            ps.setString(3, client.groupe.get());
            ps.setString(4,client.numero.get());
            ps.setString(5,client.observation.get());
            ps.setString(6,client.email.get());
            ps.setString(7,client.adresse.get());
            ps.setString(8,client.codePostale.get());
            ps.setString(9,client.datenaissance.get());
            ps.setString(10,client.sexe.get());





            res=ps.executeUpdate();
            ress=ps.getGeneratedKeys();
            try {
                if(ress.next()){
                    tab[1]=ress.getInt(1);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreerSejour.class.getName()).log(Level.SEVERE, null, ex);
        }

        tab[0]=res;

        return tab;

    }

    @Override
    public int insererClient(Client client) {
        String sql="INSERT INTO client (nom_client,prenom_client,groupe_client,numero,observation,email,adresse,code_postale,datenaissance,sexe) VALUES (?,?,?,?,?,?,?,?,?,?)";

        int res=0;
        try {


            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            System.out.println("prenom_client :"+client.prenom_client.get());
            System.out.println("cote postale :"+client.codePostale.get());

            ps.setString(1, client.prenom_client.get());
            ps.setString(2, client.nom_client.get());
            ps.setString(3, client.groupe.get());
            ps.setString(4,client.numero.get());
            ps.setString(5,client.observation.get());
            ps.setString(6,client.email.get());
            ps.setString(7,client.adresse.get());
            ps.setString(8,client.codePostale.get());
            ps.setString(9,client.datenaissance.get());
            ps.setString(10,client.sexe.get());





            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerSejour.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;

    }

    @Override
    public int supprimerClient(String id) {
        int res=0;
        String sql ="DELETE FROM client WHERE id=?";
        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1,id);
            res= ps.executeUpdate();


        }catch (Exception e){

        }

        return res;

    }

    @Override
    public List<Client> listeClient() {
        System.out.println("xxxx");

        GroupeDao groupeDao=new GroupeDaoImpl(connect);


        String sql="SELECT * FROM client ";
        List<Client>liste=new ArrayList<>();

        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();


            while(rs.next()){

                System.out.println("xxxx1");
                Groupe groupe=groupeDao.getGroupeParId(rs.getString(4));
                liste.add(new Client(rs.getString(1),rs.getString(2),rs.getString(3),
                        groupe.nom_groupe.get(),rs.getString(5),
                        rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
                        rs.getString(10),rs.getString(11)));



            }


        }catch (Exception e){

        }

        return liste;
    }

    @Override
    public Client getClientParId(String id){
    String sql="SELECT * FROM Client WHERE id ='"+id+"'";
    Client client=null;
        try{
        PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();

        while(rs.next()){

            client=new Client(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            ,rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),rs.getString(11));

        }


    }catch (Exception e){

    }

        return client;
}


    @Override
    public Client getClientParNomPrenomAnneeNaissance(String text, String text1, String text2) {
        Client Client=null;

        String sql="SELECT * FROM client where nom_client ='"+text+"' AND prenom_client='"+text1+
                "' AND datenaissance='"+text2;

        System.out.println("sql "+sql);

        try {
            PreparedStatement ps = (PreparedStatement) DBconnexion.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            System.out.println("requete execute");

            while (rs.next()) {
                Client=new Client(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9)
                        ,rs.getString(10),rs.getString(11));

            }
        }catch (Exception e){

        }
        return Client;

    }

    @Override
    public Client getClientParNomEtPrenom(String arg, String arg1) {
        Client Client=null;

        String sql="SELECT * FROM Client WHERE nom_client ='"+arg+"' AND prenom_client ='"+arg1+"'";

        System.out.println("sql "+sql);

        try {
            PreparedStatement ps = (PreparedStatement)this.connect.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            System.out.println("requete execute");

            while (rs.next()) {
                Client=new Client(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9)
                        ,rs.getString(10),rs.getString(11));

            }
        }catch (Exception e){

        }
        return Client;
    }

    @Override
    public Client getClient(String nom, String prenom, String dN, String idGrp) {
        Client client=null;

        String sql="SELECT * FROM client WHERE nom_client ='"+nom+"' AND prenom_client ='"+prenom+"'AND datenaissance ='"+dN+"'AND groupe_client ='"+idGrp+"'";
        //

        try {
            PreparedStatement ps = (PreparedStatement)this.connect.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                client=new Client(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9)
                        ,rs.getString(10),rs.getString(11));

            }


            return client;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int mettreAjourClient(String id, Client client) {
        int x=0;

        try{

            String query = "update client set numero = ? ,  observation=?,email=?,adresse=?,code_postale=? where id = ?";

            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(query);
          ps.setString(1,client.numero.get());
          ps.setString(2,client.observation.get());
          ps.setString(3,client.email.get());
          ps.setString(4,client.adresse.get());
          ps.setString(5,client.codePostale.get());
          ps.setString(6,id);
            x=ps.executeUpdate();



        }catch (Exception e){

            e.printStackTrace();
        }

        return x;
    }

}
