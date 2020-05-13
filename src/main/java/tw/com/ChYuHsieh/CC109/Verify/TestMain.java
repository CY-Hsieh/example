package tw.com.ChYuHsieh.CC109.Verify;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.text.html.HTMLEditorKit.Parser;
import crypto.Key;
import java.util.Base64;


public class TestMain {
  
  private static final Key.ExpandedKey KEY_AES = Key.KeySize.AES_128.genKeysHmacSha("eNvElOpEbInGo".getBytes());
//  boolean free;
//  
//  public boolean setFree() {
//   return  free;
//  }
  
//  class person{
//    final String name;
//    public void Person(String nm) {
//      name = nm;
//    }
//  }
  
  public static String encodeUid(Long id, String mode, Instant endedAt) {
    try {
      String source = String.format("%s-%d-%d", mode, id,
          endedAt.toEpochMilli());
      byte[] data = crypto.AES.encryptGCM(KEY_AES, source.getBytes());
      return Base64.getUrlEncoder().encodeToString(data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public static Map<String,String> decodeUid(String uid) {
      try {
        Map<String, String> resultMap = new HashMap<>();
        byte[] data = Base64.getUrlDecoder().decode(uid);
        byte[] source = crypto.AES.decryptGCM(KEY_AES, data);
        String[] token = new String(source).split("-");
        resultMap.put("mode", token[0]);
        resultMap.put("id", token[1]);
        long date = Long.parseLong(token[2]);
        Date longToDate = new Date(date);
        resultMap.put("endedAt", longToDate.toString());
        return resultMap;
      } catch (Exception e) {
        return new HashMap<>();
      }     
  }
  
  public static int byteArrayToInt(byte[] b){ 
    byte[] a = new byte[4]; 
    int i = a.length - 1,j = b.length - 1; 
    for (; i >= 0 ; i--,j--) {//從b的尾部(即int值的低位)開始copy數據 
      if(j >= 0) 
        a[i] = b[j]; 
      else 
        a[i] = 0;//如果b.length不足4,則將高位補0
      } int v0 = (a[0] & 0xff) << 24;//&0xff將byte值無差異轉成int,避免Java自動類型提升後,會保留高位的符號位
      int v1 = (a[1] & 0xff) << 16; int v2 = (a[2] & 0xff) << 8; int v3 = (a[3] & 0xff) ; 
      return v0 + v1 + v2 + v3; }
 



//  public static void main(String[] args) throws ParseException, InterruptedException {
   
//    Long id = 133L;
//    String mode = "all";
//    byte[] byteMode =  mode.getBytes();
//    System.out.println(byteArrayToInt(byteMode));
//    Instant endedAt = Instant.now();
//    String result = encodeUid(id,mode,endedAt);
//    Map<String, String> decodeResult = decodeUid(result);
//    for(String str: decodeResult.keySet()) {
//        String value = decodeResult.get(str);
//      System.out.println("key: " + str + " value: " + value);
//    }
//    System.out.println(result);
    
    

    
//    String[] s1 = {"1","2","3"};
//    String[] s2 = {"4","5","6"};
//    s1 = s2;
//    for(String st : s1) {
//      System.out.println(st);
//    }

//    Instant time = Instant.now();
//    int i = time.get(ChronoField.MILLI_OF_SECOND);
    
//    System.out.println(ZonedDateTime.now());
//    System.out.println(Instant.now().plus(10, ChronoUnit.MINUTES));
//    System.out.println(Instant.now().atOffset(ZoneOffset.ofHours(8)));
//    System.out.println(Instant.now());
//    String seed = "sadawdascassdafghfd";
//    byte[] seedByte = seed.getBytes();
//    String DEFAULT_ENCODING = StandardCharsets.UTF_8.displayName();
//    System.out.println(DEFAULT_ENCODING);
//    String as = Base64.getEncoder().encodeToString(seedByte);
//    System.out.println(as);
//    double max = 1000;
//    double min = max / 2;
//    double a = ((Math.random() * (max - min + 1)) + min);
//    System.out.println(a);
//    List<Integer> list1 = new ArrayList<>();
//    list1.add(1);
//    list1.add(2);
//    list1.add(3);
//    list1.add(4);
//    list1.add(5);
//    System.out.println("normal list1 : " + list1);
//    Collections.shuffle(list1);
//    System.out.println("random list1 : " + list1);
//    List<Integer> list2 = new ArrayList<>();
//    list2.add(6);
//    list2.add(7);
//    list2.add(8);
//    list2.add(9);
//    list2.add(10);
//    System.out.println("normal list2 : " + list2);
//    Collections.shuffle(list2);
//    System.out.println("random list2 : " + list2);
//    
//    List<Integer> allList = Stream.of(list1,list2)
//        .flatMap(all -> all.stream()).collect(Collectors.toList());
//    System.out.println(allList);


//    final char[] DIGITS_UPPER =
//      {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//    
//    byte[] bytes = {1,2,3,4,5,6};
//      StringBuilder builder = new StringBuilder(bytes.length << 1);
//
//      for (byte iterator : bytes) {
//        System.out.println("byte iterator " + iterator);
//        builder.append(DIGITS_UPPER[(0xF0 & iterator) >>> 4]).append(DIGITS_UPPER[0x0F & iterator]);
//        System.out.println("0xF0 " + DIGITS_UPPER[(0xF0 & iterator) >>> 4]);
//        System.out.println("0x0F " + DIGITS_UPPER[0x0F & iterator]);
//        System.out.println("byte builder " + builder.toString());
//      }

//    String[] color = {"blue", "red", "green", "yellow", "orange"};
//    Arrays.sort(color);
//    for(int i = 0; i < color.length; i++) {
//      System.out.println(color[i]);
//    }
   
//    ArrayList<Long> aleradyGetUser = new ArrayList<>();
//    aleradyGetUser.add(0L);
//    aleradyGetUser.add(1L);
//    aleradyGetUser.add(2L);
//    aleradyGetUser.add(3L);
//    aleradyGetUser.add(4L);
//    aleradyGetUser.add(5L);
//    aleradyGetUser.add(6L);
//    System.out.println(aleradyGetUser);
//    Long[] info = new Long[aleradyGetUser.size()];
//    for(int n = 0; n < aleradyGetUser.size(); n++) {
//      info[n] = aleradyGetUser.get(n);
//      System.out.println(info.toString());
//    }
//     int a = 1, b = 2;
//     
//     if(a <= b) {
//       System.out.println("JH");
//     }
//    System.out.println(info);
//    Date dt = new Date();
//    Calendar date = Calendar.getInstance();
//    System.out.println(new Timestamp(System.currentTimeMillis()).toString());
//    System.out.println(date);
//    boolean check = false;
//    ArrayList<String> list = new ArrayList<>();
//      list.add("A");
//      list.add("A");
//      list.add("C");
//      list.add("B");
//      list.add("A");
//      list.add("B");
//      list.add("A");
//      for(int i = 0; i < list.size(); i++) {
//        if(list.get(i).equals("C")) {
//          check = true;
//        }else {
//          check = false;
//        }
//        System.out.println("check inside for loop "+ i + check);
//      }
//      System.out.println("check outside for loop " + check);
//    Timestamp systemTime = new Timestamp(System.currentTimeMillis());
//    System.out.println(systemTime);
//    Thread.sleep(30000);
//    Timestamp systemTime2 = new Timestamp(System.currentTimeMillis());
//    System.out.println(systemTime2);
//    System.out.println(systemTime.compareTo(systemTime2));
//    Date currentDate = new Date();
//    System.out.println(currentDate);
    
//    java.text.SimpleDateFormat simple = new java.text.SimpleDateFormat();
//    simple.applyPattern("yyyy-MM-dd");
//    try {
//        java.util.Date date = new java.util.Date();
//        String str = "2020-01-09 14:14:14";
//        java.sql.Date nDay(date.getTime());
//        date = simple.parse(str);
//        System.out.println(date);
//        java.sql.Date nDate = new java.sql.Date(date.getTime());
//        System.out.println(nDate);
//        System.out.println(nDate.compareTo(currentDate));
//    } catch (ParseException e) {
//      e.printStackTrace();
//    }
//    Calendar date = Calendar.getInstance();
//    date.setTime(new Timestamp(System.currentTimeMillis()));
//    date.add(Calendar.MONTH, 1);
//    date.set(Calendar.MILLISECOND, 0);
//    java.util.Date y = date.getTime();
//    String pattern = "yyyy-MM-dd HH:mm:ss";
//    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//    String demo = sdf.format(y);
//
//    System.out.println(demo);
//    int x = 5;
//    boolean b1 = true;
//    boolean b2 = false;
//
//    if ((x == 4) && !b2)
//        System.out.print("1 ");
//        System.out.print("2 ");
//    if ((b2 = true) && b1)
//        System.out.print("3 ");
    
//    String o = "";
//    z:
//    for (int x = 0; x < 3; x++) {
//        for (int y = 0; y < 2; y++) {
//            if (x == 1)break;
//            if (x == 2 && y == 1)break z;
//            o = o + x + y;
//        }
//    }
//    System.out.println(o);

//    int x = 0;
//    int y = 10;
//    while (x < 5) {
//        y--;
//        ++x;
//    } ;
//    System.out.print(x + "," + y);
//    java.sql.Date currentDate = new java.sql.Date
//        (System.currentTimeMillis());
//    String strCurrentDate = currentDate.toString();
//    System.out.println(currentDate);
//    System.out.println(strCurrentDate);
//    
//    Date sTime = new Date(System.currentTimeMillis());
//    java.sql.Date startTime =  new java.sql.Date(sTime.getTime());
//    System.out.println(sTime);
//    System.out.println(startTime);
    
//    Long a = 1L;
//    System.out.println(a);
//    String[] result = {"05","08","02","03","09","01","06","04","10","07"};
//    
//    for(String str : result) {
//      System.out.println(str);
//    }
//    Calendar date = Calendar.getInstance();
//    date.setTime(new Timestamp(System.currentTimeMillis()));
//   System.out.println(date); 
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    Map<String,String> checkResult = new HashMap<>();
//    Calendar dateNow = Calendar.getInstance();
//    Calendar dateStartInData = Calendar.getInstance();
//    String date = "2020-01-11 00:00:00";
//    Date date2 = sdf.parse(date);
//    
//    dateStartInData.setTime(date2);
//    
//    if(dateStartInData.get(Calendar.HOUR_OF_DAY) == 0) {
//       dateStartInData.set(Calendar.HOUR_OF_DAY, dateNow.get(Calendar.HOUR_OF_DAY));
//    }
//    if(dateStartInData.get(Calendar.MINUTE) == 0) {
//       dateStartInData.set(Calendar.MINUTE, dateNow.get(Calendar.MINUTE));
//    }
//    if(dateStartInData.get(Calendar.SECOND) == 0) {
//       dateStartInData.set(Calendar.SECOND, dateNow.get(Calendar.SECOND));
//    }
//    if(dateStartInData.get(Calendar.MILLISECOND) == 0) {
//       dateStartInData.set(Calendar.MILLISECOND, dateNow.get(Calendar.MILLISECOND));
//    }
//    
//    if(dateNow.compareTo(dateStartInData) == -1) {
//      checkResult.put("startTime", "尚未開始");
//    }
//    if(dateNow.compareTo(dateStartInData) == 0) {
//      checkResult.put("startTime", "已開始");
//    }
//    if(dateNow.compareTo(dateStartInData) == 1) {
//      checkResult.put("startTime", "已過期");
//    }
//    System.out.println(checkResult.get("startTime"));
    
//    Date currentDate = new Date(System.currentTimeMillis());
//    String strCurrentDate = currentDate.toString();
//    System.out.println(strCurrentDate);
//    int a = 5, b = 0;
//    
//    try {
//      int c = a/b;
//    }catch(RuntimeException e){
//      System.out.println("Here");
//    }catch(Exception o) {
//      System.out.println("there");
//    }
//
//    String[] names = { "McDonald's", "Yun.Ma", "Samuel.L.Jackson" };
//    String s = Arrays.stream(names)
//        .max(Comparator.comparing(e -> e.toString().length()))
//        .map(e -> e.toUpperCase()).get();
//    System.out.println(s);
//    System.out.println(modify(true)); 
//    System.out.println(System.currentTimeMillis());
//    System.out.println(System.nanoTime());
    
//    System.out.println("  *");
//    System.out.println(" * * ");
//    System.out.println("* * *");
//    System.out.println(" * *");
//    System.out.println("  *");

//  }
//  @SuppressWarnings("finally")
//  static boolean modify(boolean flag) {
//
//    try {
//        if (flag) {
//            throw new RuntimeException("error");
//        }
//    } catch (Exception e) {
////      e.printStackTrace();
//        flag = false;
//        return flag;
//    } finally {
//        flag = true;
//        return flag;
//    }
//  }
  
  
 
  
 
}


