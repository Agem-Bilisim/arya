package tr.com.agem.arya.interpreter.zkoss;

public abstract class BaseNavObj {

    private BaseNavObj previous;

    private Long pkId = null;

    public abstract String getUrl();

    public BaseNavObj() {
    }

    public BaseNavObj getPrevious() {
	return previous;
    }

    public void setPrevious(BaseNavObj previous) {
	this.previous = previous;
    }

    public Long getPkId() {
	return pkId;
    }

    public void setPkId(Long pkId) {
	this.pkId = pkId;
    }

}
