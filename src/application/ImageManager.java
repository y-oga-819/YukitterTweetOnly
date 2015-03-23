package application;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import twitter4j.User;

public class ImageManager {
	private static Map<User, Image> imageManager;
	private static Map<String, Image> functionImage;

	private static ImageManager instance;
	
	private ImageManager() {
		imageManager = new HashMap<User, Image>();
		functionImage = new HashMap<String, Image>();
		
		Image replyImage = new Image("image/reply.png");
		Image favoriteImage = new Image("image/favorite.png");
		Image retweetImage = new Image("image/retweet.png");
		Image favoritedImage = new Image("image/favorited.png");
//		Image favoriteImage = new Image("image/favourites7.png");
//		Image favoriteImage = new Image("image/favourites7.png");
		
		functionImage.put("reply", replyImage);
		functionImage.put("favorite", favoriteImage);
		functionImage.put("retweet", retweetImage);
		functionImage.put("favorited", favoritedImage);
	}
	
	public Image getImage(User user) {
		if(!imageManager.containsKey(user)) {
			imageManager.put(user, new Image(user.getProfileImageURL()));
		}
		return imageManager.get(user);
	}
	
	public Image getImage(String function) {
		return functionImage.get(function);
	}

	public static ImageManager getSingleton() {
		if(instance == null) {
			instance = new ImageManager();
		}
		return instance;
	}
}
