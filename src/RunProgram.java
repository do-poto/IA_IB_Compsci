public class RunProgram {
    public static void main(String[] args){
        MainWindow Thread1 = new MainWindow();
        Alarm Thread2 = new Alarm();
        
        Thread1.start();
        Thread2.start();
    }
}
