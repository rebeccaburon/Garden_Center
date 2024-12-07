package app.routes;

import app.controller.PlantControllerMock;
import app.dao.PlantDAOMock;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class PlantMockRoute {
    private PlantControllerMock plantControllerMock;

    public PlantMockRoute(EntityManagerFactory emf) {
        plantControllerMock = new PlantControllerMock(PlantDAOMock.getInstance());
    }


    protected EndpointGroup getPlantMockRoutes() {
        return () -> {

            get("/{id}", plantControllerMock::getById);

            get("/", plantControllerMock::getAll);

            get("/{types}", plantControllerMock::getByType);

            get("/heights/{maxheight}", plantControllerMock::plantsWithMaxHeight);

            get("/names/{name}", plantControllerMock::getPlantNames);

            get("/sorted/{names}", plantControllerMock::plantsSortedByName);

            post("/", plantControllerMock::add);

        };

    }
}
