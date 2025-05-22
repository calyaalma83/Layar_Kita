package com.example.layarkita;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.SharedPreferences;


public class  ProfileFragment extends Fragment{

    public ProfileFragment() {

    }

    public void onAttach(Context context){
        super.onAttach(LocalHelper.setLocale(context, getSelectedLanguageCode(context)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tvAkun = view.findViewById(R.id.tvAkun);
        tvAkun.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AkunActivity.class);
            startActivity(intent);
        });

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
        final String[] languages = {"Bahasa Indonesia", "English"};
        final String[] languageCodes = {"id", "en"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.bahasa))
                .setItems(languages, (dialog, which)-> {
                    String selectedCode = languageCodes[which];
                    saveLanguage(selectedCode);
                    LocalHelper.setLocale(getActivity(), selectedCode);

                    getActivity().recreate();
                })
                .setNegativeButton(getString(R.string.batal), null)
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