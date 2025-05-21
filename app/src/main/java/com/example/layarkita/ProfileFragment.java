package com.example.layarkita;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.SharedPreferences;

import java.util.Locale;

public class  ProfileFragment extends Fragment{

    public ProfileFragment() {

    }

    public void onAttach(Context context){
        super.onAttach(LocalHelper.setLocale(context, getSelectedLanguageCode(context)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView profileName = view.findViewById(R.id.profileName);
        TextView tvBahasa = view.findViewById(R.id.tvBahasa);
        tvBahasa.setOnClickListener(v -> showLanguageDialog());

        SharedPreferences preferences = requireActivity().getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        String userName = preferences.getString("user_name", "Pengguna");

        String greeting = getString(R.string.hello_user);
        profileName.setText(greeting + ", " + userName);
        return view;
    }

    private void showLanguageDialog(){
        final String[] languages = {"Bahasa Indonesia", "English", "Deutsch"};
        final String[] languageCodes = {"id", "en", "de"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Pilih Bahasa")
                .setItems(languages, (dialog, which)-> {
                    String selectedCode = languageCodes[which];
                    saveLanguage(selectedCode);
                    LocalHelper.setLocale(getActivity(), selectedCode);

                    getActivity().recreate();
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    private void saveLanguage(String code){
        SharedPreferences preferences = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language", code);
        editor.apply();
    }

    private String getSelectedLanguageCode(Context context){
        SharedPreferences preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return preferences.getString("language", "id");
    }
}