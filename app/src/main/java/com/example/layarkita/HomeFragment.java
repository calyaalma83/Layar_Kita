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
                                .setMessage(getString(R.string.tersedia))
                                .setPositiveButton("OK", null)
                                .show();
                    } else {
                        WatchListHelper.addToWatchList(film);
                        builder.setTitle(getString(R.string.success))
                                .setMessage(getString(R.string.tambahkan))
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }

        );

        recyclerFilm.setAdapter(filmAdapter);
    }

    private List<Film> getDummyFilmData(){
        List<Film> list = new ArrayList<>();
        list.add(new Film("Komang", R.drawable.komang, "Fate brings together Ode (Keisha Alvaro), a young man from Buton with big dreams, and Ade (Aurora Ribero), a wanderer from Bali. Like two souls in love, they believe that fate will unite them someday. However, their journey is obstructed by the presence of another man who shares Ade’s faith, and Ode must go to Jakarta to pursue his dreams. Will fate bring Ode and Ade together?", "https://youtu.be/BpvnEiG9bTE?si=ZvZ13IJdaAqXm6zF"));
        list.add(new Film("Miracle in Cell No. 7", R.drawable.miracle, "Dodo Rozak only wants to be a good father to his daughter, Kartika, even though he is just a man with limited intelligence, acting and behaving like a child. In reality, it is Kartika who often takes care of and looks after her father. But they both live happily. Kartika is proud of her father who sells balloons every day.", "https://youtu.be/0uf6QUacVgs?si=RAgsmSF6am5Y1bRP"));
        list.add(new Film("Plankton The Movie", R.drawable.plankton, "Plankton keeps trying to take over the world and beat Mr. Krabs’ Krusty Krab but always fails. His Chum Bucket restaurant business with his wife, Karen, never matches Krusty Krab’s success. These failures make his life and relationship with Karen difficult.", "https://youtu.be/IHRScjhllsQ?si=PFI2m3xFgN_OJN1n"));
        list.add(new Film("Final Destination Bloodlines", R.drawable.final_destination, "In 1968, Iris Campbell had a vision of a fire and explosion at the Sky View Tower opening party that saved many lives. Sixty-five years later, Stefani has nightmares about the tower. Death starts hunting the survivors and their descendants. Iris gives Stefani crucial information to help prevent her family’s death.", "https://youtu.be/UWMzKXsY9A4?si=BnRPV-iP4nuLbIaI"));
        list.add(new Film("99 Cahaya di Langit Eropa", R.drawable.cahaya_eropa, "Hanum, an Indonesian journalist, moves to Vienna with her husband for his doctoral studies. Feeling bored, she befriends Fatma, a Turkish woman facing discrimination for wearing a hijab. Their friendship opens Hanum’s eyes to the challenges of being Muslim in Europe, while Rangga learns valuable lessons from his Muslim friends.", "https://youtu.be/OUPQ4kMD620?si=9uK0ttTH7s98xp58"));
        list.add(new Film("Despicable Me 4", R.drawable.film_despicableme4, "Despicable Me 4 follows Gru, who now lives as an AVL agent with his family. Trouble arises when Maxime Le Mal, Gru’s old enemy, escapes from prison and plans to turn humans into cockroach hybrids. Gru and his family go into hiding with new identities, while Maxime kidnaps Gru Jr. and transforms him into a hybrid. With the help of his family and five super Minions, Gru manages to save his son and defeat Maxime.", "https://youtu.be/qQlr9-rF32A?si=o9ApyFD4TTgQV_ml"));
        list.add(new Film("Love Revolution", R.drawable.film_loverevolution, "The story centers on Gong Ju-young (played by Park Ji-hoon), a cheerful, expressive high school student who is deeply loyal to his friends. He falls in love at first sight with Wang Ja-rim (played by Lee Ruby), a beautiful and popular girl with a cold and reserved personality. Despite Ja-rim’s aloof attitude and difficulty to approach, Ju-young doesn’t give up and keeps showing his sincerity through funny and adorable efforts.", " "));
        list.add(new Film("Final Destinaton 3", R.drawable.fd3, "Final Destination 3 follows Wendy, a high school student who has a premonition of a deadly roller coaster accident. After saving some of her friends, they begin dying one by one in strange accidents. Wendy and Kevin try to stop the deaths using clues from photographs, but soon realize that death's design is hard to escape.","https://youtu.be/ectjqGg92M8?si=B9xPKRkpaikaTA-Q"));
        list.add(new Film("The Martian", R.drawable.themartian, "Mark Watney is stranded on Mars after his crew leaves him behind, believing he died in a storm. Now, with limited supplies, Mark must fight to stay alive.", "https://youtu.be/ej3ioOneTy8?si=-ETSKwIjRzaPPQJh"));
        list.add(new Film("Everest", R.drawable.everest, "Everest (2015) tells the true story of a group of climbers who face a deadly snowstorm while descending Mount Everest in 1996. Led by expedition guides Rob Hall and Scott Fischer, the climbers struggle to survive against extreme weather, lack of oxygen, and harsh natural conditions.", " "));
        list.add(new Film("Pengabdi Setan 2", R.drawable.setan2, "After the previous tragedy, Rini and her family move into an apartment building, hoping for a peaceful life. But terror returns as mysterious events and unnatural deaths occur. Rini realizes the cult is still after them, and dark secrets begin to surface.", " "));
        list.add(new Film("Pengepungan di Bukit Duri", R.drawable.bukitduri, "Set in Jakarta, 2027, the film follows Edwin, a Chinese-Indonesian teacher assigned to a troubled high school while secretly searching for his missing nephew. When racial riots erupt, he and fellow teacher Diana fight to protect their students from violence—especially from Jefri, a student leading the siege against them.", "https://youtu.be/OBbE4wK47ts?si=xV_UMUo4wpWxUtFB"));
        return list;
    }
}