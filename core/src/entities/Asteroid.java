package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.test.screens.MainGame;

public class Asteroid {
	public static final int SPEED = 250;
	public static final int WIDTH = 44;
	public static final int HEIGHT = 42;
	private static Texture texture;
	private static TextureRegion region;
	
	float x, y;
	public boolean remove = false;
	public float rotation;
	public float randomScale;
	
	public Asteroid (float x) {
		this.x = x;
		this.y = Gdx.graphics.getHeight() + HEIGHT;
		//this.rect = new CollisionRect(x, y, WIDTH, HEIGHT);
		
		if (texture == null)
			texture = new Texture("asteroid.png");
		
		region = new TextureRegion(texture, 0, 0, WIDTH, HEIGHT);
		rotation = MainGame.getRandomFloat(-360, 360);
		randomScale = MainGame.getRandomFloat(0.3f, 1);
		
		System.out.println("Asteroid!");
	}
	
	public void update (float deltaTime) {
		y -= SPEED * deltaTime;
		if ( y < -(HEIGHT) )
			remove = true;
		
		//Rotação do asteroide!
		
		if( rotation >= 0){
			rotation--;
			
		}else{
			rotation++;
		}
		
		
		//rect.move(x, y);
	}
	
	public void render (SpriteBatch batch) {
		batch.draw(region, x, y, WIDTH / 2f, HEIGHT / 2f, WIDTH, HEIGHT, randomScale, randomScale, rotation);
		
	}
	
	/*public CollisionRect getCollisionRect () {
		return rect;
	}*/
	
	public float getX () {
		return x;
	}
	
	public float getY () {
		return y;
	}
}
