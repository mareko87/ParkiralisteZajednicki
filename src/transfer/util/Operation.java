/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author marek
 */
public interface Operation {
    
    public static final int LOGIN = 0;

    public static final int ADD_ADMINISTRATOR = 1;
    public static final int DELETE_ADMINISTRATOR = 2;
    public static final int EDIT_ADMINISTRATOR = 3;
    public static final int GET_ALL_ADMINISTRATOR = 4;
    public static final int GET_ADMINISTRATOR = 15;
    
    public static final int GET_ALL_PARKIRALISTE = 5;
    
    public static final int ADD_ORGANIZACIJA = 6;
    public static final int DELETE_ORGANIZACIJA = 7;
    public static final int EDIT_ORGANIZACIJA = 8;
    public static final int GET_ALL_ORGANIZACIJA = 9;
    public static final int GET_ORGANIZACIJA = 16;
    
    public static final int ADD_VOZILO = 10;
    public static final int DELETE_VOZILO = 11;
    public static final int GET_ALL_VOZILO = 12;
    
    public static final int ADD_RACUN = 13;
    public static final int GET_ALL_RACUN = 14;

}
