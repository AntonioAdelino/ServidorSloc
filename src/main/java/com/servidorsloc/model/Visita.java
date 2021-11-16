package com.servidorsloc.model;

import javax.persistence.*;

@Entity
@Table(name = "visita")
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int distanciaCheckin;

    @ManyToOne
    private Rota rota;

    @OneToOne
    private Profissional profissional;

    public Visita(int distanciaCheckin, Rota rota, Profissional profissional) {
        this.distanciaCheckin = distanciaCheckin;
        this.rota = rota;
        this.profissional = profissional;
    }

    public Visita() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDistanciaCheckin() {
        return distanciaCheckin;
    }

    public void setDistanciaCheckin(int distanciaCheckin) {
        this.distanciaCheckin = distanciaCheckin;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
}
