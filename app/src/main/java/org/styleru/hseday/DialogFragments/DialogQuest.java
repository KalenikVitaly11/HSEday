package org.styleru.hseday.DialogFragments;

import android.content.DialogInterface;
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

import com.bumptech.glide.Glide;
import org.styleru.hseday.R;
public class DialogQuest extends DialogFragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Button CheckCodeButton;
    Button BackButton;
    EditText EditCodeText;
    TextView QuestTitle;
    TextView QuestText;
    ImageView QuestImage;
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

        Glide
                .with(this)
                .load(R.drawable.quest_2)
                .into(QuestImage);

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
        switch (view.getId()){
            case R.id.back_button:
                break;
            case R.id.check_button:
                break;
        }
        dismiss();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
