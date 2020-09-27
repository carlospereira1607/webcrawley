package webcrawley;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import webcrawley.configuration.CrawlerConfiguration;
import webcrawley.configuration.Qualifier;
import webcrawley.configuration.Url;

import java.util.*;

import static helper.WebCrawleyTestHelper.*;

class WebCrawleyTest {

    @Test
    void attributeValidationTest() {
        final HashMap<String, Qualifier> innerHtmlQualifier = new HashMap<>();
        innerHtmlQualifier.put(NEWS_ELEMENT, NEWS_QUALIFIER);

        final CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(FETCH_TIMEOUT, innerHtmlQualifier);
        final WebCrawley webCrawley = new WebCrawley(crawlerConfiguration);

        final List<Url> urls = new ArrayList<>(Arrays.asList(VALID_URL_1, VALID_URL_2, VALID_URL_3));

        final List<String> validUrls = webCrawley.validateUrls(urls);

        Assert.assertEquals(2, validUrls.size());
        Assert.assertTrue(validUrls.contains(urls.get(0).getCompleteUrl()));
        Assert.assertTrue(validUrls.contains(urls.get(1).getCompleteUrl()));
        Assert.assertFalse(validUrls.contains(urls.get(2).getCompleteUrl()));
    }

    @Test
    void innerHtmlValidationTest() {
        final HashMap<String, Qualifier> attributeQualifier = new HashMap<>();
        attributeQualifier.put(GITHUB_ELEMENT, GITHUB_QUALIFIER);

        final CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(FETCH_TIMEOUT, attributeQualifier);
        final WebCrawley webCrawley = new WebCrawley(crawlerConfiguration);

        final List<Url> urls = new ArrayList<>(Arrays.asList(VALID_URL_1, VALID_URL_2, VALID_URL_3));

        final List<String> validUrls = webCrawley.validateUrls(urls);

        Assert.assertEquals(1, validUrls.size());
        Assert.assertFalse(validUrls.contains(urls.get(0).getCompleteUrl()));
        Assert.assertFalse(validUrls.contains(urls.get(1).getCompleteUrl()));
        Assert.assertTrue(validUrls.contains(urls.get(2).getCompleteUrl()));
    }

    @Test
    void invalidUrlTest() {
        final HashMap<String, Qualifier> innerHtmlQualifier = new HashMap<>();
        innerHtmlQualifier.put(GITHUB_ELEMENT, GITHUB_QUALIFIER);

        final CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(FETCH_TIMEOUT, innerHtmlQualifier);
        final WebCrawley webCrawley = new WebCrawley(crawlerConfiguration);

        final List<Url> urls = new ArrayList<>(Collections.singletonList(INVALID_URL));

        final List<String> validUrls = webCrawley.validateUrls(urls);

        Assert.assertEquals(0, validUrls.size());
    }


    @Test
    void validAndInvalidUrlsTest() {
        final HashMap<String, Qualifier> qualifierHashMap = new HashMap<>();
        qualifierHashMap.put(GITHUB_ELEMENT, GITHUB_QUALIFIER);
        qualifierHashMap.put(NEWS_ELEMENT, NEWS_QUALIFIER);

        final CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(FETCH_TIMEOUT, qualifierHashMap);
        final WebCrawley webCrawley = new WebCrawley(crawlerConfiguration);

        final List<Url> urls = new ArrayList<>(Arrays.asList(VALID_URL_1, INVALID_URL, VALID_URL_2, VALID_URL_3));

        final List<String> validUrls = webCrawley.validateUrls(urls);

        Assert.assertEquals(3, validUrls.size());
        Assert.assertTrue(validUrls.contains(urls.get(0).getCompleteUrl()));
        Assert.assertTrue(validUrls.contains(urls.get(2).getCompleteUrl()));
        Assert.assertTrue(validUrls.contains(urls.get(3).getCompleteUrl()));
    }
}
