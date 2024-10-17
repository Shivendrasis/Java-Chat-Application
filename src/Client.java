import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Client implements ActionListener {

    JTextField textBox;  // Class-level textBox
    static JPanel text;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;

    static  JFrame jf = new JFrame();

    Client() {
        jf.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 85));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        jf.add(p1);

        // Arrow icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);

        jf.setSize(450, 700);
        jf.setLocation(200, 50);
        jf.getContentPane().setBackground(Color.white);

        // Click event
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        // Profile icon
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/4.png"));
        Image i5 = i4.getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(35, 10, 45, 45);
        p1.add(profile);

        // Video icon
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 25);
        p1.add(video);

        // Phone icon
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(360, 20, 25, 25);
        p1.add(phone);

        // Morevert icon
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(420, 20, 10, 25);
        p1.add(morevert);

        // Set name
        JLabel name = new JLabel("Trisha");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.white);
        p1.add(name);

        // Show active now
        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 35, 100, 14);
        status.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(status);

        // JPanel Text Area
        text = new JPanel();
        text.setBounds(5, 75, 440, 570);
        jf.add(text);

        // TextField Input box
        textBox = new JTextField();  // Use class-level textBox
        textBox.setBounds(5, 655, 310, 40);
        textBox.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        jf.add(textBox);

        // Send button
        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 85));
        send.setForeground(Color.white);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        send.addActionListener(this);
        jf.add(send);

        jf.setSize(450, 700);
        jf.setLocation(800, 50);
        jf.setUndecorated(true);
        jf.getContentPane().setBackground(Color.white);

        jf.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String out = textBox.getText();

            JPanel p2 = formatLabel(out);

            text.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            text.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(out);

            // for empty text
            textBox.setText("");

            // Reload Repaint
            jf.repaint();
            jf.invalidate();
            jf.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,15));

        panel.add(output);

        // calender class
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        // Anonymous object
        new Client();

        try{
            Socket s = new Socket("127.0.0.1", 6001);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (true){
                text.setLayout(new BorderLayout());
                String msg = din.readUTF();
                JPanel panel = formatLabel(msg);

                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);

                vertical.add(Box.createVerticalStrut(15));
                text.add(vertical, BorderLayout.PAGE_START);

                jf.validate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
