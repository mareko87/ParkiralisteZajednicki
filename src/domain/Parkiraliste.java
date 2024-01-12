/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marek
 */
public class Parkiraliste extends AbstractDomainObject {
    
    private Long parkiralisteID;
    private String adresa;
    private int kapacitet;

    public Parkiraliste() {
    }

    public Parkiraliste(Long parkiralisteID, String adresa, int kapacitet) {
        this.parkiralisteID = parkiralisteID;
        this.adresa = adresa;
        this.kapacitet = kapacitet;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public Long getParkiralisteID() {
        return parkiralisteID;
    }

    public void setParkiralisteID(Long parkiralisteID) {
        this.parkiralisteID = parkiralisteID;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return adresa;
    }
    
    @Override
    public String nazivTabele() {
        return " parkiraliste ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            Parkiraliste p = new Parkiraliste(rs.getLong("ParkiralisteID"),
                    rs.getString("Adresa"), rs.getInt("Kapacitet"));
            
            lista.add(p);
        }
        
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " ParkiralisteID = " + parkiralisteID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }
    
}
