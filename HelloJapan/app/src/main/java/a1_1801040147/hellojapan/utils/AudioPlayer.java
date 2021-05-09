package a1_1801040147.hellojapan.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class AudioPlayer {

    private static final String PATH = "mp3/";

    private static AudioPlayer audioPlayer;

    private MediaPlayer mediaPlayer;

    private AudioPlayer() {
        this.mediaPlayer = new MediaPlayer();
    }

    public static AudioPlayer getInstance() {
        if (audioPlayer == null)
            audioPlayer = new AudioPlayer();
        return audioPlayer;
    }

    public void playFromAsset(Context context, String assetName) {
        try {
            mediaPlayer.reset();
            AssetFileDescriptor descriptor = context.getAssets().openFd(PATH + assetName);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
