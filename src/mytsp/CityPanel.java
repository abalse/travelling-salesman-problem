package mytsp;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CityPanel extends JPanel implements MouseListener{
	
	private static final long serialVersionUID = 9220036263068464736L;
	private BufferedImage image;
	private static Map<Integer,Point> cityLocations = new HashMap<Integer, Point>();
	private static Map<String, Point[]> cityDistances = new HashMap<String, Point[]>();
	Point[] cityPoints12 = new Point[2];
	Integer[] city12 = new Integer[2];
	private static int cityOrder = 1;
	
	private boolean drawCity = false;
	private boolean activateDrawCity = false;
	private boolean activatePaths = false;
	private boolean drawPathMode = false;
	
	private boolean city1selected = false;
	private boolean city2selected = false;
	
	public void activateAddCity(){
		activateDrawCity = true;
	}
	
	public void activateAddPaths(){
		activatePaths = true;
		activateDrawCity = false;
	}
	
	public void resetEverything(){
		cityLocations = new HashMap<Integer, Point>();
		cityDistances = new HashMap<String, Point[]>();
		cityPoints12 = new Point[2];
		cityOrder = 1;
		activateDrawCity = false;
		drawCity = false;
		activatePaths = false;
		drawPathMode = false;
		city1selected = false;
		city2selected = false;
		repaint();
	}
	
	public void getDistanceMatrix(){
		
    	
    		
    		Object distanceMatrix[][] = new Object[10][10];
    		
    		for(int i = 1; i< cityOrder ; i ++){
    			for(int j = 1; j< cityOrder ; j ++){
    				distanceMatrix[i][j] = 999;
    			}
    		}
    		for(int i = 1; i< cityOrder ; i ++){
    			for(int j = 1; j< cityOrder ; j ++){
    				Iterator<Map.Entry<String,Point[]>> cityDistancesMapping = cityDistances.entrySet().iterator();
    				while (cityDistancesMapping.hasNext()) {
    		    		Map.Entry<String,Point[]> mapping = cityDistancesMapping.next();
    		    		String[] cityInfo = mapping.getKey().split("S<>M");
    		    		String weight = cityInfo[0];
    		    		String cities = cityInfo[1];
    		    		
    		    		String city1 = cities.split("-")[0];
    		    		String city2 = cities.split("-")[1];
    		    		if(i == Integer.parseInt(city1) && j == Integer.parseInt(city2)){
    		    			distanceMatrix[i][j] = weight;
    		    			distanceMatrix[j][i] = weight;
    		    		}
    		    	}
    			}
    		}
    		
    		for(int i = 1; i< cityOrder ; i ++){
    			System.out.println("\n");
    			for(int j = 1; j< cityOrder ; j ++){
    				System.out.print(distanceMatrix[i][j] + "\t");
    			}
    		}
    	}
	
	
	public CityPanel(){
		addMouseListener(this);
		 try {
			image = ImageIO.read(new File("C:\\Users\\abhbalse\\Downloads\\MyTSP\\MyTSP\\images\\cityImage.gif"));
		} catch (IOException e) {
			System.out.println("Error Reading Image");
		}
	}
	
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(activateDrawCity){
			Point whereClicked = e.getPoint();
			cityLocations.put(cityOrder++,whereClicked);
			drawCity = true;
			repaint();
		}else if (!activatePaths){
			JOptionPane.showMessageDialog(this, "Click on Add City button to activate add city feature");
		}else{
			
			Point whereClicked = e.getPoint();
			 
			int maxX = whereClicked.x + 40;
			int minX = whereClicked.x - 40;
			
			int maxY = whereClicked.y + 40;
			int minY = whereClicked.y - 40;
			
			Iterator<Map.Entry<Integer,Point>> cities = cityLocations.entrySet().iterator();
        	while (cities.hasNext()) {
        	    Map.Entry<Integer,Point> city = cities.next();
        	    if(maxX >= city.getValue().x && minX <= city.getValue().x &&
        	    		maxY >= city.getValue().y && minY <= city.getValue().y){
        	    	String message = "You have selected city " + city.getKey() + "\n city1selected :"+ city1selected +" \n city2selected" + city2selected;
        	    	JOptionPane.showMessageDialog(this, message);
        	    	
        	    	if(!city1selected && !city2selected){
        	    		city1selected = true;
        	    		cityPoints12[0] = city.getValue();
        	    		city12[0] = city.getKey();
        	    		
        	    	}else if (city1selected && !city2selected){
        	    		city2selected = true;
        	    		cityPoints12[1] = city.getValue();
        	    		city12[1] = city.getKey();
        	    	}
        	    	
        	    	if(city1selected && city2selected){
        	    		drawPathMode = true;
        	    		city1selected = false;
        	    		city2selected = false;
        	    		String pathDistance = (String)JOptionPane.showInputDialog(this, "Enter the distance between the cities");
        	    		cityDistances.put(pathDistance +"S<>M" +city12[0]+"-"+city12[1], cityPoints12);
        	    		cityPoints12 = new Point[2];
        	    		city12 = new Integer[2];
        	    		repaint();
        	    	}
        	    	
        	    	
        	    }
        	}
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xCordinate = 0;
        int yCordinate = 0;
        
        
        String cityLabel = "City";
        if((drawCity && activateDrawCity) || activatePaths){
        	Iterator<Map.Entry<Integer,Point>> cities = cityLocations.entrySet().iterator();
        	while (cities.hasNext()) {
        	    Map.Entry<Integer,Point> city = cities.next();
        	    xCordinate = city.getValue().x;
        	    yCordinate = city.getValue().y;
        	    g.drawImage(image,xCordinate,yCordinate,null);
        	    g.drawString(cityLabel+city.getKey(),xCordinate,yCordinate);
        	}
        }
        
        
        
        if(drawPathMode){
        	Iterator<Map.Entry<String,Point[]>> cityDistancesMapping = cityDistances.entrySet().iterator();
        	while (cityDistancesMapping.hasNext()) {
        		Map.Entry<String,Point[]> mapping = cityDistancesMapping.next();
        		int city1X = mapping.getValue()[0].x+22;
        		int city1Y = mapping.getValue()[0].y+22;
        		
        		int city2X = mapping.getValue()[1].x+22;
        		int city2Y = mapping.getValue()[1].y+22;
        		
        		String[] cityInfo = mapping.getKey().split("S<>M");
        		String weight = cityInfo[0];
        		String cities = cityInfo[1];
        		g.drawString(weight,(city1X+city2X)/2 +10, (city1Y+city2Y)/2 + 10);
        		g.drawLine(city1X,city1Y,city2X,city2Y);
        	}
        	
        	
        }
    }
}
