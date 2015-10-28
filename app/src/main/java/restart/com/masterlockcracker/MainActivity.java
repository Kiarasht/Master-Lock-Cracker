package restart.com.masterlockcracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int x = 0;
        crack(x);
    }

    private void crack(int x) {
        double[] second = new double[100];
        double[] third = new double[100];

        double l1 = 2;
        double l2 = 9;
        double rl = (Math.ceil(1) + 5) % 40;

        double mod = rl % 4;

        for (int i = 0; i < 4; i++) {
            if (((10 * i) + l1) % 4 == mod)
                third[i] = ((10 * i) + l1);

            if (((10 * i) + l2) % 4 == mod)
                third[i] = ((10 * i) + l2);
        }

        for (int i = 0; i < 10; i++) {
            double tmp = ((mod + 2) % 4) + (4 * i);

            if (x == 0 || ((third[x - 1] + 2) % 40 != tmp && (third[x - 1] - 2) % 40 != tmp))
                second[i] = tmp;
        }

        Log.d("restart.com.masterlock", String.valueOf(rl));
        Log.d("restart.com.masterlock", Arrays.toString(second));
        Log.d("restart.com.masterlock", Arrays.toString(third));
    }
}

