package GamePlay;

public class User {
    String name;
    String mima;

    public User() {
    }

    public User(String name, String mima) {
        this.name = name;
        this.mima = mima;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }
}
