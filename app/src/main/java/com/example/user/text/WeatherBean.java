package com.example.user.text;

/**
 * @author LiXinyu
 * @date 2017/8/8 17:29.
 */

public class WeatherBean {
    private Weatherinfo weatherinfo;

    public Weatherinfo getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(Weatherinfo weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    public static class Weatherinfo{
        private String city;
        private String cityid;
        private String temp;
        private String WD;
        private String WS;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getWD() {
            return WD;
        }

        public void setWD(String WD) {
            this.WD = WD;
        }

        public String getWS() {
            return WS;
        }

        public void setWS(String WS) {
            this.WS = WS;
        }

        @Override
        public String toString() {
            return  "城市："+getCity()+
                    "\n城市代码:"+getCityid()+
                    "\n温度："+getTemp()+"℃"+
                    "\n风向："+getWD()+
                    "\n风力："+getWS();
        }
    }
}
