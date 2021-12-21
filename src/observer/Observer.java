package observer;

import java.util.List;

public interface Observer {
    public void notify(List<Observable> observableList);
}
