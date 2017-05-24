package converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by utsav on 24/05/2017.
 */

public class JsonConverter {

    public <T> T convertJsonToObject(String jsonString, Class<T> typeClass)
    {
        T object = null;

        try {
            Gson gson = new GsonBuilder().setLenient().create();
            object = gson.fromJson(jsonString, typeClass);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return object;
    }

    public String convertToJson(Object object)
    {
        String jsonString = null;
        try {
            jsonString = new Gson().toJson(object);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return jsonString;
    }
}
