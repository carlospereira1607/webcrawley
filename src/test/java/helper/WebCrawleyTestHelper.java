package helper;

import webcrawley.configuration.Qualifier;
import webcrawley.configuration.Url;

import java.util.*;

public class WebCrawleyTestHelper {

    private WebCrawleyTestHelper() {
    }

    //Urls used for the tests
    public static final Url VALID_URL_1 = new Url("cnn.com");
    public static final Url VALID_URL_2 = new Url("https://foxnews.com");
    public static final Url VALID_URL_3 = new Url("https://github.com");
    public static final Url INVALID_URL = new Url("invalid url");

    // Setting tests fetch timeout
    public static final int FETCH_TIMEOUT = 10;

    public static final String NEWS_ELEMENT = "title";
    public static final Qualifier NEWS_QUALIFIER = new Qualifier(new String[]{"news"}, new String[]{});

    public static final String GITHUB_ELEMENT = "meta[name=description]";
    public static final Qualifier GITHUB_QUALIFIER = new Qualifier(new String[]{"GitHub"}, new String[]{"content"});
}
