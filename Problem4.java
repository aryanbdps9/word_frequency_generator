import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JTable;


public class Problem4 extends javax.swing.JFrame {
    static String filepath;
    static String filepathstatus;
    String stopwords;
    JFileChooser fc;
    //DocFilter df = new DocFilter();
    public Problem4() {
        initComponents();
        fc = new JFileChooser();
        filepathstatus = "Not opened";
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        button_sel_file = new javax.swing.JButton();
        button_process = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        //jT = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button_sel_file.setText("Select File");
        button_sel_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_sel_fileActionPerformed(evt);
            }
        });

        button_process.setText("Process");
        button_process.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_processActionPerformed(evt);
            }
        });

        /*jT.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jT);*/

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_process, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(229, 229, 229)
                        .addComponent(button_sel_file))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_process)
                    .addComponent(button_sel_file))
                .addGap(229, 229, 229))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_processActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_processActionPerformed
        if (filepathstatus.equalsIgnoreCase("opened")){
            String content = q1.filename_to_string(filepath);
            List<String> myList = q1.string_to_array_list(content);
            stopwords = jTextArea1.getText();
            List<String> banned_words = q1.csal(stopwords);
            List<String> filtered_list = new ArrayList<String>();
            Set<String> singleton_filtered_set = new TreeSet<String>();

            q1.filtr_list1(myList, filtered_list, banned_words, singleton_filtered_set);
            String columnNames[] = { "Word", "Frequency" };
            Object[][] data = new Object[singleton_filtered_set.size()][2];
            int i = 0;
            for (String s : singleton_filtered_set) {
                int cnt = Collections.frequency(filtered_list, s);
                data[i][0] = s;
                data[i][1] = cnt;
                i++;
            }
            jT = new JTable(data, columnNames);
            jT.getTableHeader().setReorderingAllowed(false);
            jT.getTableHeader().setResizingAllowed(true);
            jScrollPane1.setViewportView(jT);
        }
    }//GEN-LAST:event_button_processActionPerformed

    private void button_sel_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_sel_fileActionPerformed
        // TODO add your handling code here:
        File workingDirectory = new File(System.getProperty("user.dir"));
        fc.setCurrentDirectory(workingDirectory);
        fc.addChoosableFileFilter(new DocFilter());
        int returnVal = fc.showOpenDialog(Problem4.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filepath = fc.getSelectedFile().getAbsolutePath();
            filepathstatus = "opened";
        }
    }//GEN-LAST:event_button_sel_fileActionPerformed

    

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Problem4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Problem4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Problem4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Problem4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Problem4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_process;
    private javax.swing.JButton button_sel_file;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jT;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
