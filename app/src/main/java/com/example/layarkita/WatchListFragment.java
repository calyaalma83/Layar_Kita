package com.example.layarkita;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WatchListFragment extends Fragment {

    private RecyclerView recyclerWatchList;
    private FilmAdapter adapter;
    private List<Film> watchList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("WatchListFragment", "onCreateView dipanggil");
        return inflater.inflate(R.layout.fragment_watch_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("WatchListFragment", "onViewCreated dipanggil");

        recyclerWatchList = view.findViewById(R.id.recyclerWatchList);

        recyclerWatchList.setLayoutManager(new GridLayoutManager(getContext(), 2));

        watchList = WatchListHelper.getWatchList();
        Log.d("WatchListFragment", "Jumlah film di daftar suka: " + watchList.size());

        adapter = new FilmAdapter(
                getContext(),
                watchList,
                true,
                film -> {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("title", film.getTitle());
                    intent.putExtra("image", film.getPosterResId());
                    intent.putExtra("desc", film.getDescription_id());
                    intent.putExtra("desc", film.getDescription_en());
                    intent.putExtra("desc", film.getDescription_de());
                    startActivity(intent);
                },
                film -> {
                    new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                            .setTitle("Konfirmasi")
                            .setMessage("Yakin ingin menghapus film dari daftar suka?")
                            .setPositiveButton("Hapus", (dialog, which) -> {
                                WatchListHelper.removeFromWatchList(film);

                                watchList.clear();
                                watchList.addAll(WatchListHelper.getWatchList());
                                adapter.notifyDataSetChanged();

                                new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                                        .setTitle("Dihapus")
                                        .setMessage("Film berhasil dihapus dari daftar suka")
                                        .setPositiveButton("OK", null)
                                        .show();
                            })
                            .setNegativeButton("Batal", null)
                            .show();
                });


        watchList.clear();
        watchList.addAll(WatchListHelper.getWatchList());
        adapter.notifyDataSetChanged();


        recyclerWatchList.setAdapter(adapter);
        Log.d("WatchListFragment", "Adapter berhasil di-set");
    }

    @Override
    public void onResume(){
        super.onResume();
        refreshWatchList();
    }

    private void refreshWatchList(){
        if (watchList != null){
            watchList.clear();
            watchList.addAll(WatchListHelper.getWatchList());
            adapter.notifyDataSetChanged();
            Log.d("WatchListFragment", "Daftar suka diperbarui. Jumlah film: " + watchList.size());
        }
    }

}
