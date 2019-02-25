package fdmc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParserImpl implements JsonParser {
    @Override
    public String toJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }
}
