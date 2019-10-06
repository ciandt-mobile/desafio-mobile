package com.example.movies.src.utils;

import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Requests {
	private final static String TAG = "Requests";

	private OkHttpClient client;
	
	public Requests() {
		client = new OkHttpClient();
	}

	public String makeGet(String url) throws Exception {
		return new MyAsyncask().execute("GET", url).get();
	}

	private String httpGet(String url) throws Exception {
		String rsp = "";
		try {
			Request request = new Request.Builder().url(url).build();
			Response response = client.newCall(request).execute();
			rsp = response.body().string();
		} catch (Exception ex) {
			//Logger.log(TAG, ex);
			ex.printStackTrace();
			return "GET_ERROR";
		}
		return rsp;
	}

	private class MyAsyncask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... strings) {
			try{
				if ("GET".equals(strings[0])) {
					return httpGet(strings[1]);
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}
	}
}
