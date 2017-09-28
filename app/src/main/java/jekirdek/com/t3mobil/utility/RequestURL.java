package jekirdek.com.t3mobil.utility;

/**
 * Bu class bütün request urlleri içerir. methodlar static yapılar class name üzerinden erişebilir hale gelmiştir
 *
 */
public class RequestURL {

    public static final String baseUrl = "http://deneyap.org/mobileservlet.php?servletMethod=";
    public static final String loginUrl = "loginControl";
    public static final String dersListesiUrl = "getLessonList";


    public static String getOgrenciYoklamaListesiUrl(int studentId,int lessonId){
        String ogrenciYoklamaListesiUrl = baseUrl.concat("getStudentAttendenceList&student={\"id\":\""+studentId+"\"}&lesson={\"id\":\""+lessonId+"\"}");
        System.out.println(ogrenciYoklamaListesiUrl);

        return ogrenciYoklamaListesiUrl;
    }

    /**
     * eger student hakkında bütün bilgiler biliniyorsa bu method çağrıltı
     * overload edilmiş olan bu method eğer citizenId boş ise urlden çıkarılıp sunulabilir!
     * @param name
     * @param surname
     * @param citizenId
     * @return
     */
    public static String getOgrenciAramaUrl(String name, String surname, String citizenId){
        String ogrenciAramaUrl = baseUrl.concat("searchStudents&student={\"name\":\""+name+"\",\"surname\":\""+surname+"\",\"citizenId\":\""+citizenId+"\"}");
        System.out.println(ogrenciAramaUrl);
        return ogrenciAramaUrl;
    }

    /**
     * citizenId bilinmiyorsa bu method çagırılır parametreler urle yerleştirilmiş hali verilir
     * @param name
     * @param surname
     * @return
     */

    public static String getOgrenciAramaUrl(String name, String surname){
        String ogrenciAramaUrl = baseUrl.concat("searchStudents&student={\"name\":\""+name+"\",\"surname\":\""+surname+"\"}");
        return ogrenciAramaUrl;
    }

    /**
     *
     * @param name
     * @return
     */

    public static String getOgrenciAramaUrl(String name){
        String ogrenciAramaUrl = baseUrl.concat("searchStudents&student={\"name\":\"" + name + "\"}");
        return ogrenciAramaUrl;
    }

    /**
     *
     * @param deneyapId eğitimci id
     * @param date günün tarihi otomatik alınmalı format : 2017-07-10 / yyyy-mm-dd
     * @return sınıfın tüm öğrenci listesi
     */
    public static String getTumOgrenciListesiUrl(int deneyapId, int lessonId, String date){
        String tumOgrenciListesi = baseUrl.concat("getAttendenceList&deneyap={\"id\":\"" + deneyapId +"\"}&lesson={\"id\":\"" + lessonId +"\"}&date="+date);
        return tumOgrenciListesi;
    }

    public static String getYoklamaGuncellemeUrl(int id, int presence){
        String yoklamaGunceleme = baseUrl.concat("updateAttendence&attendence={\"id\":\"" + id +"\",\"presence\":\"" + presence + "\"}");
        return yoklamaGunceleme;
    }

    public static String getDeneyapUrl(){
        String deneyapUrl = baseUrl.concat("getDeneyapList");
        return deneyapUrl;
    }

}
