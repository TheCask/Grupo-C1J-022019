import org.junit.Test;
import static org.junit.Assert.*;

public class ClientBuilderTest {

    @Test
    public void testCreateClientWithName() {
        String firstName = "Alfonso";
        String lastName = "Prat Gay";

        Client aClient = ClientBuilder.aClient().withName(firstName, lastName).build();

        assertEquals(firstName, aClient.getFirstName());
        assertEquals(lastName, aClient.getLastName());
    }

    @Test
    public void testCreateClientWithCredit() {
        int credit = 3000;

        Client aClient = ClientBuilder.aClient().withCredit(credit).build();

        assertEquals(credit, aClient.getCredit());
    }

}
