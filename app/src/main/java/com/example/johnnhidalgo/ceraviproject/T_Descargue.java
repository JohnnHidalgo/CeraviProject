package com.example.johnnhidalgo.ceraviproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T_Descargue {
    private String id;
    private String fecha;
    private  String horno;
    private  String camara;

    public T_Descargue() {
    }

    public T_Descargue(String fecha, String horno, String camara) {
        this.fecha = fecha;
        this.horno = horno;
        this.camara = camara;
    }

    public T_Descargue(String id, String fecha, String horno, String camara) {
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
        T_Descargue t_descargue=  (T_Descargue) o;
        return id != null ? id.equals(t_descargue.id) : t_descargue.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static final List<T_Descargue> ITEMS = new ArrayList<T_Descargue>();
    public static final Map<String, T_Descargue> ITEM_MAP = new HashMap<String, T_Descargue>();
    private static final int COUNT = 0;
    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createT_descargueItem(i));
        }
    }
    public static void addItem(T_Descargue item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    public static void updateItem(T_Descargue t_descargueo){
        ITEMS.set(ITEMS.indexOf(t_descargueo), t_descargueo);
        ITEM_MAP.put(t_descargueo.getId(), t_descargueo);
    }
    public static void deleteItem(T_Descargue t_descargueo){
        ITEMS.remove(t_descargueo);
        ITEM_MAP.remove(t_descargueo);
    }
    private static T_Descargue createT_descargueItem(int position) {
        return new T_Descargue(String.valueOf(position), "Item " + position, makeDetails(position), String.valueOf(position));
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
