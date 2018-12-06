package com.example.johnnhidalgo.ceraviproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T_Moldeado {
    private String id;
    private String fecha;
    private  String cantidad;

    public T_Moldeado() {
    }

    public T_Moldeado(String fecha, String cantidad) {
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public T_Moldeado(String id, String fecha, String cantidad) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T_Moldeado t_moldeado=  (T_Moldeado) o;
        return id != null ? id.equals(t_moldeado.id) : t_moldeado.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static final List<T_Moldeado> ITEMS = new ArrayList<T_Moldeado>();
    public static final Map<String, T_Moldeado> ITEM_MAP = new HashMap<String, T_Moldeado>();
    private static final int COUNT = 0;
    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createTMoldeadoItem(i));
        }
    }
    public static void addItem(T_Moldeado item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    public static void updateItem(T_Moldeado t_moldeado){
        ITEMS.set(ITEMS.indexOf(t_moldeado), t_moldeado);
        ITEM_MAP.put(t_moldeado.getId(), t_moldeado);
    }
    public static void deleteItem(T_Moldeado t_moldeado){
        ITEMS.remove(t_moldeado);
        ITEM_MAP.remove(t_moldeado);
    }

    private static T_Moldeado createTMoldeadoItem(int position) {
        return new T_Moldeado(String.valueOf(position), "Item " + position, makeDetails(position));
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
