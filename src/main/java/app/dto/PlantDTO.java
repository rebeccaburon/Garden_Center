package app.dto;

import app.entities.Plant;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class PlantDTO {
    private long id;
    private String planttype;

    private String name;
    private int maxheight;
    private double price;
    private List<RessellerDTO> resellerDTOS;

    public PlantDTO(Plant plant){
        this.id = plant.getId();
        this.planttype = plant.getPlanttype();
        this.name = plant.getName();
        this.maxheight = plant.getMaxheight();
        this.price = plant.getPrice();
        this.resellerDTOS = RessellerDTO.toDTOList(plant.getResellers());
    }
    @Builder
    public PlantDTO(long id, String planttype, String name, int maxheight, double price) {
        this.id = id;
        this.planttype = planttype;
        this.name = name;
        this.maxheight = maxheight;
        this.price = price;
    }
    public static List<PlantDTO> toDTOList(List<Plant> platns) {
        return platns.stream().map(PlantDTO::new).toList();
    }
}
