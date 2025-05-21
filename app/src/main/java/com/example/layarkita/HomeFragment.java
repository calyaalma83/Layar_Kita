package com.example.layarkita;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerFilm;
    private FilmAdapter filmAdapter;
    private List<Film> filmList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerFilm = view.findViewById(R.id.recyclerFilm);
        recyclerFilm.setLayoutManager(new GridLayoutManager(getContext(), 2));

        filmList = getDummyFilmData();

        filmAdapter = new FilmAdapter(
                getContext(),
                filmList,
                false,
                film -> {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("title", film.getTitle());
                    intent.putExtra("image", film.getPosterResId());
                    intent.putExtra("desc", film.getDescription());
                    intent.putExtra("trailer", film.getTrailerUrl() != null ? film.getTrailerUrl() : "");
                    startActivity(intent);
                },
                film -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    if (WatchListHelper.getWatchList().contains(film)) {
                        builder.setTitle("Info")
                                .setMessage("Film ini sudah ada di daftar suka")
                                .setPositiveButton("OK", null)
                                .show();
                    } else {
                        WatchListHelper.addToWatchList(film);
                        builder.setTitle("Berhasil")
                                .setMessage("Ditambahkan ke daftar suka")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }

        );

        recyclerFilm.setAdapter(filmAdapter);
    }

    private List<Film> getDummyFilmData(){
        List<Film> list = new ArrayList<>();
        list.add(new Film("Komang", R.drawable.komang, "Lorem ipsum dolor sit amet", " "));
        list.add(new Film("Miracle in Cell No. 7", R.drawable.miracle, "Lorem ipsum dolor sit amet", " "));
        list.add(new Film("Plankton The Movie", R.drawable.plankton, "Lorem ipsum dolor sit amet", " "));
        list.add(new Film("Final Destination Bloodlines", R.drawable.final_destination, "Lorem ipsum dolor sit amet", "https://youtu.be/UWMzKXsY9A4?si=gGgP3EuOrM8oB66m"));
        list.add(new Film("99 Cahaya di Langit Eropa", R.drawable.cahaya_eropa, "Lorem ipsum dolor sit amet", "https://youtu.be/OUPQ4kMD620?si=5B79tpjmPr9lAQj7"));
        list.add(new Film("Despicable Me 4", R.drawable.film_despicableme4, "Lorem ipsum dolor sit amet", " "));
        list.add(new Film("Love Revolution", R.drawable.film_loverevolution, "Lorem ipsum dolor sit amet", " "));
        list.add(new Film("Final Destinaton 3", R.drawable.fd3, "Lorem ipsum dolor sit amet","https://youtu.be/ectjqGg92M8?si=B9xPKRkpaikaTA-Q"));
        list.add(new Film("The Martian", R.drawable.themartian, "Lorem ipsum dolor sit amet.", "https://youtu.be/ej3ioOneTy8?si=-ETSKwIjRzaPPQJh"));
        list.add(new Film("Everest", R.drawable.everest, "Lorem ipsum dolor sit amet", " "));
        list.add(new Film("Pengabdi Setan 2", R.drawable.setan2, "Lorem ipsum dolor sit amet", " "));
        list.add(new Film("Pengepungan di Bukit Duri", R.drawable.bukitduri, "Lorem ipsum dolor sit amet", "https://youtu.be/OBbE4wK47ts?si=xV_UMUo4wpWxUtFB"));
        return list;
    }
}