package HamroBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class CovidApi {
    String url = "https://covid-api.mmediagroup.fr/v1/cases?country=Nepal";
    BufferedReader reader;
    URL covidUrl;
    String lines;
    StringBuffer responseValue = new StringBuffer();
    String confirmed, recovered, deaths,location,date,life_expect,capital_city;

    public void sendHttpRequest(GuildMessageReceivedEvent eve,String $country) {

        try {
            covidUrl = new URL("https://covid-api.mmediagroup.fr/v1/cases?country="+$country);
            BufferedReader reader = new BufferedReader(new InputStreamReader(covidUrl.openConnection().getInputStream()));
            while ((lines = reader.readLine()) != null) {
                responseValue.append(lines);
            }
            reader.close();
            //  System.out.print(responseValue.toString());
            JSONObject object1 = new JSONObject(responseValue.toString());
            JSONObject object2 = object1.getJSONObject("All");
            //System.out.println(object2.get("confirmed"));
            confirmed = object2.get("confirmed").toString();
            recovered = object2.get("recovered").toString();
            deaths = object2.get("deaths").toString();
            try {
                date = object2.get("updated").toString();
            }catch(Exception e){
                date = "2021/04/28 06:20:49+00";
            }
            location=object2.get("location").toString();
            life_expect=object2.get("life_expectancy").toString();
            capital_city=object2.get("capital_city").toString();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Covid-19 Tracker "+$country);
            embedBuilder.addField("*) Location",location,true).setColor(Color.CYAN);
            embedBuilder.addField("*) Life Expectancy",life_expect,true).setColor(Color.CYAN);
            embedBuilder.addField("*) Capital City",capital_city,true).setColor(Color.CYAN);
            embedBuilder.addField("1) Confirmed",confirmed,false).setColor(Color.CYAN);
            embedBuilder.addField("2) Recovered",recovered,false).setColor(Color.CYAN);
            embedBuilder.addField("3) Total Deaths",deaths,false).setColor(Color.RED);
            embedBuilder.setFooter("Updated Date "+date);
            // embedBuilder.setImage(poster_image);
            embedBuilder.setColor(Color.GREEN);
            eve.getChannel().sendMessage(embedBuilder.build()).queue();
            embedBuilder.clear();

        } catch (MalformedURLException murl) {
            murl.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
