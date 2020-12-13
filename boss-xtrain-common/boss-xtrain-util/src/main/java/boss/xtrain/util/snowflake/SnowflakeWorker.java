package boss.xtrain.util.snowflake;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 雪花算法工具类
 * @create 2020-07-03 20:36
 * @since 1.0
 */

@Slf4j
public class SnowflakeWorker {


    //下面两个每个5位, 加起来就是10位的工作机器id
    /**
     * 工作ID
     */
    private long workerId;
    /**
     * 数据ID
     */
    private long dataCenterId;
    /**
     * 12位的序列号
     */
    private long sequence;

    /**
     * 初始时间戳
     */
    private static final long INIT_TIMESTAMP = 1288834974657L;

    /**
     * 定义workerId的长度最多为5位
     */
    private static final long WORKER_ID_BITS = 5L;

    /**
     * 定义dataCenterId的长度最多为5位
     */
    private static final long DATA_CENTER_ID_BITS = 5L;

    /**
     * 获得最多有几个workerId
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 获得最多有几个workerId
     */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    /**
     * 序列号ID长度
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * 序列号最大值
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * 工作id需要左移的位数,12位
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 数据ID需要左移的位数, 12+5=17位
     */
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 时间戳需要左移位数, 12+5+5=22位
     */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    /**
     * 上次时间戳，初始值为负数
     */
    private long lastTimestamp = -1L;

    public SnowflakeWorker(long workerId, long dataCenterId, long sequence){
        // sanity check for workerId
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",MAX_WORKER_ID));
        }
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",MAX_DATA_CENTER_ID));
        }
        log.info("worker starting. timestamp left shift " + TIMESTAMP_LEFT_SHIFT +
                        ", dataCenter id bits " + DATA_CENTER_ID_BITS +
                        ", worker id bits "+ WORKER_ID_BITS +
                        ", sequence bits " + SEQUENCE_BITS +
                        ", workerId " + workerId);

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
    }

    /**
     * 获取WorkerId
     * @return WorkerId
     */
    public long getWorkerId(){
        return workerId;
    }

    /**
     * 获取dataCenterId
     * @return dataCenterId
     */
    public long getDataCenterId(){
        return dataCenterId;
    }

    /**
     * 获取当前的时间戳
     * @return 当前时间戳
     */
    public long getTimestamp(){
        return System.currentTimeMillis();
    }

    /**
     * 生成下一个ID
     * @return 新的ID
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //获取当前时间戳如果小于上次时间戳，则表示时间戳获取出现异常
        if (timestamp < lastTimestamp) {
            log.error("clock is moving backwards.  Rejecting requests until " + lastTimestamp);
            throw new SnowflakeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        //获取当前时间戳如果等于上次时间戳（同一毫秒内），则在序列号加一；否则序列号赋值为0，从0开始。
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        //将上次时间戳值刷新
        lastTimestamp = timestamp;


         // 返回结果：
         // (timestamp - twepoch) << timestampLeftShift) 表示将时间戳减去初始时间戳，再左移相应位数
         // (datacenterId << datacenterIdShift) 表示将数据id左移相应位数
         // (workerId << workerIdShift) 表示将工作id左移相应位数
         // | 是按位或运算符，例如：x | y，只有当x，y都为0的时候结果才为0，其它情况结果都为1。
         // 因为个部分只有相应位上的值有意义，其它位上都是0，所以将各部分的值进行 | 运算就能得到最终拼接好的id

        return ((timestamp - INIT_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT) |
                (dataCenterId << DATA_CENTER_ID_SHIFT) |
                (workerId << WORKER_ID_SHIFT) |
                sequence;
    }

    /**
     * 获取时间戳 并与上次时间戳比较 会一直获取到比上一次时间戳更晚的时间戳
     * @param lastTimestamp 上次的时间戳
     * @return 新的时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取系统时间戳
     * @return 系统时间戳
     */
    private long timeGen(){
        return System.currentTimeMillis();
    }

}