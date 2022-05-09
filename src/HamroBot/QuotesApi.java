package HamroBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class QuotesApi {
    String url = "https://zenquotes.io/api/random";
    BufferedReader reader;
    URL quotesUrl;
    String lines;
    StringBuffer responseValue = new StringBuffer();
    public void sendHttpRequest(GuildMessageReceivedEvent eve) throws IOException {

        try {
            quotesUrl = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(quotesUrl.openConnection().getInputStream()));
            while ((lines = reader.readLine()) != null) {
                responseValue.append(lines);
            }
            reader.close();
           // System.out.print(responseValue);
          JSONArray array = new JSONArray(responseValue.toString());
            JSONObject object=array.getJSONObject(0);
           // System.out.print(object.get("q"));

            EmbedBuilder embedBuilder = new EmbedBuilder();
          embedBuilder.setTitle(object.get("a").toString());
            embedBuilder.setDescription(object.get("q").toString());
           // embedBuilder.setImage("https://picsum.photos/200");
            //embedBuilder.setAuthor("a","https://picsum.photos/200");
            embedBuilder.setThumbnail("https://picsum.photos/200");
            embedBuilder.setColor(Color.ORANGE);
            eve.getChannel().sendMessage(embedBuilder.build()).queue();
            embedBuilder.clear();
        }catch(Exception e){
          e.printStackTrace();
        }
    }
}
