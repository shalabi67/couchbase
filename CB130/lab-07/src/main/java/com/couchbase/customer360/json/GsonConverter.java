package com.couchbase.customer360.json;

import static org.apache.commons.lang3.time.DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * JsonConverter implementation based on the GSON library.
 * 
 * @author Tony Piazza
 */
public class GsonConverter implements JsonConverter {
	private final Gson gson = 
		new GsonBuilder()
			.setDateFormat(ISO_DATETIME_TIME_ZONE_FORMAT.getPattern())
			.create();

	public <T> T fromJson(String source, Class<T> type) {
		return gson.fromJson(source, type);
	}

	public <T> String toJson(T source) {
		return gson.toJson(source);
	}
}