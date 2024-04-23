package net.risaton.yukimusicjava;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import static java.lang.System.out;

public class Main extends ListenerAdapter {

    public static void main(String[] args) {
        out.println("Hello world!");
        Dotenv dotenv = Dotenv.load();
        String TOKEN = dotenv.get("DISCORD_TOKEN");
        JDA jda = JDABuilder.createDefault(TOKEN, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_VOICE_STATES)
                .addEventListeners(new MusicBOT())
                .setActivity(Activity.playing("Created by Yuki"))
                .build();
        jda.updateCommands().addCommands(
                Commands.slash("ping", "Reply with Pong!"),
                Commands.slash("play", "音楽を再生する.").addOption(OptionType.STRING, "url", "YouTubeのURL")
        ).queue();
        out.println("Logged in as: " + jda.getSelfUser().getName());

    }

}
//    @Override
//    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
//        switch (event.getName()){
//            case "ping":
//                event.reply("Pong!").queue();
//        }
//    }t







