package com.example.johnnhidalgo.ceraviproject.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {
    /*********CLIENTE*********/
    public static final List<Cliente> ITEMS = new ArrayList<Cliente>();
    public static final Map<String, Cliente> ITEM_MAP = new HashMap<String, Cliente>();
    private static final int COUNT = 0;
    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Cliente item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static void updateItem(Cliente cliente) {
        ITEMS.set(ITEMS.indexOf(cliente), cliente);
        ITEM_MAP.put(cliente.getId(), cliente);
    }

    public static void deleteItem(Cliente cliente) {
        ITEMS.remove(cliente);
        ITEM_MAP.remove(cliente);
    }

    private static Cliente createDummyItem(int position) {
        return new Cliente(String.valueOf(position), "Item " + position, makeDetails(position), String.valueOf(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class Cliente {
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

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

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

    }





    /*********OBRERO*********/

    public static final List<Obrero> ITEMSOb = new ArrayList<Obrero>();
    public static final Map<String, Obrero> ITEM_MAPOb = new HashMap<String, Obrero>();
    private static final int COUNTOb = 0;

    static {
        for (int i = 1; i <= COUNTOb; i++) {
            addItemOb(createDummyItemOb(i));
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

    private static Obrero createDummyItemOb(int position) {
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

    public static class Obrero {
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
    }


    /*********TRANSPORTISTA DE ARCILLA*********/
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
    public static class TransArcilla{
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
    }//TransArcilla



    /*********TRANSPORTISTA DE LADRILLO*********/
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
    public static class TransLadrillo{
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

    }//TransLadrillo


}//Fin DummyContent