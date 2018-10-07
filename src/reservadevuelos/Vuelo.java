/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservadevuelos;

import java.time.LocalDate;     ///se importa esta libreria para poder usar las fechas

/**
 *
 * @author Grupo30
 */
public class Vuelo {
    private int id_vuelo=(-1);
    private Ciudad origen;
    private Ciudad destino;
    private String aerolinea;
    private String modelo;
    private LocalDate fechaLlegada;
    private LocalDate fechaSalida;
    
    ///constructor con ID
    public Vuelo(int id,Ciudad origen,Ciudad destino,String aerolinea,String modelo,LocalDate fechaLlegada,LocalDate fechaSalida){
        this.id_vuelo=id;
        this.origen=origen;
        this.destino=destino;
        this.aerolinea=aerolinea;
        this.modelo=modelo;
        this.fechaLlegada=fechaLlegada;
        this.fechaSalida=fechaSalida;
    }
    
    ///constructor sin ID
    public Vuelo(Ciudad origen,Ciudad destino,String aerolinea,String modelo,LocalDate fechaLlegada,LocalDate fechaSalida){
        this.origen=origen;
        this.destino=destino;
        this.aerolinea=aerolinea;
        this.modelo=modelo;
        this.fechaLlegada=fechaLlegada;
        this.fechaSalida=fechaSalida;
    }
    
    ///constructor por defecto
    public Vuelo(){}
    
    ///seters y getters

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    
}
