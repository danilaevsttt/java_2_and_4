package pkg23vvi1;

import java.util.LinkedList;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JFileChooser;

/**
 * Главная форма приложения
 * Приложение для вычисления интеграла sin(x) методом трапеций
 * с хранением данных в LinkedList и поддержкой текстового/двоичного сохранения
 */
public class JForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JForm.class.getName());
    private final LinkedList<RecIntegral> integralRecords = new LinkedList<>();

    public JForm() {
        initComponents();
        
        jTable1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int col = e.getColumn();
                    
                    if (row >= 0 && row < integralRecords.size() && col >= 0 && col < 3) {
                        try {
                            RecIntegral rec = integralRecords.get(row);
                            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                            
                            switch (col) {
                                case 0:
                                    double newLow = (Double) model.getValueAt(row, 0);
                                    rec.setLowerBound(newLow);
                                    break;
                                case 1:
                                    double newUp = (Double) model.getValueAt(row, 1);
                                    rec.setUpperBound(newUp);
                                    break;
                                case 2:
                                    double newStep = (Double) model.getValueAt(row, 2);
                                    rec.setStep(newStep);
                                    break;
                            }
                            rec.setResult("");
                            model.setValueAt("", row, 3);
                            
                        } catch (InvalidIntegralDataException ex) {
                            javax.swing.JOptionPane.showMessageDialog(JForm.this,
                                ex.getMessage(),
                                "Некорректные данные",
                                javax.swing.JOptionPane.WARNING_MESSAGE);
                            
                            RecIntegral rec = integralRecords.get(row);
                            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                            switch (col) {
                                case 0: model.setValueAt(rec.getLowerBound(), row, 0); break;
                                case 1: model.setValueAt(rec.getUpperBound(), row, 1); break;
                                case 2: model.setValueAt(rec.getStep(), row, 2); break;
                            }
                        } catch (Exception ex) {
                            javax.swing.JOptionPane.showMessageDialog(JForm.this,
                                "Ошибка при обновлении: " + ex.getMessage(),
                                "Ошибка",
                                javax.swing.JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDeleted = new javax.swing.JButton();
        jButtonSolve = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jButtonFill = new javax.swing.JButton();
        jButtonSaveText = new javax.swing.JButton();
        jButtonLoadText = new javax.swing.JButton();
        jButtonSaveBin = new javax.swing.JButton();
        jButtonLoadBin = new javax.swing.JButton();
        jTextFieldLowerBound = new javax.swing.JTextField();
        jTextFieldStepBound = new javax.swing.JTextField();
        jTextFieldUpperBound = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Интегралы - LinkedList");

        jPanel1.setBackground(new java.awt.Color(173, 216, 230));

        jLabel1.setText("Формула: sin(x)");
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));

        jLabel2.setText("Нижний порог");
        jLabel3.setText("Верхний порог");
        jLabel4.setText("Шаг");

        jButtonAdd.setBackground(new java.awt.Color(240, 220, 120));
        jButtonAdd.setText("Добавить");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonDeleted.setBackground(new java.awt.Color(240, 220, 120));
        jButtonDeleted.setText("Удалить");
        jButtonDeleted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletedActionPerformed(evt);
            }
        });

        jButtonSolve.setBackground(new java.awt.Color(240, 220, 120));
        jButtonSolve.setText("Вычислить");
        jButtonSolve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSolveActionPerformed(evt);
            }
        });

        jButtonClear.setBackground(new java.awt.Color(255, 150, 150));
        jButtonClear.setText("Очистить");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jButtonFill.setBackground(new java.awt.Color(150, 255, 150));
        jButtonFill.setText("Заполнить");
        jButtonFill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFillActionPerformed(evt);
            }
        });

        jButtonSaveText.setBackground(new java.awt.Color(150, 200, 255));
        jButtonSaveText.setText("Сохранить (текст)");
        jButtonSaveText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveTextActionPerformed(evt);
            }
        });

        jButtonLoadText.setBackground(new java.awt.Color(150, 200, 255));
        jButtonLoadText.setText("Загрузить (текст)");
        jButtonLoadText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadTextActionPerformed(evt);
            }
        });

        jButtonSaveBin.setBackground(new java.awt.Color(200, 150, 255));
        jButtonSaveBin.setText("Сохранить (двоич.)");
        jButtonSaveBin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveBinActionPerformed(evt);
            }
        });

        jButtonLoadBin.setBackground(new java.awt.Color(200, 150, 255));
        jButtonLoadBin.setText("Загрузить (двоич.)");
        jButtonLoadBin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadBinActionPerformed(evt);
            }
        });

        jTextFieldLowerBound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLowerBoundActionPerformed(evt);
            }
        });

        jTextFieldStepBound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStepBoundActionPerformed(evt);
            }
        });

        jTable1.setBackground(new java.awt.Color(147, 175, 157));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Нижний порог", "Верхний порог", "Шаг", "Результат"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(204, 238, 188));
        jTable1.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldLowerBound, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(jTextFieldUpperBound)
                            .addComponent(jTextFieldStepBound))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonDeleted, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSolve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonSaveText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSaveBin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonLoadText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonLoadBin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonFill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldLowerBound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdd)
                    .addComponent(jButtonSaveText)
                    .addComponent(jButtonLoadText))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldUpperBound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDeleted)
                    .addComponent(jButtonSaveBin)
                    .addComponent(jButtonLoadBin))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldStepBound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSolve)
                    .addComponent(jButtonClear)
                    .addComponent(jButtonFill))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButtonSolveActionPerformed(java.awt.event.ActionEvent evt) {                                             
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Выберите строку в таблице для вычисления!", 
                "Внимание", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (selectedRow < integralRecords.size()) {
            RecIntegral rec = integralRecords.get(selectedRow);
            String result = rec.compute();
            rec.setResult(result);
            jTable1.setValueAt(result, selectedRow, 3);
        }
    }                                            

    private void jButtonDeletedActionPerformed(java.awt.event.ActionEvent evt) {                                               
        DefaultTableModel tModel = (DefaultTableModel) jTable1.getModel();
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            if (selectedRow < integralRecords.size()) {
                integralRecords.remove(selectedRow);
            }
            tModel.removeRow(selectedRow);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Выберите строку для удаления!", 
                "Внимание", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }                                              

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {                                           
        try {
            double low = Double.parseDouble(jTextFieldLowerBound.getText());
            double up = Double.parseDouble(jTextFieldUpperBound.getText());
            double step = Double.parseDouble(jTextFieldStepBound.getText());
            
            RecIntegral newRecord = new RecIntegral(low, up, step, "");
            integralRecords.addLast(newRecord);
            
            DefaultTableModel tModel = (DefaultTableModel) jTable1.getModel();
            tModel.addRow(new Object[]{low, up, step, ""});
            
            jTextFieldLowerBound.setText("");
            jTextFieldUpperBound.setText("");
            jTextFieldStepBound.setText("");
            
        } catch (InvalidIntegralDataException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                ex.getMessage(), 
                "Некорректные данные", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Введите корректные числовые значения!", 
                "Ошибка ввода", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Произошла ошибка: " + ex.getMessage(), 
                "Ошибка", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }                                          

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);  
        // integralRecords.clear();  // 
        jTextFieldLowerBound.setText("");
        jTextFieldUpperBound.setText("");
        jTextFieldStepBound.setText("");
    }

    private void jButtonFillActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (RecIntegral rec : integralRecords) {
            model.addRow(new Object[]{
                rec.getLowerBound(),
                rec.getUpperBound(),
                rec.getStep(),
                rec.getResult()
            });
        }
    }

    private void jButtonSaveTextActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Сохранить данные (текст)");
        fileChooser.setSelectedFile(new java.io.File("data.txt"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            try (java.io.FileWriter writer = new java.io.FileWriter(fileToSave, java.nio.charset.Charset.forName("cp1251"))) {
                writer.write("lowerBound;upperBound;step;result\n");
                for (RecIntegral rec : integralRecords) {
                    writer.write(String.format(java.util.Locale.US, "%.10f;%.10f;%.10f;%s\n", 
                        rec.getLowerBound(), 
                        rec.getUpperBound(), 
                        rec.getStep(), 
                        rec.getResult()));
                }
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Данные успешно сохранены!", "Успех", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (java.io.IOException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Ошибка сохранения: " + ex.getMessage(), 
                    "Ошибка", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                logger.log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    private void jButtonLoadTextActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Загрузить данные (текст)");
        
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToLoad = fileChooser.getSelectedFile();
            try (java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.FileReader(fileToLoad, java.nio.charset.Charset.forName("cp1251")))) {
                
                integralRecords.clear();
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                
                String line;
                boolean isFirstLine = true;
                
                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        if (line.contains("lowerBound")) continue;
                    }
                    
                    line = line.replace(',', '.');
                    
                    String[] parts = line.split(";");
                    if (parts.length >= 3) {
                        double low = Double.parseDouble(parts[0].trim());
                        double up = Double.parseDouble(parts[1].trim());
                        double step = Double.parseDouble(parts[2].trim());
                        String result = (parts.length > 3) ? parts[3].trim() : "";
                        
                        RecIntegral rec = new RecIntegral(low, up, step, result);
                        integralRecords.addLast(rec);
                        model.addRow(new Object[]{low, up, step, result});
                    }
                }
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Данные успешно загружены!", "Успех", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (InvalidIntegralDataException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Некорректные данные в файле: " + ex.getMessage(), 
                    "Ошибка данных", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Ошибка формата чисел в файле!\nПроверьте правильность разделителей (должна быть точка).", 
                    "Ошибка ввода", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (java.io.IOException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Ошибка чтения файла: " + ex.getMessage(), 
                    "Ошибка", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                logger.log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    private void jButtonSaveBinActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Сохранить данные (двоичный)");
        fileChooser.setSelectedFile(new java.io.File("data.ser"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            try (java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(
                    new java.io.BufferedOutputStream(
                        new java.io.FileOutputStream(fileToSave)))) {
                out.writeObject(integralRecords);
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Данные успешно сохранены!", "Успех", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (java.io.IOException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Ошибка сохранения: " + ex.getMessage(), 
                    "Ошибка", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                logger.log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    private void jButtonLoadBinActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Загрузить данные (двоичный)");
        
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToLoad = fileChooser.getSelectedFile();
            try (java.io.ObjectInputStream in = new java.io.ObjectInputStream(
                    new java.io.BufferedInputStream(
                        new java.io.FileInputStream(fileToLoad)))) {
                
                @SuppressWarnings("unchecked")
                LinkedList<RecIntegral> loadedRecords = (LinkedList<RecIntegral>) in.readObject();
                
                integralRecords.clear();
                integralRecords.addAll(loadedRecords);
                
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                for (RecIntegral rec : integralRecords) {
                    model.addRow(new Object[]{
                        rec.getLowerBound(),
                        rec.getUpperBound(), 
                        rec.getStep(),
                        rec.getResult()
                    });
                }
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Данные успешно загружены!", "Успех", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (java.io.IOException | ClassNotFoundException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Ошибка загрузки: " + ex.getMessage(), 
                    "Ошибка", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                logger.log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    private void jTextFieldLowerBoundActionPerformed(java.awt.event.ActionEvent evt) {}
    private void jTextFieldStepBoundActionPerformed(java.awt.event.ActionEvent evt) {}

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new JForm().setVisible(true));
    }

    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonDeleted;
    private javax.swing.JButton jButtonFill;
    private javax.swing.JButton jButtonSolve;
    private javax.swing.JButton jButtonSaveText;
    private javax.swing.JButton jButtonLoadText;
    private javax.swing.JButton jButtonSaveBin;
    private javax.swing.JButton jButtonLoadBin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldLowerBound;
    private javax.swing.JTextField jTextFieldStepBound;
    private javax.swing.JTextField jTextFieldUpperBound;
}
