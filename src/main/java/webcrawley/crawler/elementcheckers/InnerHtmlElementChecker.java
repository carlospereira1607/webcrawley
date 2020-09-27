package webcrawley.crawler.elementcheckers;

import org.jsoup.nodes.Element;

import java.util.Arrays;

/**
 * Class that checks for keywords inside an element's inner HTML.
 */
public class InnerHtmlElementChecker {

    private InnerHtmlElementChecker() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Method that looks for keywords in an element's inner HTML.
     *
     * @param element  Element to qualify.
     * @param keywords Keywords to look for.
     * @return True if the element is qualified, otherwise returns false.
     */
    public static boolean isValid(final Element element, final String[] keywords) {
        return Arrays.stream(keywords)
                .parallel()
                .anyMatch(element.html().toLowerCase()::contains);
    }
}
