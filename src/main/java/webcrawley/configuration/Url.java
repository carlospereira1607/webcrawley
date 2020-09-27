package webcrawley.configuration;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a url to fetch. It adds "http://" to the url if it doesn't start with http/https.
 */
@Data
public class Url {

    @NotNull
    private String completeUrl;

    /**
     * Creates a URL object.
     *
     * @param url Base URL.
     */
    public Url(@NotNull final String url) {
        this.completeUrl = buildCompleteUrl(url);
    }

    /**
     * Builds a complete URL by adding "http://" to the url if it doesn't start with http/https.
     *
     * @param url Base URL
     * @return Complete URL
     */
    private String buildCompleteUrl(final String url) {
        if (!url.contains("http://") && !url.contains("https://")) {
            return "http://" + url;
        } else {
            return url;
        }
    }
}
