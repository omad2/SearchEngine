package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import javax.swing.filechooser.*;

public class GUI implements ActionListener {
    JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    JButton btn1 = new JButton("SUBMIT");
    JButton btn2 = new JButton("OPEN");
    JButton btn3 = new JButton("CLEAR");
    JTextField textField = new JTextField();
    JPanel panel = new JPanel();
    List<JCheckBox> checkBoxes = new ArrayList<>();

    SearchFiles sf;
    JFrame frame;
    JLabel jLabel;
    String path;
    JScrollPane scroll;
    JTextArea jTextArea;

    public GUI() {
        run();
    }

   public void run() {
       // TODO: make icon work, and in the future it must be in the resources folder.

       JFrame frame = new JFrame("Search Engine");
       frame.setVisible(true);
       frame.setSize(400,550);
       frame.setResizable(false);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       panel.setBackground(new Color(41, 50, 65));
       frame.add(panel);

       JLabel lb = new JLabel("Type in a word: ");
       lb.setBackground(Color.yellow);

       textField.setBounds(355, 0, 2000, 30);
       textField.setPreferredSize(new Dimension(100,25));
       textField.setBackground(new Color(91, 157, 194));
       textField.setBorder(null);

       panel.add(textField);

       btn1.setBackground(new Color(224, 251, 252));
       btn2.setBackground(new Color(224, 251, 252));
       btn3.setBackground(new Color(224, 251, 252));

       btn1.setOpaque(true);
       btn2.setOpaque(true);
       btn3.setOpaque(true);

       btn1.addActionListener(this);
       btn2.addActionListener(this);
       btn3.addActionListener(this);

       jLabel = new JLabel("No file selected");
       jLabel.setForeground(Color.WHITE);

       panel.add(btn1);
       panel.add(btn3);
       panel.add(btn2);
       panel.add(jLabel);

       jTextArea = new JTextArea(25,30);
       jTextArea.setVisible(true);
       jTextArea.setLocation(30,110);
       jTextArea.setSize(270,210);
       jTextArea.setBackground(new Color(91, 157, 194));
       jTextArea.setBorder(null);
       jTextArea.setLineWrap(true);
       jTextArea.setWrapStyleWord(true);

       scroll = new JScrollPane(jTextArea);
       panel.add(scroll);

       frame.setVisible(true);
    }

   /* public void initialiseSpellChecker(){
        String userDictionaryPath = "\\dictionary";
        SpellChecker.setUserDictionaryProvider(new FileUserDictionary(userDictionaryPath));
        SpellChecker.registerDictionaries(getClass().getResource(userDictionaryPath), "en");
        SpellChecker.register(textField);
        SpellChecker.register(jTextArea);
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            if (textField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a search query");
                return;
            }

            if (path == null) {
                JOptionPane.showMessageDialog(frame, "Please select a path");
                return;
            }

            try {
                Map<String, Integer> occurrences = sf.Search(textField.getText());
                String s = String.format("\nThe word %s appears in the following:\n", textField.getText());
                jTextArea.append(s);

                int index = 0;

                for (Map.Entry<String, Integer> set : occurrences.entrySet()) {
                    String key = set.getKey();
                    Integer value = set.getValue();

                    String formattedString = switch (index) {
                        case 0 -> String.format("Strongest link %s - %ox\n", key, value);
                        case 1 -> String.format("Second strongest link %s - %ox\n", key, value);
                        default -> String.format("%s - %ox\n", key, value);
                    };

                    jTextArea.append(formattedString);
                    index++;
                }
            } catch (FileNotFoundException fileNotFoundException) {
                JOptionPane.showMessageDialog(frame, fileNotFoundException.getMessage());
            }

        } else if (e.getSource() == btn2) {
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int r = jFileChooser.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                String selectedPath = jFileChooser.getSelectedFile().getAbsolutePath();
                jLabel.setText(selectedPath);
                sf = new SearchFiles(selectedPath);
                path = selectedPath;

                if (!checkBoxes.isEmpty()) {
                    panel.removeAll();
                    checkBoxes.clear();
                }

                File fileObj = new File(path);
                File[] files = fileObj.listFiles((dir, name) -> name.endsWith(".txt"));

                if (files != null) {
                    for (File file : files) {
                        JCheckBox checkBox = new JCheckBox(file.getName());
                        checkBox.setBounds(100,100, 50,50);
                        checkBox.addActionListener(this);

                        panel.add(checkBox);
                    }
                }
            }
        } else if(e.getSource() == btn3) {
            textField.setText("");
            jTextArea.setText("");
        }

        if (e.getSource().getClass() == JCheckBox.class) {
            JCheckBox checkBox = (JCheckBox)e.getSource();

            if (sf.allowedFiles.contains(checkBox.getText())) {
                sf.allowedFiles.remove(checkBox.getText());
                return;
            }

            sf.allowedFiles.add(checkBox.getText());
        }
    }
}
