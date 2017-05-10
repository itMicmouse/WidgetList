package org.micmource.appidcard;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_select_print).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    USBPrint4DialogFragment mUSBPrint4DialogFragment = USBPrint4DialogFragment.newInstance(new USBPrint4DialogFragment.OnClickListenerMiss() {
                        @Override
                        public void setCancle() {
//
                        }
                    });
                mUSBPrint4DialogFragment.show(getFragmentManager(), "DoctorAdvice2DialogFragment");
            }
        });
    }
}
