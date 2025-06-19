package randomInterview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import randomInterview.DesignValidators.Design;
import randomInterview.DesignValidators.DesignUpdater;
import randomInterview.DesignValidators.Element;
import randomInterview.DesignValidators.Page;

public class DesignUpdaterImpl implements DesignUpdater {
    public Design currentDesign = new Design(1000, 1000, List.of());

    @Override
    public boolean replaceWith(Design newDesign) {
        if (!validateElementsSize(newDesign)) {
            return false;
        }
        if (!validateUniqueId(newDesign)) {
            return false;
        }
        currentDesign = newDesign;
        return true;
    }

    @Override
    public boolean addPage(Page newPage) {
        if (!validateElementsSize(newPage)) {
            return false;
        }
        if (!validateUniqueId(newPage)) {
            return false;
        }
        var currentPages = new ArrayList<>(currentDesign.pages());
        currentPages.add(newPage);
        currentDesign = new Design(currentDesign.width(), currentDesign.height(), currentPages);
        return true;
    }

    private boolean validateElementsSize(Design design) {
        for (Page page : design.pages()) {
            for (Element e : page.elements()) {
                if (e.width() > design.width() || e.height() > design.height()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateUniqueId(Design design) {
        Set<String> uniqueIds = new HashSet<>();
        for (Page page : design.pages()) {
            for (Element e : page.elements()) {
                if (uniqueIds.contains(e.id())) {
                    return false;
                }
                uniqueIds.add(e.id());
            }
        }
        return true;
    }

    private boolean validateUniqueId(Page newPage) {
        Set<String> uniqueIds = new HashSet<>();
        List<Page> pages = currentDesign.pages();
        pages.add(newPage);
        for (Page page : pages) {
            for (Element e : page.elements()) {
                if (uniqueIds.contains(e.id())) {
                    return false;
                }
                uniqueIds.add(e.id());
            }
        }
        return true;
    }

    private boolean validateElementsSize(Page page) {
        for (Element e : page.elements()) {
            if (e.width() > currentDesign.width() || e.height() > currentDesign.height()) {
                return false;
            }
        }
        return true;
    }

}
