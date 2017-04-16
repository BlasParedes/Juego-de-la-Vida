/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegovida;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 *
 * @author usuario
 */
public class JuegoVida extends JFrame{
    private boolean Matriz[][];
    private JLabel JMatriz[][];
    private JButton Inicio;
    private JButton Limpiar;
    private Timer tiempo;
    public static final int N = 100;
    public JuegoVida(int WIDTH,int HEIGHT) {
        super("Juego de la Vida");
        this.setSize(WIDTH, HEIGHT);
        this.Matriz = new boolean[N][N];
        this.JMatriz = new JLabel[N][N];
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        for(int i=0;i<Matriz.length;i++){
            for(int j=0;j<Matriz[i].length;j++){
                this.Matriz[i][j] = false;
                this.JMatriz[i][j] = new JLabel();
                this.JMatriz[i][j].setBounds(i*5, j*5, 5, 5);
                this.JMatriz[i][j].setBackground(Color.black);
                this.JMatriz[i][j].setOpaque(true);
                this.JMatriz[i][j].addMouseListener(new Raton());
                this.add(this.JMatriz[i][j]);
            }
        }
        this.tiempo = new Timer(100,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean M2[][] = new boolean[100][100];
                for(int i=0;i<Matriz.length;i++)
                    for(int j=0;j<Matriz[i].length;j++)
                        M2[i][j] = Matriz[i][j];
                for(int i=0;i<Matriz.length;i++){
                    for(int j=0;j<Matriz[i].length;j++){
                        if(Matriz[i][j]){
                            if( CelulasVivas(i,j) != 2 && CelulasVivas(i,j) != 3){
                                M2[i][j] = false;
                                JMatriz[i][j].setBackground(Color.black);                                
                            }
                        }else{
                            if( CelulasVivas(i,j) == 3 ){
                                M2[i][j] = true;
                                JMatriz[i][j].setBackground(Color.white);
                                
                            }
                        }
                    }                    
                }
                Matriz = M2;
            }
        
        });
        this.Inicio = new JButton("Inicio");
        this.Inicio.setBounds(0, 500, 100, 25);
        this.Inicio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tiempo.isRunning()){
                    tiempo.stop();
                    Inicio.setText("Iniciar");
                }else{
                    tiempo.start();
                    Inicio.setText("Parar");
                }
            }        
        });
        this.Inicio.setVisible(true);
        this.add(this.Inicio);
        this.Limpiar = new JButton("Limpiar");
        this.Limpiar.setBounds(150, 500, 100, 25);
        this.Limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tiempo.isRunning()){ 
                    tiempo.stop(); 
                    Inicio.setText("Iniciar"); 
                }
                for(int i=0;i<Matriz.length;i++){
                    for(int j=0;j<Matriz[i].length;j++){
                        Matriz[i][j] = false;
                        JMatriz[i][j].setBackground(Color.black);
                    }
                }
            }
        });
        this.add(Limpiar);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);     
        this.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public int CelulasVivas(int i,int j){
        int cont = 0;
        if(i<99)
        if(Matriz[i+1][j] && i<99 )
            cont++;
        if(i>0)
        if(Matriz[i-1][j])
            cont++;
        if(j>0)
        if(Matriz[i][j-1])
            cont++;
        if(j<99)
        if(Matriz[i][j+1])
            cont++;
        if(i<99 && j<99)
        if(Matriz[i+1][j+1])
            cont++;
        if(i>0 && j<99)
        if(Matriz[i-1][j+1])
            cont++;
        if(i<99 && j>0)
        if(Matriz[i+1][j-1])
            cont++;
        if(i>0 && j>0)
        if(Matriz[i-1][j-1])
            cont++;
        return cont;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        new JuegoVida(500,600);
    }
    public class Raton implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            for(int i=0;i<Matriz.length;i++){
                for(int j=0;j<Matriz[i].length;j++){
                    if(e.getComponent() == JMatriz[i][j])
                        if( !Matriz[i][j]){
                           JMatriz[i][j].setBackground(Color.white);
                           Matriz[i][j] = true;
                        }else{
                           JMatriz[i][j].setBackground(Color.black);
                           Matriz[i][j] = false;
                        }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            for(int i=0;i<Matriz.length;i++){
                for(int j=0;j<Matriz[i].length;j++){
                    if(e.getComponent() == JMatriz[i][j]){
                        JMatriz[i][j].setBackground(Color.white);
                    }
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            for(int i=0;i<Matriz.length;i++){
                for(int j=0;j<Matriz[i].length;j++){
                    if(e.getComponent() == JMatriz[i][j] && !Matriz[i][j]){
                        JMatriz[i][j].setBackground(Color.black);
                    }
                }
            }
        }
    }
    
}
