package webcrawley.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Configuration class for the web crawler qualifier.
 * */
@AllArgsConstructor
@Data
public class CrawlerConfiguration {

    /**
     * Timeout (in seconds) used for fetching a web page.
    */
    private int fetchTimeout;

    /**
     * Web page qualifier configurations.
     */
    @NotNull
    private Map<String, Qualifier> crawlerQualifiers;
}
