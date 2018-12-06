package com.example.johnnhidalgo.ceraviproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cliente {
    private String id;
    private String nombre;
    private String ciudad;
    private String telefono;
    private String nit;

    public Cliente() {
    }

    public Cliente(String nombre, String telefono, String ciudad, String nit) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.nit=nit;
    }

    public Cliente(String id, String nombre, String ciudad, String telefono, String nit) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.nit=nit;
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

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono;}

    public String getNit() { return nit; }

    public void setNit(String nit) { this.nit = nit;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id != null ? id.equals(cliente.id) : cliente.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static final List<Cliente> ITEMS = new ArrayList<Cliente>();
    public static final Map<String, Cliente> ITEM_MAP = new HashMap<String, Cliente>();
    private static final int COUNT = 0;
    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createClienteItem(i));
        }
    }
    public static void addItem(Cliente item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    public static void updateItem(Cliente cliente){
        ITEMS.set(ITEMS.indexOf(cliente), cliente);
        ITEM_MAP.put(cliente.getId(), cliente);
    }
    public static void deleteItem(Cliente cliente){
        ITEMS.remove(cliente);
        ITEM_MAP.remove(cliente);
    }
    private static Cliente createClienteItem(int position) {
        return new Cliente(String.valueOf(position), "Item " + position, makeDetails(position), String.valueOf(position));
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
