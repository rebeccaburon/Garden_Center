package app.routes;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {
    private PlantMockRoute plantMockRoute;
    private PlantRoute plantRoute;


    public Routes(EntityManagerFactory emf) {
        plantMockRoute = new PlantMockRoute(emf);
        plantRoute = new PlantRoute(emf);
    }

    public EndpointGroup getApiRoutes() {
        return () -> {
            path("/plants", plantMockRoute.getPlantMockRoutes());

            path("/resellerplants", plantRoute.getPlantRoutes());


        };
    }
}