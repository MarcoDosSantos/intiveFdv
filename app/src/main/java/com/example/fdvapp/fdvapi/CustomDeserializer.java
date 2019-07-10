package com.example.fdvapp.fdvapi;

import com.example.fdvapp.model.User;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class CustomDeserializer implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        String first = json.getAsJsonObject().get("name").getAsJsonObject().get("first").getAsString();
        String last = json.getAsJsonObject().get("name").getAsJsonObject().get("last").getAsString();
        String email = json.getAsJsonObject().get("email").getAsString();
        String username = json.getAsJsonObject().get("login").getAsJsonObject().get("username").getAsString();
        String largeUrl = json.getAsJsonObject().get("picture").getAsJsonObject().get("large").getAsString();
        String thumbnailUrl = json.getAsJsonObject().get("picture").getAsJsonObject().get("thumbnail").getAsString();

        return new User(first, last, email, username, largeUrl, thumbnailUrl);
    }
}
