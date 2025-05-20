package com.example.layarkita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private Context context;
    private List<Film> filmList;
    private OnFilmClickListener filmClickListener;
    private OnFilmActionListener actionListener;
    private boolean isWatchList;

    public FilmAdapter(Context context, List<Film> filmList, boolean isWatchList, OnFilmClickListener filmClickListener, OnFilmActionListener actionListener) {
        this.context = context;
        this.filmList = filmList;
        this.filmClickListener = filmClickListener;
        this.actionListener = actionListener;
        this.isWatchList = isWatchList;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_film, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        Film film = filmList.get(position);

        holder.imagePoster.setImageResource(film.getPosterResId());
        holder.textTitle.setText(film.getTitle());

        holder.imagePoster.setOnClickListener(v -> {
            if (filmClickListener != null) {
                filmClickListener.onFilmClick(film);
            }
        });

        if (isWatchList) {
            holder.buttonAction.setImageResource(R.drawable.ic_delete);
        } else {
            holder.buttonAction.setImageResource(R.drawable.ic_add);
        }

        holder.buttonAction.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onFilmAction(film);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePoster;
        TextView textTitle;
        ImageView buttonAction;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePoster = itemView.findViewById(R.id.imagePoster);
            textTitle = itemView.findViewById(R.id.textTitle);
            buttonAction = itemView.findViewById(R.id.buttonAction);
        }
    }

    public interface OnFilmClickListener {
        void onFilmClick(Film film);
    }

    public interface OnFilmActionListener {
        void onFilmAction(Film film);
    }
}
