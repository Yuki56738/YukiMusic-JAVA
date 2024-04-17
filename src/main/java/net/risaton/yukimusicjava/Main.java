package net.risaton.yukimusicjava;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class Main extends ListenerAdapter {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Dotenv dotenv = Dotenv.load();
        String TOKEN = dotenv.get("DISCORD_TOKEN");
        JDA jda = JDABuilder.createDefault(TOKEN)
                .addEventListeners(new Main())
                .setActivity(Activity.playing("Created by Yuki"))
                .build();
        jda.updateCommands().addCommands(
                Commands.slash("ping", "Reply with Pong!")
        ).queue();
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        switch (event.getName()){
            case "ping":
                event.reply("Pong!").queue();
        }
    }
}