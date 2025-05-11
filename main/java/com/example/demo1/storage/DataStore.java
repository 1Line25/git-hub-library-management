package com.example.demo1.storage;

import com.example.demo1.models.*;
import com.example.demo1.utils.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataStore {

    // --- User ---
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try (Connection connection = DatabaseConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("id"),
                        resultSet.getString("lastName"),
                        resultSet.getString("firstName"),
                        resultSet.getString("gender"),
                        resultSet.getString("birthDate"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void addUser(User user) {
        String query = "INSERT INTO Users (id, lastName, firstName, gender, birthDate, email, address, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, user.getId());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getGender());

            // Convert LocalDate to java.sql.Date
            stmt.setDate(5, Date.valueOf(user.getBirthDate()));

            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getAddress());
            stmt.setString(8, user.getUsername());
            stmt.setString(9, user.getPassword());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // --- Author ---
    public static List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM Authors";
        try (Connection connection = DatabaseConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Author author = new Author(
                        resultSet.getInt("maTacGia"),
                        resultSet.getString("tenTacGia"),
                        resultSet.getDate("ngaySinh").toLocalDate()
                );
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static void addAuthor(Author author) {
        String query = "INSERT INTO Authors (maTacGia, tenTacGia, ngaySinh) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, author.getMaTacGia());
            stmt.setString(2, author.getTenTacGia());
            stmt.setDate(3, Date.valueOf(author.getNgaySinh()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- Book ---
    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Books";
        try (Connection connection = DatabaseConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getString("maSach"),
                        resultSet.getString("tenSach"),
                        resultSet.getString("tacGia"),
                        resultSet.getDate("ngayXuatBan").toLocalDate()
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static void addBook(Book book) {
        String query = "INSERT INTO Books (maSach, tenSach, tacGia, ngayXuatBan) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, book.getMaSach());
            stmt.setString(2, book.getTenSach());
            stmt.setString(3, book.getTacGia());
            stmt.setDate(4, Date.valueOf(book.getNgayXuatBan()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- BookTitle ---
    public static List<BookTitle> getAllBookTitles() {
        List<BookTitle> bookTitles = new ArrayList<>();
        String query = "SELECT * FROM BookTitles";
        try (Connection connection = DatabaseConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                BookTitle bookTitle = new BookTitle(
                        resultSet.getString("tenSach"),
                        resultSet.getString("tacGia"),
                        resultSet.getString("theLoai"),
                        resultSet.getString("nhaXuatBan"),
                        resultSet.getInt("namXuatBan")
                );
                bookTitles.add(bookTitle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookTitles;
    }

    public static void addBookTitle(BookTitle bookTitle) {
        String query = "INSERT INTO BookTitles (tenSach, tacGia, theLoai, nhaXuatBan, namXuatBan) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, bookTitle.getTenSach());
            stmt.setString(2, bookTitle.getTacGia());
            stmt.setString(3, bookTitle.getTheLoai());

            // Use the correct getter method: getNxb()
            stmt.setString(4, bookTitle.getNxb());

            stmt.setInt(5, bookTitle.getNamXB());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // --- Reader ---
    public static List<Reader> getAllReaders() {
        List<Reader> readers = new ArrayList<>();
        String query = "SELECT * FROM Readers";
        try (Connection connection = DatabaseConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Reader reader = new Reader(
                        resultSet.getString("maDocGia"),
                        resultSet.getString("tenDocGia"),
                        resultSet.getString("diaChi"),
                        resultSet.getString("soDienThoai")
                );
                readers.add(reader);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readers;
    }

    public static void addReader(Reader reader) {
        String query = "INSERT INTO Readers (maDocGia, tenDocGia, diaChi, soDienThoai) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, reader.getMaDocGia());
            stmt.setString(2, reader.getTenDocGia());
            stmt.setString(3, reader.getDiaChi());
            stmt.setString(4, reader.getSoDienThoai());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- LendingSlip ---
    public static List<LendingSlip> getAllLendingSlips() {
        List<LendingSlip> lendingSlips = new ArrayList<>();
        String query = "SELECT * FROM LendingSlips";
        try (Connection connection = DatabaseConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                LendingSlip slip = new LendingSlip(
                        resultSet.getInt("maPhieuMuon"),
                        resultSet.getString("maDocGia"),
                        resultSet.getString("maSach"),
                        resultSet.getInt("soLuong"),  // Add this if soLuong is in the database
                        resultSet.getDate("ngayMuon").toLocalDate(),
                        resultSet.getDate("ngayTra").toLocalDate()
                );
                lendingSlips.add(slip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lendingSlips;
    }


    public static void addLendingSlip(LendingSlip lendingSlip) {
        String query = "INSERT INTO LendingSlips (maPhieuMuon, maSach, maDocGia, ngayMuon, ngayTra) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, lendingSlip.getMaPhieuMuon());
            stmt.setString(2, lendingSlip.getSach());  // Using getSach() here
            stmt.setString(3, lendingSlip.getDocGia());  // Corrected to getDocGia()
            stmt.setDate(4, Date.valueOf(lendingSlip.getNgayMuon()));
            stmt.setDate(5, Date.valueOf(lendingSlip.getHanTra()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // --- LibraryCard ---
    public static List<LibraryCard> getAllLibraryCards() {
        List<LibraryCard> libraryCards = new ArrayList<>();
        String query = "SELECT * FROM LibraryCards";
        try (Connection connection = DatabaseConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                LibraryCard card = new LibraryCard(
                        resultSet.getString("maThe"),
                        resultSet.getString("maDocGia"),
                        resultSet.getDate("ngayCap").toLocalDate()
                );
                libraryCards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libraryCards;
    }

    public static void addLibraryCard(LibraryCard libraryCard) {
        String query = "INSERT INTO LibraryCards (maThe, maDocGia, ngayCap) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, libraryCard.getMaThe());
            stmt.setString(2, libraryCard.getMaDocGia());
            stmt.setDate(3, Date.valueOf(libraryCard.getNgayCap()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- Librarian ---
    public static List<Librarian> getAllLibrarians() {
        List<Librarian> librarians = new ArrayList<>();
        String query = "SELECT * FROM Librarians";
        try (Connection connection = DatabaseConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Librarian librarian = new Librarian(
                        resultSet.getString("maThuThu"),  // Use maThuThu for ID
                        resultSet.getString("hoTen"),
                        resultSet.getString("ngaySinh"),
                        resultSet.getString("gioiTinh"),
                        resultSet.getString("soDienThoai"),
                        resultSet.getString("email"),
                        resultSet.getString("ngayVaoLam")
                );
                librarians.add(librarian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librarians;
    }


    private static List<Librarian> librarians = new ArrayList<>();

    // Giả sử bạn đã có một danh sách Librarian
    public static boolean addLibrarian(Librarian librarian) {
        // Câu lệnh SQL để thêm một librarian vào cơ sở dữ liệu
        String query = "INSERT INTO Librarians (maThuThu, hoTen, ngaySinh, gioiTinh, soDienThoai, email, ngayVaoLam) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Đặt các giá trị cho câu lệnh SQL từ đối tượng librarian
            stmt.setString(1, librarian.getMaThuThu());  // maThuThu
            stmt.setString(2, librarian.getHoTen());  // hoTen
            stmt.setString(3, librarian.getNgaySinh());  // ngaySinh
            stmt.setString(4, librarian.getGioiTinh());  // gioiTinh
            stmt.setString(5, librarian.getSoDienThoai());  // soDienThoai
            stmt.setString(6, librarian.getEmail());  // email
            stmt.setString(7, librarian.getNgayVaoLam());  // ngayVaoLam

            // Thực thi câu lệnh SQL để thêm dữ liệu vào cơ sở dữ liệu
            int affectedRows = stmt.executeUpdate();

            // Trả về true nếu có ít nhất một dòng được thêm vào
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Trả về false nếu có lỗi xảy ra
    }


    // Phương thức kiểm tra tài khoản và mật khẩu hợp lệ
    public static boolean isValidUser(String username, String password) {
        String query = "SELECT * FROM Librarians WHERE maThuThu = ? AND soDienThoai = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Đặt giá trị cho câu lệnh SQL
            stmt.setString(1, username);  // Đặt maThuThu là username
            stmt.setString(2, password);  // Đặt soDienThoai là password

            // Thực thi câu lệnh SQL và lấy kết quả
            try (ResultSet resultSet = stmt.executeQuery()) {
                // Nếu có ít nhất một dòng dữ liệu trả về, tài khoản và mật khẩu hợp lệ
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Nếu không tìm thấy tài khoản hoặc mật khẩu không hợp lệ
    }

    private static List<User> users = new ArrayList<>();

    // Phương thức cập nhật thông tin người dùng
    public static boolean updateUserProfile(String id, String lastName, String firstName, String gender,
                                            String birthDate, String email, String address, String username,
                                            String password) {
        String query = "UPDATE Users SET lastName = ?, firstName = ?, gender = ?, birthDate = ?, email = ?, " +
                "address = ?, password = ? WHERE username = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Cập nhật thông tin người dùng
            stmt.setString(1, lastName);
            stmt.setString(2, firstName);
            stmt.setString(3, gender);
            stmt.setDate(4, Date.valueOf(birthDate));  // Chuyển String thành Date (giả sử birthDate là String kiểu 'yyyy-MM-dd')
            stmt.setString(5, email);
            stmt.setString(6, address);
            stmt.setString(7, password);
            stmt.setString(8, username);  // Tìm theo username

            // Thực thi câu lệnh SQL và kiểm tra số dòng bị ảnh hưởng
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;  // Nếu có ít nhất một dòng bị thay đổi, trả về true

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Nếu có lỗi xảy ra, trả về false
        }
    }


    // Phương thức này tìm người dùng theo username
    public static User getUserByUsername(String username) {
        String query = "SELECT * FROM Users WHERE username = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Đặt giá trị username vào câu lệnh SQL
            stmt.setString(1, username);

            // Thực hiện câu lệnh và lấy kết quả từ ResultSet
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Lấy dữ liệu từ ResultSet và tạo đối tượng User
                    String id = resultSet.getString("id");
                    String lastName = resultSet.getString("lastName");
                    String firstName = resultSet.getString("firstName");
                    String gender = resultSet.getString("gender");
                    String birthDate = resultSet.getDate("birthDate").toLocalDate().toString();  // Chuyển từ Date thành LocalDate và chuyển sang chuỗi
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    String usernameFromDb = resultSet.getString("username");
                    String password = resultSet.getString("password");

                    // Tạo đối tượng User và thiết lập thông tin
                    User user = new User(id, lastName, firstName, gender, birthDate, email, address, usernameFromDb, password);
                    return user;  // Trả về đối tượng User nếu tìm thấy
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Trả về null nếu không tìm thấy người dùng
    }


    // Các phương thức khác như addUser, getUsers, etc..
    // Giả sử danh sách tác giả được lưu trữ trong một List
    public static boolean updateAuthor(Author author) {
        String query = "UPDATE Authors SET tenTacGia = ?, ngaySinh = ? WHERE maTacGia = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Cập nhật thông tin của tác giả
            stmt.setString(1, author.getTenTacGia());
            stmt.setDate(2, Date.valueOf(author.getNgaySinh()));  // Convert LocalDate to SQL Date
            stmt.setInt(3, author.getMaTacGia());  // Sử dụng mã tác giả để xác định tác giả cần cập nhật

            // Thực thi câu lệnh SQL và kiểm tra số dòng bị ảnh hưởng
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;  // Nếu có ít nhất một dòng bị thay đổi, trả về true

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Nếu xảy ra lỗi, trả về false
        }
    }


    public static boolean deleteAuthor(int maTacGia) {
        String sql = "DELETE FROM Authors WHERE maTacGia = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maTacGia);  // Set the author's ID (maTacGia) to the SQL query

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;  // Returns true if deletion is successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Returns false if an error occurs
        }
    }

    public static boolean updateBook(Book book) {
        String sql = "UPDATE Books SET tenSach = ?, tacGia = ?, ngayXuatBan = ? WHERE maSach = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTenSach());
            stmt.setString(2, book.getTacGia());
            stmt.setDate(3, Date.valueOf(book.getNgayXuatBan()));  // Convert LocalDate to SQL Date
            stmt.setString(4, book.getMaSach());  // Assuming 'maSach' is a String

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;  // Returns true if update is successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false if update fails
        }
    }
    public static boolean deleteBook(String maSach) {
        String sql = "DELETE FROM Books WHERE maSach = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maSach);  // Assuming 'maSach' is a String

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;  // Returns true if delete is successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false if delete fails
        }
    }

    public static boolean updateBookTitle(BookTitle bookTitle) {
        String sql = "UPDATE BookTitles SET tenSach = ?, tacGia = ?, theLoai = ?, nxb = ?, namXB = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookTitle.getTenSach());
            stmt.setString(2, bookTitle.getTacGia());
            stmt.setString(3, bookTitle.getTheLoai());
            stmt.setString(4, bookTitle.getNxb());
            stmt.setInt(5, bookTitle.getNamXB());
            stmt.setInt(6, bookTitle.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteBookTitle(int id) {
        String sql = "DELETE FROM BookTitles WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteLendingSlip(int maPhieuMuon) {
        String sql = "DELETE FROM LendingSlips WHERE MaPhieuMuon = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maPhieuMuon);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteLibrarian(String maThuThu) {
        String sql = "DELETE FROM Librarians WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maThuThu);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateLibraryCard(LibraryCard libraryCard) {
        String query = "UPDATE LibraryCards SET maThe = ?, maDocGia = ?, ngayCap = ? WHERE maThe = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set values to the query
            stmt.setString(1, libraryCard.getMaThe());  // Update maThe
            stmt.setString(2, libraryCard.getMaDocGia());  // Update maDocGia
            stmt.setDate(3, Date.valueOf(libraryCard.getNgayCap()));  // Convert LocalDate to SQL Date
            stmt.setString(4, libraryCard.getMaThe());  // WHERE condition: Update the card with the specific maThe

            // Execute the update query
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;  // Return true if at least one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if there was an error or no rows were updated
    }
    public static boolean deleteLibraryCard(String maThe) {
        String query = "DELETE FROM LibraryCards WHERE maThe = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set maThe value in the SQL query
            stmt.setString(1, maThe);

            // Execute the delete query
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;  // Return true if at least one row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if there was an error or no rows were deleted
    }
    public static Reader getReader(String maDocGia) {
        String query = "SELECT * FROM Readers WHERE maDocGia = ?";  // SQL query to select reader by maDocGia
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, maDocGia);  // Set the maDocGia parameter in the query

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve data from the result set and create a Reader object using StringProperties
                    String maDocGiaValue = resultSet.getString("maDocGia");
                    String tenDocGiaValue = resultSet.getString("tenDocGia");
                    String diaChiValue = resultSet.getString("diaChi");
                    String soDienThoaiValue = resultSet.getString("soDienThoai");

                    // Create a Reader object with data retrieved from the ResultSet
                    Reader reader = new Reader(maDocGiaValue, tenDocGiaValue, diaChiValue, soDienThoaiValue);
                    return reader;  // Return the reader object if found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle any SQL exceptions
        }
        return null;  // Return null if no reader is found with the given maDocGia
    }
    // Method to update a reader in the database
    public static boolean updateReader(Reader reader) {
        String updateQuery = "UPDATE Readers SET tenDocGia = ?, diaChi = ?, soDienThoai = ? WHERE maDocGia = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(updateQuery)) {

            // Set values for the update query
            stmt.setString(1, reader.getTenDocGia());
            stmt.setString(2, reader.getDiaChi());
            stmt.setString(3, reader.getSoDienThoai());
            stmt.setString(4, reader.getMaDocGia());

            // Execute the update query
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;  // If at least one row is updated, return true
        } catch (SQLException e) {
            e.printStackTrace();  // Handle SQL exceptions
        }
        return false;  // Return false if the update fails
    }
    // Method to delete a reader from the database by maDocGia
    public static boolean deleteReader(String maDocGia) {
        String deleteQuery = "DELETE FROM Readers WHERE maDocGia = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(deleteQuery)) {

            // Set maDocGia as the parameter for the DELETE query
            stmt.setString(1, maDocGia);

            // Execute the delete query
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;  // If at least one row is deleted, return true
        } catch (SQLException e) {
            e.printStackTrace();  // Handle SQL exceptions
        }
        return false;  // Return false if the delete operation fails
    }











}




    // Các phương thức khác như thêm tác giả, lấy danh sách tác giả, etc.




