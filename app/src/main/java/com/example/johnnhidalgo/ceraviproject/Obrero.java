package com.example.johnnhidalgo.ceraviproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Obrero {
    private String id;
    private String nombre;
    private String ciudad;
    private String telefono;

    public Obrero() {
    }

    public Obrero(String nombre, String telefono, String ciudad) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    public Obrero(String id, String nombre, String ciudad, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Obrero obrero = (Obrero) o;
        return id != null ? id.equals(obrero.id) : obrero.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    public static final List<Obrero> ITEMSOb = new ArrayList<Obrero>();
    public static final Map<String, Obrero> ITEM_MAPOb = new HashMap<String, Obrero>();
    private static final int COUNTOb = 0;

    static {
        for (int i = 1; i <= COUNTOb; i++) {
            addItemOb(createObreroItem(i));
        }
    }

    public static void addItemOb(Obrero item) {
        ITEMSOb.add(item);
        ITEM_MAPOb.put(item.id, item);
    }

    public static void updateItemOb(Obrero obrero) {
        ITEMSOb.set(ITEMSOb.indexOf(obrero), obrero);
        ITEM_MAPOb.put(obrero.getId(), obrero);
    }

    public static void deleteItemOb(Obrero obrero) {
        ITEMSOb.remove(obrero);
        ITEM_MAPOb.remove(obrero);
    }

    private static Obrero createObreroItem(int position) {
        return new Obrero(String.valueOf(position), "Item " + position, makeDetailsOb(position));
    }

    private static String makeDetailsOb(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore ciudad information here.");
        }
        return builder.toString();
    }
}
