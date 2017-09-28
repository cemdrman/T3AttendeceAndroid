package jekirdek.com.t3mobil.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jekirdek.com.t3mobil.model.Attendence;
import jekirdek.com.t3mobil.model.DeneYap;
import jekirdek.com.t3mobil.model.User;

/**
 *This class for only and main job is json parsing
 */
public class JsonParse {


    public User userJsonParser(String userJsonResponse){

        User user = new User();

        try {
            JSONArray jsonArray = new JSONArray(userJsonResponse);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            user.setId(jsonObject.getInt("id"));
            user.setName(jsonObject.getString("name"));
            user.setSurname(jsonObject.getString("surname"));
            user.setCitizenId(jsonObject.getString("citizenId"));
            user.setGender(jsonObject.getString("gender"));
            user.setEmail(jsonObject.getString("email"));
            user.setPassword(jsonObject.getString("password"));
            user.setPhoneNumber(jsonObject.getString("phoneNumber"));
            user.setUserType(jsonObject.getString("userType"));
            user.setSchoolName(jsonObject.getString("schoolName"));
            user.setSection(jsonObject.getString("section"));
            user.setClas(jsonObject.getString("class"));
            user.setGrade(jsonObject.getString("grade"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(user.toString());

        return user;
    }

    public ArrayList<String> getLessonList(String lessonJsonResponse){

        ArrayList<String> lessons = null;
        try {
            JSONArray jsonArray = new JSONArray(lessonJsonResponse);
            int jsonArrayLenght = jsonArray.length();
            lessons = new ArrayList<>();
            for (int i = 0; i < jsonArrayLenght; i++) {
                lessons.add(jsonArray.getJSONObject(i).getString("name"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lessons;
    }

    /**
     *
     * @param attendenceJsonResponse : öğrencinin dönen devamsızlık listesi
     * @return öğrencinin devamsızlık listesi döner
     */
    public Attendence[] getAttendenceList(String attendenceJsonResponse){


        /**
         * attendences[i].setId((jsonArray.getJSONObject(i).getString("id").equals("null")  )  ? "-" : jsonArray.getJSONObject(i).getString("id"));
         * yukarıdaki işlem hata vermekte idi çözümü yeni bir nesne oluşturarak buldum.
         *
         */

        Attendence[] attendences = null;

        try {
            JSONArray jsonArray = new JSONArray(attendenceJsonResponse);
            int jsonArrayLenght = jsonArray.length();

            attendences = new Attendence[jsonArrayLenght];
            for (int i = 0; i < jsonArrayLenght; i++) {
                Attendence attendence = new Attendence();

                /**
                 * metodlardan null gelebildiği için one-line if kullanılarak ve bu sebepten ötürü gelen datanın 'null' kontrolünü de
                 * attendence modelinin özelliklerini String yaparak çözüldü
                 */

                attendence.setId((jsonArray.getJSONObject(i).getString("id").equals("null")  )  ? "-" : jsonArray.getJSONObject(i).getString("id"));
                attendence.setScheduleId( (jsonArray.getJSONObject(i).getString("scheduleId").equals("null") ) ? "-" : jsonArray.getJSONObject(i).getString("scheduleId"));
                attendence.setStudentId(jsonArray.getJSONObject(i).getString("studentId").equals("null") ? "-" : jsonArray.getJSONObject(i).getString("studentId"));
                attendence.setPresence(jsonArray.getJSONObject(i).getString("presence").equals("null") ? "-" : jsonArray.getJSONObject(i).getString("presence"));
                attendence.setStudentNameSurname(jsonArray.getJSONObject(i).getString("studentNameSurname").equals("null") ? "-" : jsonArray.getJSONObject(i).getString("studentNameSurname"));
                attendence.setLessonDate(jsonArray.getJSONObject(i).getString("lessonDate").equals("null") ? "-" : jsonArray.getJSONObject(i).getString("lessonDate"));
                attendences[i] = attendence;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return attendences;
    }

    public DeneYap[] getDeneyapList(String responce){

        DeneYap[] deneYapListesi = null;
        try {
            JSONArray jsonArray = new JSONArray(responce);
            int jsonArrayLenght = jsonArray.length();
            deneYapListesi = new DeneYap[jsonArrayLenght];
            for (int i = 0; i < jsonArrayLenght; i++) {
                DeneYap deneYap = new DeneYap();

                deneYap.setId(jsonArray.getJSONObject(i).getInt("id"));
                deneYap.setName(jsonArray.getJSONObject(i).getString("name"));
                deneYapListesi[i] = deneYap;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deneYapListesi;
    }

}
