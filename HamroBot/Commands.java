package HamroBot;

import com.fasterxml.jackson.core.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Commands extends ListenerAdapter {
   private JSONParser parser;
   private BufferedReader reader;
   private String lines;
   private JSONArray array;
   private URL memeUrl;
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String postLink="";
        String url="";
        String title="";
        String[] args = event.getMessage().getContentRaw().split(" ");
        String test = event.getMessage().getContentRaw();
        parser = new JSONParser();
       if(test.equalsIgnoreCase("meme dey")) {
           try {
              memeUrl = new URL("https://meme-api.herokuapp.com/gimme");
                       BufferedReader reader = new BufferedReader(new InputStreamReader(memeUrl.openConnection().getInputStream()));
                       while((lines=reader.readLine())!=null){
                           array = new JSONArray();
                           array.add(parser.parse(lines));

                           for(Object obj : array){
                               JSONObject object = (JSONObject)obj;
                               postLink=(String)object.get("postLink");
                               url=(String)object.get("url");
                               title=(String)object.get("title");
                   }
               }
               reader.close();

               EmbedBuilder embedBuilder = new EmbedBuilder();
               embedBuilder.setTitle(title,postLink);
               embedBuilder.setImage(url);
               embedBuilder.setColor(Color.ORANGE);
               event.getChannel().sendMessage(embedBuilder.build()).queue();
               embedBuilder.clear();
           }catch(MalformedURLException ex){
               ex.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           } catch (ParseException e) {
               e.printStackTrace();
           }
       }
      else
         {
           for (int i = 0; i < args.length; i++) {
               if (args[i].contains("masu")) {
                       event.getChannel().sendMessage("Masu suney maile malai pani chaiyo").queue();
                   break;
               }
               }
           }
       }
    }

