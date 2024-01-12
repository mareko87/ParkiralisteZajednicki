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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marek
 */
public class Racun extends AbstractDomainObject {

    private Long racunID;
    private Date datum;
    private int brojRezervsisanihMesta;
    private double iznosBezPopusta;
    private double popust;
    private double iznosSaPopustom;
    private Administrator administrator;
    private Organizacija organizacija;

    public Racun() {
    }

    public Racun(Long racunID, Date datum, int brojRezervsisanihMesta, double iznosBezPopusta, double popust, double iznosSaPopustom, Administrator administrator, Organizacija organizacija) {
        this.racunID = racunID;
        this.datum = datum;
        this.brojRezervsisanihMesta = brojRezervsisanihMesta;
        this.iznosBezPopusta = iznosBezPopusta;
        this.popust = popust;
        this.iznosSaPopustom = iznosSaPopustom;
        this.administrator = administrator;
        this.organizacija = organizacija;
    }

    public Organizacija getOrganizacija() {
        return organizacija;
    }

    public void setOrganizacija(Organizacija organizacija) {
        this.organizacija = organizacija;
    }

    public Long getRacunID() {
        return racunID;
    }

    public void setRacunID(Long racunID) {
        this.racunID = racunID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getBrojRezervsisanihMesta() {
        return brojRezervsisanihMesta;
    }

    public void setBrojRezervsisanihMesta(int brojRezervsisanihMesta) {
        this.brojRezervsisanihMesta = brojRezervsisanihMesta;
    }

    public double getIznosBezPopusta() {
        return iznosBezPopusta;
    }

    public void setIznosBezPopusta(double iznosBezPopusta) {
        this.iznosBezPopusta = iznosBezPopusta;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public double getIznosSaPopustom() {
        return iznosSaPopustom;
    }

    public void setIznosSaPopustom(double iznosSaPopustom) {
        this.iznosSaPopustom = iznosSaPopustom;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        Racun other = (Racun)obj;
        if ((this.getDatum().getDay() != other.getDatum().getDay()) ||
                (this.getDatum().getMonth() != other.getDatum().getMonth()) ||
                (this.getDatum().getYear() != other.getDatum().getYear())) {
            return false;
        }
        if (!this.getOrganizacija().equals(other.getOrganizacija())) {
            return false;
        }
        return true;
    }

    @Override
    public String nazivTabele() {
        return " racun ";
    }

    @Override
    public String alijas() {
        return " r ";
    }

    @Override
    public String join() {
        return " JOIN ORGANIZACIJA O ON (O.ORGANIZACIJAID = R.ORGANIZACIJAID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = R.ADMINISTRATORID) "
                + "JOIN PARKIRALISTE P ON (A.PARKIRALISTEID = P.PARKIRALISTEID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Parkiraliste p = new Parkiraliste(rs.getLong("ParkiralisteID"),
                    rs.getString("Naziv"), rs.getInt("Kapacitet"));

            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"),
                    rs.getBoolean("GlavniAdministrator"), p);
            
            Organizacija o = new Organizacija(rs.getLong("OrganizacijaID"),
                    rs.getString("Naziv"), rs.getString("Adresa"), null);
            
            Racun r = new Racun(rs.getLong("RacunID"), rs.getDate("Datum"), 
                    rs.getInt("BrojRezervisanihMesta"), rs.getDouble("IznosBezPopusta"), 
                    rs.getDouble("Popust"), rs.getDouble("IznosSaPopustom"), a, o);

            lista.add(r);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Datum, BrojRezervisanihMesta, IznosBezPopusta, Popust, IznosSaPopustom,"
                + " AdministratorID, OrganizacijaID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " RacunID = " + racunID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Date(datum.getTime()) + "', " + brojRezervsisanihMesta + ", "
                + iznosBezPopusta + ", " + popust + ", "
                + iznosSaPopustom + ", " + administrator.getAdministratorID() + ", "
                + organizacija.getOrganizacijaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }


}
