package InstaGalleryAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by KBibars on 5/28/2016.
 */
public class IGSort {
    public static ArrayList<IGImage> sortBySize(ArrayList<IGImage> igImages)
    {
        Collections.sort(igImages, new Comparator<IGImage>() {
            @Override
            public int compare(IGImage lhs, IGImage rhs) {
                return lhs.getmImageSize().compareTo(rhs.getmImageSize());
            }
        });

        return igImages ;
    }

    public static ArrayList<IGImage> sortByName(ArrayList<IGImage> igImages)
    {
        Collections.sort(igImages, new Comparator<IGImage>() {
            @Override
            public int compare(IGImage lhs, IGImage rhs) {
                return lhs.getmImageName().compareTo(rhs.getmImageName());
            }
        });

        return igImages ;
    }

}
