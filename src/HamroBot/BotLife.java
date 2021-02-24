package HamroBot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;

public class BotLife {
    public static String prefix = "$";
    BotLife() throws LoginException{
        JDABuilder builder = JDABuilder.create(GatewayIntent.GUILD_MESSAGES);
        builder.setToken("Nzc1OTc4NDAxNjUwOTY2NTQw.X6uMMw.YtEeBTMKWBapijdZeydCmqO8dsQ");
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("Masu \uD83C\uDF56"));
        builder.addEventListeners(new Commands());
        builder.build();
    }
}
