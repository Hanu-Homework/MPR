package a1_1801040147.hellojapan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import a1_1801040147.hellojapan.models.CharacterDefinition;
import a1_1801040147.hellojapan.models.ContentType;
import a1_1801040147.hellojapan.services.CharacterDefinitionService;
import a1_1801040147.hellojapan.utils.AudioPlayer;

public class MainActivity extends AppCompatActivity {

    private ContentType currentActiveContent;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView header = findViewById(R.id.headerText);

        TableLayout tableLayout = findViewById(R.id.table);
        currentActiveContent = ContentType.HIRAGANA;
        populateCharacterTable(tableLayout, ContentType.HIRAGANA);

        Button hiraganaButton = findViewById(R.id.hiraganaButton);
        Button katakanaButton = findViewById(R.id.katakanaButton);
        katakanaButton.setOnClickListener(onNavButtonClick(tableLayout, header, hiraganaButton, katakanaButton));
        hiraganaButton.setOnClickListener(onNavButtonClick(tableLayout, header, hiraganaButton, katakanaButton));
    }

    protected void populateCharacterTable(TableLayout table, ContentType content) {

        CharacterDefinition[] definitions = getAllCharacterDefinitions(content);

        final int CELLS_PER_ROW = 5;

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(new TableRow.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        ));

        for (int i = 0; i < definitions.length; i += CELLS_PER_ROW) {

            TableRow row = new TableRow(this);
            row.setLayoutParams(layoutParams);

            for (int j = 0; j < CELLS_PER_ROW; j++) {
                CharacterDefinition characterDefinition = definitions[i + j];

                String jpCharacter;
                String vnCharacter;
                String audioUrl = null;

                if (characterDefinition.isBlank()) {
                    jpCharacter = "";
                    vnCharacter = "";
                } else {
                    jpCharacter = characterDefinition.getJpCharacter();
                    vnCharacter = characterDefinition.getSpelling();
                    audioUrl = characterDefinition.getAudioFileName();
                }

                TextView jpTextView = new TextView(this);
                jpTextView.setText(jpCharacter);
                jpTextView.setTextSize(30);
                jpTextView.setGravity(Gravity.CENTER);

                TextView vnTextView = new TextView(this);
                vnTextView.setText(vnCharacter);
                vnTextView.setTextSize(24);
                vnTextView.setGravity(Gravity.CENTER);

                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setBackgroundResource(R.drawable.cell_border_background);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setMinimumHeight(300);
                linearLayout.setMinimumWidth(250);
                linearLayout.setGravity(Gravity.CENTER);
                linearLayout.setClickable(true);

                if (audioUrl != null)
                    linearLayout.setOnClickListener(onCharacterClickListener(audioUrl));

                linearLayout.addView(jpTextView);
                linearLayout.addView(vnTextView);

                row.addView(linearLayout);
            }
            table.addView(row);
        }
    }

    protected CharacterDefinition[] getAllCharacterDefinitions(ContentType content) {
        CharacterDefinitionService service = CharacterDefinitionService.getInstance();
        try {
            return service.getAllCharacterDefinitions(this, content.getAssetName());
        } catch (Exception e) {
            // TODO: display error
            e.printStackTrace();
            return null;
        }
    }

    protected View.OnClickListener onCharacterClickListener(String audioAssetName) {
        return v -> AudioPlayer.getInstance().playFromAsset(v.getContext(), audioAssetName);
    }

    protected View.OnClickListener onNavButtonClick(
            TableLayout table,
            TextView header,
            Button hiragana,
            Button katakana
    ) {
        return v -> {
            Button buttonToDeactivate;
            ContentType contentTypeToActivate = null;

            if (v.getId() == R.id.hiraganaButton) {
                buttonToDeactivate = katakana;
                if (this.currentActiveContent != ContentType.HIRAGANA)
                    contentTypeToActivate = ContentType.HIRAGANA;
            } else {  // Must be katakana button
                buttonToDeactivate = hiragana;
                if (this.currentActiveContent != ContentType.KATAKANA)
                    contentTypeToActivate = ContentType.KATAKANA;
            }

            if (contentTypeToActivate != null) {
                table.removeAllViews();
                populateCharacterTable(table, contentTypeToActivate);
                header.setText(contentTypeToActivate.getName());
                this.currentActiveContent = contentTypeToActivate;

                Button buttonToActivate = (Button) v;
                buttonToActivate.setBackgroundColor(0xFF6200EE);
                buttonToActivate.setTextColor(Color.WHITE);

                buttonToDeactivate.setBackgroundColor(Color.WHITE);
                buttonToDeactivate.setTextColor(0xFF6200EE);
            }
        };
    }
}