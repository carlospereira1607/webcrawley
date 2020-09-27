package webcrawley;

import org.jetbrains.annotations.NotNull;
import webcrawley.configuration.CrawlerConfiguration;
import webcrawley.configuration.Url;
import webcrawley.crawler.urlchecker.UrlChecker;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Main web crawler qualifier class.
 */
@AllArgsConstructor
public class WebCrawley {
    /**
     * Configuration to qualify a web page.
     */
    @NotNull
    private CrawlerConfiguration crawlerConfigurations;

    /**
     * Method for checking which urls are qualified.
     *
     * @param urls List of urls to check.
     * @return List of valid urls.
     */
    public List<String> validateUrls(final List<Url> urls) {
        return UrlChecker.checkUrls(urls, crawlerConfigurations);
    }
}
