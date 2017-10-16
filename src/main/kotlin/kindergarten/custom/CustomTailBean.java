package kindergarten.custom;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.beetl.sql.core.TailBean;

import java.util.Map;

public class CustomTailBean extends TailBean {

    @JsonAnyGetter
    public Map<String, Object> getTails() {
        return super.getTails();
    }
}