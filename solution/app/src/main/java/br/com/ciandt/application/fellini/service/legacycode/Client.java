package br.com.ciandt.application.fellini.service.legacycode;

import br.com.ciandt.application.fellini.service.apiconstants.APIConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(APIConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }


}
