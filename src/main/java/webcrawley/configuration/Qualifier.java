package webcrawley.configuration;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Class that represents an element's qualifier, which is a list of keywords and attributes.
 * */
@Data
public class Qualifier {
    /**
     * List of keywords to search for
     */
    @NotNull
    private String[] keywords;

    /**
     * List of HTML attributes to search keywords for in their values
     */
    @NotNull
    private String[] attributes;

    /**
     * Creates a qualifier to be used on a web page. Receives a list attributes and keywords to look for inside them.
     *
     * @param keywords   List of keywords to look for in the attributes or elements' inner HTML.
     * @param attributes List of a attributes to check.
     *                   If empty, the crawler searches for the keywords inside the element's inner HTML.
     */
    public Qualifier(final String[] keywords, final String[] attributes) {
        this.attributes = attributes;
        this.keywords = Arrays.stream(keywords).parallel().map(String::toLowerCase).toArray(String[]::new);
    }
}

