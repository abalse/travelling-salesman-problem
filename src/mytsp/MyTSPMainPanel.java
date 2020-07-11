package mytsp;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class MyTSPMainPanel extends JFrame {
     
    private JButton addCity = new JButton("Add City");
    private JButton addDistance = new JButton("Add Distance , Click on any 2 cities");
    private JButton clearAll = new JButton("Clear All");
    private JButton getDistanceMatrix = new JButton("Get Distance Matrix");
	
	JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel leftPanel = new JPanel();
    CityPanel centerPanel = new CityPanel();
    
    public MyTSPMainPanel() {
        super("Travelling Salesman Problem");
        
        Dimension minimumSize = new Dimension(1000,600);
        
        GridLayout leftPanelLayout = new GridLayout(10,1);
        leftPanelLayout.setHgap(0);
        leftPanelLayout.setVgap(10);
        leftPanel.setLayout(leftPanelLayout);
        
        mainPanel.add(leftPanel,  BorderLayout.WEST);
        
        centerPanel.setBorder(
                BorderFactory.createTitledBorder("City View"));
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        
        leftPanel.add(addCity);
        leftPanel.add(addDistance);
        leftPanel.add(clearAll);
        leftPanel.add(getDistanceMatrix);
        
        ImageIcon pic = new ImageIcon("C:\\Users\\abhbalse\\Downloads\\MyTSP\\MyTSP\\images\\salesLabel.gif");
        
        leftPanel.add(new JLabel(pic));
        
        addCity.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                centerPanel.activateAddCity();
            }
        });
        
        addDistance.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                centerPanel.activateAddPaths();
                addCity.setEnabled(false);
            }
        });
        
        clearAll.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                addCity.setEnabled(true);
                centerPanel.resetEverything();
            }
        });
        
        
        getDistanceMatrix.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                centerPanel.getDistanceMatrix();
            }
        });
       // set border for the panel
        mainPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Travelling Salesman Window"));
         
        // add the panel to this frame
        add(mainPanel);
        
        setMinimumSize(minimumSize);
        pack();
        setLocationRelativeTo(null);
    }
     
    public static void main(String[] args) {
        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyTSPMainPanel().setVisible(true);
            }
        });
    }
}