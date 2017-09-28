package jekirdek.com.t3mobil;


import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

import jekirdek.com.t3mobil.activityes.LoginActivity;

/**
 * Created by cem on 9.07.2017.
 */
public class EmailValidatorTest {

    private LoginActivity emailValidator = new LoginActivity();

    @Test()
    public void ValidEmailTest() {

        String[] emails = { "mkyong@yahoo.com",
                "mkyong-100@yahoo.com", "mkyong.100@yahoo.com",
                "mkyong111@mkyong.com", "mkyong-100@mkyong.net",
                "mkyong.100@mkyong.com.au", "mkyong@1.com",
                "mkyong@gmail.com.com", "mkyong+100@gmail.com",
                "mkyong-100@yahoo-test.com","mkyong", "mkyong@.com.my",
                "mkyong123@gmail.a", "mkyong123@.com", "mkyong123@.com.com",
                ".mkyong@mkyong.com", "mkyong()*@gmail.com", "mkyong@%*.com",
                "mkyong..2002@gmail.com", "mkyong.@gmail.com",
                "mkyong@mkyong@gmail.com", "mkyong@gmail.com.1a" };
        for (String temp : emails) {
            boolean valid = emailValidator.validate(temp);
            System.out.println("Email validation : " + temp + " , " + valid);

        }

    }

}
