package co.com.sofka.domain.team;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.BicycleType;
import co.com.sofka.domain.team.values.MechanicId;
import co.com.sofka.domain.team.values.Tool;

import java.util.Objects;
import java.util.Set;

public class Mechanic extends Entity<MechanicId>{

    protected BicycleType bicycleType;
    protected PersonalInformation personalInformation;
    protected Set<Tool> tools;

    public Mechanic(MechanicId mechanicId, BicycleType bicycleType, PersonalInformation personalInformation, Set<Tool> tools) {
        super(mechanicId);
        Objects.requireNonNull(mechanicId, "mechanicId can't be null");
        Objects.requireNonNull(bicycleType, "bicycleType can't be null");
        Objects.requireNonNull(personalInformation, "personalInformation can't be null");
        Objects.requireNonNull(tools, "tools can't be null");
        this.bicycleType = bicycleType;
        this.personalInformation = personalInformation;
        this.tools = tools;
    }

    public void agregarHerramienta(String funcion, Double tamanho){
        Objects.requireNonNull(funcion, "function can't be null");
        Objects.requireNonNull(tamanho, "tamanho can't be null");
        this.tools.add(new Tool(funcion, tamanho));

    }

    public void cambiarTipoBicicleta(String pinhon, String especialidad){
        Objects.requireNonNull(pinhon, "pinhon can't be null");
        Objects.requireNonNull(especialidad, "especialidad can't be null");
        this.bicycleType = new BicycleType(pinhon, especialidad);
    }

    void cambiarTelefonoMecanico(String telefono){
        Objects.requireNonNull(telefono, "telefono can't be null");
        this.personalInformation = personalInformation.updatePhoneNumber(telefono);
    }

    public BicycleType tipoBicicleta() {
        return bicycleType;
    }

    public PersonalInformation datosPersonales() {
        return personalInformation;
    }

    public Set<Tool> herramientas() {
        return tools;
    }
}
