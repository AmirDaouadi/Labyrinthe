public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        HomeView home = new HomeView(window);
        window.setContentPane(home);
        window.setVisible(true);
    }
}