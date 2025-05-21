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
                    intent.putExtra("desc", film.getDescription_id());
                    intent.putExtra("desc", film.getDescription_en());
                    intent.putExtra("desc", film.getDescription_de());
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
        list.add(new Film("Komang", R.drawable.komang, "Takdir mempertemukakn Ode (Keisha Alvaro), pemuda Buton yang punya mimpi besar, dengan Ade (Aurora Ribero), perantau dari Bali. Sebagaimana dua insan yang sedang jatuh cinta, mereka percaya kelak takdir akan mempersatukan." +
                "Namun perjalanan mereka terhambat dengan kehadiran pria lain yang seiman dengan Ade, dan Ode harus ke Jakrta untuk mengejar mimpinya. Akankah Ode dan Ade dipersatuakn takdir?", "Fate brings together Ode (Keisha Alvaro), a young man from Buton with big dreams, and Ade " +
                "(Aurora Ribero), a wanderer from Bali. Like two souls in love, they believe that fate will unite them someday. However, their journey is obstructed by the presence of another man who shares Ade’s faith, and Ode must go to Jakarta to pursue his dreams. Will fate bring Ode and Ade together?",
                "Das Schicksal bringt Ode (Keisha Alvaro), einen jungen Mann aus Buton mit großen Träumen, und Ade (Aurora Ribero), eine Auswanderin aus Bali, zusammen. Wie zwei Liebende glauben sie daran, dass das Schicksal sie eines Tages vereinen wird. Doch ihre Reise wird durch die " +
                        "Anwesenheit eines anderen Mannes, der denselben Glauben wie Ade teilt, erschwert, und Ode muss nach Jakarta gehen, um seine Träume zu verfolgen. Wird das Schicksal Ode und Ade zusammenführen?", "https://youtu.be/BpvnEiG9bTE?si=ROZGbrjOJ4rYz_YB"));

        list.add(new Film("Miracle in Cell No. 7", R.drawable.miracle, "Dodo Rozak hanya ingin menjadi ayah yang baik bagi anaknya, Kartika, sekalipun dia hanyalah pria dengan kecerdasan terbatas, bertingkah, dan berperilaku seperti anak-anak. Pada kenyataannya, justru Kartika" +
                "yang lebih sering menjaga dan merawat ayahnya. Tapi keduanya hidup bahagia. Kartika bangga pada ayahnya yang berjualan balon sehari-harinyna.", "Dodo Rozak only wants to be a good father to his daughter, Kartika, even though he is just a man with limited intelligence, acting " +
                "and behaving like a child. In reality, it is Kartika who often takes care of and looks after her father. But they both live happily. Kartika is proud of her father who sells balloons every day.", "Dodo Rozak möchte nur ein guter Vater für seine Tochter Kartika sein, obwohl er ein " +
                "Mann mit begrenzter Intelligenz ist, der sich wie ein Kind verhält und benimmt. In Wirklichkeit ist es Kartika, die sich oft um ihren Vater kümmert und ihn betreut. Doch beide leben glücklich. Kartika ist stolz auf ihren Vater, der täglich Luftballons verkauft.", " "));

        list.add(new Film("Plankton The Movie", R.drawable.plankton, "Plankton terus berusaha menguasai dunia dan mengalahkan Krusty Krab milik Mr. Krabs, tapi selalu gagal. Bisnis restoran Chum Bucket miliknya bersama istrinya, Karen, tak pernah sesukses Krusty Krab. Kegagalan ini membuat hidup dan hubungannya dengan Karen menjadi sulit.",
                "Plankton terus berusaha menguasai dunia dan mengalahkan Krusty Krab milik Mr. Krabs, tapi selalu gagal. Bisnis restoran Chum Bucket miliknya bersama istrinya, Karen, tak pernah sesukses Krusty Krab. Kegagalan ini membuat hidup dan hubungannya dengan Karen menjadi sulit.", "Plankton versucht ständig, die Welt zu erobern " +
                "und Mr. Krabs’ Krusty Krab zu besiegen, doch er scheitert immer wieder. Sein Chum Bucket-Restaurantgeschäft mit seiner Frau Karen erreicht nie den Erfolg von Krusty Krab. Diese Misserfolge machen sein Leben und seine Beziehung zu Karen schwierig.", " "));

        list.add(new Film("Final Destination Bloodlines", R.drawable.final_destination, "Pada 1968, Iris Campbell mendapat firasat kebakaran dan ledakan di pesta pembukaan Sky View Tower yang menyelamatkan banyak orang. 65 tahun kemudian, Stefani mengalami mimpi buruk tentang menara itu. Death mulai memburu para korban selamat dan keturunannya. " +
                "Iris memberikan informasi penting kepada Stefani agar dapat mencegah kematian keluarganya.", "In 1968, Iris Campbell had a vision of a fire and explosion at the Sky View Tower opening party that saved many lives. Sixty-five years later, Stefani has nightmares about the tower. Death starts hunting the survivors and their descendants. Iris " +
                "gives Stefani crucial information to help prevent her family’s death.", "1968 hatte Iris Campbell eine Vision von Feuer und Explosion auf der Eröffnungsparty des Sky View Tower, die viele Leben rettete. Fünfundsechzig Jahre später hat Stefani Albträume über den Turm. Der Tod beginnt, die Überlebenden und ihre Nachkommen zu jagen. Iris gibt Stefani " +
                "wichtige Informationen, um den Tod ihrer Familie zu verhindern.", "https://youtu.be/UWMzKXsY9A4?si=gGgP3EuOrM8oB66m"));

        list.add(new Film("99 Cahaya di Langit Eropa", R.drawable.cahaya_eropa, "Hanum, seorang jurnalis Indonesia, mengikuti suaminya ke Vienna untuk studi doktor. Dia merasa bosan, tapi kemudian berteman dengan Fatma, wanita Turki yang menghadapi diskriminasi karena berhijab. Persahabatan ini membuka pandangan Hanum tentang tantangan menjadi Muslim di Eropa, " +
                "sementara Rangga belajar banyak dari teman-teman Muslimnya.", "Hanum, an Indonesian journalist, moves to Vienna with her husband for his doctoral studies. Feeling bored, she befriends Fatma, a Turkish woman facing discrimination for wearing a hijab. Their friendship opens Hanum’s eyes to the challenges of being Muslim in Europe, while Rangga learns valuable " +
                "lessons from his Muslim friends.", "Hanum, eine indonesische Journalistin, zieht mit ihrem Mann nach Wien, wo er promoviert. Sie fühlt sich gelangweilt, freundet sich aber mit Fatma an, einer türkischen Frau, die wegen ihres Kopftuchs diskriminiert wird. Diese Freundschaft öffnet Hanums Augen für die Herausforderungen als Muslimin in Europa, während Rangga viel von seinen muslimischen Freunden lernt",
                "https://youtu.be/OUPQ4kMD620?si=5B79tpjmPr9lAQj7"));

        list.add(new Film("Despicable Me 4", R.drawable.film_despicableme4, "Despicable Me 4 mengikuti kisah Gru yang kini hidup sebagai agen AVL bersama keluarganya. Ancaman muncul ketika Maxime Le Mal, musuh lama Gru, kabur dari penjara dan berencana mengubah manusia menjadi hibrida kecoa. Gru dan keluarganya" +
                "bersembunyi dengan identitas baru, sementara Maxime menculik Gru Jr. dan mengubahnya menjadi hibrida. Dengan bantuan keluarga dan lima Minion super, Gru berhasil menyelamatkan anaknya dan mengalahkan Maxime.", "Despicable Me 4 follows Gru, who now lives as an AVL agent with his family. Trouble arises when Maxime Le Mal, Gru’s old enemy, escapes from prison and plans to turn humans " +
                "into cockroach hybrids. Gru and his family go into hiding with new identities, while Maxime kidnaps Gru Jr. and transforms him into a hybrid. With the help of his family and five super Minions, Gru manages to save his son and defeat Maxime.", "Ich – Einfach unverbesserlich 4 erzählt die Geschichte von Gru, der jetzt als AVL-Agent mit seiner Familie lebt. Ärger entsteht, als Maxime Le Mal, " +
                "Grus alter Feind, aus dem Gefängnis entkommt und plant, Menschen in Kakerlaken-Hybriden zu verwandeln. Gru und seine Familie verstecken sich unter neuen Identitäten, während Maxime Gru Jr. entführt und ihn in einen Hybrid verwandelt. Mit Hilfe seiner Familie und fünf Super-Minions gelingt es Gru, seinen Sohn zu retten und Maxime zu besiegen.", " "));

        list.add(new Film("Love Revolution", R.drawable.film_loverevolution, "Cerita berpusat pada Gong Ju-young (diperankan oleh Park Ji-hoon), seorang siswa SMA yang ceria, ekspresif, dan sangat setia kawan. Ia jatuh cinta pada pandangan pertama kepada Wang Ja-rim (diperankan oleh Lee Ruby), seorang siswi" +
                "cantik dan populer yang berkepribadian dingin dan tertutup. Meskipun sikap Ja-rim cuek dan sulit didekati, Ju-young pantang menyerah dan terus berusaha menunjukkan ketulusannya dengan berbagai cara lucu dan menggemaskan.", "The story centers on Gong Ju-young (played by Park Ji-hoon), a cheerful, " +
                "expressive high school student who is deeply loyal to his friends. He falls in love at first sight with Wang Ja-rim (played by Lee Ruby), a beautiful and popular girl with a cold and reserved personality. Despite Ja-rim’s aloof attitude and difficulty to approach, Ju-young doesn’t give up and keeps showing his " +
                "sincerity through funny and adorable efforts.", "Die Geschichte dreht sich um Gong Ju-young (gespielt von Park Ji-hoon), einen fröhlichen, ausdrucksstarken Oberschüler, der sehr loyal gegenüber seinen Freunden ist. Er verliebt sich auf den ersten Blick in Wang Ja-rim (gespielt von Lee Ruby), ein schönes und beliebtes Mädchen mit kühler und " +
                "verschlossener Persönlichkeit. Trotz Ja-rims distanziertem Verhalten gibt Ju-young nicht auf und versucht weiterhin auf süße und lustige Weise, ihr seine Aufrichtigkeit zu zeigen.", " "));

        list.add(new Film("Final Destinaton 3", R.drawable.fd3, "Final Destination 3 menceritakan Wendy, siswi SMA yang mendapat firasat kecelakaan maut di wahana roller coaster. Setelah berhasil menyelamatkan beberapa teman, mereka mulai tewas satu per satu dalam kecelakaan aneh. " +
                "Wendy dan Kevin mencoba mencegah kematian berdasarkan petunjuk dari foto-foto, namun mereka segera menyadari bahwa takdir kematian sulit untuk dihindar.", "Final Destination 3 follows Wendy, a high school student who has a premonition of a deadly roller coaster accident. After saving some of her friends, they begin dying one by one in strange " +
                "accidents. Wendy and Kevin try to stop the deaths using clues from photographs, but soon realize that death's design is hard to escape.", "Final Destination 3 erzählt die Geschichte von Wendy, einer Oberschülerin, die eine Vorahnung eines tödlichen Achterbahnunfalls hat. Nachdem sie einige ihrer Freunde rettet, sterben diese nacheinander bei seltsamen Unfällen. Wendy und " +
                "Kevin versuchen mithilfe von Hinweisen aus Fotos, den Tod zu verhindern, doch sie erkennen bald, dass dem Schicksal des Todes kaum zu entkommen ist.","https://youtu.be/ectjqGg92M8?si=B9xPKRkpaikaTA-Q"));

        list.add(new Film("The Martian", R.drawable.themartian, "Mark Watney terdampar di planet Mars setelah kru pesawat meninggalkannya, mengira dia telah tewas terhantam badai. Kini, dengan pasokan minim, Mark harus berjuang untuk menjaga dirinya tetap hidup.", "Mark Watney is stranded on Mars after his crew leaves him behind, believing he died in a storm. Now, with limited " +
                "supplies, Mark must fight to stay alive.", "Mark Watney ist auf dem Mars gestrandet, nachdem seine Crew ihn zurückgelassen hat, in dem Glauben, er sei in einem Sturm ums Leben gekommen. Nun muss er mit begrenzten Vorräten ums Überleben kämpfen.", "https://youtu.be/ej3ioOneTy8?si=-ETSKwIjRzaPPQJh"));

        list.add(new Film("Everest", R.drawable.everest, "Everest (2015) menceritakan kisah nyata sekelompok pendaki yang menghadapi badai salju mematikan saat menuruni Gunung Everest pada tahun 1996. Dipimpin oleh dua pemandu ekspedisi, Rob Hall dan Scott Fischer, para pendaki berjuang bertahan hidup di" +
                "tengah cuaca ekstrem, kekurangan oksigen, dan kondisi alam yang brutal.", "Everest (2015) tells the true story of a group of climbers who face a deadly snowstorm while descending Mount Everest in 1996. Led by expedition guides Rob Hall and Scott Fischer, the climbers struggle to survive against extreme weather, lack of oxygen, and harsh natural conditions.",
                "Everest (2015) erzählt die wahre Geschichte einer Gruppe von Bergsteigern, die 1996 beim Abstieg vom Mount Everest in einen tödlichen Schneesturm geraten. Unter der Leitung der Expeditionsführer Rob Hall und Scott Fischer kämpfen die Bergsteiger ums Überleben gegen extremes Wetter, Sauerstoffmangel und brutale Naturbedingungen.", " "));

        list.add(new Film("Pengabdi Setan 2", R.drawable.setan2, "Rini dan keluarganya pindah ke rumah susun setelah tragedi sebelumnya, berharap hidup tenang. Namun teror kembali saat kejadian misterius dan kematian tak wajar terjadi. Rini menyadari sekte sesat masih memburu mereka, dan rahasia kelam mulai terungkap", "After the previous tragedy, Rini and her family move into an " +
                "apartment building, hoping for a peaceful life. But terror returns as mysterious events and unnatural deaths occur. Rini realizes the cult is still after them, and dark secrets begin to surface.", "Nach der früheren Tragödie zieht Rini mit ihrer Familie in ein Wohnhaus, in der Hoffnung auf ein ruhiges Leben. Doch der Horror kehrt zurück, als mysteriöse Ereignisse und unnatürliche Todesfälle " +
                "geschehen. Rini erkennt, dass der Kult sie noch immer verfolgt und dunkle Geheimnisse ans Licht kommen.", " "));

        list.add(new Film("Pengepungan di Bukit Duri", R.drawable.bukitduri, "Di Jakarta tahun 2027, guru keturunan Tionghoa bernama Edwin mengajar di SMA bermasalah sambil mencari keponakannya yang hilang. Saat kerusuhan rasial meletus, ia dan guru lain berjuang melindungi siswa dari kekerasan, termasuk dari Jefri, siswa yang memimpin pengepungan sekolah.", "" +
                "Set in Jakarta, 2027, the film follows Edwin, a Chinese-Indonesian teacher assigned to a troubled high school while secretly searching for his missing nephew. When racial riots erupt, he and fellow teacher Diana fight to protect their students from violence—especially from Jefri, a student leading the siege against them.", "Im Jakarta des Jahres 2027 wird Edwin, ein chinesisch-indonesischer Lehrer, " +
                "an eine Problemschule versetzt, während er nach seinem vermissten Neffen sucht. Als rassistische Unruhen ausbrechen, kämpft er gemeinsam mit Lehrerin Diana darum, die Schüler vor der Gewalt zu schützen – insbesondere vor Jefri, einem Schüler, der die Belagerung anführt.", "https://youtu.be/OBbE4wK47ts?si=xV_UMUo4wpWxUtFB"));
        return list;
    }
}