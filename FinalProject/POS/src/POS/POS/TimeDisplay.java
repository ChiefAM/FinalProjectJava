package POS;    
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeDisplay extends JLabel {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public TimeDisplay() {
        
        super();
        setFont(new Font("Cosmic Sans", Font.BOLD, 25));
        setForeground(Color.YELLOW);
        setBackground(Color.BLACK);
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(MenuUI.border);
        setFocusable(false);

        Timer timer = new Timer(1, e -> updateTime());
        timer.start();
    }

    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        setText(now.format(formatter));
    }
    public static String getFormattedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }
}


