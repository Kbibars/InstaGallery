package InstaGalleryAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by KBibars on 5/28/2016.
 */
public class IGSort {



    public static ArrayList<IGImage> sortBySize(ArrayList<IGImage> igImages, final boolean ascending)
    {
        Collections.sort(igImages, new Comparator<IGImage>() {
            @Override
            public int compare(IGImage lhs, IGImage rhs) {
                int sort=0;
                if (ascending) {
                    sort= lhs.getmImageSize().compareTo(rhs.getmImageSize());
                }
                else {
                    sort= rhs.getmImageSize().compareTo(lhs.getmImageSize());
                }
                return sort;
            }
        });

        return igImages ;
    }

    public static ArrayList<IGImage> sortByName(ArrayList<IGImage> igImages, final boolean ascending)
    {
        Collections.sort(igImages, new Comparator<IGImage>() {
            @Override
            public int compare(IGImage lhs, IGImage rhs) {
                int sort=0;
                if(ascending)
                {
                    sort= lhs.getmImageName().compareTo(rhs.getmImageName());
                }
                else
                {
                    sort= rhs.getmImageName().compareTo(lhs.getmImageName());
                }
                return sort;
            }
        });

        return igImages ;
    }

    public static ArrayList<IGImage> sortByDate(ArrayList<IGImage> igImages, final boolean ascending)
    {
        Collections.sort(igImages, new Comparator<IGImage>() {
            @Override
            public int compare(IGImage lhs, IGImage rhs) {
                int sort=0;
                if(ascending)
                {
                    sort= lhs.getmDate().compareTo(rhs.getmDate());
                }
                else
                {
                    sort= rhs.getmDate().compareTo(lhs.getmDate());
                }
                return sort;
            }
        });

        return igImages ;
    }
}
