package com.iitr.iitroorkee;

import java.util.Calendar;
import java.util.Locale;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


 public class HomeFragment extends Fragment implements OnClickListener {
	
	public HomeFragment(){}
	
	Intent service_intent;
    Button start_notifying,stop_notfying;
    static Button selectedDayMonthYearButton;
    Button currentMonth;
    ImageView prevMonth;
    ImageView nextMonth;
    GridView calendarView;
    GridCellAdapter adapter;
    Calendar _calendar;
    Context context;
	static PendingIntent pendingIntent;
	static AlarmManager alarmManager;
    
    
    int month, year;
    public static final String dateTemplate = "MMMM yyyy";
    static String flag ="abc";
    static String date_month_year;
	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
        context=getActivity();
        
        _calendar = Calendar.getInstance();
        month = _calendar.get(Calendar.MONTH) + 1;
        year = _calendar.get(Calendar.YEAR);

        selectedDayMonthYearButton = (Button) rootView.findViewById(R.id.selectedDayMonthYear);
        selectedDayMonthYearButton.setText("Select Date");

        prevMonth = (ImageView)rootView.findViewById(R.id.prevMonth);
        prevMonth.setOnClickListener(this);

        currentMonth = (Button) rootView.findViewById(R.id.currentMonth);
        currentMonth.setText(DateFormat.format(dateTemplate, _calendar.getTime()));

        nextMonth = (ImageView) rootView.findViewById(R.id.nextMonth);
        nextMonth.setOnClickListener(this);

        calendarView = (GridView) rootView.findViewById(R.id.calendar);
           
         
        
        
        
        
        adapter = new GridCellAdapter(getActivity(),  month, year);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
        
        Intent intentsOpen = new Intent(getActivity(), AlarmReceiver.class);
		intentsOpen.setAction("com.iitr.ittroorkee.ACTION");
		pendingIntent = PendingIntent.getBroadcast(getActivity(),111, intentsOpen, 0);
		alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
        //fireAlarm();
        return rootView;
    }


	private void setGridCellAdapterToDate(int month, int year){
        adapter = new GridCellAdapter(getActivity(), month, year);
        _calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
        currentMonth.setText(DateFormat.format(dateTemplate, _calendar.getTime()));
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
    }

	@Override
    public void onClick(View v){
            if (v == prevMonth){
            	fireAlarm();
                if (month <= 1){
                    month = 12;
                    year--;
                }
                else
                    month--;
                setGridCellAdapterToDate(month, year);
            }
            if (v == nextMonth){
                if (month > 11){
                    month = 1;
                    year++;
                }
                else
                    month++;
                setGridCellAdapterToDate(month, year);
            }
}
	
	public void fireAlarm() {
		/**
		 * call broadcost reciver
		 */
		//Date date=new Date();
		_calendar.get(Calendar.HOUR);
		_calendar.get(Calendar.MINUTE);
		_calendar.get(Calendar.SECOND);
		_calendar.add(Calendar.MINUTE, 1);
		//alarmManager.set(AlarmManager.RTC_WAKEUP, _calendar.getTimeInMillis(), pendingIntent);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, _calendar.getTimeInMillis(), 10000, pendingIntent);
		
		
	}
	public void stopAlarm(){
				alarmManager.cancel(pendingIntent);
		
		
		
	}


}

