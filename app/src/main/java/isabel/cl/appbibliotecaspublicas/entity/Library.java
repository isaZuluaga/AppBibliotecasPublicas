package isabel.cl.appbibliotecaspublicas.entity;

import java.io.Serializable;

/**
 * Created by Isabel on 28/05/18.
 */
public class Library implements Serializable {

    private int id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo;
    private String link;
    private String tipo;
    private String horario;
    private String barrio;
    private String comuna;

    // Constructor


    public Library() {
        // este constructor es el que permite construir los objetos
    }

    public Library(int id, String nombre, String telefono, String direccion, String correo, String link, String tipo, String horario, String barrio, String comuna) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.link = link;
        this.tipo = tipo;
        this.horario = horario;
        this.barrio = barrio;
        this.comuna = comuna;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
}