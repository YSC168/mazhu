package code.skit.vip.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import code.skit.vip.R;

//import com.flyco.tablayoutsamples.R;

@SuppressLint("ValidFragment")
public class fg_code_help extends Fragment {
    private String mTitle;

    public static fg_code_help getInstance(String title) {
        fg_code_help sf = new fg_code_help();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_code_all, null);
//        TextView card_title_tv = (TextView) v.findViewById(R.id.card_title_tv);
//        card_title_tv.setText(mTitle);

        return v;
    }
}

