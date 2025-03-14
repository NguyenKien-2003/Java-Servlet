package vn.edu.t3h.model;

public class ProductDTO {

    private int id;
    private String bookTitle;
    private String author;
    private int pageCount;
    private String publisher;
    private int publicationYear;
    private String genre;
    private double price;
    private double discount;
    private int stockQuantity;
    private String description;
    private String image;


    public ProductDTO() {
    }

    public ProductDTO(Integer id, String bookTitle, String author, Integer pageCount, String publisher, Integer publicationYear, String genre, Double price, Double discount, Integer stockQuantity, String description,String image) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.author = author;
        this.pageCount = pageCount;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.price = price;
        this.discount = discount;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.image = image;
    }


    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
