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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Random;

public class MovieApiRequest {

    String pageValue;
    String movieUrl = "http://api.themoviedb.org/3/movie/top_rated?api_key=6f9c4a6a1bef0a6e31cffc4b5f308bf4&page="+pageValue;
    URL movie;
    JSONParser parser;
    JSONArray array;
    BufferedReader reader;
    String lines,title,poster_image,imdbRating,overview;
    float vote_avg;
    String mainValue;
    StringBuffer responseValue=new StringBuffer();
    int randomPageNumber;
    public void apiCallRequest(GuildMessageReceivedEvent eve)  {
        randomPageNumber=0;
        parser = new JSONParser();
        int minNum=1;
        int maxNum=430;
        Random randomTwo = new Random();
        randomPageNumber=randomTwo.nextInt(maxNum - minNum + 1) + minNum;
        pageValue=Integer.toString(randomPageNumber);
        try {
            movie = new URL("http://api.themoviedb.org/3/movie/top_rated?api_key=6f9c4a6a1bef0a6e31cffc4b5f308bf4&page="+pageValue);
            BufferedReader reader = new BufferedReader(new InputStreamReader(movie.openConnection().getInputStream()));
            while((lines=reader.readLine())!=null) {
                responseValue.append(lines);
            }
            reader.close();
          System.out.print(responseValue.toString());
            mainValue=responseValue.toString();
           JSONObject object = new JSONObject(mainValue);
            JSONArray array = object.getJSONArray("results");
            Random random = new Random();
            int min=0;
            int max=10;
            int value=random.nextInt(max - min + 1) + min;
            JSONObject obj=array.getJSONObject(value);
           // System.out.print(obj.getString("title"));
            title=obj.getString("title");
            vote_avg=obj.getInt("vote_average");
            String voteAvg=Float.toString(vote_avg);
         // poster_image= obj.getString("poster_path");
           overview=obj.getString("overview");
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(title);
           // embedBuilder.setImage(String.valueOf((URI.create(poster_image))));
            embedBuilder.setFooter("Vote Average : "+voteAvg);
            embedBuilder.setDescription(overview);
           // embedBuilder.setImage(poster_image);
            embedBuilder.setColor(Color.ORANGE);
            eve.getChannel().sendMessage(embedBuilder.build()).queue();
            embedBuilder.clear();
        }catch(MalformedURLException murl){

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  /* public static void main(String[] args){
       MovieApiRequest movieApiRequest=new MovieApiRequest();
       movieApiRequest.apiCallRequest();
   }*/
}
