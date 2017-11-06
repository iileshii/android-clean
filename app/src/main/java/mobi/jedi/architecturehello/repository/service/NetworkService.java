package mobi.jedi.architecturehello.repository.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alkuznetsov
 * on 07/08/2017.
 */

public class NetworkService {

    private static final String BASE_URL = "https://api.github.com";
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm'Z'";

    private final GithubApi mNetworkService;

    public NetworkService() {
        final Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();

        final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final HeaderInterceptor headerInterceptor = new HeaderInterceptor();

        final OkHttpClient build =
                new OkHttpClient.Builder()
                        .addInterceptor(headerInterceptor)
                        .addInterceptor(loggingInterceptor)
                        .build();

        final RxJavaCallAdapterFactory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

        mNetworkService = new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .baseUrl(BASE_URL)
                .client(build)
                .build()
                .create(GithubApi.class);
    }

    public GithubApi getNetworkService() {
        return mNetworkService;
    }


    private static class HeaderInterceptor implements Interceptor {

        private static final String ACCEPT_HEADER_NAME = "Accept";
        private static final String ACCEPT_HEADER_BODY = "application/vnd.github.v3+json";

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            final Request request =
                    chain.request().newBuilder()
                            .addHeader(ACCEPT_HEADER_NAME, ACCEPT_HEADER_BODY)
                            .build();

            return chain.proceed(request);
        }
    }
}
