package app.config;

import app.exceptions.ApiException;
import app.controller.ExceptionController;
import app.routes.Routes;
import app.security.controller.AccessController;
import app.security.routes.SecurityRoutes;
import app.util.ApiProps;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class AppConfig {
    private static Javalin app;
    private static final ExceptionController getExceptionController = new ExceptionController();
    private static Routes routes;
    private static AccessController accessController = new AccessController();
    private static final ExceptionController exceptionController = new ExceptionController();



    private static void configuration (JavalinConfig config){
        //Server
        config.router.contextPath = ApiProps.API_CONTEXT;

        //Server respond - Json
        config.http.defaultContentType = "application/json";


        //Plugin
        config.bundledPlugins.enableRouteOverview("/routes");
        config.bundledPlugins.enableDevLogging();

        //Routes
        config.router.apiBuilder(routes.getApiRoutes());

        //Security
        config.router.apiBuilder(SecurityRoutes.getSecuredRoutes());
        config.router.apiBuilder(SecurityRoutes.getSecurityRoutes());
    }

    //Exceptions
    private static void exceptionContext(Javalin app) {
        app.exception(ApiException.class, exceptionController::apiExceptionHandler);

        app.exception(Exception.class, exceptionController::exceptionHandler);
    }

    //start server
    public static void startServer(EntityManagerFactory emf) {
        routes = new Routes(emf);
        app = Javalin.create(AppConfig::configuration);
        app.beforeMatched(accessController::accessHandler);
        //-------------------------
        app.before(AppConfig::corsHeaders);
        app.options("/*", AppConfig::corsHeadersOptions);

        //-------------------------
        app.start(ApiProps.PORT);
        exceptionContext(app);
    }
    //stop server
    public static void stopServer(Javalin app) {
        app.stop();
    }


    //Setting up CORS headers -------------------------------------------------------
    private static void corsHeaders(Context ctx) {
        ctx.header("Access-Control-Allow-Origin", "*");
        ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // You could add PATCH
        ctx.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
        ctx.header("Access-Control-Allow-Credentials", "true");
    }

    private static void corsHeadersOptions(Context ctx) {
        ctx.header("Access-Control-Allow-Origin", "*");
        ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // You could add PATCH
        ctx.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
        ctx.header("Access-Control-Allow-Credentials", "true");
        ctx.status(204);
    }
}
