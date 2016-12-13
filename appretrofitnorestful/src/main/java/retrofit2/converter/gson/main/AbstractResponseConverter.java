package retrofit2.converter.gson.main;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.squareup.okhttp.ResponseBody;

/**
 * Created by yakun on 2016/5/14.
 */
public abstract class AbstractResponseConverter<T> implements retrofit.Converter<ResponseBody, T>{

    protected Gson gson;
    protected TypeAdapter<T> adapter;

    public AbstractResponseConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }
}
