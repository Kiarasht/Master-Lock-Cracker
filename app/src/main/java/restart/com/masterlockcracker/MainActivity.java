package restart.com.masterlockcracker;

import android.app.Activity;
import android.os.Bundle;

/**
 * The basic concept and algorithm for this program was taken from Samy Kamkar. In this program
 * it the language been converted to java / android version so it could be more accessible. But
 * remeber that the entire code was not released but only a part of it. The rest was created here.
 * http://null-byte.wonderhowto.com/how-to/crack-any-master-combination-lock-8-tries-less-using-calculator-0161629/
 */
public class MainActivity extends Activity {

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
    }
}

