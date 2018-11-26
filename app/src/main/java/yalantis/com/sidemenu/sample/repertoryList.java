package yalantis.com.sidemenu.sample;

/**
 * Created by 曾艺涵 on 2018/4/25.
 */

public class repertoryList {
    private String name;
    private int imageId;
    private String whereId;
    private String num;


    public repertoryList(String name, int imageId, String whereId,String num) {
        this.name = name;
        this.imageId = imageId;
        this.whereId = whereId;
        this.num = num;
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
    public String getSale() {return num;}

}

