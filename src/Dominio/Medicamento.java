package Dominio;

import java.util.Date;

/**
 * @author grupo1
 */
public class Medicamento {

    private int id;
    private String nombre;
    private Double stock;
    private Double stock_min;
    private String unidad;
    private Date vencimiento;

    public Medicamento() {
        this.nombre = "";
        this.stock = 0.00;
        this.stock_min = 0.00;
        this.vencimiento = new Date();
    }

    public Medicamento(int codigo, String nombre, Double stock, Double stock_min, String unidad, Date vencimiento) {
        this.id = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.stock_min = stock_min;
        this.unidad = unidad;
        this.vencimiento = vencimiento;
    }

    public int getCodigo() {
        return id;
    }

    public void setCodigo(int codigo) {
        this.id = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getStock_min() {
        return stock_min;
    }

    public void setStock_min(Double stock_min) {
        this.stock_min = stock_min;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

}
