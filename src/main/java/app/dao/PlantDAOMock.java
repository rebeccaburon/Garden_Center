package app.dao;

import app.dto.PlantDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlantDAOMock implements IDAO<PlantDTO> {
    public static List<PlantDTO> plants = new ArrayList<>();
    private static Long idCounter = 1L;

    private static PlantDAOMock instance;

    public static PlantDAOMock getInstance() {
        if (instance == null) {
            instance = new PlantDAOMock();
            plants.addAll(instance.populate());
        }
        return instance;
    }

    public List<PlantDTO> populate() {
        List<PlantDTO> initialPlants = List.of(
                PlantDTO.builder()
                        .id(idCounter++)
                        .planttype("Rose")
                        .name("Albertine")
                        .maxheight(400)
                        .price(199.99)
                        .build(),
                PlantDTO.builder()
                        .id(idCounter++)
                        .planttype("Bush")
                        .name("Aronia")
                        .maxheight(200)
                        .price(159.99)
                        .build(),
                PlantDTO.builder()
                        .id(idCounter++)
                        .planttype("Rose")
                        .name("Albertine")
                        .maxheight(80)
                        .price(299.99)
                        .build(),
                PlantDTO.builder()
                        .id(idCounter++)
                        .planttype("Cactus")
                        .name("Golden Barrel")
                        .maxheight(80)
                        .price(99.99)
                        .build(),
                PlantDTO.builder()
                        .id(idCounter++)
                        .planttype("Fern")
                        .name("Boston Fern")
                        .maxheight(60)
                        .price(49.99)
                        .build(),
                PlantDTO.builder()
                        .id(idCounter++)
                        .planttype("Herb")
                        .name("Lavender")
                        .maxheight(90)
                        .price(29.99)
                        .build()

        );
        return initialPlants;
    }

    @Override
    public List<PlantDTO> getAll() {
        return new ArrayList<>(plants);

    }

    @Override
    public PlantDTO getById(Long id) {
        return plants.stream()
                .filter(plant -> plant.getId() == id)
                .findFirst()
                .orElse(null);
    }


    @Override
    public List<PlantDTO> getByType(String type) {
        return plants.stream()
                .filter(plant -> plant.getPlanttype().equals(type))
                .collect(Collectors.toList());

    }

    @Override
    public PlantDTO add(PlantDTO plant) {
        plant.setId(idCounter);
        plants.add(plant);
        idCounter++;
        return plant;
    }

    @Override
    public List<PlantDTO> plantsWithMaxHeight(int maxheight) {
        return plants.stream()
                .filter(p -> p.getMaxheight() == maxheight)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getPlantNames() {
        return plants.stream()
                .map(PlantDTO::getName)
                .collect(Collectors.toList());
    }

    public List<PlantDTO> plantsSortedByName() {
        return plants.stream()
                .sorted(Comparator.comparing(PlantDTO::getName))
                .collect(Collectors.toList());
    }

}
