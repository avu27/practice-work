package title10;

public class Reading {
    private final String st;
    private final double temp;
    private final int hum;

    public Reading(String st, double temp, int hum) {
        this.st = st;
        this.temp = temp;
        this.hum = hum;
    }

    public String getSt() {
        return st;
    }

    public double getTemp() {
        return temp;
    }

    public int getHum() {
        return hum;
    }

    @Override
    public String toString() {
        return String.format("%s (темп: %.1f°C, влажн: %d%%)", st, temp, hum);
    }
}
