/*
 *  Program CompareCollections
 *  Pozwala sprawdzić różnice w przechowywaniu zmiennych w kolekcjach.
 *  Obejmuje: Vector, ArrayList, LinkedList, HashSet, TreeSet
 *
 *  @author Tobiasz Rumian
 *  @version 1.0
 *   Data: 05 Listopad 2016 r.
 *   Indeks: 226131
 *   Grupa: śr 13:15 TN
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


public class CompareCollections extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JLabel valueLabel = new JLabel("Wartość:");
    private JTextField valueField = new JTextField(10);
    private JButton buttonAdd = new JButton("Dodaj");
    private JButton buttonDelete = new JButton("Usuń");
    private JButton buttonClear = new JButton("Wyczyść");
    private JButton buttonAbout = new JButton("Autor");

    private ArrayList<Collection> collections = new ArrayList<>();
    private ArrayList<CollectionView> collectionViews = new ArrayList<>();

    public CompareCollections() {
        super("Porównanie działania kolekcji");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 300);

        collections.add(new Vector<String>());
        collections.add(new ArrayList<String>());
        collections.add(new LinkedList<String>());
        collections.add(new HashSet<String>());
        collections.add(new TreeSet<String>());

        JPanel panel = new JPanel();
        buttonAdd.addActionListener(this);
        buttonDelete.addActionListener(this);
        buttonClear.addActionListener(this);
        buttonAbout.addActionListener(this);

        menuBar = new JMenuBar();
        menuBar.add(valueLabel);
        menuBar.add(valueField);
        menuBar.add(buttonAdd);
        menuBar.add(buttonDelete);
        menuBar.add(buttonClear);
        menuBar.add(buttonAbout);

        setJMenuBar(menuBar);

        collectionViews.add(new CollectionView(collections.get(0), 150, 200, "vector:"));
        collectionViews.add(new CollectionView(collections.get(1), 150, 200, "arrayView:"));
        collectionViews.add(new CollectionView(collections.get(2), 150, 200, "linkedView:"));
        collectionViews.add(new CollectionView(collections.get(3), 150, 200, "hashView:"));
        collectionViews.add(new CollectionView(collections.get(4), 150, 200, "treeView:"));
        collectionViews.forEach(panel::add);

        setContentPane(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value;
        Object source = e.getSource();

        if (source == buttonAdd) {
            value = valueField.getText();
            for (Collection c:collections) {
                c.add(value);
            }

        } else if (source == buttonClear) {
            collections.forEach(Collection::clear);
        } else if (source == buttonDelete) {
            value = valueField.getText();
            for (Collection c:collections) {
                c.remove(value);
            }
        } else if (source == buttonAbout) {
            About about;
            try {
                about = new About(this);
                about.setVisible(true);
            } catch (Exception event) {
                System.err.println(event.getMessage());
            }
        }
        collectionViews.forEach(CollectionView::refresh);
    }

    public static void main(String[] args) {
        new CompareCollections();
    }

    private class About extends JDialog {
        About(JFrame owner) throws MalformedURLException {
            super(owner, "O Autorze", true);
            URL url = null;
            try {
                url = new URL("https://media.giphy.com/media/l0HlIKdi4DIEDk92g/giphy.gif");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            Icon icon = new ImageIcon(url);
            JLabel label = new JLabel(icon);
            add(new JLabel("Autor:\t Tobiasz Rumian\t Indeks: 226131"), BorderLayout.NORTH);
            add(label, BorderLayout.CENTER);
            JButton ok = new JButton("ok");
            ok.addActionListener(e -> setVisible(false));
            add(ok, BorderLayout.SOUTH);
            setSize(400, 400);
        }
    }
}
