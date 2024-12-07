package app.controller;

import app.dao.PlantDAOMock;
import app.dto.PlantDTO;
import app.exceptions.ApiException;
import io.javalin.http.Context;
import java.util.List;


public class PlantControllerMock {
    private final PlantDAOMock daoMock;

    public PlantControllerMock(PlantDAOMock daoMock) {
        this.daoMock = PlantDAOMock.getInstance();
    }


    public void getAll(Context ctx) {
        List<PlantDTO> plants = daoMock.getAll();
        try {
            if (plants.isEmpty()) {
                throw new ApiException(204, "NO CONTENT, no Plants was found");
            } else {
                ctx.status(200);
                ctx.json(plants);
            }
        } catch (ApiException e) {
            throw new ApiException(400, e.getMessage());
        }

    }

    public void getById(Context ctx) {
        long id = Long.parseLong(ctx.pathParam("id"));
        PlantDTO plant = daoMock.getById(id);
        try {
            if (plant != null) {
                ctx.status(200);
                ctx.json(plant);
            } else {
                throw new ApiException(404, "File not found, no Plants with id " + id + " was found");
            }
        } catch (ApiException e) {
            throw new ApiException(400, e.getMessage());
        }

    }

    public void getByType(Context ctx) {
        String type = ctx.pathParam("types");
        List<PlantDTO> plants = daoMock.getByType(type);
        try {
            if (plants.isEmpty()) {
                throw new ApiException(204, "NO CONTENT, no Plant with type " + type + " was found");
            }
            ctx.status(200);
            ctx.json(plants);
        } catch (ApiException e) {
            throw new ApiException(400, e.getMessage());
        }

    }

    public void add(Context ctx) {
        try {
            PlantDTO newPlant = ctx.bodyAsClass(PlantDTO.class);
            PlantDTO addedPlant = daoMock.add(newPlant);
            if (addedPlant != null) {
                ctx.status(200);
                ctx.json(addedPlant);
            }
        } catch (Exception e) {
            throw new ApiException(400, e.getMessage());
        }

    }

    public void plantsWithMaxHeight(Context ctx) {

        int maxheight = Integer.parseInt(ctx.pathParam("maxheight"));
        List<PlantDTO> plants = daoMock.plantsWithMaxHeight(maxheight);
        try {
            if (maxheight < 100) {
                if (plants.isEmpty()) {
                    throw new ApiException(204, "NO CONTENT, no Plant with maxheight " + maxheight + " was found");
                }
                ctx.status(200);
                ctx.json(plants);
            }
        } catch (ApiException e) {
            throw new ApiException(400, e.getMessage());
        }

    }

    public void getPlantNames(Context ctx) {
        String plantName = ctx.pathParam("name");
        List<String> plants = daoMock.getPlantNames();
        try {
            if (plants.isEmpty()) {
                throw new ApiException(204, "NO CONTENT, no Plants with names " + plantName + " found");
            } else {
                ctx.status(200);
                ctx.json(plants);
            }
        } catch (ApiException e) {
            throw new ApiException(400, e.getMessage());
        }

    }

    public void plantsSortedByName(Context ctx) {
        String plantName = ctx.pathParam("names");
        List<PlantDTO> plants = daoMock.plantsSortedByName();
        try {
            if (plants.isEmpty()) {
                throw new ApiException(204, "NO CONTENT, no Plants with names " + plantName + " found");
            } else {
                ctx.status(200);
                ctx.json(plants);
            }
        } catch (ApiException e) {
            throw new ApiException(400, e.getMessage());
        }
    }


}
