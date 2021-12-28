import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;


public class Program {

    static Connection conn;
    public static void main(String[] args) throws Exception 
    {
        DateFormat Tanggal = new SimpleDateFormat("dd MMMM yyyy");
        DateFormat Jam = new SimpleDateFormat("HH:mm:ss");
        Date tanggalhari = new Date();

        Scanner input = new Scanner(System.in);
        int pilihan;
        boolean balikMenu = true;
        String link = "jdbc:mysql://localhost:3306/tugas14a";

        System.out.println("================================");
        System.out.println("       Data Pegawai PTABC");
        System.out.println("================================");
        

        while (balikMenu)
        {
            System.out.println("\n=====================");
			System.out.println("Database Pegawai");
			System.out.println("=====================");
			System.out.println("1.\tLihat Data Pegawai");
			System.out.println("2.\tTambah Data Pegawai");
			System.out.println("3.\tUbah Data Pegawai");
			System.out.println("4.\tHapus Data Pegawai");
			System.out.println("5.\tCari Data Pegawai");
            System.out.println("6.\tKeluar");
				
			System.out.print("\nPilihan anda (1/2/3/4/5/6): ");
			pilihan = input.nextInt();

            Gaji gaji = new Gaji();
            TerimaGaji terimaGaji = new TerimaGaji();
            switch (pilihan)
            {
                case 1:
                    gaji.view();
                break;
                    
                case 2:
                    terimaGaji.save();
                break;

                case 3:
                    terimaGaji.update();
                break;

                case 4:
                    terimaGaji.delete();
                break;

                case 5:
                    terimaGaji.search();
                break;

                case 6:
                    balikMenu = false;
                break;

                default:
                    System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-6]");
                break;
            }
        }

        
            System.out.println("Hari/Tanggal    : "+Tanggal.format(tanggalhari));
            System.out.println("Jam             : "+Jam.format(tanggalhari)+" WIB");
            System.out.println("------------------------");
            String akhir = "terima kasih sudah bekerja";
            String penutup = akhir.replace("bekerja","datang"); //replace
            System.out.println(penutup.toUpperCase()); //uppercase
            String end = "SELESAI.";
            System.out.println(end.toLowerCase());//lowercase
            System.out.println("------------------------");
         }
}

