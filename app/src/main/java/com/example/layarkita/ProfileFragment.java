package com.example.layarkita;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

public class  ProfileFragment extends Fragment{

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tvBahasa = view.findViewById(R.id.tvBahasa);
        tvBahasa.setOnClickListener(v -> showLanguageDialog());
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
}