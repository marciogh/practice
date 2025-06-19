package randomInterview;

import java.util.List;

import org.junit.Test;

import randomInterview.DesignValidators.Design;
import randomInterview.DesignValidators.Page;

import static org.junit.jupiter.api.Assertions.*;

public class DesignUpdaterImplTest {

    @Test
    public void designUpdateShouldReplaceDesignIfSuccessful() {
        var updater = new DesignUpdaterImpl();
        var newDesign = new DesignValidators.Design(50, 50, List.of(new Page("page-id", List.of())));
        var updated = updater.replaceWith(newDesign);

        // did replace design
        assertTrue(updated);
        assertEquals(newDesign, updater.currentDesign);
    }

    @Test
    public void designUpdateShouldNotReplaceWhenNewDesignHasElementsLargerThanDesign() {
        var updater = new DesignUpdaterImpl();
        var element = new DesignValidators.Element("element", 50, 50, "font");
        var elementLargerThanDesign = new DesignValidators.Element("element-larger-than-design", 200, 100, "font");
        var newDesign = new Design(100, 100, List.of(new Page("page-id", List.of(
                element, elementLargerThanDesign))));
        var updated = updater.replaceWith(newDesign);

        // did not replace design
        assertFalse(updated);
        assertNotEquals(newDesign, updater.currentDesign);
    }

    @Test
    public void designUpdateShouldNotReplaceWhenElementsIdsAreNotUnique() {
        var updater = new DesignUpdaterImpl();
        var element = new DesignValidators.Element("dup", 50, 50, "font");
        var dupElement = new DesignValidators.Element("dup", 50, 50, "font");
        var newDesign = new Design(100, 100, List.of(new Page("page-id", List.of(
                element, dupElement))));
        var updated = updater.replaceWith(newDesign);

        // did not replace design
        assertFalse(updated);
        assertNotEquals(newDesign, updater.currentDesign);
    }

    @Test
    public void designCanHavePagesAdded() {
        var updater = new DesignUpdaterImpl();
        var element = new DesignValidators.Element("e1", 50, 50, "font");
        updater.addPage(new Page("page-id", List.of(element)));
        assertEquals(updater.currentDesign.pages().size(), 1);
    }
}