import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class JframeBox {

    public volatile static String jlabel="";
    public static String City="";
    public static String data="";
    public static String temp1="";
    public static String temp2="";


    public static void main(String[] args) {
        jlabel = weatherConnecting();
        var ex = Executors.newScheduledThreadPool(1);
        ex.scheduleAtFixedRate((new Runnable() {
            @Override
            public void run() {
                jlabel = weatherConnecting();
            }
        }), 1, 5, TimeUnit.SECONDS);


        JFrame jFrame = new JFrame();
        jFrame.add(new Form().getjPanel());
        jFrame.setSize(400, 300);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);






    }

    public static String weatherConnecting(){
        StringBuilder stringBuilder = new StringBuilder();
        String url = "https://www.gismeteo.ru/";
        String finish = "";
        try{

            Document doc = Jsoup.parse(new URL(url), 3000);
            var city = doc.select("div.city")
                    .select("a.city-link").first();
            var date = doc.select("div.current-time").first();
            var temperature = doc.select("div.weather-item")
                    .select("div.weather-feeling")
                    .select("div.item-value")
                    .select("span.unit")
                    .select("span.unit_temperature_c");
            var temperature2 = doc.select("div.temperature")
                    .select("span.unit")
                    .select("span.unit_temperature_c");

            City = city.text();
            data = date.text();
            temp1 = temperature.text();
            temp2 = temperature2.text();

            finish = stringBuilder.append("\n\n").
                    append("                   ")
                    .append(city.text()+"\n")
                            .
                    append("                   ")
                    .append(date.text()+"\n")
                            .
                    append("                   ")
                    .append("Температура по ощущению: "+temperature.text()+"\n")
                            .
                    append("                   ")
                    .append("Температура: " +temperature2.text()+"\n").
                    append("\n\n").append("                   ")
                    .append("Last update: "+ new Date().toString()).toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return finish;
    }

}
