/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservadevuelos;

import java.sql.Connection;

/**
 *
 * @author lucas
 */
public class ReservaDeVuelos {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Conexion conexion;
        CiudadData cd;
        conexion=new Conexion("localhost/phpmyadmin/db_structure.php?server=1&db=vuelos","root","");
       
        cd = new CiudadData(conexion);
        cd.guardarCiudad(new Ciudad("San Luis", true, "Argentina"));
        cd.guardarCiudad(new Ciudad(100,"San Luis", true, "Argentina"));
        System.out.println("fin.");
        
    }
    
}
