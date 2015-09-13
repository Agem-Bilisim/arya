package tr.com.agem.arya.interpreter.components;

/**
 * Created by volkan on 09.09.2015.
 */
public class AryaMain {

    AryaWindow aryaWindow;
    AryaNavBar aryaNavBar;

    public AryaMain(AryaWindow aryaWindow, AryaNavBar aryaNavBar) {
        this.aryaWindow = aryaWindow;
        this.aryaNavBar = aryaNavBar;
    }

    public AryaWindow getAryaWindow() {
        return aryaWindow;
    }

    public void setAryaWindow(AryaWindow aryaWindow) {
        this.aryaWindow = aryaWindow;
    }

    public AryaNavBar getAryaNavBar() {
        return aryaNavBar;
    }

    public void setAryaNavBar(AryaNavBar aryaNavBar) {
        this.aryaNavBar = aryaNavBar;
    }
}
