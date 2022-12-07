package prime.generator;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Word {

    private final String word_url = "https://random-words-api.vercel.app/word";
    private String subject = "";
    private String meaning = "";
    private String pronunciation = "";

    public Word() throws org.json.simple.parser.ParseException {

        getData();
    }

    private void getData() throws org.json.simple.parser.ParseException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(word_url)
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            JSONParser parser = new JSONParser();
            JSONArray Object = (JSONArray) parser.parse(data);
            JSONObject list = (JSONObject) Object.get(0);
            subject = (String) list.get("word");
            meaning = (String) list.get("definition");
            pronunciation = (String) list.get("pronunciation");

        } catch (IOException e) {
        }
    }

    public String getWord() {
        return subject;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getPronunciation() {
        return pronunciation;
    }

}
