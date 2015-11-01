package restart.com.masterlockcracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The basic concept and algorithm for this program was taken from Samy Kamkar. In this program
 * it the language been converted to java / android version so it could be more accessible. The
 * entire original code was not released but only a part of it. The rest was created here for
 * reasons.
 * http://null-byte.wonderhowto.com/how-to/crack-any-master-combination-lock-8-tries-less-using-calculator-0161629/
 */

public class MainActivity extends Activity {
    private EditText r1c2;
    private EditText r2c2;
    private EditText r3c2;
    private TextView r5c2;
    private TextView r6c2;
    private TextView r7c2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * r = row
         * c = column
         * Each of the text or view variable point in regards to their position.
         */
        r1c2 = (EditText) findViewById(R.id.r1c2);
        r2c2 = (EditText) findViewById(R.id.r2c2);
        r3c2 = (EditText) findViewById(R.id.r3c2);
        r5c2 = (TextView) findViewById(R.id.r5c2);
        r6c2 = (TextView) findViewById(R.id.r6c2);
        r7c2 = (TextView) findViewById(R.id.r7c2);
    }

    /**
     * Takes the numbers from the three EditTexts and starts the algorithm. At this point
     * all the fields have been filled with the correct values.
     *
     * @param x Zero
     */
    private void crack(int x) {
        /**
         * The first digit is always and always will result in one number. Second and third
         * digit however may be somewhere from 2 to 25. We will store them in an array.
         */
        int[] second = new int[100];
        int[] third = new int[100];

        /**
         * l1 = Lock position 1
         * l2 = Lock position 2
         * r1 = Resistance position 1. The first digit is simply resistance location + 5.
         */
        int l1 = Integer.parseInt(r1c2.getText().toString());
        int l2 = Integer.parseInt(r2c2.getText().toString());
        int rl = ((int) Math.ceil(Integer.parseInt(r3c2.getText().toString())) + 5) % 40;

        int mod = rl % 4;

        /**
         * List of possible combinations for the third digit.
         */
        for (int i = 0; i < 4; i++) {
            if (((10 * i) + l1) % 4 == mod)
                third[i] = ((10 * i) + l1);

            if (((10 * i) + l2) % 4 == mod)
                third[i] = ((10 * i) + l2);
        }

        /**
         * List of possible combinations for the second digit.
         */
        for (int i = 0; i < 10; i++) {
            int tmp = ((mod + 2) % 4) + (4 * i);

            if (x == 0 || ((third[x - 1] + 2) % 40 != tmp && (third[x - 1] - 2) % 40 != tmp))
                second[i] = tmp;
        }

        /**
         * Build the results from the arrays by parsing them first removing any
         * additional unneeded results and divide them with a comma.
         */
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < second.length; i++) {
            if (i > 0 && second[i] != 0) {
                builder.append(", ");
            }
            if ((i > 0 && second[i] != 0) || i == 0) {
                builder.append(second[i]);
            }
        }

        StringBuilder builder2 = new StringBuilder();
        for (int i = 0; i < third.length; i++) {
            if (i > 0 && third[i] != 0) {
                builder2.append(", ");
            }
            if (third[i] != 0) {
                builder2.append(third[i]);
            }
        }

        /**
         * Set all the results to the TextViews
         */
        r5c2.setText(String.valueOf(rl));
        r6c2.setText(String.valueOf(builder));
        r7c2.setText(String.valueOf(builder2));
    }

    /**
     * Checks the fields of the EditText by checking if they are empty first and then
     * if they are within the allowed range.
     *
     * @return Error code
     */
    private int parse() {
        if (r1c2.getText().toString().trim().length() == 0) {
            return 1;
        } else if (Integer.parseInt(r1c2.getText().toString()) >= 11) {
            return 2;
        }
        if (r2c2.getText().toString().trim().length() == 0) {
            return 3;
        } else if (Integer.parseInt(r2c2.getText().toString()) >= 11) {
            return 4;
        }
        if (r3c2.getText().toString().trim().length() == 0) {
            return 5;
        }
        return 0;
    }

    /**
     * Either starts the calculations or waits on user to enter good data
     *
     * @param v View
     */
    public void buttonOnClick(View v) {
        int result = parse();
        if (result == 0) {
            crack(result);
        } else {
            error(result);
        }
    }

    /**
     * Prints a toast message depending on what error code has been passed.
     *
     * @param result Error code
     */
    private void error(int result) {
        if (result == 1) {
            Toast.makeText(getApplicationContext(), "1st Lock Position can not be empty.", Toast.LENGTH_SHORT).show();
        } else if (result == 2) {
            Toast.makeText(getApplicationContext(), "1st Lock Position can not be over 10.", Toast.LENGTH_SHORT).show();
        }
        if (result == 3) {
            Toast.makeText(getApplicationContext(), "2st Lock Position can not be empty.", Toast.LENGTH_SHORT).show();
        } else if (result == 4) {
            Toast.makeText(getApplicationContext(), "2st Lock Position can not be over 10.", Toast.LENGTH_SHORT).show();
        }
        if (result == 5) {
            Toast.makeText(getApplicationContext(), "Resistance Location can not be empty.", Toast.LENGTH_SHORT).show();
        }
    }
}

