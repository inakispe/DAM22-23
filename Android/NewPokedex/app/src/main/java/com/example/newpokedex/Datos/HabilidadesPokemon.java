package com.example.newpokedex.Datos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HabilidadesPokemon {
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("abilites")
    @Expose
    List<Abilities> abilities;
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("sprites")
    @Expose
    Sprites sprites;
    @SerializedName("stats")
    @Expose
    List<Stats> stats;

    @SerializedName("types")
    @Expose
    private List<Tipos> types;

    @SerializedName("base_experience")
    @Expose
    String base_experience;

    public String getName() {
        return name;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public int getId() {
        return id;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public List<Stats> getStats() {
        return stats;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }

    public List<Tipos> getTypes() {
        return types;
    }

    public String getBase_experience() {
        return base_experience;
    }
}
