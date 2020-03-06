package com.groceriesdelivered.inventory.io;

import backtrace.io.BacktraceClient;
import backtrace.io.BacktraceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BacktraceBootstrapper {
    private static final transient Logger LOGGER = LoggerFactory.getLogger(BacktraceBootstrapper.class);

    private static final Thread HOOK = new Thread() {

        public Thread getThreadByName(String threadName) {
            for (Thread t : Thread.getAllStackTraces().keySet()) {
                LOGGER.debug(t.getName());
                if (t.getName().equals(threadName)) {
                    return t;
                }
            }
            return null;
        }

        @Override
        public void run() {
            getThreadByName("backtrace-deamon").interrupt();
        }
    };

    public static BacktraceClient getClient() {
        String token = "2fd0f15d6a112d5b255136b7cd6407284823425318354ba4cf3e9e9d6625b36f";
        String universe = "bt-demos";
        String submissionUrl = "https://submit.backtrace.io/" + universe + "/" + token + "/json";
        BacktraceConfig config = new BacktraceConfig(submissionUrl);
        BacktraceClient client = new BacktraceClient(config);

        client.enableUncaughtExceptionsHandler();
        client.setApplicationName("InventorySynchronizationService");
        client.setApplicationVersion("3.43.4");

        //Runtime.getRuntime().addShutdownHook(HOOK);

        return  client;
    }
}
