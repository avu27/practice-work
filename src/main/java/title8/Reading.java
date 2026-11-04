package title8;

/**
 * Класс сущности метеорологических показаний.
 */
public class Reading {
    private final String station;
    private final String date;
    private final double temp;
    private final int humidity;

    public Reading(String station, String date, double temp, int humidity) {
        this.station = station;
        this.date = date;
        this.temp = temp;
        this.humidity = humidity;
    }

    public String getStation() {
        return station;
    }

    public String getDate() {
        return date;
    }

    public double getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    @Override
    public String toString() {
        return String.format("Станция: %s | Дата: %s | Темп: %.1f°C | Влажность: %d%%",
                station, date, temp, humidity);
    }
}
