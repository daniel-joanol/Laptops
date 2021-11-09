package com.sessiones8910.Laptops.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
@ApiModel("Representación de la entidad laptop")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave primaria auto-incremental")
    private Long id;
    @ApiModelProperty("Modelo")
    private String model;
    @ApiModelProperty("Nombre del fabricante")
    private String fabricante;
    @ApiModelProperty("Capacidad del disco rígido")
    private int hd;
    @ApiModelProperty("Valor de memoria RAM")
    private int ram;
    @ApiModelProperty("Tamaño de la pantalla")
    private int screen;

    public Laptop() {}

    public Laptop(Long id, String model, String fabricante, int hd, int ram, int screen) {
        this.id = id;
        this.model = model;
        this.fabricante = fabricante;
        this.hd = hd;
        this.ram = ram;
        this.screen = screen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getHd() {
        return hd;
    }

    public void setHd(int hd) {
        this.hd = hd;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }
}
