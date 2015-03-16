package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterFactory;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DialogManager {
	private static DialogManager instance;
	private static List<ReplyDialogController> dialogs;
	
	private DialogManager() {
		dialogs  = new ArrayList<ReplyDialogController>();
	}
	
	public void createDialog(Status status) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("replyDialog.fxml"));
		try {
			loader.load();
			Parent root = loader.getRoot();
			ReplyDialogController controller = loader.getController();

			Scene scene = new Scene(root);
			Stage dialog = new Stage(StageStyle.TRANSPARENT);
			dialog.setScene(scene);
			dialog.setResizable(false);
			
			controller.setStatus(status);
			controller.setUserName(status.getUser().getName());
			controller.setText(status.getText());
			Image image = new Image(status.getUser().getBiggerProfileImageURL());
			controller.setIcon(image);

			controller.setNum(dialogs.size());
			controller.setStage(dialog);
			
			dialogs.add(controller);
			showDialogs();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showDialogs() {
		for(int i=0; i<dialogs.size(); i++) {
			Stage dialog = dialogs.get(i).getStage();
			dialog.setX(1150);
			dialog.setY(50 + 90*i);
			dialog.show();
		}
		System.out.println(dialogs);
	}
	
	public List<ReplyDialogController> getDialogControllerList() {
		return dialogs;
	}
	
	public void hideDialog(int num) {
		System.out.println("DELETE:num="+num+" are deleted.");
		dialogs.get(num).getStage().hide();
		dialogs.remove(num);
		reNumbering(num);
		showDialogs();
	}
	
	private void reNumbering(int num) {
		for(int i=num; i<dialogs.size();i++) {
			dialogs.get(i).setNum(i);
		}
	}
	
	public static DialogManager getInstance() {
		if(instance == null) {
			instance = new DialogManager();
		}
		return instance;
	}
}