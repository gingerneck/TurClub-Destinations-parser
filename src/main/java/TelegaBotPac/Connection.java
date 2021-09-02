package TelegaBotPac;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class Connection {

    public static Document getDOMDocument(URL url) throws IOException {
        Document doc = null;
        try {
            doc = Jsoup.connect(url.toString()).
//                        proxy("128.199.74.103", 8080).
        timeout(10 * 3000).
                            validateTLSCertificates(false).
                            get();

        } catch (IOException e) {
            System.out.println(e);
        }
        return doc;
    }

}
