package webcrawley.crawler.elementcheckers;

import org.jsoup.nodes.Element;

import java.util.Arrays;

/**
 * Class that checks for keywords in an element's attributes.
 */
public class AttributeElementChecker {

    private AttributeElementChecker() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Method that looks for keywords in an element's attributes.
     *
     * @param element    Element to qualify.
     * @param keywords   Keywords to look for.
     * @param attributes Attributes to check.
     * @return True if the element is qualified, otherwise returns false.
     */
    public static boolean isValid(final Element element, final String[] keywords, final String[] attributes) {
        boolean isQualified = false;

        for (int j = 0; j < attributes.length && !isQualified; j++) {
            isQualified = Arrays.stream(keywords)
                    .parallel()
                    .anyMatch(element
                            .getElementsByAttribute(attributes[j])
                            .toString()
                            .toLowerCase()::contains);
        }

        return isQualified;
    }
}
