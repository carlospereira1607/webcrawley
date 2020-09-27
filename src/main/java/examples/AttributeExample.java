package examples;

import webcrawley.WebCrawley;
import webcrawley.configuration.CrawlerConfiguration;
import webcrawley.configuration.Qualifier;
import webcrawley.configuration.Url;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * Example of using the web crawler qualifier to search for the keyword "GitHub" in the "content" attribute.
 * */
public class AttributeExample {
    public static void main() {
        //creating the website qualifier map
        //where the key is the CSS query and the value are the attributes to check from the results of the query
        final HashMap<String, Qualifier> attributeQualifier = new HashMap<>();
        final String[] keywords = new String[]{"GitHub"};
        final String[] attributes = new String[]{"content"};

        //because the attributes array is not empty, the qualifier will check the values of the attributes returned
        //from the previous CSS query
        attributeQualifier.put("meta[name=description]",  new Qualifier(keywords, attributes));

        //setting a 2 second timeout for fetch the webpage
        //be careful with this because every unreachable websites will delay the crawler into checking the next one
        final CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(2, attributeQualifier);

        //passing the configuration to the main crawler object
        final WebCrawley webCrawley = new WebCrawley(crawlerConfiguration);

        //creating a list of urls
        //don't worry if the URL doesn't start with http/https the crawler adds it
        final List<Url> urls = new ArrayList(Arrays.asList("cnn.com", "https://foxnews.com", "https://github.com", "invalid url"));

        //the return of this call is a list with the valid urls
        //in this case it will only return the github entry
        final List<String> validUrls = webCrawley.validateUrls(urls);
    }
}