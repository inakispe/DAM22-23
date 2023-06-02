package com.example.newpokedex.Datos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Abilities {
@SerializedName("ability")
    @Expose
    List ability;

    public List getAbility() {
        return ability;
    }

    public void setAbility(List<Ability> ability) {
        this.ability = ability;
    }
@SerializedName("is_hidden")
    @Expose
    String is_hidden;

@SerializedName("slot")
    @Expose
    int slot;

    public String getIs_hidden() {
        return is_hidden;
    }

    public int getSlot() {
        return slot;
    }
}
