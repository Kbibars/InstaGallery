package InstaGalleryAPI;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by KBibars on 5/28/2016.
 */
public class IGFilter {
    /*Returns a list containing all images between two specified Dates*/
    public static ArrayList<IGImage> filterByDate(ArrayList<IGImage>igImages,Date fromDate,Date toDate){
        ArrayList<IGImage> temp=new ArrayList<>();

        for (int i=0;i<igImages.size();i++)
        {
            if(isDateInBetweenIncludingEndPoints(fromDate,toDate,igImages.get(i).getmDate()))
            {
                temp.add(igImages.get(i));
            }
        }

        return temp;
    }

    public static boolean isDateInBetweenIncludingEndPoints( Date fromDate,  Date toDate,Date date){
         return date.after(fromDate) && date.before(toDate);
    }
}
