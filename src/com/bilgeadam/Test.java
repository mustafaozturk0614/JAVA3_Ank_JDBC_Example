package com.bilgeadam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.Driver;

//main metodunda ilk önce database başarılı bir şekilde bağlanalım 
//- connection nesnesi ile sql sorgusu alsın sql sorgusuna göre update insert delete yapsın ;
//-Bir student sınıfı yaratalaım 
// create metotu yazalım create metodu dışarıdan bir student nesenesi ve 
//connection alsın alsın bize içerde işlemleri yapıp database eklesin
//Güncel student veriletiyle bir stundet oluşturalım onu metoda verelim yine 
//metoda hangi veriyi güncelleyiceğimize dair bir id verelim

//Dışarıdan girdimiz şehir isminden kaçtane verimiz var ;

public class Test {

	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/school";
		String username = "postgres";
		String password = "root";
		Connection connect = null;
		try {
			Driver.class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection(url, username, password);

			System.out.println("Bağlantı Başarılı");
//			String sql = "insert into student(name,age,city) values ('Mustafa',34,'Ankara')   ";
//			String sql2 = "delete from student where id=2";
//			execute(connect, sql2);
			Student student = new Student("Merve", 30, "Ankara");
//			create(connect, student);
//			update(connect, student, 4);
			getCityCountry(connect, "Ankara");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Başarısız bağlantı");
			e.printStackTrace();
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void execute(Connection connection, String sqlsorgu) throws SQLException {

		try {
			PreparedStatement ps = connection.prepareStatement(sqlsorgu);
			ps.executeUpdate();
			System.out.println("İşlem Başarılı");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void create(Connection connection, Student student) {
//		String sql = "insert into student (name,age,city) values('" + student.getName() + "','" + student.getAge()
//				+ "','" + student.getCity() + "')";

		String sql2 = "insert into student(name,age,city) values(?,?,?)";

		try {
			PreparedStatement pStatement = connection.prepareStatement(sql2);
			pStatement.setString(1, student.getName());
			pStatement.setInt(2, student.getAge());
			pStatement.setString(3, student.getCity());
			pStatement.executeUpdate();
			System.out.println("Veri eklem başarılı ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void update(Connection connection, Student student, int id) {

		String sql2 = "update student set name=? ,age=?, city=? where id=?";

		try {
			PreparedStatement pStatement = connection.prepareStatement(sql2);
			pStatement.setString(1, student.getName());
			pStatement.setInt(2, student.getAge());
			pStatement.setString(3, student.getCity());
			pStatement.setInt(4, id);
			int rowEffected = pStatement.executeUpdate();

			if (rowEffected > 0) {
				System.out.println("Güncelleme Başarılı");
			} else {

				System.out.println("Güncelleme Başarısız");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getCityCountry(Connection connection, String cityname) {
		String sql = "select count(city) from student where city='" + cityname + "'";
		String sql2 = "select count(city) from student where city=?";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql2);
			pStatement.setString(1, cityname);
			ResultSet rSet = pStatement.executeQuery();

			rSet.next();

			System.out.println(rSet.getInt(1));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
