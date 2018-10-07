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
public class Ciudad {
    private int id_ciudad=(-1);
    private String nombre;
    private boolean vigencia=false;
    private String pais;
    
    ///constructor por defecto
    public Ciudad(){}
    
    ///constructor con ID
    public Ciudad(int id, String nombre, boolean vig,String pais){
        this.id_ciudad=id;
        this.nombre=nombre;
        this.vigencia=vig;
        this.pais=pais;
    }
    
    ///constructor sin ID
    public Ciudad(String nombre, boolean vig,String pais){
        this.nombre=nombre;
        this.vigencia=vig;
        this.pais=pais;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    
    
}
