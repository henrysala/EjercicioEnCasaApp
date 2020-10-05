package com.example.ejercicioencasaapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.ejercicioencasaapp.R;
import com.example.ejercicioencasaapp.models.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout myTabs;
    private View myIndicator;
    private ViewPager myViewPager;
    private ViewPagerAdapter adapter;

    private int indicatorWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        //Assign view reference (slider)
        myTabs = findViewById(R.id.tab);
        myIndicator = findViewById(R.id.indicator);
        myViewPager = findViewById(R.id.viewPager);

        //Set up the view pager and fragments
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(RutinasFragment.newInstance(), "Ejercicios");
        adapter.addFragment(MisPlanesFragment.newInstance(), "Mis Planes");
        myViewPager.setAdapter(adapter);
        myTabs.setupWithViewPager(myViewPager);

        //Determine indicator width at runtime
        myTabs.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = myTabs.getWidth() /myTabs.getTabCount();

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) myIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                myIndicator.setLayoutParams(indicatorParams);
            }
        });

        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //To move the indicator as the user scroll, we will need the scroll offset values
            //positionOffset is a value from [0..1] which represents how far the page has been scrolled
            //see https://developer.android.com/reference/android/support/v4/view/ViewPager.OnPageChangeListener

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)myIndicator.getLayoutParams();

                //Multiply positionOffset with indicatorWidth to get translation
                float translationOffset =  (positionOffset + position) * indicatorWidth ;
                params.leftMargin = (int) translationOffset;
                myIndicator.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}