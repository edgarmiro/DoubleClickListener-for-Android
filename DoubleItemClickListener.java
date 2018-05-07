import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;

public abstract class DoubleItemClickListener implements AdapterView.OnItemClickListener {

    private static final long SINGLE_CLICK_DELAY = 250; // milliseconds

    private final long mDelay;

    protected DoubleItemClickListener() {
        this.mDelay = SINGLE_CLICK_DELAY;
    }

    protected DoubleItemClickListener(long delay) {
        this.mDelay = delay;
    }

    private CountDownTimer timer;
    private State currentState = State.IDLE;

    private enum State {
        WAITING_FOR_DOUBLE_CLICK,
        CLICK_EXECUTED,
        IDLE
    }

    private void startSingleClickTimer(final AdapterView<?> parent, final View v, final int pos, final long id) {
        currentState = State.WAITING_FOR_DOUBLE_CLICK;
        timer = new CountDownTimer(mDelay, mDelay) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                startAfterClickTimer();
                onSingleClick(parent, v, pos, id);
            }
        };
        timer.start();
    }

    private void startAfterClickTimer() {
        currentState = State.CLICK_EXECUTED;
        timer = new CountDownTimer(mDelay, mDelay) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                currentState = State.IDLE;
            }
        };
        timer.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, final View v, final int pos, long id) {

        switch (currentState) {
            case IDLE:
                startSingleClickTimer(parent, v, pos, id);
                break;
            case WAITING_FOR_DOUBLE_CLICK:
                timer.cancel();
                startAfterClickTimer();
                onDoubleClick(parent, v, pos, id);
                break;
            case CLICK_EXECUTED:
                break; // ignore clicks when an action resulting from a recent click is currently executing
        }
    }

    protected abstract void onSingleClick(AdapterView<?> parent, View v, int position, long id);

    protected abstract void onDoubleClick(AdapterView<?> parent, View v, int position, long id);
}