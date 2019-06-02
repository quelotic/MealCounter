package com.quelotic.mealcounter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SmartTabLayout viewPagerTab;
    final static String filename = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        viewPager = findViewById(R.id.viewPager);
        viewPagerTab = findViewById(R.id.tabs);

        viewPager.setAdapter(new com.quelotic.mealcounter.PagerAdapter(getSupportFragmentManager()));
        viewPagerTab.setViewPager(viewPager);

    }

//    public void checkButton(View view) {
//
//        try {
//            int plates = 0;
//            File file = new File(getFilesDir(), filename);
//
//            if (file.exists()) {
//                FileInputStream fileInputStream = openFileInput(filename);
//                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//
//                while (bufferedReader.readLine() != null) plates++;
//                bufferedReader.close();
//            }
//
//            Toast.makeText(getBaseContext(), "Έχεις φάει " + plates + " γεύματα! " + (30 - plates) + " ακόμη διαθέσιμα!", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(this, "Πρόβλημα!", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//
//    }
//
//    public void logButton(View view) {
//        LogDialog logDialog = new LogDialog();
//        logDialog.show(getSupportFragmentManager(), "log_dialog");
//    }

}