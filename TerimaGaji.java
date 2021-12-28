import java.sql.*;
import com.mysql.cj.protocol.Resultset;
import java.util.InputMismatchException;

public class TerimaGaji extends Gaji
{
    public Integer totalgaji;
    static Connection conn;
	String link = "jdbc:mysql://localhost:3306/tugas14a";  

    @Override
    public void Potongan() 
    {
        this.potongan = (30-jumlahhadir)*50000;
    }

    @Override
    public void TotalGaji() 
    {
        this.totalgaji = this.gajipokok - this.potongan;
    }

    public void TampilJabatan()
    {
        if (tampilJabatan==1)
        {
            System.out.println("Brand Manager");
        }
        else if (tampilJabatan==2)
        {
            System.out.println("Secretary");
        }
        else if (tampilJabatan==3)
        {
            System.out.println("Digital Marketing");
        }
        else if (tampilJabatan==4)
        {
            System.out.println("Administrator");
        }
        else if (tampilJabatan==5)
        {
            System.out.println("Security");
        }
    }

    public void tampilInfo()
    {
        System.out.println("\n================================");
        System.out.println("     Informasi Gaji Pegawai");
        System.out.println("================================");
        System.out.println("Nama pegawai    : "+this.namaPegawai);
        System.out.println("(Jumlah karakter: " +this.namaPegawai.length() + " Huruf)"); //lenght
        System.out.println("Nomor Pegawai   : "+this.noPegawai);
        System.out.print("Jabatan         : ");
        TampilJabatan();
        System.out.println("Gaji Pokok      : Rp. " + this.gajipokok);
        System.out.println("Jumlah kehadiran: "+this.jumlahhadir+" Hari");
        System.out.println("Potongan gaji   : Rp. "+this.potongan);
        System.out.println("Total gaji      : Rp. "+this.totalgaji);

    }

    @Override
    public void save() throws SQLException 
    {
        try 
        {
            System.out.println("Masukkan data karyawan"); 
            NamaPegawai();
            NoPegawai();
            Jabatan();
            GajiPokok();
            JumlahHariMasuk();
            Potongan();
            TotalGaji();
    
            String sql = "INSERT INTO karyawan (namapegawai, nopegawai, jabatan, jumlahharimasuk, totalgaji,gajipokok) VALUES ('"+namaPegawai+"','"+noPegawai+"','"+jabatan+"','"+jumlahhadir+"','"+totalgaji+"','"+gajipokok+"')";
            conn = DriverManager.getConnection(link,"root","");
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil input data!!");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            
            System.err.println("Error masukkan nomor jabatan dengan benar");
        }

        catch (IndexOutOfBoundsException e)
        {
            
            System.err.println("Masukkan rentang jumlah hadir 1-30");
        }

        catch (InputMismatchException e)
        {
            System.err.println("Input pilihan dengan angka saja");
        }
        
    }

    @Override
    public void delete() throws SQLException
    {
        view();
        try
        {
            System.out.println("Hapus data karyawan");
            System.out.print("Masukkan nomor pegawai yang akan dihapus : ");
            String noPegawai = input.nextLine();

            String sql = "DELETE FROM karyawan WHERE nopegawai = "+ noPegawai;

            conn = DriverManager.getConnection(link,"root","");
	        Statement statement = conn.createStatement();

            if(statement.executeUpdate(sql) > 0)
            {
	            System.out.println("Berhasil menghapus data pegawai (Nomor "+noPegawai+")");
	        }
        }

        catch(SQLException e)
        {
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }
        catch(Exception e)
        {
            System.out.println("masukan data yang benar");
        }
    }

    @Override
    public void update() throws SQLException
    {
        view();
        try
        {
            System.out.print("Masukkan nomor pegawai hendak diubah: ");
            String noPegawai = input.nextLine();

            String sql = "SELECT * FROM karyawan WHERE nopegawai = " +noPegawai;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next())
            {
                System.out.print("Nama baru ["+result.getString("namapegawai")+"]\t: ");
                String namaPegawai = input.nextLine();

                sql = "UPDATE karyawan SET namapegawai='"+namaPegawai+"' WHERE nopegawai='"+noPegawai+"'";

                if(statement.executeUpdate(sql) > 0)
                {
                    System.out.println("Berhasil memperbaharui data pegawai (Nomor "+noPegawai+")");
                }
            }
            statement.close();
        }

            catch (SQLException e) 
            {
                System.err.println("Terjadi kesalahan dalam mengedit data");
                System.err.println(e.getMessage());
            }
        
    }
        
    

    @Override
    public void search() throws SQLException
    {
        System.out.print("Masukkan Nama Pegawai yang ingin dilihat : ");    
		String keyword = input.nextLine();
		
		String sql = "SELECT * FROM karyawan WHERE namapegawai LIKE '%"+keyword+"%'";
		conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql); 

        while (result.next())
        {
            System.out.print("\nNo pegawai\t\t: ");
            System.out.print(result.getString("nopegawai"));
            System.out.print("\nNama pegawai\t\t: ");
            System.out.print(result.getString("namapegawai"));
            System.out.print("\nJabatan\t\t\t: ");
            System.out.print(result.getString("jabatan"));
            System.out.print("\nKehadiran\t\t: ");
            System.out.print(result.getInt("jumlahharimasuk"));
            System.out.print("\nTotal gaji\t\t: ");
            System.out.println(result.getInt("totalgaji"));
            System.out.print("\nTotal gajipokok\t\t: ");
            System.out.println(result.getInt("gajipokok"));
        }
        
    }
    
}