/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservadevuelos;

/**
 *
 * @author Grupo30
 */
public class Cliente {
    private int id_cliente;
    private String nPasaporte;
    private String nTarjeta;
    private String nombre;
    
    ///constructor por defecto
    public Cliente(){}
    
    ///constructor con ID
    public Cliente(int id_cliente,String nPasaporte,String nTarjeta,String nombre){
        this.id_cliente=id_cliente;
        this.nPasaporte=nPasaporte;
        this.nTarjeta=nTarjeta;
        this.nombre=nombre;
    }
    
    ///constructor sin ID
    public Cliente(String nPasaporte,String nTarjeta,String nombre){
        this.nPasaporte=nPasaporte;
        this.nTarjeta=nTarjeta;
        this.nombre=nombre;
    }
    
    ///seters y getters
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getnPasaporte() {
        return nPasaporte;
    }

    public void setnPasaporte(String nPasaporte) {
        this.nPasaporte = nPasaporte;
    }

    public String getnTarjeta() {
        return nTarjeta;
    }

    public void setnTarjeta(String nTarjeta) {
        this.nTarjeta = nTarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
