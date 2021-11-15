package cr.ac.una.net.util.netbuffer;

public class IONetBuffer {

    public INetBuffer getInputBuffer() {
        return inputBuffer;
    }

    public ONetBuffer getOutputBuffer() {
        return outputBuffer;
    }

    private INetBuffer inputBuffer = new INetBuffer();
    private ONetBuffer outputBuffer = new ONetBuffer();
}
