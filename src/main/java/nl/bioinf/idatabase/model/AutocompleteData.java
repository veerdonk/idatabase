package nl.bioinf.idatabase.model;

import java.util.List;

/**
 * Created by dvandeveerdonk on 13-6-17.
 */
public class AutocompleteData {
    private List<Suggestion> suggestions;

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }
}
