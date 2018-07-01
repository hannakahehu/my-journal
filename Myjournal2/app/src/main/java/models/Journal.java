package models;

public class Journal {
    private int id;
    private String title;
    private String content;
    private String dateTime;

    public Journal(int id,String title,String content,String dateTime) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getTitle() {
        return title;
    }
}
