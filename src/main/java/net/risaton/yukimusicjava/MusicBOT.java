package net.risaton.yukimusicjava;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MusicBOT extends ListenerAdapter {
    private AudioPlayerManager playerManager;
    private AudioPlayer player;
    public MusicBOT(){
        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        this.player = playerManager.createPlayer();
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        if(event.getUser().isBot()) return;
        if(event.getName().equals("play")){
            event.reply("頑張っています...").queue();
            String url = event.getOption("url").getAsString();
            if(url == null || url.isEmpty()) {
                event.getChannel().sendMessage("URLがNULLです.");
                return;
            };
            loadAndPlay(event, url);
        } else
        if (event.getName().equals("ping")) {
            System.out.println("Ping: " + event.getUser().getAsTag());
            event.reply("Pong!").queue();
        }
    }

    private void loadAndPlay(SlashCommandInteractionEvent event, String url) {
        playerManager.loadItem(url, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                player.playTrack(track);
                event.getChannel().sendMessage("再生中: " + track.getInfo().title).queue();
            }

            @Override
            public void noMatches() {
                event.getChannel().sendMessage("該当なし.").queue();

            }

            @Override
            public void loadFailed(FriendlyException exception) {
                event.getChannel().sendMessage("読み込み失敗").queue();
                exception.printStackTrace();
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {

            }

        });
    }
}
