package main;

import java.io.Serial;
import java.io.Serializable;

public class CiagPomiarow implements Serializable {

    @Serial
    private static final long serialVersionUID = 5118171961401777645L;
    private Pomiar[] pomiary;

    public CiagPomiarow(Pomiar[] pomiary) {
        this.pomiary = pomiary;
    }

    @Override
    public String toString() {
        String s = "";
        for(Pomiar p : pomiary) {
            s += p + "\n";
        }
        return s;
    }
}
