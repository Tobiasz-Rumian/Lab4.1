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

    private Vector<String> vector = new Vector<>();
    private ArrayList<String> arrayList = new ArrayList<>();
    private LinkedList<String> linkedList = new LinkedList<>();
    private HashSet<String> hashSet = new HashSet<>();
    private TreeSet<String> treeSet = new TreeSet<>();

    private CollectionView vectorView;
    private CollectionView arrayView;
    private CollectionView linkedView;
    private CollectionView hashView;
    private CollectionView treeView;

    private JLabel valueLabel = new JLabel("Wartość:");
    private JTextField valueField = new JTextField(10);
    private JButton buttonAdd = new JButton("Dodaj");
    private JButton buttonDelete = new JButton("Usuń");
    private JButton buttonClear = new JButton("Wyczyść");
    private JButton buttonAbout = new JButton("Autor");

    public CompareCollections() {
        super("Porownanie działania kolekcji");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 300);

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

        vectorView = new CollectionView(vector, 150, 200, "vector:");
        panel.add(vectorView);

        arrayView = new CollectionView(arrayList, 150, 200, "arrayView:");
        panel.add(arrayView);

        linkedView = new CollectionView(linkedList, 150, 200, "linkedView:");
        panel.add(linkedView);

        hashView = new CollectionView(hashSet, 150, 200, "hashView:");
        panel.add(hashView);
        treeView = new CollectionView(treeSet, 150, 200, "treeView:");
        panel.add(treeView);


        setContentPane(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value;
        Object source = e.getSource();

        if (source == buttonAdd) {
            value = valueField.getText();
            arrayList.add(value);
            vector.add(value);
            linkedList.add(value);
            hashSet.add(value);
            treeSet.add(value);

        } else if (source == buttonClear) {
            arrayList.clear();
            vector.clear();
            linkedList.clear();
            hashSet.clear();
            treeSet.clear();
        } else if (source == buttonDelete) {
            value = valueField.getText();
            arrayList.remove(value);
            vector.remove(value);
            linkedList.remove(value);
            hashSet.remove(value);
            treeSet.remove(value);
        } else if (source == buttonAbout) {
            About about;
            try {
                about = new About(this);
                about.setVisible(true);
            } catch (Exception event) {
                System.err.println(event.getMessage());
            }
        }

        arrayView.refresh();
        vectorView.refresh();
        linkedView.refresh();
        hashView.refresh();
        treeView.refresh();
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
