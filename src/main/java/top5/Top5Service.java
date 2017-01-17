package top5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top5.controller.Top5APIController;

import java.net.URISyntaxException;

import static spark.Spark.*;

public class Top5Service {
    private static final Logger logger = LoggerFactory.getLogger(Top5Service.class);
    Top5APIController controller;

    public static void main(String[] args) {

        logger.debug("Starting " + Top5Service.class.getName() + "Server is running");
        Top5Service application = new Top5Service();
        application.controller = new Top5APIController();
        staticFiles.location("/static");
        port(60001);
        //post to shot

        // --- ROOTING ---
        post("/api/:apikey/addproduct", application.controller::addProduct);
        // get prod details
        get("/api/:apikey/gettop5", application.controller::getTop5);

        get("/status", application.controller::status);

        // --- EXCEPTION HANDLING ---
        exception(URISyntaxException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });
    }
}
