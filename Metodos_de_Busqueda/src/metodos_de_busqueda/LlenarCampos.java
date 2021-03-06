/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos_de_busqueda;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class LlenarCampos extends JFrame {
    JButton Aceptar = new JButton("Aceptar");
    GridLayout grid;
    
    //public static Metodos_de_Busqueda metodos=new Metodos_de_Busqueda();
    public static String nodos[];
    
    public static ArrayList<String> ndos ;
      public static LlenarCampos frame;
    
    public LlenarCampos() {
        
        setResizable(false);
    }
    
    public void initGaps() {
    }
    
    public void addComponentsToPane(final Container pane) {
        initGaps();
        //metodos=Crear.metodos;
        final JPanel compsToExperiment = new JPanel();
        new GridLayout(0,2);
        ndos=Crear.metodos.getNdos();
        
        int cantidad=ndos.size();
        grid= new GridLayout(cantidad+2,cantidad+1);
        compsToExperiment.setLayout(grid);
        int total=cantidad*cantidad;
        int p=0;
        TextField tf[][]=new TextField[cantidad][cantidad+1];
        int matriz[][]=new int[cantidad][cantidad+1];
        
        
        
            compsToExperiment.add(new JLabel(""));
        
        for(int x=0;x<cantidad;x++){
            compsToExperiment.add(new JLabel(""+ndos.get(x)));
        }
        
            compsToExperiment.add(new JLabel("Val. Heur."));
        for(int x=0;x<cantidad;x++){
            compsToExperiment.add(new JLabel(""+ndos.get(x)));
            for(int y=0;y<cantidad;y++){
                //compsToExperiment.add(tf[p]);
                //tf[p].setText(x+","+y);
                //p++;
                compsToExperiment.add(tf[x][y]=new TextField("0"));
            }
                compsToExperiment.add(tf[x][cantidad]=new TextField("0"));
            
        }
        
        compsToExperiment.add(Aceptar);
        //Process the Apply gaps button press
        Aceptar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                //Set up the layout of the buttons
                grid.layoutContainer(compsToExperiment);
                
                boolean positivos=true;
                boolean peso=false;
                
                try{
                    for(int x=0;x<cantidad;x++){
                        for(int y=0;y<=cantidad;y++){
                            matriz[x][y]=Integer.parseInt(tf[x][y].getText());
                            if(matriz[x][y]<0)positivos=false;
                            if(matriz[x][y]>1||matriz[x][y]<0)peso=true;
                            
                        }
                    }
                    
                    if(positivos==false)JOptionPane.showMessageDialog(null, "Rango incorrecto, solo numeros positivos");
                    else if(Crear.metodos.peso==false&&peso==true)JOptionPane.showMessageDialog(null, "No se va a considerar el peso, solo puede indicar 0 o 1");
                    else{
                    Crear.metodos.setMatriz(matriz,cantidad);   
                    Crear.metodos.L_Adya.linea=new Nodo[cantidad];
                    for(int k=0;k<cantidad;k++){
                        //Crear.metodos.L_Adya.linea[k]=null;
                        
                        //Crear.metodos.L_Adya.agregar_nodo_i(ndos.get(k),0,0,null);
                        
                        Nodo ini=Crear.metodos.L_Info.buscar(ndos.get(k));
                        //System.out.println(ini+"     "+ ndos.get(k));
                        for(int j=0;j<cantidad;j++){
                            if(matriz[k][j]!=0){
                                Crear.metodos.L_Adya.agregar_nodo_i(ndos.get(j),matriz[k][j],0,ini);
                                ini.adyacente=Crear.metodos.L_Adya.ultimo;
                                //System.out.println("                       "+ini+"     ");
                                ini=Crear.metodos.L_Adya.ultimo;
                                //System.out.println("                       "+ini+"     ");
                        
                            }
                        }
                        
                        Crear.metodos.L_Adya.reset();
                    }
                    
                    
                    
                    
                    Crear.metodos.principal.setVisible(true);
                    frame.dispose();
                    }
                }
                catch (NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros en los campos");
        }
            }
        });
        pane.add(compsToExperiment, BorderLayout.NORTH);
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
     static void abrir() {
         frame = new LlenarCampos();
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                abrir();
            }
        });
    }
}