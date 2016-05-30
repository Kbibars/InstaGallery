package instagallery.kbibars.com.instagallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import InstaGalleryAPI.IGFilter;
import InstaGalleryAPI.IGImage;
import InstaGalleryAPI.IGImageLoader;
import InstaGalleryAPI.IGSort;
import instagallery.kbibars.com.instagallery.Utilities.MyAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner sortCriteria;
    Spinner sortType;
    Button applySort;
    EditText fromDate;
    EditText toDate;
    Button applyFilter;
    Button switchLayout;
    Button reload;

    RecyclerView recyclerView;
    int orientation = 0;
    ArrayList<IGImage> mImageList;
    ArrayList<String> mImageUriList;
    IGImageLoader mImageLoader;
    MyAdapter adapter;

    private String[] criteriaSpinner;
    private String[] typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*View intializations (Should have used Dagger)*/
        recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        sortCriteria = (Spinner) findViewById(R.id.sort_criteria);
        sortType = (Spinner) findViewById(R.id.sort_type);
        applySort = (Button) findViewById(R.id.apply_sort);
        fromDate = (EditText) findViewById(R.id.from_date);
        toDate = (EditText) findViewById(R.id.to_date);
        applyFilter = (Button) findViewById(R.id.apply_filter);
        switchLayout = (Button) findViewById(R.id.switch_layout);
        reload = (Button) findViewById(R.id.reload);

        /*Initializing the spinners used for the sorting*/
        initializeAdapters();

        applySort.setOnClickListener(this);
        applyFilter.setOnClickListener(this);
        switchLayout.setOnClickListener(this);
        reload.setOnClickListener(this);
        /*Hide the keyboard*/
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        /*Loads all the images in the device and show them in GridView*/
        reload();


    }

    private void initializeAdapters() {
        this.criteriaSpinner = new String[]{"Name", "Size", "Date"};
        this.typeSpinner = new String[]{"Asc", "Desc"};
        ArrayAdapter<String> tempAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, criteriaSpinner);
        sortCriteria.setAdapter(tempAdapter);

        ArrayAdapter<String> tempAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, typeSpinner);
        sortType.setAdapter(tempAdapter2);
    }

    @Override
    public void onClick(View v) {
/*
        Handling clicklistners to apply filters and sorts and to switch the layout from grid to list and viceversa
*/

        switch (v.getId()) {
            case R.id.apply_sort:
                applySort(sortCriteria.getSelectedItem().toString(), sortType.getSelectedItem().toString());

                break;
            case R.id.apply_filter:
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                try {
                    Date fromdate = sdf.parse(fromDate.getText().toString());
                    Date todate = sdf.parse(toDate.getText().toString());
                    applyDateFilter(fromdate, todate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.switch_layout:
                switchLayout();
            case R.id.reload:
                reload();
            default:
                break;

        }
    }

    /*
        Apply sort based on the spinners values and update the RecyclerView
    */
    public void applySort(String criteria, String type) {
        ArrayList<IGImage> temp = new ArrayList<>();
        temp = mImageList;
        switch (criteria) {
            case "Name":
                if (type.equals("Asc")) {

                    temp = IGSort.sortByName(temp, true);
                } else if ((type.equals("Desc"))) {

                    temp = IGSort.sortByName(temp, false);
                }
                break;
            case "Size":
                if (type.equals("Asc")) {

                    temp = IGSort.sortBySize(temp, true);
                } else if ((type.equals("Desc"))) {

                    temp = IGSort.sortBySize(temp, false);
                }
                break;
            case "Date":
                if (type.equals("Asc")) {

                    temp = IGSort.sortByDate(temp, true);
                } else if ((type.equals("Desc"))) {

                    temp = IGSort.sortByDate(temp, false);
                }
                break;
            default:
                break;
        }

        adapter = new MyAdapter(temp, MainActivity.this, 1);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    /*
        Update the RecyclerView.LayoutManager to switch between Gridlayout and LinearLayout
    */
    public void switchLayout() {
        if (orientation == 0) {
            orientation = 1;
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 3);
            recyclerView.setLayoutManager(mLayoutManager);
        } else if (orientation == 1) {
            orientation = 0;
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(mLayoutManager);
        }

    }

    public void applyDateFilter(Date fromDate, Date toDate) {
        ArrayList<IGImage> igImages;


        igImages = IGFilter.filterByDate(mImageList, fromDate, toDate);
        adapter = new MyAdapter(igImages, MainActivity.this, 1);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    public void reload() {
/*
        Create a new instance of IGImageLoader
*/
        mImageLoader = new IGImageLoader();
/*
        Load all the images URIs
*/
        mImageUriList = mImageLoader.loadAllDeviceImages(this);
        /*Call loadImagesList using the list returned from loadAllDeviceImages and passing 0 as the starting index and -1 to load all */
        mImageList = mImageLoader.loadImagesList(mImageUriList, 0, -1);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new MyAdapter(mImageList, this, 1);
        recyclerView.setAdapter(adapter);
    }
}












