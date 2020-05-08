package com.groceriesdelivered.inventory.io;

import backtrace.io.BacktraceClient;;
import backtrace.io.data.BacktraceReport;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventorySynchronizerService {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(InventorySynchronizerService.class);
    private final transient BacktraceClient client;

    public InventorySynchronizerService(BacktraceClient client) {
        this.client = client;
    }

    public void syncItem() {
        try {

            throw new RuntimeException("Skipping record sync due to IOException.", new IOException("Timed out trying to reach endpoint"));
        } catch (Exception e) {

            BacktraceReport report = new BacktraceReport(e,
                    new HashMap<String, Object>() {{
                        put("productGroupId", "12312");
                    }});
            client.send(report);
            try {
                client.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        final BacktraceClient client = BacktraceBootstrapper.getClient();
        new InventorySynchronizerService(client).syncItem();

    }
}
