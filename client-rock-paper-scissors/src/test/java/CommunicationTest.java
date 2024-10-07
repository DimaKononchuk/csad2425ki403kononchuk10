import com.fazecast.jSerialComm.SerialPort;
import org.example.SerialCommunicator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


public class CommunicationTest {

    private SerialCommunicator communicator;
    private String testPortName = "COM2"; // Вкажіть реальний порт, який використовується вашим Arduino
    private int baudRate = 9600;

    @BeforeEach
    public void setUp() {
        communicator = new SerialCommunicator();
        boolean portOpened = communicator.openPort(testPortName, baudRate);
        assertTrue(portOpened, "Порт має бути відкритий перед тестами");
    }

    @AfterEach
    public void tearDown() {
        communicator.closePort();
    }

    @Test
    public void testOpenAndClosePort() {
        communicator.closePort();
        boolean portOpened = communicator.openPort(testPortName, baudRate);
        assertTrue(portOpened, "Порт має успішно відкритися після закриття");
    }

    @Test
    public void testSendMessageAndReceiveResponse() throws InterruptedException {
        // Надсилаємо тестове повідомлення
        String testMessage = "Hello, Arduino!";
        boolean messageSent = communicator.sendMessage(testMessage);
        assertTrue(messageSent, "Повідомлення має бути успішно надіслано");

        // Додаємо затримку, щоб дати Arduino час на обробку та відповісти
        Thread.sleep(5000); // Час затримки залежить від відповіді вашого пристрою

        // Чекаємо на відповідь від Arduino
        if (communicator.hasAvailableData()) {
            String response = communicator.readMessage();
            assertNotNull(response, "Повинно отримати відповідь від Arduino");
            System.out.println("Отримана відповідь: " + response);
        } else {
            fail("Немає доступних даних для читання");
        }
    }

    @Test
    public void testHasAvailableData() throws InterruptedException {
        // Спочатку переконаємося, що на початку немає даних
        assertFalse(communicator.hasAvailableData(), "На початку не повинно бути доступних даних");

        // Надсилаємо тестове повідомлення на Arduino
        String testMessage = "Test data check";
        boolean messageSent = communicator.sendMessage(testMessage);
        assertTrue(messageSent, "Повідомлення має бути надіслано");

        // Додаємо затримку, щоб дати Arduino час відповісти
        Thread.sleep(5000);

        // Тепер перевіряємо, чи доступні дані для читання
        assertTrue(communicator.hasAvailableData(), "Дані мають бути доступні після отримання відповіді");
    }
}
