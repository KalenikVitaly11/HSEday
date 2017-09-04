package org.styleru.hseday2017.MarkerScreens;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.styleru.hseday2017.DataBaseHelper;
import org.styleru.hseday2017.MainActivity;
import org.styleru.hseday2017.R;

import static android.content.Context.MODE_PRIVATE;

public class DialogQuest extends DialogFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    DataBaseHelper dbHelper;

    Button CheckCodeButton;
    Button BackButton;
    EditText EditCodeText;
    TextView QuestTitle;
    TextView QuestsPassed;
    TextView QuestText;
    ImageView QuestImage;
    String passCode;
    SharedPreferences sPref;
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
        getDialog().setTitle("Title!");
        View view = inflater.inflate(R.layout.fragment_dialog_quest, null);
        CheckCodeButton = (Button) view.findViewById(R.id.check_button);
        BackButton = (Button) view.findViewById(R.id.back_button);
        EditCodeText = (EditText) view.findViewById(R.id.quest_dialog_edit_code);
        QuestText = (TextView) view.findViewById(R.id.quest_dialog_text);
        QuestTitle = (TextView) view.findViewById(R.id.quest_dialog_title);
        QuestImage = (ImageView) view.findViewById(R.id.quest_dialog_image);
        QuestsPassed = (TextView) view.findViewById(R.id.quests_passed);



        Bundle mArgs = getArguments();
       // Glide.with(this).load("http://dayhse.styleru.net/docs/img/qst/sunlit.jpg").into(QuestImage);
        passCode = mArgs.getString("passcode");
        String imageUrl = mArgs.getString("imageurl");
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
                    dbHelper = new DataBaseHelper(getContext());
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    Cursor cursorQuest = database.query(DataBaseHelper.TABLE_QUESTS_NAME, null, null, null, null, null, null);
                    if (cursorQuest.moveToFirst()) {
                        int passedIndex = cursorQuest.getColumnIndex(DataBaseHelper.QUESTS_PASSED);
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

                    sPref = getActivity().getPreferences(MODE_PRIVATE);
                    Integer questsPassedNumber = sPref.getInt("questsPassed", -1) + 1;
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putInt("questsPassed", questsPassedNumber);
                    ed.apply();


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
