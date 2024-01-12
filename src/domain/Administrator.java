/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Marko Milosevic
 */
public class Administrator extends AbstractDomainObject {
    
    private Long administratorID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private boolean glavniAdministrator;
    private Parkiraliste parkiraliste;

    public Administrator() {
    }

    public Administrator(long administratorID, String ime, String prezime, String korisnickoIme, String lozinka, boolean glavniAdministrator, Parkiraliste parkiraliste) {
        this.administratorID = administratorID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.glavniAdministrator = glavniAdministrator;
        this.parkiraliste = parkiraliste;
    }
    
    public boolean isGlavniAdministrator() {
        return glavniAdministrator;
    }

    public void setGlavniAdministrator(boolean glavniAdministrator) {
        this.glavniAdministrator = glavniAdministrator;
    }

    public Parkiraliste getParkiraliste() {
        return parkiraliste;
    }

    public void setParkiraliste(Parkiraliste parkiraliste) {
        this.parkiraliste = parkiraliste;
    }

    public Long getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(Long administratorID) {
        this.administratorID = administratorID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrator other = (Administrator) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String nazivTabele() {
        return " administrator ";
    }

    @Override
    public String alijas() {
        return " a ";
    }

    @Override
    public String join() {
        return " JOIN PARKIRALISTE P ON (A.PARKIRALISTEID = P.PARKIRALISTEID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            
            Parkiraliste p = new Parkiraliste(rs.getLong("ParkiralisteID"),
                    rs.getString("Adresa"), rs.getInt("Kapacitet"));
            
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"), 
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"),
                    rs.getBoolean("GlavniAdministrator"), p);
            
            lista.add(a);
        }
        
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, KorisnickoIme, Lozinka, GlavniAdministrator, ParkiralisteID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " AdministratorID = " + administratorID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + korisnickoIme + "', '" + lozinka + "', "
                + (glavniAdministrator ? 1 : 0) + ", " + parkiraliste.getParkiralisteID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return  "korisnickoIme = '" + korisnickoIme + "', Lozinka = '" + lozinka + "', ParkiralisteID = " + parkiraliste.getParkiralisteID() + " ";
    }

}
