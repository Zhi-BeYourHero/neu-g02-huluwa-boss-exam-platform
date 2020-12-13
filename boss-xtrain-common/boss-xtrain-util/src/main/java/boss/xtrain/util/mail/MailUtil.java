package boss.xtrain.util.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 发送邮件的工具类
 * @create 2020-07-03 15:11
 * @since 1.0
 */
@Component
public class MailUtil {

    private final static String USERNAME = "boss_exam_g02@163.com";
    private MailUtil(){
        // 工具类不能被实例化 只有静态方法
    }

    private static JavaMailSender mailSender;


    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        MailUtil.mailSender = mailSender;
    }

    /**
     * 邮件标题
     */
    private static final String TITLE = "【博思在线考试系统G02】验证邮件";

    /**
     * 发送文本文件
     * @param receiverEmail 收件人的邮箱
     * @param content 邮件内容
     */
    public static void sentTextEmail(String receiverEmail, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(USERNAME);
        message.setTo(receiverEmail);
        message.setSubject(TITLE);
        message.setText(content);
        mailSender.send(message);
    }

    /**
     * 发送文本内容，自定义标题
     * */
    public static void sentTextEmail(String receiverEmail, String content,String title){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(USERNAME);
        message.setTo(receiverEmail);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }
}
