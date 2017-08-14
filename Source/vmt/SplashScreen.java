package vmt;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class SplashScreen extends Window
{
    // Create the status label
    protected JLabel labelStatus = new JLabel( " Welcome", JLabel.LEFT );

    //==========================================================================
    // Constructor(s)
    //==========================================================================
    public SplashScreen( String strImageFilename ) 
    {
        super( new JFrame() );
        
        // Create our ImageIcon
        ImageIcon imageScreen = new ImageIcon( strImageFilename );

        // Create a JLabel to hold the image
        JLabel labelImage = new JLabel( imageScreen, JLabel.CENTER );

        // Create a JPanel to hold our JLabels        
        JPanel panelImage = new JPanel( new BorderLayout() );
        
        // Add the Image Icon JLabel to the image panel     
        panelImage.add( labelImage, BorderLayout.CENTER );

        // Add the status label to the panel
        panelImage.add( labelStatus, BorderLayout.SOUTH );

        // Set the border type to be a BevelBorder
        panelImage.setBorder( new BevelBorder( BevelBorder.RAISED ) );

        // Add the image panel to the Window
        add( panelImage );

        // Compress the window to fit the size of the components
        pack();

        // Center our window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = getSize();
        this.setBounds( (screenSize.width - windowSize.width) / 2,
                        (screenSize.height - windowSize.height) / 2,
                        windowSize.width,
                        windowSize.height);

        // Hide our Window        
        setVisible( false );
    }

    //==========================================================================
    // Misc. Method(s)
    //==========================================================================
    public void updateStatus( String strStatus ) {
        labelStatus.setText(" " + strStatus );
        setVisible( true );
    }

    public void openWindow() {
        setVisible( true );
    }

     public void closeWindow() {
        setVisible( false );
    }

    //==========================================================================
    // Test Method
    //==========================================================================
    public static void main(String[] args) {
        SplashScreen s = new SplashScreen("images\\VMT_splash_blue.jpg");
        s.openWindow();
        Thread t = new Thread();
        try {
            t.sleep(5000);
        }
        catch (InterruptedException e) {

        }
        s.closeWindow();
    }

}