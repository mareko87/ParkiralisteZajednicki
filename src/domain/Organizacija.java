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
import java.util.Objects;

/**
 *
 * @author marek
 */
public class Organizacija extends AbstractDomainObject {
    
    private Long organizacijaID;
    private String naziv;
    private String adresa;
    private ArrayList<Vozilo> vozila;

    public Organizacija() {
    }

    public Organizacija(Long organizacijaID, String naziv, String adresa, ArrayList<Vozilo> vozila) {
        this.organizacijaID = organizacijaID;
        this.naziv = naziv;
        this.adresa = adresa;
        this.vozila = vozila;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Long getOrganizacijaID() {
        return organizacijaID;
    }

    public void setOrganizacijaID(Long organizacijaID) {
        this.organizacijaID = organizacijaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    public ArrayList<Vozilo> getVozila() {
        return vozila;
    }

    public void setVozila(ArrayList<Vozilo> vozila) {
        this.vozila = vozila;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
   
        final Organizacija other = (Organizacija) obj;
        if (Objects.equals(this.naziv, other.naziv) && Objects.equals(this.adresa, other.adresa)) {
            return true;
        }

        return false;
    }
     
    @Override
    public String nazivTabele() {
        return " organizacija ";
    }

    @Override
    public String alijas() {
        return " o ";
    }

    @Override
    public String join() {
        return " ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            Organizacija o = new Organizacija(rs.getLong("OrganizacijaID"),
                    rs.getString("Naziv"), rs.getString("Adresa"), null);
            
            lista.add(o);
        }
        
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv, Adresa) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " OrganizacijaID = " + organizacijaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + adresa + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return  "Naziv = '" + naziv + "', Adresa = '" + adresa + "' ";
    }

    
}
