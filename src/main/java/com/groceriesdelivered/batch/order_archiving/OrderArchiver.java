package com.groceriesdelivered.batch.order_archiving;

import backtrace.io.BacktraceClient;
import backtrace.io.data.BacktraceReport;
import com.groceriesdelivered.inventory.io.BacktraceBootstrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderArchiver {
    private static final transient Logger LOGGER = LoggerFactory.getLogger(OrderArchiver.class);
    private final transient BacktraceClient client;

    public OrderArchiver(BacktraceClient client) {
        this.client = client;
    }

    public void archiveOrders() {
        try {

            throw new RuntimeException("FATAL: JDBC Batch size exceeded: [2133] is larger than [2000].");
        } catch (Exception e) {
            BacktraceReport report = new BacktraceReport(e,
                    new HashMap<String, Object>() {{
                        put("batchSize", "2133");
                    }}, new ArrayList<String>() {
                {
                    add("/Users/vincent/IdeaProjects/backtrace-sandbox/src/main/resources/orders_to_archive.txt");
                }
            });
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
        new OrderArchiver(client).archiveOrders();

    }
}
