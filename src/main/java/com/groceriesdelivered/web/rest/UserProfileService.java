package com.groceriesdelivered.web.rest;

import backtrace.io.BacktraceClient;
import backtrace.io.data.BacktraceReport;
import com.groceriesdelivered.inventory.io.BacktraceBootstrapper;
import com.groceriesdelivered.inventory.io.InventorySynchronizerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class UserProfileService {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(UserProfileService.class);
    private final transient BacktraceClient client;

    public UserProfileService(BacktraceClient client) {
        this.client = client;
    }

    public static void main(String[] args) {
        final BacktraceClient client = BacktraceBootstrapper.getClient();
        new UserProfileService(client).addAddress("Address@3434" + new Random(10).longs());

    }
    public void addAddress(Object address) {
        try {
            throw new RuntimeException("Unable to deserialize address: " + address + ".");
        } catch (Exception e) {
            BacktraceReport report = new BacktraceReport(e,
                    new HashMap<String, Object>() {{
                        put("address", address);
                        put("street", "7 W 73RD ST");
                        put("city", "NEW YORK");
                        put("state", "NY");
                    }});
            client.send(report);
            try {
                client.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
