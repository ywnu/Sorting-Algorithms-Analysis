import javax.swing.SwingUtilities;
public class QuickSortVisualizer extends MergeSortVisualizer {
    public QuickSortVisualizer(){super(true,false);}
    public static void main(String[] args){SwingUtilities.invokeLater(()->new QuickSortVisualizer().setVisible(true));}
}
