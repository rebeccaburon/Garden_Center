package app.dto;

import app.entities.Reseller;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RessellerDTO {
    private long id;
    private String name;
    private String address;
    private int phone;
    @JsonProperty("plants")
    private List<PlantDTO> plantsDTO;


    public RessellerDTO(Reseller reseller){
        this.id = reseller.getId();
        this.name = reseller.getName();
        this.address = reseller.getAddress();
        this.phone = reseller.getPhone();
        this.plantsDTO = PlantDTO.toDTOList(reseller.getPlants());
    }

    public static List<RessellerDTO> toDTOList(List<Reseller> resellers){
        return resellers.stream().map(RessellerDTO::new).toList();
    }

}
