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
public class Asiento {
    private int id_asiento=(-1);
    private String letra;
    private int numero;
    private boolean esPasillo;
    private int disponibilidad=0;
    private double precio;
    private Vuelo vuelo;
    
    ///constructor por defecto
    public Asiento(){}
    
    ///constructor con ID
    public Asiento(int id_asiento, String letra, int numero, boolean esPasillo, int disponibilidad, double precio, Vuelo vuelo){
        this.id_asiento=id_asiento;
        this.letra=letra;
        this.numero=numero;
        this.esPasillo=esPasillo;
        this.disponibilidad=disponibilidad;
        this.precio=precio;
        this.vuelo=vuelo;
    }
    
    ///constructor sin ID
    public Asiento( String letra, int numero, boolean esPasillo, int disponibilidad, double precio, Vuelo vuelo){
        this.letra=letra;
        this.numero=numero;
        this.esPasillo=esPasillo;
        this.disponibilidad=disponibilidad;
        this.precio=precio;
        this.vuelo=vuelo;
    }
    
    ///seters y getters

    public int getId_asiento() {
        return id_asiento;
    }

    public void setId_asiento(int id_asiento) {
        this.id_asiento = id_asiento;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isEsPasillo() {
        return esPasillo;
    }

    public void setEsPasillo(boolean esPasillo) {
        this.esPasillo = esPasillo;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    
}
