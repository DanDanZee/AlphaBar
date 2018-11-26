package yalantis.com.sidemenu.sample;

public class MenuList {
    private String name;
    private int imageId;
    private String whereId;
    private String content;
    private String element;

    public MenuList(String name, int imageId, String whereId,String content,String element) {
        this.name = name;
        this.imageId = imageId;
        this.whereId = whereId;
        this.content = content;
        this.element = element;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getWhereId() {
        return whereId;
    }
    public String getContent() {return content;}
    public String getElement() {return element;}
}
