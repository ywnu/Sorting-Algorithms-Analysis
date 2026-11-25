import javax.swing.SwingUtilities;
public class SortingVisualizer extends MergeSortVisualizer {
    public SortingVisualizer(){super(false,true);}
    public static void main(String[] args){SwingUtilities.invokeLater(()->new SortingVisualizer().setVisible(true));}
}
