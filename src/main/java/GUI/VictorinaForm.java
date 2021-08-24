package GUI;

import db.DAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.prefs.Preferences;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import org.apache.commons.lang3.StringEscapeUtils;
import model.Question;
import model.User;
import repository.Victorina;

public class VictorinaForm extends javax.swing.JFrame {

    private LinkedList<Question> list;
    private ArrayList<JPanel> panel = new ArrayList<>();
    private ArrayList<JRadioButton> resultsCorr;
    private ArrayList<JRadioButton> resultsIncorr;
    private User user;
    private Victorina victorina;
    private HashMap<JRadioButton, Integer> mapQA;

    public VictorinaForm(Victorina victorina, User user) {
        initComponents();
        this.mapQA = new HashMap<>();
        this.user = user;
        this.victorina = victorina;
        resultsCorr = new ArrayList<>();
        resultsIncorr = new ArrayList<>();
        this.list = new LinkedList<>(victorina.getQuestionsWithoutEncrypt());
        for (int i = 0; i < list.size(); i++) {
            String corA = list.get(i).getCorrectAnswer();
            JRadioButton jr1 = new JRadioButton(corA);
            resultsCorr.add(jr1);
            ArrayList<String> incAns = new ArrayList<>(list.get(i).getIncorrectAnswers());
            this.mapQA.put(jr1, i);
            int x1 = 6;
            int y1 = 34;
            int x2 = 351;
            int y2 = 90;
            int x3 = 6;
            int y3 = 90;
            int x4 = 351;
            int y4 = 34;
            JPanel firstPanel = new JPanel();
            firstPanel.setLayout(null);

            String text = StringEscapeUtils.unescapeHtml4(list.get(i).getQuestion());
            JLabel jl = new JLabel(text);
            jl.setBounds(5, 100, 200, 300);
            jl.setLocation(6, -140);
            jl.setSize(1000, 300);
            if (incAns.size() > 2) {
                JRadioButton jr2 = new JRadioButton(incAns.get(0));
                JRadioButton jr3 = new JRadioButton(incAns.get(1));
                JRadioButton jr4 = new JRadioButton(incAns.get(2));
                resultsIncorr.add(jr2);
                resultsIncorr.add(jr3);
                resultsIncorr.add(jr4);
                firstPanel.add(jl);
                firstPanel.add(jr1);
                firstPanel.add(jr2);
                firstPanel.add(jr3);
                firstPanel.add(jr4);
                this.mapQA.put(jr2, i);
                this.mapQA.put(jr3, i);
                this.mapQA.put(jr4, i);
                jr1.setBounds(x1, y1, 30, 150);
                jr1.setSize(200, 17);
                jr1.setLocation(x1, y1);
                jr2.setLocation(x2, y2);
                jr2.setBounds(x2, y2, 30, 150);
                jr2.setSize(200, 17);
                jr3.setLocation(x3, y3);
                jr3.setBounds(x3, y3, 30, 150);
                jr3.setSize(200, 17);
                jr4.setLocation(x4, y4);
                jr4.setBounds(x4, y4, 30, 150);
                jr4.setSize(200, 17);
                ButtonGroup bg = new ButtonGroup();
                bg.add(jr1);
                bg.add(jr2);
                bg.add(jr3);
                bg.add(jr4);
            } else {
                JRadioButton jr2 = new JRadioButton(incAns.get(0));
                resultsIncorr.add(jr2);
                firstPanel.add(jl);
                firstPanel.add(jr1);
                firstPanel.add(jr2);
                this.mapQA.put(jr1, i);
                this.mapQA.put(jr2, i);
                jr1.setBounds(x1, y1, 30, 150);
                jr1.setSize(200, 17);
                jr1.setLocation(x1, y1);
                jr2.setLocation(x4, y4);
                jr2.setBounds(x4, y4, 30, 150);
                jr2.setSize(200, 17);
                ButtonGroup bg = new ButtonGroup();
                bg.add(jr1);
                bg.add(jr2);
            }
            panel.add(firstPanel);
            jTabbedPane1.addTab(i + 1 + "", firstPanel);
        }
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrollPane1 = new java.awt.ScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFocusCycleRoot(true);
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setName(""); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(100, 90));

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane1.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Final", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Final");

        jButton1.setText("Result");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Finish");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jButton3)
                .addGap(94, 94, 94)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setUserAnswer() {
        for (HashMap.Entry<JRadioButton, Integer> entry : this.mapQA.entrySet()) {
            if (entry.getKey().isSelected() && this.list.get(entry.getValue()).getCorrectAnswer().equals(entry.getKey().getText())) {
                this.list.get(entry.getValue()).setCorrectUser(true);
            }
        }
        this.victorina.setQuestions(new ArrayList<>(list));
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Preferences prefs = Preferences.userRoot().node("ShowAnswers");
        int countIncorr = 0;
        int countCorr = 0;
        String results = "";
        String res = "";
        for (int i = 0; i < resultsCorr.size(); i++) {
            if (prefs.getBoolean("isShow", rootPaneCheckingEnabled)) {
                res = resultsCorr.get(i).isSelected() ? "+" : "- " + "Correct: " + resultsCorr.get(i).getText();
            } else {
                res = resultsCorr.get(i).isSelected() ? "+" : "-";
            }
            results += "Question " + (i + 1) + " " + res + "\n";
            if (resultsCorr.get(i).isSelected()) {
                countCorr++;
            }
        }
        for (int i = 0; i < resultsIncorr.size(); i++) {
            if (this.resultsIncorr.get(i).isSelected()) {
                countIncorr++;
            }
        }
        results += "RESULT: " + countCorr + " correct answers!\n";
        results += String.format("Correct answers: %.2f", (double) countCorr / this.list.size() * 100) + "%";
        if (countIncorr + countCorr < this.list.size()) {
            JOptionPane.showMessageDialog(rootPane, "Answer all questions!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            jTextArea2.setText(results);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setUserAnswer();
        DAO.addObject(this.victorina);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea2;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
