package boss.xtrain.domain;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-14
 * @since
 */
public class CapchaEntity {

    private String uuid;
    private String img;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "CapchaEntity{" +
                "uuid='" + uuid + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
