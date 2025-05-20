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
        list.add(new Film("Komang", R.drawable.komang, "Takdir mempertemukakn Ode (Keisha Alvaro), pemuda Buton yang punya mimpi besar, dengan Ade (Aurora Ribero), perantau dari Bali. Sebagaimana dua insan yang sedang jatuh cinta, mereka percaya kelak takdir akan mempersatukan." +
                "Namun perjalanan mereka terhambat dengan kehadiran pria lain yang seiman dengan Ade, dan Ode harus ke Jakrta untuk mengejar mimpinya. Akankah Ode dan Ade dipersatuakn takdir?", "https://youtu.be/IHRScjhllsQ?si=Ef-1IIWUntvOmgRt"));
        list.add(new Film("Miracle in Cell No. 7", R.drawable.miracle, "Dodo Rozak hanya ingin menjadi ayah yang baik bagi anaknya, Kartika, sekalipun dia hanyalah pria dengan kecerdasan terbatas, bertingkah, dan berperilaku seperti anak-anak. Pada kenyataannya, justru Kartika" +
                "yang lebih sering menjaga dan merawat ayahnya. Tapi keduanya hidup bahagia. Kartika bangga pada ayahnya yang berjualan balon sehari-harinyna.", "https://youtu.be/BpvnEiG9bTE?si=LDGeUhvb0FBug9yj"));
        list.add(new Film("Plankton The Movie", R.drawable.plankton, "Cerita dimulai dengan Plankton yang berusaha menguasai dunia, namun berulang kali gagal, terutama dalam upayanya untuk menyaingi Krusty Krab yang dikelola oleh Mr. Krabs. Bisnis restoran Chum Bucket milik Plankton" +
                "bersama istrinya, Karen, tidak pernah mendapatkan sebanyak Krusty Krab. Dia selalu terjebak dalam kegagalan yang membuat hidupnya berantakan dan berpikir bahwa tidak ada jalan keluar. Kegagaln ini tidak hanya berpengaruh pada bisnisnya, tetapi juga hubungan pribadi dengan Karen, yang" +
                "sering kali menjadi pengingat atas ketidakberhasilannya.", "https://youtu.be/0uf6QUacVgs?si=Cob34MYqAztoP-5N"));
        list.add(new Film("Final Destination Bloodlines", R.drawable.final_destination, "Pada tahun 1968, Iris Campbell (Brec Bassinger) mendapatkan firasat mengerikan saat menghadiri pesta pembukaan Sky View Restaurant Tower bersama tunangannya, Paul (Max Lloyd-Jones). Ia melihat lantai" +
                "kaca pecah, kebocoran gas, dan ledakan yang membunuh semua tamu. Berkat peringatannya, banyak orang berhasil selamat. Enam puluh lima tahun kemudian, seorang pemuda bernama Stefani Reyes (Kaitlyn Santa Juana) mengalami mimpi buruk tentang kehancuran menara tersebut. Iris mengungkap bahwa" +
                "firasat itu nyata dan ia pernah mengganggu rencana Death yang ingin mengambil nyawa orang-orang di pesta tersebut. Karena itu, Death mulai memburu semua korban selamat dan keturunannya. Iris, yang telah mengumpulkan informasi penting selama bertahun-tahun, menyerahkannya kepada Stefani agar" +
                "ia bisa mencegah kematian keluarganya, meski usaha tersebut tidak mudah karena Death memiliki banyak cara untuk mengeksekusi takdir maut.", "https://youtu.be/UWMzKXsY9A4?si=rj_6kx6n8aS46E7J"));
        list.add(new Film("99 Cahaya di Langit Eropa", R.drawable.cahaya_eropa, "Film 99 Cahaya di Langit Eropa mengisahkan Hanum Salsabiela Rais, seorang jurnalis Indonesia yang meninggalkan pekerjaannya untuk mendampingi suaminya, Rangga Almahendra, melanjutkan studi doktorat di Vienna, Austria." +
                "Awalnya merasa bosan dengan kehidupannya yang monoton. Hanum memutuskan mencari pekerjaan dan bertemu Fatma Pasha, wanita Turki yang juga berjuang di negeri asing. Persahabatan mereka membuka mata Hanum tentang tantanga menjadi Muslim di Eropa, termasuk diskriminasi yang dialami Fatma karena berhijab." +
                "Fatma menjadi inspirasi Hanum, sementara Rangga juga mendapat pelajaran berharga melalui interaksi dengan teman-teman Muslim seperti Khan dan Stefan.", " "));
        list.add(new Film("Despicable Me 4", R.drawable.film_despicableme4, "Despicable Me 4 mengikuti kisah Gru yang kini hidup sebagai agen AVL bersama keluarganya. Ancaman muncul ketika Maxime Le Mal, musuh lama Gru, kabur dari penjara dan berencana mengubah manusia menjadi hibrida kecoa. Gru dan keluarganya" +
                "bersembunyi dengan identitas baru, sementara Maxime menculik Gru Jr. dan mengubahnya menjadi hibrida. Dengan bantuan keluarga dan lima Minion super, Gru berhasil menyelamatkan anaknya dan mengalahkan Maxime.", "https://youtu.be/LtNYaH61dXY?si=qT4ecXMKYxEov07P"));
        list.add(new Film("Love Revolution", R.drawable.film_loverevolution, "Cerita berpusat pada Gong Ju-young (diperankan oleh Park Ji-hoon), seorang siswa SMA yang ceria, ekspresif, dan sangat setia kawan. Ia jatuh cinta pada pandangan pertama kepada Wang Ja-rim (diperankan oleh Lee Ruby), seorang siswi" +
                "cantik dan populer yang berkepribadian dingin dan tertutup. Meskipun sikap Ja-rim cuek dan sulit didekati, Ju-young pantang menyerah dan terus berusaha menunjukkan ketulusannya dengan berbagai cara lucu dan menggemaskan.", " "));
        list.add(new Film("Final Destinaton 3", R.drawable.fd3, "Final Destination 3 menceritakan Wendy, siswi SMA yang mendapat firasat kecelakaan maut di wahana roller coaster. Setelah berhasil menyelamatkan beberapa teman, mereka mulai tewas satu per satu dalam kecelakaan aneh. " +
                "Wendy dan Kevin mencoba mencegah kematian berdasarkan petunjuk dari foto-foto, namun mereka segera menyadari bahwa takdir kematian sulit untuk dihindar.", " "));
        list.add(new Film("The Martian", R.drawable.themartian, "Mark Watney terdampar di planet Mars setelah kru pesawat meninggalkannya, mengira dia telah tewas terhantam badai. Kini, dengan pasokan minim, Mark harus berjuang untuk menjaga dirinya tetap hidup.", " "));
        list.add(new Film("Everest", R.drawable.everest, "Everest (2015) menceritakan kisah nyata sekelompok pendaki yang menghadapi badai salju mematikan saat menuruni Gunung Everest pada tahun 1996. Dipimpin oleh dua pemandu ekspedisi, Rob Hall dan Scott Fischer, para pendaki berjuang bertahan hidup di" +
                "tengah cuaca ekstrem, kekurangan oksigen, dan kondisi alam yang brutal.", "https://youtu.be/79Q2rrQlPW4?si=UHDp5XnqQb_PM20v"));
        list.add(new Film("Pengabdi Setan 2", R.drawable.setan2, "Pengabdi Setan 2: Communion melanjutkan kisah keluarga Rini yang pindah ke rumah susun setelah tragedi di film pertama. Mereka berharap bisa hidup tenang, namun teror kembali muncul saat rumah susun tersebut dihantui oleh kejadian-kejadian misterius " +
                "dan kematian yang tidak wajar. Rini mulai menyadari bahwa sekte sesat yang dulu meneror keluarganya masih mengincar mereka. Teror semakin mencekam saat rahasia kelam penghuni rumah susun dan masa lalu keluarganya terungkap.", "https://youtu.be/8LIHcd7WfWI?si=b2cCXwB2KNvs84Z0"));
        list.add(new Film("Pengepungan di Bukit Duri", R.drawable.bukitduri, "Berlatar di Jakarta tahun 2027, film ini mengikuti kisah Edwin (Morgan Oey), seorang guru keturunan Tionghoa yang ditugaskan mengajar di SMA Bukit Duri—sekolah yang dikenal dengan siswa-siswa bermasalah. Dengan misi pribadi mencari keponakannya " +
                "yang hilang, Edwin terjebak di tengah kerusuhan sosial yang dipicu oleh diskriminasi rasial dan kekerasan sistemik. Bersama rekan guru Diana (Hana Malasan), ia berjuang menyelamatkan para siswa dari ancaman brutal, termasuk dari Jefri (Omara Esteghlal), siswa yang memimpin pengepungan terhadap mereka.", " "));
        return list;
    }
}