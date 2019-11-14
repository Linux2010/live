package cn.com.myproject.json;

import cn.com.myproject.mysql.DBHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;

import java.io.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by liyang-macbook on 2017/8/28.
 */
public class ExportJson {
    public static void main(String[] args) throws UnsupportedEncodingException, JSONException, SQLException {
     //   String path = ExportJson.class.getClassLoader().getResource("").toString();
        String encoding = "utf-8";
        File file = new File( "/Users/liyang-macbook/Downloads/世界各国省市数据.json");
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();



        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = new String(filecontent, encoding);
        System.out.println(str);

        JsonObject obj = new JsonParser().parse(str).getAsJsonObject().get("Earth").getAsJsonObject();
        JsonArray jsonArray = obj.get("country").getAsJsonArray();

        String country_id;
        String station_id;
        String city_id;
        Iterator<JsonElement>  it1 = jsonArray.iterator();
        DBHelper dbHelper ;
        while(it1.hasNext()) {
            JsonObject je = it1.next().getAsJsonObject();
            country_id = UUID.randomUUID().toString().replace("-","");
            System.out.println(je.get("countryID").toString().replace("\"",""));
            System.out.println(je.get("countryName").toString().replace("\"",""));
            String _str = je.get("countryName").toString().replace("\"","");
            if("中国".equals(_str) || "马来西亚".equals(_str) || "泰国".equals(_str) || "新加坡".equals(_str) || "美国".equals(_str)
                    || "加拿大".equals(_str)
                    || "印度尼西亚".equals(_str)
                    || "缅甸".equals(_str)
                    || "柬埔寨".equals(_str)
                    ){
//            if("中国".equals(_str)){
                    dbHelper = new DBHelper("insert into sys_region(region_id,region_en,region_name,p_id) values(?,?,?,?)");
                    String[] _p = new String[4];
                    _p[0] = country_id;
                    _p[1] = je.get("countryID").toString().replace("\"","");
                    _p[2] = je.get("countryName").toString().replace("\"","");
                    _p[3] = "0";
                    dbHelper.pst.setString(1,_p[0]);
                    dbHelper.pst.setString(2,_p[1]);
                    dbHelper.pst.setString(3,_p[2]);
                    dbHelper.pst.setString(4,_p[3]);
                    dbHelper.pst.executeUpdate();


                    dbHelper.close();
                    if(je.get("station") == null){
                        continue;
                    }
                    Iterator<JsonElement>  it2 =je.get("station").getAsJsonArray().iterator();
                    while(it2.hasNext()) {
                        JsonObject je2 = it2.next().getAsJsonObject();
                        station_id = UUID.randomUUID().toString().replace("-","");
                        System.out.println(je2.get("stationID").toString().replace("\"",""));
                        System.out.println(je2.get("stationName").toString().replace("\"",""));
                        dbHelper = new DBHelper("insert into sys_region(region_id,region_en,region_name,p_id) values(?,?,?,?)");
                        _p = new String[4];
                        _p[0] = station_id;
                        _p[1] = je2.get("stationID").toString().replace("\"","");
                        _p[2] = je2.get("stationName").toString().replace("\"","");
                        _p[3] = country_id;

                        dbHelper.pst.setString(1,_p[0]);
                        dbHelper.pst.setString(2,_p[1]);
                        dbHelper.pst.setString(3,_p[2]);
                        dbHelper.pst.setString(4,_p[3]);
                        dbHelper.pst.executeUpdate();

                        dbHelper.close();
                        if(je2.get("city") == null){
                            continue;
                        }
                        try {
                            Iterator<JsonElement>  it3 =je2.get("city").getAsJsonArray().iterator();
                            while(it3.hasNext()) {
                                city_id = UUID.randomUUID().toString().replace("-","");
                                JsonObject je3 = it3.next().getAsJsonObject();
                                System.out.println(je3.get("cityID").toString().replace("\"",""));
                                System.out.println(je3.get("cityName").toString().replace("\"",""));

                                dbHelper = new DBHelper("insert into sys_region(region_id,region_en,region_name,p_id) values(?,?,?,?)");
                                _p = new String[4];
                                _p[0] = city_id;
                                _p[1] = je3.get("cityID").toString().replace("\"","");
                                _p[2] = je3.get("cityName").toString().replace("\"","");
                                _p[3] = station_id;
                                dbHelper.pst.setString(1,_p[0]);
                                dbHelper.pst.setString(2,_p[1]);
                                dbHelper.pst.setString(3,_p[2]);
                                dbHelper.pst.setString(4,_p[3]);
                                dbHelper.pst.executeUpdate();
                                dbHelper.close();
                            }
                        }catch (Exception e) {
                            JsonObject jsonElement = je2.get("city").getAsJsonObject();
                            dbHelper = new DBHelper("insert into sys_region(region_id,region_en,region_name,p_id) values(?,?,?,?)");
                            _p = new String[4];
                            city_id = UUID.randomUUID().toString().replace("-","");
                            _p[0] = city_id;
                            _p[1] = jsonElement.get("cityID").toString().replace("\"","");
                            _p[2] = jsonElement.get("cityName").toString().replace("\"","");
                            _p[3] = station_id;
                            dbHelper.pst.setString(1,_p[0]);
                            dbHelper.pst.setString(2,_p[1]);
                            dbHelper.pst.setString(3,_p[2]);
                            dbHelper.pst.setString(4,_p[3]);
                            dbHelper.pst.executeUpdate();
                            dbHelper.close();
                        }


                    }

            }

        }
    }
}
