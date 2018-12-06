package com.example.johnnhidalgo.ceraviproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransArcilla {
    private String id;
    private String nombre;
    private String telefono;
    private String cooperativa;
    private String placa;

    public TransArcilla(){}

    public TransArcilla(String nombre, String telefono , String cooperativa,String placa ) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.cooperativa = cooperativa;
        this.placa = placa;
    }
    public TransArcilla(String id, String nombre, String telefono, String cooperativa, String placa) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.cooperativa = cooperativa;
        this.placa = placa;

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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransArcilla transArcilla = (TransArcilla) o;
        return id != null ? id.equals(transArcilla.id) : transArcilla.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }



    public static final List<TransArcilla> ITEMSTRA = new ArrayList<TransArcilla>();
    public static final Map<String, TransArcilla> ITEM_MAPTRA = new HashMap<String, TransArcilla>();
    private static final int COUNTTRA = 0;
    static {
        for(int i =1 ;i<=COUNTTRA;i++){
            addItemTra(createDummyItemTRA(i));
        }
    }
    public static void addItemTra(TransArcilla item) {
        ITEMSTRA.add(item);
        ITEM_MAPTRA.put(item.id, item);
    }
    public static void updateItemTra(TransArcilla transArcilla){
        ITEMSTRA.set(ITEMSTRA.indexOf(transArcilla), transArcilla);
        ITEM_MAPTRA.put(transArcilla.getId(), transArcilla);
    }
    public static void deleteItemTra(TransArcilla transArcilla){
        ITEMSTRA.remove(transArcilla);
        ITEM_MAPTRA.remove(transArcilla);
    }
    private static TransArcilla createDummyItemTRA(int position) {
        return new TransArcilla(String.valueOf(position), "Item " + position, makeDetailsTRA(position), String.valueOf(position));
    }
    private static String makeDetailsTRA(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore ciudad information here.");
        }
        return builder.toString();
    }
}
