package com.test.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.test.screens.MainGame;

public class MyTest extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainGame(this));
	}

	@Override
	public void render () {
		super.render();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		super.dispose();
		
	}
	
    @Override
    public void pause() {
        super.pause();
    }
    
    @Override
    public void resize(int width, int height) {
         super.resize(width, height);  

    }
}
