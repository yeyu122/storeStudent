package cn.tedu.storeStudent.entity;

public class Cartvo {
    private Integer cid;
    private Integer pid;
    private String image;
    private String title;
    private Integer realPrice;
    private Integer price;
    private Integer num;

    @Override
    public String toString() {
        return "Cartvo{" +
                "cid=" + cid +
                ", pid=" + pid +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", realPrice=" + realPrice +
                ", price=" + price +
                ", num=" + num +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Integer realPrice) {
        this.realPrice = realPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
