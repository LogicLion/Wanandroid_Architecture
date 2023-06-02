package com.wanandroid.module_main.net;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.wanandroid.module_main.BuildConfig;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


/**
 * Hello World
 * Date: 2019/5/20
 * Author: Cham
 */
public class LoggingInterceptor implements Interceptor {
    static String TAG = "Retrofit";
    private static final Charset UTF8 = StandardCharsets.UTF_8;
    private static final String NEW_LINE = "\n";
    private static final String SPACE = "\t\t";

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();
        printRequestLog(request);

        long t1 = System.nanoTime();//请求发起的时间

        Response response = chain.proceed(request);

        //收到响应的时间
        long t2 = System.nanoTime();
        printResponseLog(response, t1, t2);

        return response;
    }

    /**
     * 打印响应log
     *
     * @param response 响应
     * @param t1       请求发起时间
     * @param t2       请求响应时间
     * @throws IOException io异常
     */
    private void printResponseLog(Response response, long t1, long t2) throws IOException {
        if (!BuildConfig.DEBUG) {
            return;
        }

        String responseStr;
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        String bodyStr = responseBody.string();
        if (response.code() == 200) {
            try {
                //格式化json
                Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();
                JsonElement je = JsonParser.parseString(bodyStr);
                responseStr = gson.toJson(je);
            } catch (JsonSyntaxException e) {
                responseStr = bodyStr;
                e.printStackTrace();
            }
        } else {
            responseStr = "响应错误:" + response.code() + bodyStr;
        }
        Log.i(TAG, String.format(Locale.CHINA, "%n响应URL-------: %s%n响应类型-------: %s%n响应数据------%s%n请求用时--------%.1fms%n%s",
                response.request().url(),
                response.request().method(),
                responseStr,
                (t2 - t1) / 1e6d,
                response.headers()));
    }

    /**
     * 打印请求log
     *
     * @param request 请求
     * @throws IOException io异常
     */
    private void printRequestLog(Request request) throws IOException {
        if (!BuildConfig.DEBUG) {
            return;
        }
        RequestBody body = request.body();
        StringBuilder sb = new StringBuilder();
        if (body != null) {
            MediaType mediaType = body.contentType();
            if (mediaType != null) {
                sb.append("content type:");
                sb.append(mediaType.toString());
                sb.append(NEW_LINE);
            }
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            if (isPlaintext(buffer)) {
                sb.append("params:");
                sb.append(buffer.readString(UTF8));
            }
        }

        Log.i(TAG, String.format("%n请求URL------%s%n请求方式------%s%n请求实体------%s%n请求头------%s",
                request.url(), request.method(), sb, request.headers()));

    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }



/*    public static NetEventListener netEventListener;

    public static void setNetEventListener(NetEventListener listener) {
        netEventListener = listener;
    }*/
}
