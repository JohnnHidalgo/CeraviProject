package com.example.johnnhidalgo.ceraviproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T_Arcilla {
    private String id;
    private String fecha;
    private String Hora;
    private String TransportistaName;

    public T_Arcilla() {
    }


    public T_Arcilla(String fecha, String Hora, String TransportistaName) {
        this.fecha = fecha;
        this.Hora = Hora;
        this.TransportistaName = TransportistaName;
    }
    public T_Arcilla(String id, String fecha, String hora, String transportistaName) {
        this.id = id;
        this.fecha = fecha;
        Hora = hora;
        TransportistaName = transportistaName;
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
    public String getHora() {
        return Hora;
    }
    public void setHora(String hora) {
        Hora = hora;
    }
    public String getTransportistaName() {
        return TransportistaName;
    }
    public void setTransportistaName(String transportistaName) {
        TransportistaName = transportistaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T_Arcilla t_arcilla = (T_Arcilla) o;
        return id != null ? id.equals(t_arcilla.id) : t_arcilla.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    public static final List<T_Arcilla> ITEMS = new ArrayList<T_Arcilla>();
    public static final Map<String, T_Arcilla> ITEM_MAP = new HashMap<String, T_Arcilla>();
    private static final int COUNT = 0;
    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createT_arcillaItem(i));
        }
    }
    public static void addItem(T_Arcilla item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    public static void updateItem(T_Arcilla t_arcilla){
        ITEMS.set(ITEMS.indexOf(t_arcilla), t_arcilla);
        ITEM_MAP.put(t_arcilla.getId(), t_arcilla);
    }
    public static void deleteItem(T_Arcilla t_arcilla){
        ITEMS.remove(t_arcilla);
        ITEM_MAP.remove(t_arcilla);
    }
    private static T_Arcilla createT_arcillaItem(int position) {
        return new T_Arcilla(String.valueOf(position), "Item " + position, makeDetails(position), String.valueOf(position));
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
