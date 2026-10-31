import java.util.Arrays;

public class Tugas2Sorting {

    // 1: mengurutkan angka dari terbesar ke terkecil menggunakan Merge Sort
    public static void mergeSort(int[] data) {
        // Jika cuma ada 0 atau 1 angka, tidak perlu diurutkan
        if (data.length <= 1) {
            return;
        }

        // Menentukan titik tengah dan membagi data menjadi kiri dan kanan
        int tengah = data.length / 2;
        int[] kiri = Arrays.copyOfRange(data, 0, tengah);
        int[] kanan = Arrays.copyOfRange(data, tengah, data.length);

        // Mengurutkan masing-masing bagian dengan memanggil method yang sama
        mergeSort(kiri);
        mergeSort(kanan);

        int i = 0, j = 0, k = 0;

        // Membandingkan data kiri dan kanan, angka besar dimasukkan lebih dulu
        while (i < kiri.length && j < kanan.length) {
            if (kiri[i] >= kanan[j]) {
                data[k++] = kiri[i++];
            } else {
                data[k++] = kanan[j++];
            }
        }

        // Memasukkan sisa angka jika salah satu bagian sudah habis
        while (i < kiri.length) {
            data[k++] = kiri[i++];
        }
        while (j < kanan.length) {
            data[k++] = kanan[j++];
        }
    }

    // 2: mengurutkan angka dari terbesar ke terkecil menggunakan Counting Sort
    public static void countingSort(int[] data) {
        if (data.length <= 1) {
            return;
        }

        // Mencari angka terkecil dan terbesar untuk menentukan rentang hitungan
        int nilaiMin = data[0];
        int nilaiMax = data[0];
        for (int angka : data) {
            if (angka < nilaiMin) nilaiMin = angka;
            if (angka > nilaiMax) nilaiMax = angka;
        }

        // Array jumlah menyimpan frekuensi, contohnya angka 12 muncul 2 kali
        int[] jumlah = new int[nilaiMax - nilaiMin + 1];
        for (int angka : data) {
            jumlah[angka - nilaiMin]++;
        }

        // Menulis kembali angka dari yang terbesar sampai yang terkecil
        int posisi = 0;
        for (int i = jumlah.length - 1; i >= 0; i--) {
            while (jumlah[i] > 0) {
                data[posisi++] = i + nilaiMin;
                jumlah[i]--;
            }
        }
    }

    public static void main(String[] args) {
        // Memakai 10 angka, sesuai syarat minimal 8 elemen pada soal
        int[] dataAwal = {34, 12, 56, 7, 89, 23, 45, 12, 67, 3};

        // Data disalin supaya kedua algoritma mengurutkan angka awal yang sama
        int[] dataMerge = dataAwal.clone();
        int[] dataCounting = dataAwal.clone();

        // Menjalankan kedua algoritma
        mergeSort(dataMerge);
        countingSort(dataCounting);

        // Menampilkan data sebelum dan sesudah pengurutan
        System.out.println("Data awal      : " + Arrays.toString(dataAwal));
        System.out.println("Merge Sort     : " + Arrays.toString(dataMerge));
        System.out.println("Counting Sort  : " + Arrays.toString(dataCounting));
    }
}