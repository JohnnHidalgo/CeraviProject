package com.example.johnnhidalgo.ceraviproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T_Cargue {
    private String id;
    private String fecha;
    private  String horno;
    private  String camara;

    public T_Cargue() {
    }

    public T_Cargue(String fecha, String horno, String camara) {
        this.fecha = fecha;
        this.horno = horno;
        this.camara = camara;
    }

    public T_Cargue(String id, String fecha, String horno, String camara) {
        this.id = id;
        this.fecha = fecha;
        this.horno = horno;
        this.camara = camara;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHorno() {
        return horno;
    }

    public void setHorno(String horno) {
        this.horno = horno;
    }

    public String getCamara() {
        return camara;
    }

    public void setCamara(String camara) {
        this.camara = camara;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T_Cargue t_cargue=  (T_Cargue) o;
        return id != null ? id.equals(t_cargue.id) : t_cargue.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static final List<T_Cargue> ITEMS = new ArrayList<T_Cargue>();
    public static final Map<String, T_Cargue> ITEM_MAP = new HashMap<String, T_Cargue>();
    private static final int COUNT = 0;
    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createT_cargueItem(i));
        }
    }
    public static void addItem(T_Cargue item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    public static void updateItem(T_Cargue t_cargue){
        ITEMS.set(ITEMS.indexOf(t_cargue), t_cargue);
        ITEM_MAP.put(t_cargue.getId(), t_cargue);
    }
    public static void deleteItem(T_Cargue t_cargue){
        ITEMS.remove(t_cargue);
        ITEM_MAP.remove(t_cargue);
    }
    private static T_Cargue createT_cargueItem(int position) {
        return new T_Cargue(String.valueOf(position), "Item " + position, makeDetails(position), String.valueOf(position));
    }
    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore ciudad information here.");
        }
        return builder.toString();
    }
}
