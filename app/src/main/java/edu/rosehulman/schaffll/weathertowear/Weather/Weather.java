package edu.rosehulman.schaffll.weathertowear.Weather;

/**
 * Created by Kiana on 2/5/17.
 */

public class Weather {

    public Location location;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();

    public byte[] iconData;

    public class CurrentCondition {
        private String icon;
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }
        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public class Temperature {
        private float temp;
        private float minTemp;
        private float maxTemp;

        public float getTemp() {
            return temp;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public float getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(float minTemp) {
            this.minTemp = minTemp;
        }

        public float getMaxTemp() {
            return maxTemp;
        }

        public void setMaxTemp(float maxTemp) {
            this.maxTemp = maxTemp;
        }

    }
}