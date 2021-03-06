package com.quelotic.mealcounter.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.quelotic.mealcounter.notifications.AlarmReceiver;
import com.quelotic.mealcounter.R;

import java.util.Calendar;
import java.util.Objects;

public class ClockFragment extends Fragment {

    private TimePicker timePicker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_clock, container, false);

        timePicker = rootView.findViewById(R.id.timePicker);
        SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        timePicker.setHour(sharedPref.getInt("HOUR", 0));
        timePicker.setMinute(sharedPref.getInt("MIN", 0));

        Button deactivateButton = rootView.findViewById(R.id.deactivateBtn);
        deactivateButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) Objects.requireNonNull(getActivity()).getSystemService(Context.ALARM_SERVICE);
            am.cancel(pendingIntent);
            Toast.makeText(getContext(),"Η ειδοποίηση απενεργοποιήθηκε",Toast.LENGTH_SHORT).show();
        });

        Button applyButton = rootView.findViewById(R.id.applyBtn);
        applyButton.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            calendar.set(Calendar.MINUTE, timePicker.getMinute());
            if (calendar.before(now)) calendar.add(Calendar.DAY_OF_MONTH, 1);
            Intent intent = new Intent(getContext(), AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) Objects.requireNonNull(getActivity()).getSystemService(Context.ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            Toast.makeText(getContext(), "Έτοιμο", Toast.LENGTH_SHORT).show();

            // Save preference
            SharedPreferences sharedPref1 = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref1.edit();
            editor.putInt("HOUR", timePicker.getHour());
            editor.putInt("MIN", timePicker.getMinute());
            editor.apply();
        });

        return rootView;
    }

}