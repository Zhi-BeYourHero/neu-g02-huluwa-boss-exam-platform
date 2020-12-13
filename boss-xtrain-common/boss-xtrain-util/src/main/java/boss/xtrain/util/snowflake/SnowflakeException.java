package boss.xtrain.util.snowflake;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 雪花算法中的异常
 * @create 2020-07-06 11:59
 * @since 1.0
 */
public class SnowflakeException extends RuntimeException {

    public SnowflakeException(String message){
        super(message);
    }
}