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
* Example of using the web crawler qualifier to search for the keyword "news" inside the <title> element's HTML
* */
public class InnerHtmlExample {
    public static void main() {
        //creating the website qualifier map
        //where the key is the CSS query and the value are the attributes to check from the results of the query
        final HashMap<String, Qualifier> innerHtmlQualifier = new HashMap<>();
        final String[] keywords = new String[]{"news"};
        final String[] attributes = new String[]{};

        //in this example, an empty attributes array makes the crawler look for the keywords in the element's inner HTML
        innerHtmlQualifier.put("title",  new Qualifier(keywords, attributes));
        //NOTE: if you instantiate the attributes array as new String[]{""} it'll consider as having one element
        //therefore it will look inside the attribute instead and not in the inner HTML

        //setting a 2 second timeout for fetch the webpage
        //be careful with this because every unreachable websites will delay the crawler into checking the next one
        final CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(2, innerHtmlQualifier);

        //passing the configuration to the main crawler object
        final WebCrawley webCrawley = new WebCrawley(crawlerConfiguration);

        //creating a list of urls
        //don't worry if the URL doesn't start with http/https the crawler will add
        final List<Url> urls = new ArrayList(Arrays.asList("cnn.com", "https://foxnews.com", "https://github.com", "invalid url"));

        //the return of this call is a list with the valid urls
        //in this case it will only return the first 2 entries
        final List<String> validUrls = webCrawley.validateUrls(urls);
    }
}