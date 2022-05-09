package HamroBot.poem;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

public class DisplayAuthor {
    String url = "https://poetrydb.org/author";
    BufferedReader reader;
    URL authorUrl;
    String lines;
    StringBuffer responseValue = new StringBuffer();
    Random random = new Random();
    int min=0;
    int max=128;
    public void sendHttpRequest(GuildMessageReceivedEvent eve) throws IOException {
        try {
            authorUrl = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(authorUrl.openConnection().getInputStream()));
            while ((lines = reader.readLine()) != null) {
                responseValue.append(lines);
            }
            reader.close();
            System.out.print(responseValue);
            JSONObject object = new JSONObject(responseValue.toString());
            JSONArray array = object.getJSONArray("authors");
            int value=random.nextInt(max - min + 1) + min;
            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setTitle("TRY THIS AUTHOR'S POEM");
            embedBuilder.addField(array.get(value).toString(),"$poem_<authors-name>",false);
            embedBuilder.setFooter("Poem's written by the author will be displayed");
            embedBuilder.setColor(Color.ORANGE);
            eve.getChannel().sendMessage(embedBuilder.build()).queue();
            embedBuilder.clear();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
