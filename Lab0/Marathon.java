package Ap_assignment.Lab0;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class Runner{
    public String name;
    public int Time;
    public int category;
    public Runner next;
    Runner(){
        name="No runner";
        Time=Integer.MAX_VALUE;
    }

    public Runner(String name, int Time, int category) {
        this.name = name;
        this.Time = Time;
        this.category = category;
        this.next = null;
    }

}
class linkedlist{
    Runner top, back;
    int size;
    Runner[][] ans=new Runner[3][2];
    public linkedlist(){
        size=0;
        top=null;
        back=null;
        for(int i=0;i<3;i++){
            for(int j=0;j<2;j++){
                ans[i][j]=new Runner();
            }
        }

    }

    public linkedlist(Runner n){
        top=n;
        back=n;
        size=1;

    }

    public void insert(Runner k){
        if(top==null){
            top=k;
        }
        else{
            back.next=k;
        }
        size+=1;
        back=k;

    }

    public void sort(){
        Runner temp;
        for(int i=0;i<size;i++){
            temp=top;
            for(int j=0;j<size-1;j++){
                if(temp.Time>temp.next.Time){
                    int t=temp.next.Time;
                    temp.next.Time=temp.Time;
                    temp.Time=t;
                }
                temp=temp.next;
            }
        }
    }

    public Runner[][] findwinner(){

        int cat=0;
        while(cat<3){
            Runner temp=top;
            while(temp!=null){
                if(temp.category==cat && temp.Time<ans[cat][0].Time){
                    ans[cat][1]=ans[cat][0];
                    ans[cat][0]=temp;
                }
                else if(temp.category==cat && temp.Time<ans[cat][1].Time){
                    ans[cat][1]=temp;
                }
                temp=temp.next;
            }
            cat++;
        }
        return ans;
    }

}

public class Marathon {
    public static JPanel p_name;
    public static JLabel l_name;
    public static JPanel p_time;
    public static JLabel l_time;
    public static JTextField tf_name;
    public static JTextField tf_time,tf_winner1a,tf_winner2a,tf_winner3a,tf_winner1b,tf_winner2b,tf_winner3b;
    public static JLabel l_prize1a,l_prize2a,l_prize3a,l_prize1b,l_prize2b,l_prize3b,count;
    public static JPanel result;
    public static JPanel winner1,winner2,winner3;
    public static linkedlist details=new linkedlist();
    public static String winners[][] = {{"Rs. 2,80,000/-","Rs. 2,10,000/-"},{"Rs. 1,90,000/-","Rs.1,50,00/-"},{"Rs. 1,35,000/-","Rs.1,15,000/-"}};


    public static void main(String[] args){


        final JFrame frame=new JFrame("Assignment");
        final JPanel mainPanel=new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        count=new JLabel("total runners upto now: "+ details.size);
        mainPanel.add(count);

        p_name=new JPanel();
        p_name.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(p_name);

        l_name=new JLabel("Name: ");
        tf_name=new JTextField();
        tf_name.setPreferredSize(new Dimension(150,35));
        p_name.add(l_name);
        p_name.add(tf_name);

        p_time=new JPanel();
        p_time.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(p_time);

        l_time=new JLabel("Time: ");
        tf_time=new JTextField();
        tf_time.setPreferredSize(new Dimension(150,35));
        p_time.add(l_time);
        p_time.add(tf_time);


        JPanel p_category=new JPanel();
        p_category.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel l_category=new JLabel("Category");
        p_category.add(l_category);
        mainPanel.add(p_category);
        ButtonGroup bg_joined=new ButtonGroup();

        final JRadioButton GDR=new JRadioButton("Great Delhi Run");
        final JRadioButton OTR=new JRadioButton("Open 10K Run");
        JRadioButton HM=new JRadioButton("Half Marathon");
        bg_joined.add(GDR);
        bg_joined.add(OTR);
        bg_joined.add(HM);
        p_category.add(GDR);
        p_category.add(OTR);
        p_category.add(HM);
        GDR.setSelected(true);

        JPanel p_buttons=new JPanel();
        p_buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton cont=new JButton("Continue");
        JButton winner=new JButton("Winner");
        JButton exit=new JButton("Exit");

        cont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                String n=tf_name.getText();
                int t= Integer.parseInt(tf_time.getText());
                boolean G= GDR.isSelected();
                boolean O= OTR.isSelected();
                int cat=2;
                if(G){
                    cat=0;
                }
                else if(O){
                    cat=1;
                }
                Runner R=new Runner(n,t,cat);
                details.insert(R);
                tf_name.setText("");
                tf_time.setText("");
                GDR.setSelected(true);
                count.setText("total runners upto now: "+ details.size);
            }
                catch (Exception e) {
                    tf_name.setText("");
                    tf_time.setText("");
                }
            }
        });

        winner.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
               mainPanel.setVisible(false);
               Runner[][] ans=details.findwinner();

               result=new JPanel();
               result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));

               winner1=new JPanel();
               winner1.setLayout(new FlowLayout(FlowLayout.CENTER));
               result.add(winner1);
               tf_winner1a=new JTextField();
               tf_winner1a.setPreferredSize(new Dimension(150,35));
               tf_winner1a.setText(ans[0][0].name);
               winner1.add(new JLabel("Great Delhi Run: "));
               winner1.add(tf_winner1a);
               if(ans[0][0].Time!=Integer.MAX_VALUE){
                    winner1.add(new JLabel(winners[0][0]));
               }
               tf_winner1b=new JTextField();
               tf_winner1b.setPreferredSize(new Dimension(150,35));
               tf_winner1b.setText(ans[0][1].name);
               winner1.add(tf_winner1b);
               if(ans[0][1].Time!=Integer.MAX_VALUE){
                    winner1.add(new JLabel(winners[0][1]));
               }


               winner2=new JPanel();
               winner2.setLayout(new FlowLayout(FlowLayout.CENTER));
               result.add(winner2);
               tf_winner2a=new JTextField();
               tf_winner2a.setPreferredSize(new Dimension(150,35));
               tf_winner2a.setText(ans[1][0].name);
               winner2.add(new JLabel("Open 10K Run: "));
               winner2.add(tf_winner2a);
               if(ans[1][0].Time!=Integer.MAX_VALUE){
                    winner2.add(new JLabel(winners[1][0]));
               }
               tf_winner2b=new JTextField();
               tf_winner2b.setPreferredSize(new Dimension(150,35));
               tf_winner2b.setText(ans[1][1].name);
               winner2.add(tf_winner2b);
               if(ans[1][1].Time!=Integer.MAX_VALUE){
                    winner2.add(new JLabel(winners[1][1]));
               }

               winner3=new JPanel();
               winner3.setLayout(new FlowLayout(FlowLayout.CENTER));
               result.add(winner3);

               tf_winner3a=new JTextField();
               tf_winner3a.setPreferredSize(new Dimension(150,35));
               tf_winner3a.setText(ans[2][0].name);
               winner3.add(new JLabel("Half Marathon: "));
               winner3.add(tf_winner3a);
               if(ans[2][0].Time!=Integer.MAX_VALUE){
                    winner3.add(new JLabel(winners[2][0]));
               }
               tf_winner3b=new JTextField();
               tf_winner3b.setPreferredSize(new Dimension(150,35));
               tf_winner3b.setText(ans[2][1].name);
               winner3.add(tf_winner3b);
               if(ans[2][1].Time!=Integer.MAX_VALUE){
                    winner3.add(new JLabel(winners[2][1]));
               }

               frame.add(result);

            }});

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);            }
        });

        p_buttons.add(cont);
        p_buttons.add(winner);
        p_buttons.add(exit);
        mainPanel.add(p_buttons);
        frame.add(mainPanel);
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
