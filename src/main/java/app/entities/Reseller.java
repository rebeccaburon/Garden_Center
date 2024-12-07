package app.entities;

import app.dto.RessellerDTO;
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
@Table(name = "reseller")
public class Reseller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    private String address;
    private int phone;

    @ManyToMany
    @JoinTable(name = "reseller_plant", joinColumns = @JoinColumn (name = "reseller_id"), inverseJoinColumns = @JoinColumn (name = "plant_id")) // Foreign key for Plant in the join table
    private List<Plant> plants = new ArrayList<>();

    public Reseller (RessellerDTO resellerDTO) {
        this.name = resellerDTO.getName();
        this.address = resellerDTO.getAddress();
        this.phone = resellerDTO.getPhone();
    }

}
