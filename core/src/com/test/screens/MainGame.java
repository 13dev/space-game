package com.test.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.test.game.MyTest;

import entities.Asteroid;
import entities.Bullet;

public class MainGame implements Screen {
	
	public static final float SHIP_WIDTH = 99 / 2;
	public static final float SHIP_HEIGHT = 75 / 2;
	public static final int SHIP_SPEED = 320;
	public static final float SHIP_OFFSET = 8f;
	public static final float ROLL_TIMER = 1f;
	public static final float SHOOT_WAIT_TIME = 0.3f;
	public static final float ASTEROID_SPAWN_INTERVAL = 3f;
	
	public MyTest game;
	public ArrayList<Texture> rolls;
	
	public int shipState;
	public float shipX, shipY, rollTimer, shootTimer, asteroidTimer;
	
	//bullets
	public ArrayList<Bullet> bullets;
	public ArrayList<Bullet> bulletsToRemove;
	
	//asteroids
	public ArrayList<Asteroid> asteroids;
	public ArrayList<Asteroid> asteroidsToRemove;
	
	//Randoms
	
	public float randomX;
	
	
	public MainGame(MyTest game){
		this.game = game;
		rolls = new ArrayList<Texture>();
		rolls.add(new Texture(Gdx.files.internal("player.png")));
		rolls.add(new Texture(Gdx.files.internal("player_right.png")));
		rolls.add(new Texture(Gdx.files.internal("player_left.png")));
		
		//bullets 
		bullets = new ArrayList<Bullet>();
		bulletsToRemove = new ArrayList<Bullet>();
		
		//bullets 
		asteroids = new ArrayList<Asteroid>();
		asteroidsToRemove = new ArrayList<Asteroid>();
		
		//Asteroids
		randomX = getRandomFloat(0, Gdx.graphics.getWidth() - Asteroid.WIDTH);
	}
	
	@Override
	public void show() {
		shipState = 0;
		shipX = Gdx.graphics.getWidth() / 2 - SHIP_WIDTH / 2;
		shipY = 10;
		rollTimer = 0;
		asteroidTimer = 0;
		
	}

	@Override
	public void render(float delta) {
		//clear screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Ship limitions
		if( shipX < 0 + SHIP_OFFSET)
			shipX = 0 + SHIP_OFFSET;
		
		if( shipX > Gdx.graphics.getWidth() - SHIP_WIDTH - SHIP_OFFSET)
			shipX = Gdx.graphics.getWidth() - SHIP_WIDTH - SHIP_OFFSET;
		
		//ship Controls
		
		if(isRight()) {
			shipX += SHIP_SPEED * delta;
			shipState = 1;
		}
		
		if(isLeft()) {
			shipX -= SHIP_SPEED * delta;
			shipState = 2;
			
		}
		
		//shoot
		shootTimer += delta;
		if(Gdx.input.isKeyPressed(Keys.SPACE) && shootTimer >= SHOOT_WAIT_TIME){
			shootTimer = 0;
			bullets.add(new Bullet(shipX  + (SHIP_WIDTH / 2) - 4));
			
		}
		
		//Asteroids
		asteroidTimer += delta;
		if(asteroidTimer >= ASTEROID_SPAWN_INTERVAL){
			asteroidTimer = 0;
			asteroids.add(new Asteroid(randomX));
			randomX = getRandomFloat(0, Gdx.graphics.getWidth() - Asteroid.WIDTH);
		}
		
		
		// Update bullets
		this.updateBullets(delta);
		
		//Update asteroids
		this.updateAsteroids(delta);
		
		//Reset Ship State
		rollTimer += delta;
		if( rollTimer >= ROLL_TIMER ){
			rollTimer = 0;
			shipState = 0;
			
		}
		
		game.batch.begin();
		game.batch.draw(rolls.get(shipState), shipX, shipY, SHIP_WIDTH, SHIP_HEIGHT);
		
		for (Bullet bullet : bullets) {
			bullet.render(game.batch);
		}
		
		for (Asteroid asteroid : asteroids) {
			
			asteroid.render(game.batch);
		}
		
		game.batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	private boolean isRight () {
		return Gdx.input.isKeyPressed(Keys.RIGHT) || (Gdx.input.isTouched());
	}
	
	private boolean isLeft () {
		return Gdx.input.isKeyPressed(Keys.LEFT) || (Gdx.input.isTouched());
	}
	
	private void updateBullets(float delta){
		//Update bullets
		for (Bullet bullet : bullets) {
			bullet.update(delta);
			if (bullet.remove){
				bulletsToRemove.add(bullet);
				System.out.println("Bullet removida BulletsToRemove Size: " + bulletsToRemove.size());
			}
		}
		
		bullets.removeAll(bulletsToRemove);
		// Clear list of bullets to remove
		bulletsToRemove.clear();
	}
	
	private void updateAsteroids(float delta) {
		//Update Asteroids
		for (Asteroid asteroid : asteroids) {
			asteroid.update(delta);
			if (asteroid.remove){
				asteroidsToRemove.add(asteroid);
				System.out.println("Asteroid removido AsteroidsToRemove Size: " + asteroidsToRemove.size());
			}
		}
		
		asteroids.removeAll(asteroidsToRemove);
		// Clear list of Asteroids to remove
		asteroidsToRemove.clear();
		
	}
	
	public static float getRandomFloat (float min, float max){
		float v = min + (new Random()).nextFloat() * (max - min);
		System.out.println("" + v);
		return v;
	}

}
