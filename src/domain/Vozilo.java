/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marko Milosevic
 */
public class Vozilo extends AbstractDomainObject {

    private Organizacija organizacija;
    private String registarskiBroj;
    private String marka;

    public Vozilo() {
    }

    public Vozilo(Organizacija organizacija, String registarskiBroj, String marka) {
        this.organizacija = organizacija;
        this.registarskiBroj = registarskiBroj;
        this.marka = marka;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(String registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    @Override
    public String toString() {
        return marka + ": " + registarskiBroj;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        
        final Vozilo other = (Vozilo) obj;
        
        if (!this.getRegistarskiBroj().equals(other.getRegistarskiBroj())) {
            return false;
        }
        return true;
    }

    @Override
    public String nazivTabele() {
        return " vozilo ";
    }

    @Override
    public String alijas() {
        return " v ";
    }

    @Override
    public String join() {
        return " JOIN ORGANIZACIJA O ON (O.ORGANIZACIJAID = V.ORGANIZACIJAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Organizacija o = new Organizacija(rs.getLong("OrganizacijaID"),
                    rs.getString("Naziv"), rs.getString("Adresa"), null);

            Vozilo v = new Vozilo(o, rs.getString("RegistarskiBroj"),
                    rs.getString("Marka"));

            lista.add(v);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (OrganizacijaID, RegistarskiBroj, Marka) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " OrganizacijaID = " + organizacija.getOrganizacijaID() + " "
                + "AND RegistarskiBroj = '" + registarskiBroj + "' ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + organizacija.getOrganizacijaID() + ", "
                + "'" + registarskiBroj + "', '" + marka + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    public Organizacija getOrganizacija() {
        return organizacija;
    }

    public void setOrganizacija(Organizacija organizacija) {
        this.organizacija = organizacija;
    }

}
