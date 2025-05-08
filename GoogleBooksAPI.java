import java.net.http.*;
import java.net.URI;
import javafx.application.Platform;
import org.json.*;

public class GoogleBooksAPI {
    public static void fetchBookInfo(String isbn, java.util.function.Consumer<Book> callback) {
        new Thread(() -> {
            try {
                String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                JSONObject json = new JSONObject(response.body());
                JSONObject volumeInfo = json.getJSONArray("items")
                        .getJSONObject(0)
                        .getJSONObject("volumeInfo");

                String title = volumeInfo.getString("title");
                String author = volumeInfo.getJSONArray("authors").getString(0);

                Book book = new Book(title, author, isbn, false);
                Platform.runLater(() -> callback.accept(book)); // cập nhật UI
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
