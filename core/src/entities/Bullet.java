package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
	
	public static final int SPEED = 320;
	public static final float DEFAULT_Y = 40;
	public static final float WIDTH = 8 / 1.3f;
	public static final float HEIGHT = 33 /1.3f;
	
	public float x,y;
	public Texture texture;
	public boolean remove;
	
	public Bullet(float x){
		this.x = x;
		this.y = DEFAULT_Y;
		remove = false;
		if (texture == null)
			texture = new Texture(Gdx.files.internal("bullet.png"));
	}
	
	public void update (float deltaTime) {
		y += SPEED * deltaTime;
		if (y > Gdx.graphics.getHeight() + HEIGHT)
			remove = true;
	}
	
	public void render (SpriteBatch batch) {
		batch.draw(texture, x, y, WIDTH, HEIGHT);
	}
	

}
