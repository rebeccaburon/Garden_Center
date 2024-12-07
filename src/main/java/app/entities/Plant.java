package app.entities;

import app.dto.PlantDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "plant")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column( nullable = false)
    private String planttype;
    @Column( nullable = false)
    private String name;
    private int maxheight;
    @Column( nullable = false)
    private double price;

    @ManyToMany(mappedBy = "plants", fetch = FetchType.EAGER)
    @JsonIgnore // avoiding stackoverflow
    private List<Reseller> resellers = new ArrayList<>();

    public Plant (PlantDTO plantDTO){
        this.id = plantDTO.getId();
        this.planttype = plantDTO.getPlanttype();
        this.name = plantDTO.getName();
        this.maxheight = plantDTO.getMaxheight();
        this.price = plantDTO.getPrice();

    }

    public void addReseller(Reseller reseller){
        this.resellers.add(reseller);
        if(!reseller.getPlants().contains(this)){
            reseller.getPlants().add(this);
        }
    }

}
