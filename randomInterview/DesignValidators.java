package randomInterview;

import java.util.List;

public class DesignValidators {
    record Design(int width, int height, List<Page> pages) {
    }

    record Page(String id, List<Element> elements) {
    }

    record Element(String id, int width, int height, String font) {
    }

    interface DesignUpdater {
        /**
         * Replaces the entire design with the provided design.
         * 
         * @return true if successful, false if the design was not replaced.
         */
        boolean replaceWith(Design newDesign);

        /**
         * 
         * Adds the provided page to the existing design
         * 
         * @return true if successful, false if the design was not updated.
         * 
         */

        boolean addPage(Page newPage);
    }
}
