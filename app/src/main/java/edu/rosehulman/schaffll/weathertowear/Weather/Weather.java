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
        private String condition;
        private String description;


        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

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

        public float getTemp() {
            return temp;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }


    }




}