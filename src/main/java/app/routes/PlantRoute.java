package app.routes;

import app.controller.PlantController;
import app.dao.PlantDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;


public class PlantRoute {
    private PlantController plantController;

    public PlantRoute(EntityManagerFactory emf) {
        plantController = new PlantController(new PlantDAO(emf));
    }


    protected EndpointGroup getPlantRoutes() {
        return () -> {

            post("/resseller", plantController::createReseller);

            get("{resellerid}", plantController::getPlantsByReseller);
            put("/ressellers/{resellerid}/{plantid}", plantController::addPlantToReseller);

            delete("/plantsd", plantController::deletePlant);
        };

    }
}
