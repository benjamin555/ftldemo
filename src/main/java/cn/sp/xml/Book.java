package cn.sp.xml;

public class Book {
    public String title;
    public double price;
    public String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "图书ISBN为：" + id + "   书名为：" + title + "    价格为：" + price;
    }
}
