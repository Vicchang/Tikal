
package tikal_dev;

import java.awt.Color;
import java.awt.GridLayout;

/**
 *	@authors: 
 * 	
 */


public class TileColumn extends javax.swing.JPanel { 
    
    
    public TileColumn(){

    	//Size of 5x1 of Tiles
        this.setSize(500, 100);
        this.setOpaque(false);
        
        //Set Holder Layout 
        GridLayout layout = new GridLayout();
        layout.setColumns(1);
        layout.setRows(5);
        this.setLayout(layout);
                    
    }
}
