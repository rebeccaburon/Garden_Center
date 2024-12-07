package app.controller;

import app.dao.PlantDAO;
import app.dto.PlantDTO;
import app.dto.RessellerDTO;
import app.entities.Reseller;
import app.exceptions.ApiException;
import io.javalin.http.Context;

import java.util.List;

public class PlantController {
    private PlantDAO plantDAO;
    private PlantDTO plantDTO;

    public PlantController(PlantDAO plantDAO) {
        this.plantDAO = plantDAO;
    }

    public void createReseller (Context ctx) {
        try {
            RessellerDTO[] newRessellers = ctx.bodyAsClass(RessellerDTO[].class);
            RessellerDTO[] savedRessellers = new RessellerDTO[newRessellers.length];

            int i = 0;
            for (RessellerDTO resseller : newRessellers) {
                RessellerDTO savedReseller = plantDAO.createReseller(resseller);
                savedRessellers[i] = savedReseller;
                i++;
            }
            ctx.res().setStatus(201);
            ctx.json(savedRessellers, RessellerDTO.class);
        } catch (Exception e) {
            throw new ApiException(400, e.getMessage());
        }
    }

    public void getPlantsByReseller(Context ctx) {
        try {
            long resellerId = Long.parseLong(ctx.pathParam("resellerid"));
            List<PlantDTO> plantsByReseller = plantDAO.getPlantsByReseller(resellerId);
            if (plantsByReseller.isEmpty()) {
                throw new ApiException(204, "No Content, No Plant by Reseller, with id" + resellerId + " was  found");
            } else {
                ctx.status(200);
                ctx.json(plantsByReseller);
            }
        } catch (ApiException e) {
            throw new ApiException(400, e.getMessage());
        }
    }



    public void addPlantToReseller(Context ctx) {
        try {
            long resellerId = Long.parseLong(ctx.pathParam("resellerid"));

            long plantId = Long.parseLong(ctx.pathParam("plantid"));

            Reseller updatedReseller = plantDAO.addPlantToReseller(resellerId, plantId);

            if (updatedReseller != null) {
                ctx.status(200);
                ctx.json(updatedReseller);
            } else {
                throw new ApiException(204, "NO CONTENT, Reseller " + resellerId + " not added");
            }

        } catch (ApiException e) {
            throw new ApiException(400, e.getMessage());
        }

    }
    public void deletePlant(Context ctx) {
        try {
            long plantId = Long.parseLong(ctx.pathParam("plantId"));
            plantDAO.deletePlant(plantId);
            ctx.status(204);
            ctx.result("Plant deleted");
        } catch (Exception e) {
            throw new ApiException(400, e.getMessage());
        }

    }



}
