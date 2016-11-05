/*
 * @version 1.0
 * @author Tobiasz Rumian
 * Data: 05 Listopad 2016 r.
 * Indeks: 226131
 * Grupa: śr 13:15 TN
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;


class CollectionView extends JScrollPane {
    private static final long serialVersionUID = 1L;

    private JTable table;
    private DefaultTableModel tableModel;
    private Collection<String> collection;

    CollectionView(Collection<String> collection, int width, int height, String description) {
        String[] column = {"Wartość:"};
        tableModel = new DefaultTableModel(column, 0);
        table = new JTable(tableModel);
        table.setRowSelectionAllowed(false);
        this.collection = collection;
        setViewportView(table);
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createTitledBorder(description));
    }

    void refresh() {
        tableModel.setRowCount(0);
        for (String value : collection) {
            String[] row = {value};
            tableModel.addRow(row);
        }
    }

}

