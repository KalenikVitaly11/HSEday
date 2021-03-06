package org.styleru.hseday2017_2.MarkerScreens;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import org.styleru.hseday2017_2.DataBaseHelper;
import org.styleru.hseday2017_2.MainActivity;
import org.styleru.hseday2017_2.NavigationFragments.FragmentMap;
import org.styleru.hseday2017_2.R;

import static android.content.Context.MODE_PRIVATE;

public class DialogQuest extends DialogFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private DataBaseHelper dbHelper;

    private Button CheckCodeButton;
    private Button BackButton;
    private EditText EditCodeText;
    private TextView QuestTitle;
    private TextView QuestsPassed;
    private TextView QuestText;
    private ImageView QuestImage;
    private String passCode;
    private SharedPreferences sPref;
    private OnFragmentInteractionListener mListener;

    public DialogQuest() {
    }

    public static DialogQuest newInstance(String param1, String param2) {
        DialogQuest fragment = new DialogQuest();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog_quest, null);
        CheckCodeButton = (Button) view.findViewById(R.id.check_button);
        BackButton = (Button) view.findViewById(R.id.back_button);
        EditCodeText = (EditText) view.findViewById(R.id.quest_dialog_edit_code);
        QuestText = (TextView) view.findViewById(R.id.quest_dialog_text);
        QuestTitle = (TextView) view.findViewById(R.id.quest_dialog_title);
        QuestImage = (ImageView) view.findViewById(R.id.quest_dialog_image);
        QuestsPassed = (TextView) view.findViewById(R.id.quests_passed);



        Bundle mArgs = getArguments();
        passCode = mArgs.getString("passcode");
        String imageUrl = mArgs.getString("imageurl");
        Glide.with(this).load(imageUrl).into(QuestImage);
        QuestText.setText(mArgs.getString("description"));
        QuestTitle.setText(mArgs.getString("name"));
        sPref = getActivity().getPreferences(MODE_PRIVATE);
        Integer questsPassedNumber = sPref.getInt("questsPassed", -1);
        QuestsPassed.setText("Пройдено " + questsPassedNumber + " из " + MainActivity.questsNumber);


        dbHelper = new DataBaseHelper(getContext());
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursorQuest = database.query(DataBaseHelper.TABLE_QUESTS_NAME, null, null, null, null, null, null);
        if (cursorQuest.moveToFirst()) {
            int passedIndex = cursorQuest.getColumnIndex(DataBaseHelper.QUESTS_PASSED);
            int descriptionIndex = cursorQuest.getColumnIndex(DataBaseHelper.QUESTS_DESCRIPTION);
            do {
                if (cursorQuest.getString(descriptionIndex).equals(QuestText.getText().toString()) && cursorQuest.getInt(passedIndex) == 1) {
                    EditCodeText.setHint("Пройдено");
                    EditCodeText.setClickable(false);
                    EditCodeText.setFocusable(false);
                    EditCodeText.setFocusableInTouchMode(false);
                    CheckCodeButton.setVisibility(View.INVISIBLE);
                }
            } while (cursorQuest.moveToNext());
        }


        CheckCodeButton.setOnClickListener(this);
        BackButton.setOnClickListener(this);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                dismiss();
                break;
            case R.id.check_button:
                if (EditCodeText.getText().toString().equals("")) {
                    if (EditCodeText.getHint().toString().equals("Пройдено")) {
                        Toast.makeText(getContext(), "Эта точка уже пройдена", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Но ведь ты ничего не ввел", Toast.LENGTH_SHORT).show();
                    }

                    break;
                }

                if (EditCodeText.getText().toString().equals(passCode)) {
                    Toast.makeText(getContext(), "Верно!", Toast.LENGTH_SHORT).show();
                    sPref = getActivity().getPreferences(MODE_PRIVATE);

                    for(int i = 0;i < FragmentMap.listMarker.size(); i++){
                        if(QuestTitle.getText().equals(FragmentMap.listMarker.get(i).getTitle())){
                            Bitmap iconImage = BitmapFactory.decodeResource(getResources(), R.drawable.map_quest_passed_2);
                            FragmentMap.listMarker.get(i).setIcon(BitmapDescriptorFactory.fromBitmap(iconImage));
                        }
                    }
                    dbHelper = new DataBaseHelper(getContext());
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    Cursor cursorQuest = database.query(DataBaseHelper.TABLE_QUESTS_NAME, null, null, null, null, null, null);
                    if (cursorQuest.moveToFirst()) {
                        int descriptionIndex = cursorQuest.getColumnIndex(DataBaseHelper.QUESTS_DESCRIPTION);
                        int nameIndex = cursorQuest.getColumnIndex(DataBaseHelper.QUESTS_NAME);
                        do {
                            if (cursorQuest.getString(descriptionIndex).equals(QuestText.getText().toString()) && cursorQuest.getString(nameIndex).equals(QuestTitle.getText().toString())) {
                                ContentValues cv = new ContentValues();
                                cv.put(DataBaseHelper.QUESTS_PASSED, 1);
                                database.update(DataBaseHelper.TABLE_QUESTS_NAME, cv, DataBaseHelper.QUESTS_NAME + "=?", new String[]{QuestTitle.getText().toString()});
                                cv.clear();
                            }
                        } while (cursorQuest.moveToNext());
                    }

                    Integer questsPassedNumber = sPref.getInt("questsPassed", -1) + 1;
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putInt("questsPassed", questsPassedNumber);
                    ed.apply();
                    if(questsPassedNumber >= 20){
                        MainActivity.snackbar.show();
                    }

                    MainActivity.questsPassed++;
                    QuestsPassed.setText("Пройдено " + questsPassedNumber + " из " + MainActivity.questsNumber);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Неверно!", Toast.LENGTH_SHORT).show();
                }
                EditCodeText.setText("");
                break;
        }
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
