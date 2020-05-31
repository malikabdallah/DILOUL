package daos.impl;


import java.sql.Connection;
import java.util.List;

public abstract class Dao<T> {



    protected Connection connect = null;


    public Dao(Connection conn){
        this.connect = conn;
    }


}
