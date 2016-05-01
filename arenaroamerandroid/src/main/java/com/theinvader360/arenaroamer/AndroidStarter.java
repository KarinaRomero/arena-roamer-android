package com.theinvader360.arenaroamer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.theinvader360.arenaroamer.ArenaRoamer;

public class AndroidStarter extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // prevent the screen from dimming/sleeping (no permission required)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
        Intent intent = getIntent();
        Cliente.player = intent.getStringExtra("player");
        Log.d("Player", Cliente.player);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        initialize(new ArenaRoamer(), cfg);
    }
}
