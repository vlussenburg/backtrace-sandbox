package io.backtrace;

import backtrace.io.BacktraceClient;
import backtrace.io.BacktraceConfig;
import backtrace.io.BacktraceThread;
import backtrace.io.data.BacktraceReport;
import backtrace.io.events.OnServerResponseEvent;
import backtrace.io.http.BacktraceResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static javax.swing.UIManager.put;

public class Main {

    public static void main(String[] args) {
        String token = "0c3353a49bfe410df5e61d2e18d3a0146d438872e60ad623cb25f89ac3ef161a";
        String universe = "bt-demos";
        String submissionUrl = "https://submit.backtrace.io/" + universe + "/" + token + "/json";
        BacktraceConfig config = new BacktraceConfig(submissionUrl);
        BacktraceClient client = new BacktraceClient(config);

        client.enableUncaughtExceptionsHandler();
        client.setApplicationName("Backtrace Demo");
        client.setApplicationVersion("1.0.0");

        while (true) {
            try {
                throw new NullPointerException("hi mom" + System.currentTimeMillis());
            } catch (Exception e) {

                BacktraceReport report = new BacktraceReport(e,
                        new HashMap<>() {{
                            put("vincent", "123");
                        }});
                client.send(report);
            }
        }

    }
}
