package us.shiroyama.android.myapplication.rest.entity;

import java.util.List;

/**
 * Created by shiroyama on 2014/06/14.
 */
public class WeatherResponse {
    private long cod;
    private long id;
    private long dt;
    private String name;
    private String base;

    private Coord coord;
    private Sys sys;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;

    public long getCod() {
        return cod;
    }

    public long getId() {
        return id;
    }

    public long getDt() {
        return dt;
    }

    public String getName() {
        return name;
    }

    public String getBase() {
        return base;
    }

    public Coord getCoord() {
        return coord;
    }

    public Sys getSys() {
        return sys;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Rain getRain() {
        return rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public static class Coord {
        private double lon;
        private double lat;

        public double getLon() {
            return lon;
        }

        public double getLat() {
            return lat;
        }
    }

    public static class Sys {
        private double message;
        private String country;
        private long sunrise;
        private long sunset;

        public double getMessage() {
            return message;
        }

        public String getCountry() {
            return country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public long getSunset() {
            return sunset;
        }
    }

    public static class Weather {
        private long id;
        private String main;
        private String description;
        private String icon;

        public long getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }

    public static class Main {
        private double temp_max;
        private double temp_min;
        private long humidity;
        private double pressure;
        private double temp;

        public double getTemp_max() {
            return temp_max;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public long getHumidity() {
            return humidity;
        }

        public double getPressure() {
            return pressure;
        }

        public double getTemp() {
            return temp;
        }
    }

    public static class Wind {
        private double deg;
        private double speed;

        public double getDeg() {
            return deg;
        }

        public double getSpeed() {
            return speed;
        }
    }

    public static class Rain {
        private String _3h;

        public String get_3h() {
            return _3h;
        }
    }

    public static class Clouds {
        private String all;

        public String getAll() {
            return all;
        }
    }
}
