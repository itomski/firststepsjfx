package de.lubowiecki.firststepsjfx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// CRUD
public class BookRepository {

    private static final String TABLE = "books";

    public BookRepository() throws SQLException {
        createTable();
    }

    public List<Book> find() throws SQLException {

        final String sql = "SELECT * FROM " + TABLE;

        List<Book> books = new ArrayList<>();

        try(Connection con = DBUtils.getConnection();
            Statement stmt = con.createStatement()) {

            ResultSet results = stmt.executeQuery(sql);

            while(results.next()) {
                books.add(populate(results));
            }
        }

        return books;
    }

    public Optional<Book> find(int id) {
        // TODO: implementieren
        throw new UnsupportedOperationException("Noch nicht implementiert!");
    }

    public boolean save(Book book) throws SQLException {
        if(book.getId() > 0) {
            return update(book);
        }
        else {
            return insert(book);
        }
    }

    private boolean insert(Book book) throws SQLException {

        final String sql = "INSERT INTO " + TABLE + " (id, title, description, isbn, publisher, author) " +
                            "VALUES(NULL, ?, ?, ?, ?, ?)";

        try(Connection con = DBUtils.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getDescription());
            stmt.setString(3, book.getIsbn());
            stmt.setString(4, book.getPublisher());
            stmt.setString(5, book.getAuthor());
            return stmt.executeUpdate() > 0; // Führt das vorbereitete Statement aus
        }
    }

    private boolean update(Book book) {
        // TODO: implementieren
        throw new UnsupportedOperationException("Noch nicht implementiert!");
    }

    public boolean delete(Book book) {
        return delete(book.getId());
    }

    public boolean delete(int id) {
        // TODO: implementieren
        throw new UnsupportedOperationException("Noch nicht implementiert!");
    }

    private Book populate(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setDescription(rs.getString("description"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublisher(rs.getString("publisher"));
        book.setAuthor(rs.getString("author"));
        return book;
    }

    private void createTable() throws SQLException {

        final String sql = "CREATE TABLE IF NOT EXISTS " + TABLE + " (" +
                "id INTEGER PRIMARY KEY, " +
                "title TEXT NOT NULL, " +
                "description TEXT, " +
                "isbn TEXT NOT NULL, " +
                "publisher TEXT NOT NULL, " +
                "author TEXT NOT NULL)";

        try(Connection con = DBUtils.getConnection();
            Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        }
    }
}