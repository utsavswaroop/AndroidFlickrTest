package helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by utsav on 24/05/2017.
 */

public class DateFormater {

    public String formatDate (String dateString)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date formatedDate = null;
        try {
            formatedDate = dateFormat.parse(dateString);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return formatedDate.toString();
    }
}
