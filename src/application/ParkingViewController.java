package application;

import java.io.File;
import java.util.ArrayList;

import Logistics.Location;
import Logistics.ParkingController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author user - Noa bayer
 * @author user - Shlomi Ohana
 * @author user - Eli Golan
 * @author user - Stephanie Shalmoni
 * The class used to show the parking lot view
 */
public class ParkingViewController {

	@FXML
	private AnchorPane c1;


	@FXML
	private ImageView index11;

	@FXML
	private ImageView index12;

	@FXML
	private ImageView index13;

	@FXML
	private ImageView index14;

	@FXML
	private ImageView index21;

	@FXML
	private ImageView index22;

	@FXML
	private ImageView index23;

	@FXML
	private ImageView index24;

	@FXML
	private ImageView index31;

	@FXML
	private ImageView index32;

	@FXML
	private ImageView index33;

	@FXML
	private ImageView index34;

	/**
	 * Initialize variables
	 */
	@FXML
	public void initialize() {
		ArrayList<Location> loc = ParkingController.getInstance().display(TextEditor.getInstance().getNumParking());
		for(int i=1; i<5; i++) {
			for(int j=1; j<5; j++) {
				changeColor(i,j,1);
			}
		}

		for(Location location : loc) {
			if(location.getZ() == TextEditor.getInstance().getFloor()) {
				changeColor(location.getX(), location.getY(),2);
			}
		}
	}

	/**
	 * Change the parking lot view
	 * @param x
	 * @param y
	 * @param color
	 */
	void changeColor(int x, int y, int color)
	{
		File filer = new File("src/green-car.jpg");
		Image imagered = new Image(filer.toURI().toString()) ;

		File fileg = new File("src/red-car.jpg");
		Image imagegreen = new Image(fileg.toURI().toString()) ;
		if(x==1 && y==1)
		{
			if(color == 1) 
				index11.setImage(imagered);
			else
				index11.setImage(imagegreen);

			return;
		}
		if(x==1 && y==2){
			if(color == 1) 
				index12.setImage(imagered);
			else
				index12.setImage(imagegreen);
			return;
		}

		if(x==1 && y==3) {
			if(color == 1) 
				index13.setImage(imagered);
			else
				index13.setImage(imagegreen);
			return;
		}

		if(x==1 && y==4) {
			if(color == 1) 
				index14.setImage(imagered);
			else
				index14.setImage(imagegreen);
			return;
		}

		if(x==2 && y==1) {
			if(color == 1) 
				index21.setImage(imagered);
			else
				index21.setImage(imagegreen);
			return;
		}

		if(x==2 && y==2) {
			if(color == 1) 
				index22.setImage(imagered);
			else
				index22.setImage(imagegreen);
			return;
		}
		
		if(x==2 && y==3) {
			if(color == 1) 
				index23.setImage(imagered);
			else
				index23.setImage(imagegreen);
			return;
		}
		
		if(x==2 && y==4) {
			if(color == 1) 
				index24.setImage(imagered);
			else
				index24.setImage(imagegreen);
			return;
		}
		
		if(x==3 && y==1) {
			if(color == 1) 
				index31.setImage(imagered);
			else
				index31.setImage(imagegreen);
			return;
		}
		
		if(x==3 && y==2) {
			if(color == 1) 
				index32.setImage(imagered);
			else
				index32.setImage(imagegreen);
			return;

		}
		
		if(x==3 && y==3) {
			if(color == 1) 
				index33.setImage(imagered);
			else
				index33.setImage(imagegreen);
			return;
		}
		
		if(x==3 && y==4) {

			if(color == 1) 
				index34.setImage(imagered);
			else
				index34.setImage(imagegreen);
			return;
		}
	}
}
