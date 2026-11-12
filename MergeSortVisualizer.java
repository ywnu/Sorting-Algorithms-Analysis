import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

public class MergeSortVisualizer extends JFrame {
    static class FrameState {
        int[] order; int left, right, mid; boolean split; String label;
        FrameState(int[] a,int l,int r,int m,boolean s,String text){order=a.clone();left=l;right=r;mid=m;split=s;label=text;}
    }
    final List<FrameState> frames=new ArrayList<>();
    int[] values={38,27,43,3,9,82,10};
    int step=0; double progress=1; boolean playing=false;
    int speed=55; long last=System.nanoTime();
    final Canvas canvas=new Canvas();
    final JLabel info=new JLabel(); final JButton play=new JButton("▶ Play");
    final JSlider slider=new JSlider(10,100,55);
    final javax.swing.Timer timer;
    final Color bg=new Color(13,18,33), panel=new Color(23,31,51), fg=new Color(231,239,252);

    MergeSortVisualizer(){
        super("Merge Sort Visualizer");
        setIconImage(createAppIcon());
        setDefaultCloseOperation(EXIT_ON_CLOSE);setSize(1050,690);setMinimumSize(new Dimension(780,550));setLocationRelativeTo(null);
        getContentPane().setBackground(bg);setLayout(new BorderLayout());
        JPanel header=new JPanel(new GridLayout(2,1));header.setOpaque(false);header.setBorder(BorderFactory.createEmptyBorder(24,32,12,32));
        JLabel title=new JLabel("MERGE SORT  /  VISUALIZER");title.setForeground(fg);title.setFont(new Font("SansSerif",Font.BOLD,27));
        JLabel subtitle=new JLabel("DIVIDE & CONQUER  •  Watch elements move into sorted positions");subtitle.setForeground(new Color(138,160,195));subtitle.setFont(new Font("SansSerif",Font.PLAIN,13));
        header.add(title);header.add(subtitle);add(header,BorderLayout.NORTH);
        add(canvas,BorderLayout.CENTER);
        JPanel footer=new JPanel(new BorderLayout(12,14));footer.setBackground(bg);footer.setBorder(BorderFactory.createEmptyBorder(12,30,25,30));
        info.setForeground(fg);info.setFont(new Font("SansSerif",Font.BOLD,14));footer.add(info,BorderLayout.NORTH);
        JPanel controls=new JPanel(new FlowLayout(FlowLayout.CENTER,12,2));controls.setOpaque(false);
        JButton prev=new JButton("◀ Previous"), next=new JButton("Next ▶"), reset=new JButton("↺ Reset"),shuffle=new JButton("⤨ Shuffle");
        for(JButton b:new JButton[]{prev,play,next,reset,shuffle}){b.setBackground(panel);b.setForeground(fg);b.setFocusPainted(false);b.setFont(new Font("SansSerif",Font.BOLD,13));b.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(60,80,112)),BorderFactory.createEmptyBorder(10,15,10,15)));controls.add(b);}
        JLabel speedLabel=new JLabel("Speed");speedLabel.setForeground(fg);controls.add(speedLabel);slider.setOpaque(false);slider.setPreferredSize(new Dimension(120,30));controls.add(slider);
        footer.add(controls,BorderLayout.CENTER);add(footer,BorderLayout.SOUTH);
        play.addActionListener(e->{if(step>=frames.size()-1&&progress>=1){step=0;progress=1;}playing=!playing;play.setText(playing?"Ⅱ Pause":"▶ Play");});
        next.addActionListener(e->{playing=false;play.setText("▶ Play");if(step<frames.size()-1){step++;progress=0;}refresh();});
        prev.addActionListener(e->{playing=false;play.setText("▶ Play");if(step>0){step--;progress=0;}refresh();});
        reset.addActionListener(e->{playing=false;play.setText("▶ Play");step=0;progress=1;refresh();});
        shuffle.addActionListener(e->{playing=false;play.setText("▶ Play");Random r=new Random();for(int i=values.length-1;i>0;i--){int j=r.nextInt(i+1),t=values[i];values[i]=values[j];values[j]=t;}generate();refresh();});
        slider.addChangeListener(e->speed=slider.getValue());
        generate();
        timer=new javax.swing.Timer(16,e->{long now=System.nanoTime();double dt=Math.min(.06,(now-last)/1e9);last=now;
            if(playing){if(progress<1){progress=Math.min(1,progress+dt*(.55+speed/32.0));}else if(step<frames.size()-1){step++;progress=0;}else{playing=false;play.setText("▶ Play");}}
            canvas.repaint();refreshLabel();});timer.start();
    }
    void generate(){frames.clear();int[] a=new int[values.length];for(int i=0;i<a.length;i++)a[i]=i;
        frames.add(new FrameState(a,0,a.length-1,-1,false,"Original array"));sort(a,0,a.length-1);frames.add(new FrameState(a,0,a.length-1,-1,false,"Sorting complete!"));step=0;progress=1;}
    void sort(int[] a,int l,int r){if(l>=r)return;int m=(l+r)/2;
        frames.add(new FrameState(a,l,r,m,true,"DIVIDE  •  Split range "+(l+1)+"–"+(r+1)));
        sort(a,l,m);sort(a,m+1,r);
        int[] temp=new int[r-l+1];int i=l,j=m+1,k=0;
        while(i<=m&&j<=r)temp[k++]=values[a[i]]<=values[a[j]]?a[i++]:a[j++];
        while(i<=m)temp[k++]=a[i++];while(j<=r)temp[k++]=a[j++];
        for(int t=0;t<temp.length;t++)a[l+t]=temp[t];
        frames.add(new FrameState(a,l,r,m,false,"MERGE  •  Sorted range "+(l+1)+"–"+(r+1)));
    }
    void refresh(){refreshLabel();canvas.repaint();}
    void refreshLabel(){if(frames.isEmpty())return;info.setText(frames.get(step).label+"       |       Step "+(step+1)+" / "+frames.size());}
    class Canvas extends JPanel{
        Canvas(){setBackground(bg);}
        protected void paintComponent(Graphics gg){super.paintComponent(gg);Graphics2D g=(Graphics2D)gg.create();g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            int w=getWidth(),h=getHeight(),n=values.length;int margin=Math.max(42,w/12),spacing=(w-2*margin)/n;int barW=Math.min(95,(int)(spacing*.72));
            int baseY=h-76,maxH=Math.max(120,h-220);int max=Arrays.stream(values).max().orElse(1);
            FrameState current=frames.get(step),previous=frames.get(Math.max(0,step-1));
            int[] oldPos=new int[n],newPos=new int[n];for(int i=0;i<n;i++){oldPos[previous.order[i]]=i;newPos[current.order[i]]=i;}
            double t=step==0?1:progress;double eased=t*t*(3-2*t);
            g.setColor(new Color(41,52,75));g.setStroke(new BasicStroke(2));g.drawLine(margin-15,baseY+8,w-margin+15,baseY+8);
            if(current.split){int x1=margin+current.left*spacing-10,x2=margin+(current.right+1)*spacing-10;
                g.setColor(new Color(42,67,102,90));g.fillRoundRect(x1,35,x2-x1,baseY-15,18,18);
                int splitX=margin+(current.mid+1)*spacing-10;g.setColor(new Color(245,181,87));g.setStroke(new BasicStroke(2,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10,new float[]{8,6},0));g.drawLine(splitX,42,splitX,baseY-10);
            }
            for(int id=0;id<n;id++){
                double pos=oldPos[id]+(newPos[id]-oldPos[id])*eased;
                int x=(int)(margin+pos*spacing+(spacing-barW)/2);int height=(int)(Math.max(16,values[id]/(double)max*maxH));
                boolean active=newPos[id]>=current.left&&newPos[id]<=current.right;
                int shift= current.split&&active ? (newPos[id]<=current.mid?-18:18):0;
                int y=baseY-height+(int)(shift*eased)- (step>0&&progress<1?(int)(Math.sin(Math.PI*progress)*20):0);
                Color c=step==frames.size()-1?new Color(45,204,147):current.split&&active?new Color(245,181,87):active?new Color(70,195,225):new Color(90,114,160);
                g.setColor(new Color(c.getRed(),c.getGreen(),c.getBlue(),45));g.fillRoundRect(x-4,y-4,barW+8,height+8,16,16);
                g.setColor(c);g.fillRoundRect(x,y,barW,height,12,12);
                g.setColor(fg);g.setFont(new Font("SansSerif",Font.BOLD,16));String label=""+values[id];FontMetrics fm=g.getFontMetrics();g.drawString(label,x+(barW-fm.stringWidth(label))/2,y-12);
            }
            g.setFont(new Font("SansSerif",Font.PLAIN,13));g.setColor(new Color(145,163,194));g.drawString("Gold = dividing     Cyan = merging     Green = sorted",margin,22);
            g.dispose();}
    }

private Image createAppIcon() {
    int size = 64;

    java.awt.image.BufferedImage image =
        new java.awt.image.BufferedImage(
            size, size,
            java.awt.image.BufferedImage.TYPE_INT_ARGB
        );

    Graphics2D g = image.createGraphics();

    g.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON
    );

    g.setColor(new Color(13, 18, 33));
    g.fillRoundRect(0, 0, 64, 64, 16, 16);

    int[] heights = {18, 35, 25, 43};
    int[] x = {10, 23, 36, 49};

    for (int i = 0; i < heights.length; i++) {
        g.setColor(new Color(70, 195, 225));
        g.fillRoundRect(
            x[i], 52 - heights[i],
            8, heights[i], 4, 4
        );
    }

    g.dispose();
    return image;
}

    public static void main(String[] args){SwingUtilities.invokeLater(()->new MergeSortVisualizer().setVisible(true));}
}
