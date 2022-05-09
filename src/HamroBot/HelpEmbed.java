package HamroBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.lang.reflect.Member;

public class HelpEmbed {
    public void makeEmbed(GuildMessageReceivedEvent eve){

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Fuchhey's Command List");
        embedBuilder.setDescription("Fuchhey is here to help you with all the commands");
        embedBuilder.addField("1.Meme Command","meme dey",true);
        embedBuilder.addBlankField(true);
        embedBuilder.addField("2.Movie Suggestion Command","$movies",true);
        embedBuilder.addField("3.Covid Data","$covid_<country-name/first letter uppercase>",false);
        embedBuilder.addField("4.Random Quotes","$quotes",true);
        embedBuilder.addField("5.Poems","$poems",false);
        embedBuilder.setColor(Color.ORANGE);
        eve.getChannel().sendMessage(embedBuilder.build()).queue();
        embedBuilder.clear();
    }
}
