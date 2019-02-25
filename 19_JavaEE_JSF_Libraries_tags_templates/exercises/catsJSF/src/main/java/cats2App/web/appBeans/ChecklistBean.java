package cats2App.web.appBeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.*;

@Named(value = "checklist")
@RequestScoped
public class ChecklistBean {
    private List<String> items;

    private Map<String,Boolean> checkMap = new LinkedHashMap<>();

    public ChecklistBean() {
        items = new ArrayList<String>();
        items.add("c.name DESC");
        items.add("c.price ASC");
        items.add("c.price DESC");
        items.add("c.breed");
        items.add("c.age");
        items.add("c.color");
        items.add("c.gender");
        items.add("c.added_on ASC");
        items.add("c.added_on DESC");

        //fill the check map with the items defaulted to unchecked
        for (String item : items) {
            checkMap.put(item,Boolean.FALSE);
        }
    }

    public List<String> getItems() {
        return items;
    }

    public Map<String, Boolean> getCheckMap() {
        return checkMap;
    }



    public String getSelected() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String,Boolean> entry : checkMap.entrySet()) {
            if (entry.getValue()) {
                result.append(", "+entry.getKey());
            }
        }
        if(result.toString().isEmpty()){
            return "";
        }
        String substring = result.substring(2);
        return substring;
    }
}
