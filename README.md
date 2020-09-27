# WebCrawley

A web crawling qualifier library.

### Goal

The purpose of this project is to offer a simple and dynamic way to detect which websites are *qualified* according to your configurations. 

### How it works

This library fetches the HTML code from the input urls to visit, and applies to each one a CSS query (so that the crawler library can be easily configured to reach different elements).

A website is *qualified* if at least one keyword (there can be many in the input) are searched for in the results of the previous query.
 
Depending on the CSS query, the keywords are search for in different places:

 1. Inside the element's inner HTML (find an example [here](src/main/java/examples/InnerHtmlExample.java)).
 
 1. Inside the element's specified attributes (find an example [here](src/main/java/examples/AttributeExample.java)).

The HTML and the keywords are all transformed into lowercase before checking. Which means that the results from the qualifier are case insensitive.

The applied qualifiers can be a mix of checking the inner HTML and the attribute's value. It's your choice how to use it!

### Example

You can find an overall example [here](src/main/java/examples/OverallExample.java).