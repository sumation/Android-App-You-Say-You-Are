package vegaglobal.yousayyouare;

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import java.util.Locale;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
public class MainActivity extends Activity implements OnInitListener  {

    private TextToSpeech tts;
    GridView grid;

    String[] positive = {
            "ENERGETIC",
            "AMAZING",
            "ABUNDANT",
            "BLISSFUL",
            "CALM",
            "EMPOWERED",
            "CONFIDENT",
            "FANTASTIC",
            "FEARLESS",
            "GENIUS",
            "INSPIRED",
            "JOYFUL"
    };


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tts =new TextToSpeech(this, this);
        setContentView(R.layout.activity_main);
        grid = (GridView) findViewById(R.id.grid);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, positive);
        grid.setAdapter(adapter);

grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //cast the view
                TextView wordView = (TextView)view;
                //retrieve the chosen word
                String wordChosen = (String) wordView.getText();
                //speakOut();


                Toast.makeText(MainActivity.this, "I am " + positive[+position] + "  always", Toast.LENGTH_SHORT).show();


                //tts.speak("I am" + positive +position+"  always", TextToSpeech.QUEUE_FLUSH, null);
                tts.speak("You chose, you are  "+wordChosen+"  today and always", TextToSpeech.QUEUE_FLUSH, null);
                // String ArrayAdapter = grid.getAdapter().toString();
                // tts.speak(ArrayAdapter,  TextToSpeech.QUEUE_FLUSH, null);

            }

        });
    }
    public void onInit(int status) {


        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            tts.setPitch(1); // set pitch level

            tts.setSpeechRate(-20); // set speech speed rate

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported");
            } else {

                // speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed");









        }



    }
    private void speakOut() {


        //tts.speak( speech, TextToSpeech.QUEUE_FLUSH, null);
    }
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }


}







