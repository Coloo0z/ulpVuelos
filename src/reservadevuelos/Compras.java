/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservadevuelos;

import java.time.LocalDate;

/**
 *
 * @author Grupo30
 */
public class Compras {
    private int id_compra;
    private Cliente cliente;
    private Asiento asiento;
    private LocalDate fechaCompra;
    private double monto;
    
    ///constructor por defecto
    public Compras(){}
    
    ///constructor con ID
    public Compras(int id_compra,Cliente cliente,Asiento asiento,LocalDate fechaCompra,double monto){
        this.id_compra=id_compra;
        this.cliente=cliente;
        this.asiento=asiento;
        this.fechaCompra=fechaCompra;
        this.monto=monto;
    }
    
     ///constructor sin ID
    public Compras(Cliente cliente,Asiento asiento,LocalDate fechaCompra,double monto){
        this.cliente=cliente;
        this.asiento=asiento;
        this.fechaCompra=fechaCompra;
        this.monto=monto;
    }
    
    //seters y getters
    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
}
