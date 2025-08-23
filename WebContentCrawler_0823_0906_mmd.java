// 代码生成时间: 2025-08-23 09:06:05
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * WebContentCrawler is a simple web content crawler using Jsoup library.
 * It fetches the content of a webpage and extracts basic information.
 */
public class WebContentCrawler {

    private static final String USER_AGENT = "TheresaBrow/1.0";

    /**
     * Fetches the HTML content of the webpage at the specified URL.
     *
     * @param url The URL of the webpage to fetch.
     * @return The HTML content of the webpage.
     * @throws IOException If an I/O error occurs.
     */
    public String fetchHtmlContent(String url) throws IOException {
        // Use Jsoup to connect to the URL and fetch the HTML document
        return Jsoup.connect(url).userAgent(USER_AGENT).get().html();
    }

    /**
     * Extracts the title of the webpage from the HTML content.
     *
     * @param htmlContent The HTML content of the webpage.
     * @return The title of the webpage or null if no title is found.
     */
    public String extractTitle(String htmlContent) {
        try {
            Document doc = Jsoup.parse(htmlContent);
            return doc.title();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Extracts the links from the webpage.
     *
     * @param htmlContent The HTML content of the webpage.
     * @return A collection of links found on the webpage.
     */
    public Elements extractLinks(String htmlContent) {
        try {
            Document doc = Jsoup.parse(htmlContent);
            return doc.select("a[href]");
        } catch (Exception e) {
            e.printStackTrace();
            return new Elements();
        }
    }

    /**
     * Main method to demonstrate the functionality of the WebContentCrawler.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        WebContentCrawler crawler = new WebContentCrawler();
        try {
            String url = "http://example.com";
            String htmlContent = crawler.fetchHtmlContent(url);
            System.out.println("Title: " + crawler.extractTitle(htmlContent));
            System.out.println("Links: ");
            Elements links = crawler.extractLinks(htmlContent);
            for (Element link : links) {
                System.out.println(link.attr("abs:href"));
            }
        } catch (IOException e) {
            System.err.println("Error fetching HTML content: " + e.getMessage());
        }
    }
}