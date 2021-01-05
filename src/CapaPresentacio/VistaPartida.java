/**
 * @file VistaPartida.java
 */
package src.CapaPresentacio;

/** @class VistaPartida
 *  @brief Vista de la pantalla per jugar una partida
 * 
 *  @author pere.ginebra
 */

import src.CapaDomini.Utils.PairII;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Timer;
import java.util.Stack;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import src.CapaDomini.Classes.Taulell;
import src.CapaDomini.Classes.Partida;
/**
 *
 * @author pere.ginebra
 */
public class VistaPartida extends javax.swing.JFrame {
    private CtrlPresentacio cp;
    private javax.swing.JFrame parent;
    private javax.swing.JPanel[][] grid;
    private javax.swing.JTextField[][] gridBlanques;
    private int h, m, s; //for time
    private Timer t;
    private int numCol, numFiles;
    private Stack undoable = new Stack<move>();
    private Stack redoable = new Stack<move>();
    /**
     * Creates new form Partida
     */
    
    /**
     * Creates new form SignIn
     */
    public VistaPartida(javax.swing.JFrame parent, CtrlPresentacio cp) {
        this.cp = cp;
        this.parent = parent;
        initComponents();
        initTemps();
        initTaulell();
        t.start();
    }
    
    private void initTemps() {
        String temps = cp.getTempsPartida();
        String aux = ""+ temps.charAt(0) + temps.charAt(1);
        h = Integer.valueOf(aux);
        aux = "" + temps.charAt(3) + temps.charAt(4);
        m = Integer.valueOf(aux);
        aux = "" + temps.charAt(6) + temps.charAt(7);
        s = Integer.valueOf(aux);
        
        //per quan anava en long
        //s = (int)temps%60;
        //m = (int)(temps%3600)/60;
        //h = (int)temps/3600;
        
        hourLabel.setText(h<10? "0"+String.valueOf(h):String.valueOf(h));
        minuteLabel.setText(m<10? "0"+String.valueOf(m):String.valueOf(m));
        secondLabel.setText(s<10? "0"+String.valueOf(s):String.valueOf(s));
        
        t=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(s<59){
                    s++;
                    String sec= (s<10?"0":"")+s;
                    secondLabel.setText(""+sec);
                }
                else {
                    s=0;
                    secondLabel.setText("00");
                    if(m<59){
                        m++;
                        String min= (m<10?"0":"")+m;
                        minuteLabel.setText(""+min);
                    } 
                    else {
                        m=0;
                        minuteLabel.setText("00");
                        h++;
                        String hr= (h<10?"0":"")+h;
                        hourLabel.setText(""+hr);
                    }
                }
            }
        });
    }
    
    private void initTaulell() {
        String[][] taulell = cp.getValorsTaulell();
        numFiles = taulell.length;
        numCol = taulell[0].length;
        
        int grid_length = 50*numCol, grid_height = 50*numFiles;
        if(grid_height > 365) {
            this.setMinimumSize(new Dimension(235+grid_length, 80+grid_height));
            this.setSize(235+grid_length, 80+grid_height);
        }
        else {
            this.setMinimumSize(new Dimension(235+grid_length, 445));
            this.setSize(235+grid_length, 445);
        }
        
        grid = new javax.swing.JPanel[numFiles][numCol];
        jPanel2.removeAll();
        jPanel2.setSize(new Dimension(grid_length, grid_height));
        jPanel2.setMinimumSize(new Dimension(grid_length, grid_height));
        jPanel2.setPreferredSize(new Dimension(grid_length, grid_height));
        jPanel2.setLayout(new GridLayout(numFiles, numCol));
        
        for (int i = 0; i < numFiles; i++){
            for(int j = 0; j < numCol; j++) {
                grid[i][j] = new javax.swing.JPanel();
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));    //PER POSAR BORDES A LES CASELLES
                jPanel2.add(grid[i][j]);
                if(cp.casellaBlanca(i, j)) {            //taulell[i][j] == "?" || taulell[i][j] == "3"  | cp.casellaBlanca(i, j) testing
                    String val = cp.getValorActual(i,j);
                    if(cp.casellaEsModificable(i,j)){   //taulell[i][j] == "?"  |  cp.casellaEsModificable(i,j) 
                        javax.swing.JTextField text = new javax.swing.JTextField();
                        text.setDocument(new LengthRestrictedDocument(1));
                        if(!val.equals("?")) text.setText(val);
                        //text.getDocument().addDocumentListener(new MyDocListener());
                        text.addKeyListener(new KeyListener() {
                            public void keyReleased(KeyEvent evt) {
                                CasellaInputEvent(evt);
                            }
                            
                            public void keyPressed(KeyEvent evt) {
                            }
                            
                            public void keyTyped(KeyEvent evt) {
                            }
                        });
                        
                        text.setPreferredSize(new Dimension(40, 40));
                        text.setBorder(BorderFactory.createLineBorder(Color.black, 0));
                        text.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        text.setBackground(new Color(220, 220, 220));
                        text.setOpaque(false);
                        grid[i][j].add(text);
                        //jPanel2.add(gridBlanques[i][j]);
                    }
                    else { 
                        javax.swing.JLabel label = new javax.swing.JLabel(val);
                        label.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                        label.setVerticalAlignment(javax.swing.JLabel.CENTER);
                        label.setPreferredSize(new Dimension(40,40));
                        grid[i][j].setBackground(new Color(110,110,110));
                        grid[i][j].add(label);
                        //jPanel2.add(label);
                    }
                }
                else if(cp.casellaNegra(i, j)) {        //taulell[i][j] == "*" | cp.casellaNegra(i, j)
                    grid[i][j].setBackground(new Color(25,25,25));
                }
                else if(cp.casellaSuma(i, j)){          //else  | if(cp.casellaSuma(i, j))
                    grid[i][j].setLayout(new javax.swing.OverlayLayout(grid[i][j]));
                    
                    javax.swing.JLabel labelFons = new javax.swing.JLabel();
                    labelFons.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Extras/Imatges/CasellaSuma.png")));
                    
                    int sumaFila = cp.getSumaFila(i, j);
                    int sumaCol = cp.getSumaCol(i, j);
                    
                    
                    if(sumaFila > 0) {
                        javax.swing.JLabel labelFila = new javax.swing.JLabel(String.valueOf(sumaFila));
                        labelFila.setToolTipText(null);
                        labelFila.setMaximumSize(new Dimension(50,50));
                        labelFila.setSize(50,50);
                        labelFila.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
                        labelFila.setVerticalAlignment(javax.swing.JLabel.TOP);
                        labelFila.setForeground(Color.WHITE);
                        grid[i][j].add(labelFila);                    
                    }
                    if(sumaCol >0) {
                        javax.swing.JLabel labelCol = new javax.swing.JLabel(String.valueOf(sumaCol));
                        labelCol.setToolTipText(null);
                        labelCol.setMaximumSize(new Dimension(50,50));
                        labelCol.setSize(50,50);
                        labelCol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        labelCol.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                        labelCol.setForeground(Color.WHITE);
                        grid[i][j].add(labelCol);
                    } 
                    
                    grid[i][j].add(labelFons);
                    
                }
            }
        }
    }
    /*
    public void casellaInput(javax.swing.event.DocumentEvent e) {
        
    }*/
   
    
    private void acabar() {
        t.stop();
        String time = hourLabel.getText()+":"+minuteLabel.getText()+":"+secondLabel.getText();
        cp.setTempsPartida(time);
        HintButton.setEnabled(false);
        SolveKakuroButton.setEnabled(false);
        missatgeLabel.setText("Kakuro completed!");
        secondLabel.setForeground(Color.red);
        minuteLabel.setForeground(Color.red);
        hourLabel.setForeground(Color.red);
        jLabel4.setForeground(Color.red);
        jLabel6.setForeground(Color.red);
        missatgeLabel.setVisible(true);
        undoable.clear();
        redoable.clear();
        UndoButton.setEnabled(false);
        RedoButton.setEnabled(false);
        SaveButton.setEnabled(false);
        SaveGame.setEnabled(false);

        cp.afegirPartidaFinalitzada(cp.getNomUser(), cp.getIdKakuro(), time);
    } 
    
    private void solucionar() {
        t.stop();
        HintButton.setEnabled(false);
        SolveKakuroButton.setEnabled(false);
        missatgeLabel.setText("Kakuro solved!");
        missatgeLabel.setVisible(true);
        undoable.clear();
        redoable.clear();
        UndoButton.setEnabled(false);
        RedoButton.setEnabled(false);
        SaveButton.setEnabled(false);
        SaveGame.setEnabled(false);
    }
    
    private void actualitzarCasellesBlanques() {
        for(int i = 0; i < numFiles; i++) {
            for(int j = 0; j < numCol; j++) {
                if(cp.casellaEsModificable(i, j)) {
                    javax.swing.JTextField txtf = (javax.swing.JTextField)grid[i][j].getComponent(0);
                    String txt = txtf.getText();
                    if(!txt.equals("") && !cp.getValorActual(i, j).equals(txt)) {
                        redoable.clear();
                        RedoButton.setEnabled(false);
                        undoable.push(new move(i, j, cp.getValorActual(i, j)));
                        UndoButton.setEnabled(true);
                        cp.modificarCasella(i, j, txt);
                    }
                    
                }
            }
        }
        if(cp.partidaAcabada()) acabar();
    }
    
    private void afegirTemps(int secs) {
        if(s+secs <= 59) {
            s += secs;
        }
        else {
            int aux_m = (s+secs)/60;
            s = (s+secs)%60;
            if(m+aux_m <= 59) m += aux_m;
            else {
                m = 0;
                h += 1;
            }
        }
        hourLabel.setText(h<10? "0"+String.valueOf(h):String.valueOf(h));
        minuteLabel.setText(m<10? "0"+String.valueOf(m):String.valueOf(m));
        secondLabel.setText(s<10? "0"+String.valueOf(s):String.valueOf(s));
    }
    
    private void CasellaInputEvent(KeyEvent evt) {
        //javax.swing.JTextField txt = (javax.swing.JTextField)evt.getSource();
        //seconds.setText(txt.getText());
        actualitzarCasellesBlanques();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        UndoButton = new javax.swing.JButton();
        RedoButton = new javax.swing.JButton();
        HintButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        SolveKakuroButton = new javax.swing.JButton();
        QuitButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        hourLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        minuteLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        secondLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        missatgeLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        SaveGame = new javax.swing.JMenuItem();
        TopExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(440, 350));

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 200));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));

        jPanel2.setBackground(new java.awt.Color(210, 210, 210));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setToolTipText("");
        jPanel2.setMinimumSize(new java.awt.Dimension(25, 25));
        jPanel2.setName(""); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 146, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 146, Short.MAX_VALUE)
        );

        UndoButton.setFont(new java.awt.Font("Chandas", 0, 15)); // NOI18N
        UndoButton.setText("UNDO");
        UndoButton.setEnabled(false);
        UndoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UndoButtonActionPerformed(evt);
            }
        });

        RedoButton.setFont(new java.awt.Font("Chandas", 0, 15)); // NOI18N
        RedoButton.setText("REDO");
        RedoButton.setEnabled(false);
        RedoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RedoButtonActionPerformed(evt);
            }
        });

        HintButton.setFont(new java.awt.Font("Chandas", 0, 15)); // NOI18N
        HintButton.setText("HINT");
        HintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HintButtonActionPerformed(evt);
            }
        });

        SaveButton.setFont(new java.awt.Font("Chandas", 0, 15)); // NOI18N
        SaveButton.setText("SAVE GAME");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        SolveKakuroButton.setFont(new java.awt.Font("Chandas", 0, 15)); // NOI18N
        SolveKakuroButton.setText("SOLVE KAKURO");
        SolveKakuroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SolveKakuroButtonActionPerformed(evt);
            }
        });

        QuitButton.setFont(new java.awt.Font("Chandas", 0, 15)); // NOI18N
        QuitButton.setText("QUIT GAME");
        QuitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitButtonActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setAlignmentX(0.0F);
        jPanel3.setAlignmentY(0.0F);
        jPanel3.setFont(new java.awt.Font("Chandas", 0, 18)); // NOI18N

        hourLabel.setFont(new java.awt.Font("Chandas", 0, 24)); // NOI18N
        hourLabel.setText("00");

        jLabel4.setFont(new java.awt.Font("Chandas", 0, 24)); // NOI18N
        jLabel4.setText(":");

        minuteLabel.setFont(new java.awt.Font("Chandas", 0, 24)); // NOI18N
        minuteLabel.setText("00");

        jLabel6.setFont(new java.awt.Font("Chandas", 0, 24)); // NOI18N
        jLabel6.setText(":");

        secondLabel.setFont(new java.awt.Font("Chandas", 0, 24)); // NOI18N
        secondLabel.setText("00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hourLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minuteLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secondLabel)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minuteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(secondLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(hourLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel1.setFont(new java.awt.Font("Chandas", 0, 18)); // NOI18N
        jLabel1.setText("Time:");

        missatgeLabel.setFont(new java.awt.Font("Chandas", 1, 15)); // NOI18N
        missatgeLabel.setForeground(new java.awt.Color(0, 204, 51));
        missatgeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        missatgeLabel.setToolTipText("");
        missatgeLabel.setAlignmentY(0.0F);
        missatgeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(missatgeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(missatgeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SolveKakuroButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HintButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(QuitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(UndoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RedoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RedoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UndoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SolveKakuroButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QuitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("");

        jMenu1.setText("File");

        SaveGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SaveGame.setText("Save Game");
        SaveGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveGameActionPerformed(evt);
            }
        });
        jMenu1.add(SaveGame);

        TopExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        TopExit.setText("Exit");
        TopExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TopExitActionPerformed(evt);
            }
        });
        jMenu1.add(TopExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TopExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TopExitActionPerformed
        parent.setVisible(true);
        dispose();
    }//GEN-LAST:event_TopExitActionPerformed

    private void QuitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitButtonActionPerformed
        parent.setVisible(true);
        dispose();
    }//GEN-LAST:event_QuitButtonActionPerformed

    private void SolveKakuroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SolveKakuroButtonActionPerformed
        String[][] sol = cp.solucionar();
        for(int i = 0; i < numFiles; i++) {
            for(int j = 0; j < numCol; j++) {
                if(cp.casellaEsModificable(i, j)) {
                    grid[i][j].removeAll();
                    javax.swing.JLabel label = new javax.swing.JLabel(sol[i][j]);
                    label.setPreferredSize(new Dimension(40, 40));
                    label.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                    grid[i][j].add(label);
                    grid[i][j].setBackground(new Color(110,110,110));
                }
            }
        }
        jPanel2.updateUI();
        solucionar();
    }//GEN-LAST:event_SolveKakuroButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        //t.stop();
        String time = hourLabel.getText()+":"+minuteLabel.getText()+":"+secondLabel.getText();
        cp.setTempsPartida(time);
        //guardar partida
        cp.guardarPartidaAMitges(time);
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void HintButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HintButtonActionPerformed
        PairII ajuda = cp.demanarAjuda();
        int i = ajuda.getLeft();
        int j = ajuda.getRight();
        grid[i][j].removeAll();
        javax.swing.JLabel label = new javax.swing.JLabel(cp.getValorActual(i, j));
        label.setPreferredSize(new Dimension(40, 40));
        label.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        label.setVerticalAlignment(javax.swing.JLabel.CENTER);
        grid[i][j].add(label);
        grid[i][j].setBackground(new Color(110,110,110));
        jPanel2.updateUI();
        String diff = cp.getDificultatPartida();
        if(diff.equals("facil")) afegirTemps(30);
        else if(diff.equals("mig")) afegirTemps(60);
        else if(diff.equals("dificil")) afegirTemps(120);
        if(cp.partidaAcabada()) acabar();
    }//GEN-LAST:event_HintButtonActionPerformed

    private void RedoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RedoButtonActionPerformed
        boolean nextHinted = true;
        
        while(nextHinted && !redoable.empty()) {
            move next = (move)redoable.peek();
            if(cp.casellaEsModificable(next.i, next.j)) nextHinted = false;
            else redoable.pop();
        }
        if(!redoable.empty()) {
            move m = (move)redoable.pop();
            if(redoable.empty()) RedoButton.setEnabled(false);
            int i = m.i, j = m.j;
            String val = m.val;
            javax.swing.JTextField jt = (javax.swing.JTextField)grid[i][j].getComponent(0);
            String old = jt.getText();
            undoable.push(new move(i, j, old));    //FER redoable.clear(); al posar un nou valor tu
            UndoButton.setEnabled(true);
            jt.setText(val);
            cp.modificarCasella(m.i, m.j, m.val);
            
            actualitzarCasellesBlanques();
            
            nextHinted = true;
            while(nextHinted) {
                if(!redoable.empty()) {
                    move next = (move)redoable.peek();
                    if(cp.casellaEsModificable(next.i, next.j)) nextHinted = false;
                    else redoable.pop();
                }
                else {
                    RedoButton.setEnabled(false);
                    nextHinted = false;
                }
            }
        }
        else {
            RedoButton.setEnabled(false);
        }
    }//GEN-LAST:event_RedoButtonActionPerformed

    private void UndoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoButtonActionPerformed
        boolean nextHinted = true;
        while(nextHinted && !undoable.empty()) {
            move next = (move)undoable.peek();
            if(cp.casellaEsModificable(next.i, next.j)) nextHinted = false;
            else undoable.pop();
        }
        if(!undoable.empty()) {
            move m = (move)undoable.pop();
            if(undoable.empty()) UndoButton.setEnabled(false);
            int i = m.i, j = m.j;
            String val = m.val;
            javax.swing.JTextField jt = (javax.swing.JTextField)grid[i][j].getComponent(0);
            String old = jt.getText();
            redoable.push(new move(i, j, old));    //FER redoable.clear(); al posar un nou valor tu
            RedoButton.setEnabled(true);
            jt.setText(val);
            cp.modificarCasella(m.i, m.j, m.val);
            
            actualitzarCasellesBlanques();
            
            nextHinted = true;
            while(nextHinted) {
                if(!undoable.empty()) {
                    move next = (move)undoable.peek();
                    if(cp.casellaEsModificable(next.i, next.j)) nextHinted = false;
                    else undoable.pop();
                }
                else {
                    UndoButton.setEnabled(false);
                    nextHinted = false;
                }
            }
        }
        else {
            UndoButton.setEnabled(false);
        }
    }//GEN-LAST:event_UndoButtonActionPerformed

    private void SaveGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveGameActionPerformed
        SaveButtonActionPerformed(evt);
    }//GEN-LAST:event_SaveGameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton HintButton;
    private javax.swing.JButton QuitButton;
    private javax.swing.JButton RedoButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JMenuItem SaveGame;
    private javax.swing.JButton SolveKakuroButton;
    private javax.swing.JMenuItem TopExit;
    private javax.swing.JButton UndoButton;
    private javax.swing.JLabel hourLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel minuteLabel;
    private javax.swing.JLabel missatgeLabel;
    private javax.swing.JLabel secondLabel;
    // End of variables declaration//GEN-END:variables
    
    //nomes per si utilitzo el document listener per obtenir el valor, pero keyevent semba millor per ara
    /*
    class MyDocListener implements DocumentListener {
        public void insertUpdate(DocumentEvent e) {
            casellaInput(e);
        }

        public void removeUpdate(DocumentEvent e) {
            casellaInput(e);
        }

        public void changedUpdate(DocumentEvent e){
            casellaInput(e);
        }

        public void casellaInput(DocumentEvent e) {
            Document d = (Document)e.getDocument();
            int change = e.getLength();
            //casella.getText();
            int end = d.getLength();
            try {
                seconds.setText(d.getText(end-change, end));
            } catch (BadLocationException ex) {}
        }
    }
    */  
}
/**
 * @class move
 * @brief estructura de dades per guardar l'informació d'un moviment (per undo/redo)
 * @author pere.ginebra
 */
class move {
    public int i, j;
    public String val;
    
    move(){
    }
    
    move(int i, int j, String val) {
        this.i = i;
        this.j = j;
        this.val = val;
    }
    
    public int getFila() {
        return i;
    }
    
    public int getCol() {
        return j;
    }
    
    public String getVal() {
        return val;
    }
}

/**
 * @class LengthRestrictedDocument
 * @brief Document amb restriccio de mida per les caselles (amb 1 sol numero)
 * @author pere.ginebra
 */
class LengthRestrictedDocument extends PlainDocument {

    private final int limit;

    public LengthRestrictedDocument(int limit) {
      this.limit = limit;
    }

    public void insertString(int offs, String str, AttributeSet a)
        throws BadLocationException {
      if (str == null)
        return;

      if ((getLength() + str.length()) <= limit) {
        try {
            if(Integer.parseInt(str) != 0) super.insertString(offs, str.substring(str.length()-1), a);
            else return;
        }catch (NumberFormatException e) {
            return;
        }
      }
    }
}
