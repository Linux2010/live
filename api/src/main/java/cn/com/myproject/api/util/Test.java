package cn.com.myproject.api.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.Iterator;

/**
 * Created by Administrator on 2017/8/29 0029.
 */
public class Test {


   public static void main(String args[]){
       /*String answer = "[\n" +
               "    {\n" +
               "        \"topicId\": \"050b5f109a0145d992ffcecc151deec3\",\n" +
               "        \"answer\": \"A\"\n" +
               "    },\n" +
               "    {\n" +
               "        \"topicId\": \"18d147afea7c4615b32082dfbfbb1838\",\n" +
               "        \"answer\": \"A\"\n" +
               "    }\n" +
               "]";
       JsonElement jsonElement = new JsonParser().parse(answer);
       JsonArray array = jsonElement.getAsJsonArray();
       Iterator<JsonElement> its = array.iterator();
       while(its.hasNext()) {
           JsonElement jsonElement1 = its.next();
           String _str_topicno = jsonElement1.getAsJsonObject().get("topicId").getAsString();
           System.out.print(_str_topicno);
           String _str_answer = jsonElement1.getAsJsonObject().get("answer").getAsString();
           System.out.print(_str_answer);
       }*/
      //拆分
      String[] aa = "A,B,C".split(",");
      for (int i = 0 ; i <aa.length ; i++ ) {
         System.out.println("--"+aa[i]);
      }
      //拼接
      String [] s="A,B,C,D,E,F,G".split(",");
      String _str = "";
      for (String l: s) {
         _str += l;
         System.out.print("拼接的字符串:"+_str);
      }
      String person = "[\"{\"name\":\"Tom\"}]";
   }


}
