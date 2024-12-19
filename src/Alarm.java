import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


public class Alarm extends Thread{
    private final String DB_URL = "jdbc:sqlite:medsdata.db";
    private final String GET_DATA = "SELECT MedTime FROM meds";
    private final String Pattern = "HH:mm";
    private LinkedList<LocalTime> TimeList;
    private Queue<LocalTime> TimeQueue;
    private String MyTime;
    private DateTimeFormatter Format;
    private LocalTime DateTime, CurrentTime;

    // Getting time data and sorting it
    public LinkedList<LocalTime> GetTimeData(){
        TimeList = new LinkedList<LocalTime>();
        try(			
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(GET_DATA);
        ){
            while(rs.next()){
                MyTime = rs.getString("MedTime");
                Format = DateTimeFormatter.ofPattern(Pattern);
                DateTime = LocalTime.parse(MyTime, Format);
                TimeList.add(DateTime);
            }
            Collections.sort(TimeList);
        }
        catch(SQLException e){
            System.out.print(e);
        }
        return TimeList;
    }   
    // Function that triggers alarms
    public void GiveAlarm(Queue<LocalTime> TimeQueue){
    	// Repeat until queue is empty
        try{
            while(!TimeQueue.isEmpty()){
        	// Fetch local time and round to minutes
            CurrentTime = LocalTime.now();
            CurrentTime = CurrentTime.truncatedTo(ChronoUnit.MINUTES);
            // If its less than next alarm then wait a minute and refresh
            if(CurrentTime.compareTo(TimeQueue.peek()) < 0){
                System.out.println("w");
                try{
                    Thread.sleep(10000);
                }
                catch(InterruptedException e){
                    System.out.print(e);
                }
                GiveAlarm(TimeQueue);
            }
            // Give alarm if equal to time in alarm
            if(CurrentTime.compareTo(TimeQueue.peek()) == 0){
                // Check if its possible to notify for platform
                if(SystemTray.isSupported()){
                    // Push message
                    Notify();
                }
                // Remove notified alarm and loop
                TimeQueue.remove();
                GiveAlarm(TimeQueue);
            }
            // Skip if alarm has been earlier that day
            if(CurrentTime.compareTo(TimeQueue.peek()) > 0){
                System.out.print("s");
                TimeQueue.remove();
                GiveAlarm(TimeQueue);
            }
        }}
        catch(NullPointerException e){
            return;
        }
        
    }

    // System level notification
    public void Notify(){
        // Get system tray   
        SystemTray Tray = SystemTray.getSystemTray();
        Image DFImage = Toolkit.getDefaultToolkit().createImage("icon.png");
       
        // Icon on the system tray 
        TrayIcon Icon = new TrayIcon(DFImage, "Pill reminder notification");
        Icon.setImageAutoSize(true);
        Icon.setToolTip("System tray icon demo");
       
        // Try sending message
        try {
            Tray.add(Icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        Icon.displayMessage("Pill reminder", "It's time to take one of your medications", MessageType.INFO);
    }
    
    // Summary of all functions in one thread
    @Override
    public void run(){
        GetTimeData();
        TimeQueue = GetTimeData();
        GiveAlarm(TimeQueue);
    }
}
