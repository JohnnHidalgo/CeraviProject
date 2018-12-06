package com.example.johnnhidalgo.ceraviproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransLadrillo {
    private String id;
    private String nombre;
    private String cooperativa;
    private String telefono;

    public TransLadrillo() {
    }

    public TransLadrillo(String nombre, String cooperativa, String telefono) {
        this.nombre = nombre;
        this.cooperativa = cooperativa;
        this.telefono = telefono;
    }

    public TransLadrillo(String id, String nombre, String cooperativa, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.cooperativa = cooperativa;
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

    public String getCooperativa() {
        return cooperativa;
    }

    public void setCooperativa(String cooperativa) {
        this.cooperativa = cooperativa;
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
        TransLadrillo transLadrillo = (TransLadrillo) o;
        return id != null ? id.equals(transLadrillo.id) : transLadrillo.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static final List<TransLadrillo> ITEMSTRL = new ArrayList<TransLadrillo>();
    public static final Map<String, TransLadrillo> ITEM_MAPTRL = new HashMap<String, TransLadrillo>();
    private static final int COUNTTRL = 0;
    static {
        for (int i = 1; i <= COUNTTRL; i++) {
            addItemTrl(createDummyItemTRL(i));
        }
    }
    public static void addItemTrl(TransLadrillo item) {
        ITEMSTRL.add(item);
        ITEM_MAPTRL.put(item.id, item);
    }
    public static void updateItemTrl(TransLadrillo transLadrillo){
        ITEMSTRL.set(ITEMSTRL.indexOf(transLadrillo), transLadrillo);
        ITEM_MAPTRL.put(transLadrillo.getId(), transLadrillo);
    }
    public static void deleteItemTrl(TransLadrillo transLadrillo){
        ITEMSTRL.remove(transLadrillo);
        ITEM_MAPTRL.remove(transLadrillo);
    }

    private static TransLadrillo createDummyItemTRL(int position) {
        return new TransLadrillo(String.valueOf(position), "Item " + position, makeDetailsTRL(position));
    }

    private static String makeDetailsTRL(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore ciudad information here.");
        }
        return builder.toString();
    }
}
