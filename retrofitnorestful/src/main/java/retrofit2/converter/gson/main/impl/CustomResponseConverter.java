package retrofit2.converter.gson.main.impl;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONObject;

import java.io.IOException;

import retrofit.Converter;

/**
 * Created by yakun on 2016/5/12.
 */
public class CustomResponseConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final String name;

    public CustomResponseConverter(Gson gson, TypeAdapter<T> adapter,String name) {
        this.gson = gson;
        this.adapter = adapter;
        this.name = name;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String body = value.string();

            JSONObject json = new JSONObject(body);

            int    ret = json.optInt("ret");
            String msg = json.optString("msg", "");

            if (ret == 0) {
                if (json.has(name)) {
                    Object data = json.get(name);

                    body = data.toString();

                    return adapter.fromJson(body);
                } else {
                    return (T) msg;
                }
            } else {
                throw new RuntimeException(msg);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }
}
