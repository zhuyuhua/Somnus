package com.somnus.utils.serializer.gson;

import com.google.gson.Gson;
import com.somnus.utils.serializer.ObjectSerializer;

public class GsonObjectSerializer implements ObjectSerializer {

	private Gson gson;

	GsonObjectSerializer(Gson gson) {
		this.gson = gson;
	}

	@Override
	public String serialize(Object anObject) {
		return gson.toJson(anObject);
	}

	@Override
	public <T> T deserialize(String serializedString, Class<T> objectClass) {
		return gson.fromJson(serializedString, objectClass);
	}
}