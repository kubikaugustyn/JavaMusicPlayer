package cz.jakubaugustyn.javamusicplayer.window;

import cz.jakubaugustyn.javamusicplayer.Properties;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchWindow extends Window implements ActionListener {
    private JTextField search;
    private MainWindow parent;


    public SearchWindow(MainWindow parent) {
        super(Properties.searchWindowName, false);
        this.window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.parent = parent;
        this.setVisible(false);
        this.search = new JTextField("Hello");
        this.build();
        this.window.pack();
    }

    public void build() {
        if (this.window != null) {
            Container container = this.window.getContentPane();
            this.search.setPreferredSize(new Dimension(100, 25));
            this.search.setSize(new Dimension(100, 25));
            this.search.addActionListener(this);
            container.add(this.search, BorderLayout.CENTER);

            JLabel textLabel = new JLabel("I'm a label in the search window", SwingConstants.LEFT);
            textLabel.setVerticalAlignment(SwingConstants.CENTER);
            textLabel.setHorizontalAlignment(SwingConstants.CENTER);
            textLabel.setPreferredSize(new Dimension(300, 100));
            container.add(textLabel);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.search)) {
            this.search(this.search.getText());
            this.setVisible(true);
        } else if (e.getSource().equals(this.parent.search)) {
            this.search(this.parent.search.getText());
        } else {
            System.out.println("Event!");
            System.out.println(e);
        }
    }

    public final void search(@NotNull String text) {
        this.search.setText(text);
        this.search.setFocusable(false);
        this.search.setFocusable(true);
        this.parent.search.setText(text);
        this.parent.search.setFocusable(false);
        this.parent.search.setFocusable(true);
        if (text.length() > 0) {
            this.setVisible(true);
            System.out.println("Search: " + text);
        }
    }
}
