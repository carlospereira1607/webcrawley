package examples;

import webcrawley.WebCrawley;
import webcrawley.configuration.CrawlerConfiguration;
import webcrawley.configuration.Qualifier;
import webcrawley.configuration.Url;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class OverallExample {
    public static void main() {

        final HashMap<String, Qualifier> qualifierHashMap = new HashMap<>();

        qualifierHashMap.put("meta[name=description]", new Qualifier(new String[]{"GitHub"}, new String[]{"content"}));
        qualifierHashMap.put("news", new Qualifier(new String[]{"news"}, new String[]{}));

        final CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(2, qualifierHashMap);
        final WebCrawley webCrawley = new WebCrawley(crawlerConfiguration);

        final List<Url> urls = new ArrayList(Arrays.asList("cnn.com", "https://foxnews.com", "https://github.com", "invalid url"));

        //the return of this call is a list with the valid urls
        //even though it has a mix of inner html and attribute qualifiers, it applies these to all the urls
        //in this case it will only return the first 3 entries
        final List<String> validUrls = webCrawley.validateUrls(urls);
    }
}