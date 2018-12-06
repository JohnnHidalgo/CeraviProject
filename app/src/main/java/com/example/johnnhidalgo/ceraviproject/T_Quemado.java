package com.example.johnnhidalgo.ceraviproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T_Quemado {
    private String id;
    private String fecha;
    private  String horno;
    private  String camara;

    public T_Quemado() {
    }

    public T_Quemado(String fecha, String horno, String camara) {
        this.fecha = fecha;
        this.horno = horno;
        this.camara = camara;
    }

    public T_Quemado(String id, String fecha, String horno, String camara) {
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
        T_Quemado t_quemado=  (T_Quemado) o;
        return id != null ? id.equals(t_quemado.id) : t_quemado.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static final List<T_Quemado> ITEMS = new ArrayList<T_Quemado>();
    public static final Map<String, T_Quemado> ITEM_MAP = new HashMap<String, T_Quemado>();
    private static final int COUNT = 0;
    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createT_quemadoItem(i));
        }
    }
    public static void addItem(T_Quemado item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    public static void updateItem(T_Quemado t_quemado){
        ITEMS.set(ITEMS.indexOf(t_quemado), t_quemado);
        ITEM_MAP.put(t_quemado.getId(), t_quemado);
    }
    public static void deleteItem(T_Quemado t_quemado){
        ITEMS.remove(t_quemado);
        ITEM_MAP.remove(t_quemado);
    }
    private static T_Quemado createT_quemadoItem(int position) {
        return new T_Quemado(String.valueOf(position), "Item " + position, makeDetails(position), String.valueOf(position));
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
