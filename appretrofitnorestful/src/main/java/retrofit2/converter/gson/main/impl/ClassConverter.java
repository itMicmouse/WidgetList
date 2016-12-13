package retrofit2.converter.gson.main.impl;

import com.example.retrofitnorestful.test.ClazzInfo;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;

import retrofit2.converter.gson.main.AbstractResponseConverter;

/**
 * Created by yakun on 2016/5/14.
 */
public class ClassConverter extends AbstractResponseConverter<ClazzInfo> {

    public ClassConverter(Gson gson,TypeAdapter adapter){
        super(gson,adapter);
    }

    @Override
    public ClazzInfo convert(ResponseBody value) throws IOException {
        ClazzInfo clazzInfo = gson.fromJson(value.toString(), ClazzInfo.class);
        return clazzInfo;
    }
}
