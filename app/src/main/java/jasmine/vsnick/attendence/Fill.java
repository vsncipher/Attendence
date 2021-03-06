package jasmine.vsnick.attendence;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fill extends Fragment {

    String date;
    public Fill() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        date = getArguments().getString("date");
        return inflater.inflate(R.layout.fragment_fill, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        DBHelper dbHelper=new DBHelper(getActivity().getApplicationContext(),"myDB",1);
        final ListView listView = (ListView) getActivity().findViewById(R.id.listview);
        String s = dbHelper.no_of_periods(date);
        Switch myswitch = (Switch) getActivity().findViewById(R.id.holiday);
        if(s.equals("free free free free free free free free "))
        {
            myswitch.setChecked(true);
            listView.setVisibility(View.INVISIBLE);
        }
        else {

            listView.setVisibility(View.VISIBLE);
        }
        s.trim();
        String[] periods = s.split(" ");
        CustomAdapter customAdapter = new CustomAdapter(getActivity().getApplicationContext(), periods);
        listView.setAdapter(customAdapter);
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    String t="free free free free free free free free ";
                    t.trim();
                    String[] periods = t.split(" ");
                    CustomAdapter customAdapter = new CustomAdapter(getActivity().getApplicationContext(), periods);
                    listView.setAdapter(customAdapter);
                    listView.setVisibility(View.INVISIBLE);
                }
                else
                {
                    String t="false false false false false false false false ";
                    t.trim();
                    String[] periods = t.split(" ");
                    CustomAdapter customAdapter = new CustomAdapter(getActivity().getApplicationContext(), periods);
                    listView.setAdapter(customAdapter);
                    listView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
