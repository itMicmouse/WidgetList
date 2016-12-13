package retrofit2.converter.gson;

import com.example.retrofitnorestful.annotation.Data;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import retrofit.Converter;
import retrofit2.converter.gson.main.AbstractResponseConverter;
import retrofit2.converter.gson.main.impl.CustomResponseConverter;

/**
 * Created by yakun on 2016/5/12.
 */
public class CustomConverterFactory extends Converter.Factory  {

    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static CustomConverterFactory create() {
        return create(new Gson());
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static CustomConverterFactory create(Gson gson) {
        return new CustomConverterFactory(gson);
    }

    private final Gson gson;

    private CustomConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        String name = "data";// 默认"data"
        for (Annotation annotation : annotations) {
            if (annotation instanceof Data) {
                name = ((Data) annotation).value();
                break;
            }
        }
        for (Annotation annotation : annotations) {
            if (annotation instanceof retrofit2.converter.gson.main.Converter) {
                try {
                    Class<? extends AbstractResponseConverter> converterClazz = ((retrofit2.converter.gson.main.Converter) annotation).converter();
                    // 获取有 以gson参数的 构造函数
                    Constructor<? extends AbstractResponseConverter> constructor = converterClazz .getConstructor(Gson.class,TypeAdapter.class);
                    AbstractResponseConverter  converter = constructor.newInstance(gson);
                    System.out.println("abvc");
                    return converter;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new CustomResponseConverter<>(gson, adapter,name);
    }


}
