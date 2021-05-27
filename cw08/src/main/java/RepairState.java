import java.io.Serializable;

public enum RepairState implements Serializable {
    WAITING,
    IN_PROGRESS,
    FINISHED,
    CANCELED
}