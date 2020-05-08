package com.groceriesdelivered.inventory.io;

import backtrace.io.BacktraceClient;
import backtrace.io.BacktraceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BacktraceBootstrapper {
    private static final transient Logger LOGGER = LoggerFactory.getLogger(BacktraceBootstrapper.class);

    public static BacktraceClient getClient() {
        String token = "283a66bc10939a663e35c55c37deaad1571c6f8aa64145a4dc79b834f711e7f9";
        String universe = "cd03";
        String submissionUrl = "https://submit.backtrace.io/" + universe + "/" + token + "/json";
        BacktraceConfig config = new BacktraceConfig(submissionUrl);
        BacktraceClient client = new BacktraceClient(config);

        client.enableUncaughtExceptionsHandler();
        client.setApplicationName("InventorySynchronizationService");
        client.setApplicationVersion("3.43.4");

        return  client;
    }
}
