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

}
