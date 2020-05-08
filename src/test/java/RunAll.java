import backtrace.io.BacktraceClient;
import com.groceriesdelivered.batch.order_archiving.OrderArchiver;
import com.groceriesdelivered.inventory.io.BacktraceBootstrapper;
import com.groceriesdelivered.inventory.io.InventorySynchronizerService;
import com.groceriesdelivered.web.rest.UserProfileService;
import org.junit.Test;

public class RunAll {

    @Test
    public void runIt() {
        final BacktraceClient client = BacktraceBootstrapper.getClient();
        new OrderArchiver(client).archiveOrders();
        new UserProfileService(client).addAddress("");
        new InventorySynchronizerService(client).syncItem();
    }
}
