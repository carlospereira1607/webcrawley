package webcrawley.crawler.urlchecker;

import webcrawley.configuration.CrawlerConfiguration;
import webcrawley.configuration.Qualifier;
import webcrawley.configuration.Url;
import webcrawley.crawler.elementcheckers.AttributeElementChecker;
import webcrawley.crawler.elementcheckers.InnerHtmlElementChecker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Class that receives a list of urls, fetches the valid web pages and applies the qualifiers and looks for the keywords.
 */
public class UrlChecker {

    private static final Logger LOGGER = Logger.getLogger(UrlChecker.class.getName());

    private UrlChecker() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Applies the crawler configuration to the list of urls.
     *
     * @param urls                 URLs to search through.
     * @param crawlerConfiguration Configuration to qualify a web page.
     * @return List of qualified urls.
     */
    public static List<String> checkUrls(final List<Url> urls, final CrawlerConfiguration crawlerConfiguration) {
        return urls
                .parallelStream()
                .filter(url -> validateUrl(url, crawlerConfiguration))
                .map(Url::getCompleteUrl)
                .collect(Collectors.toList());
    }

    /**
     * Checks if a URL is valid by applying the crawler configuration.
     *
     * @param url                  URL to check.
     * @param crawlerConfiguration Configuration to qualify a web page.
     * @return True if the URL is qualified, otherwise returns false.
     */
    private static boolean validateUrl(final Url url, final CrawlerConfiguration crawlerConfiguration) {
        if (url == null) {
            return false;
        }

        final String completeUrl = url.getCompleteUrl();
        boolean isQualified = false;

        try {

            final Document doc = Jsoup.connect(completeUrl).timeout(crawlerConfiguration.getFetchTimeout() * 1000).get();

            for (Map.Entry<String, Qualifier> configurationEntry : crawlerConfiguration.getCrawlerQualifiers().entrySet()) {
                //Applying each CSS query to the fetched document
                final Elements elements = doc.select(configurationEntry.getKey());

                final String[] keywords = configurationEntry.getValue().getKeywords();
                final String[] attributes = configurationEntry.getValue().getAttributes();

                for (int i = 0; i < elements.size() && !isQualified; i++) {
                    final Element element = elements.get(i);

                    if (attributes.length == 0) {
                        //If attributes to search through are not specified
                        //search for the keywords in the element's inner html
                        isQualified = InnerHtmlElementChecker.isValid(element, keywords);
                    } else {
                        //Search for the keywords through the element's attributes values
                        isQualified = AttributeElementChecker.isValid(element, keywords, attributes);
                    }
                }

                if (isQualified) {
                    break;
                }
            }
        } catch (SocketTimeoutException e) {
            LOGGER.warning("Web page timeout " + completeUrl);
        } catch (UnknownHostException e) {
            LOGGER.warning("Unknown host " + completeUrl);
        } catch (Exception e) {
            LOGGER.warning("Unhandled exception " + completeUrl);
            e.printStackTrace();
        }

        return isQualified;
    }

}
