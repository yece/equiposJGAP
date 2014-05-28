/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 * @author Henry Paz
 */
public class Equipo {

   private String nombre;
    private int rFifa;
    private String grupo;
    private int nCopas;
    private int partidoA;
    private int partidoB;
    private int partidoC;

    public Equipo() {
        this.nombre = "";
        this.rFifa = 0;
        this.grupo = "";
        this.nCopas = 0;
        this.partidoA = 0;
        this.partidoB = 0;
        this.partidoC = 0;
    }

    public Equipo(String nombre, int rFifa, String grupo, int nCopas, int partidoA, int partidoB, int partidoC) {
        this.nombre = nombre;
        this.rFifa = rFifa;
        this.grupo = grupo;
        this.nCopas = nCopas;
        this.partidoA = partidoA;
        this.partidoB = partidoB;
        this.partidoC = partidoC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getrFifa() {
        return rFifa;
    }

    public void setrFifa(int rFifa) {
        this.rFifa = rFifa;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getnCopas() {
        return nCopas;
    }

    public void setnCopas(int nCopas) {
        this.nCopas = nCopas;
    }

    public int getPartidoA() {
        return partidoA;
    }

    public void setPartidoA(int partidoA) {
        this.partidoA = partidoA;
    }

    public int getPartidoB() {
        return partidoB;
    }

    public void setPartidoB(int partidoB) {
        this.partidoB = partidoB;
    }

    public int getPartidoC() {
        return partidoC;
    }

    public void setPartidoC(int partidoC) {
        this.partidoC = partidoC;
    }

     
     @Override
    public String toString() {
        return "Equipo [Nombre= " + nombre + ", Ranking Fifa= " + rFifa+", Grupo= " + grupo+  ", Copas Ganadas=" + nCopas + ", Partido A=" + partidoA + ", Partido B=" + partidoB + ", Partido C=" + partidoC + "]\n";
    }
}
