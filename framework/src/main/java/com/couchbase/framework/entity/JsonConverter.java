package com.couchbase.framework.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter {
	/**
	 * expected to be used by other Date conversion code
	 */
	@SuppressWarnings("WeakerAccess")
	public static final String ISO_DATETIME_TIME_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	private final Gson gson = createGson();


	public <T> T fromJson(String source, Class<T> type) {
		return gson.fromJson(source, type);
	}

	public <T> String toJson(T source) {
		return gson.toJson(source);
	}

	private Gson createGson() {
		return 	new GsonBuilder()
				.setDateFormat(ISO_DATETIME_TIME_ZONE_FORMAT)
				.create();
	}
}